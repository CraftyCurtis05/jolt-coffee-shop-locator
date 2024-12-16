package com.jolt.dao;

import com.jolt.model.Favorites;
import com.jolt.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.jolt.exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoritesDao implements FavoritesDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;  // Inject UserDao to fetch user details

    // Constructor-based injection for JdbcTemplate to interact with the database
    public JdbcFavoritesDao(DataSource dataSource, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = userDao; // Initialize UserDao
    }

    /**
     * Retrieves a specific favorite by its ID and user ID.
     *
     * @param userId The ID of the user whose favorite is to be retrieved.
     * @param favoriteId The ID of the favorite to be retrieved.
     * @return The Favorites object corresponding to the given favoriteId and userId.
     */
    public Favorites getFavoriteById(int userId, int favoriteId) {
        Favorites favorite = null;
        // SQL query to fetch a favorite by its favorites_id and user_id
        String sql = "SELECT favorite_id, user_id, business_id, business_name, business_address1, business_address2, business_city, business_state, business_zipcode, business_image, business_url " +
                "FROM favorites " +
                "WHERE favorite_id = ? AND user_id = ?";  // Ensure the user only sees their own favorites

        try {
            // Execute the query and retrieve the result set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, favoriteId, userId);
            if (results.next()) {
                // If a row is found, map it to a Favorites object
                favorite = mapRowToFavorites(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        }
        return favorite;
    }

    /**
     * Retrieves all favorites for a given user.
     *
     * @param userId The ID of the user whose favorites are to be retrieved.
     * @return A list of Favorites objects associated with the given userId.
     */
    @Override
    public List<Favorites> getFavorites(int userId) {
        List<Favorites> favorites = new ArrayList<>();
        String sql = "SELECT favorite_id, user_id, business_id, business_name, business_address1, business_address2, business_city, business_state, business_zipcode, business_image, business_url " +
                "FROM favorites WHERE user_id = ? ORDER BY favorite_id ASC;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                favorites.add(mapRowToFavorites(results));  // Map row to Favorites object
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return favorites;
    }

    /**
     * Creates a new favorite for a user.
     *
     * @param favorite The Favorites object containing details about the business to be favorite.
     * @param userId The ID of the user adding the favorite.
     * @return The newly created Favorites object, including the generated favorites_id.
     */
    @Override
    public Favorites createFavorite(Favorites favorite, int userId) {
        Favorites newFavorite = null;
        // SQL query to insert a new favorite for a user, returning the generated favorites_id
        String sql = "INSERT INTO favorites (user_id, business_id, business_name, business_address1, business_address2, business_city, business_state, business_zipcode, business_image, business_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING favorite_id;";

        try {
            // Insert the favorite into the database and get the generated favorites_id
            int newFavoritesId = jdbcTemplate.queryForObject(sql, int.class, userId,
                    favorite.getBusinessId(), favorite.getBusinessName(),
                    favorite.getBusinessAddress1(), favorite.getBusinessAddress2(),
                    favorite.getBusinessCity(), favorite.getBusinessState(),
                    favorite.getBusinessZipcode(), favorite.getBusinessImage(),
                    favorite.getBusinessUrl());

            // Fetch the newly created favorite using its ID and the userId
            newFavorite = getFavoriteById(userId, newFavoritesId);
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            // Handle any data integrity issues (e.g., foreign key violations)
            throw new DaoException("Data integrity violation", e);
        }
        return newFavorite;
    }

    /**
     * Deletes a favorite for a given user.
     *
     * @param favoriteId The ID of the favorite to be deleted.
     * @param userId The ID of the user who wants to delete the favorite.
     * @throws DaoException if the favorite does not exist or the user is not authorized to delete it.
     */
    @Override
    public void deleteFavorite(int favoriteId, int userId) {
        // SQL query to delete a favorite by its ID and the user's ID
        String sql = "DELETE FROM favorites WHERE favorite_id = ? AND user_id = ?";

        try {
            // Execute the delete query and check how many rows were affected
            int rowsAffected = jdbcTemplate.update(sql, favoriteId, userId);
            if (rowsAffected == 0) {
                // If no rows were affected, either the favorite does not exist or the user is not authorized
                throw new DaoException("Favorite not found or not authorized to delete");
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    // Check if favorite already exists for userID
    public boolean isFavoriteExists(int userId, String businessId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND business_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, businessId);
        return count != null && count > 0; // Return true if count > 0
    }

    /**
     * Maps a row from the result set to a Favorites object.
     *
     * @param rs The SqlRowSet containing the result of a query.
     * @return A Favorites object populated with the values from the result set.
     */
    private Favorites mapRowToFavorites(SqlRowSet rs) {
        Favorites favorites = new Favorites();
        favorites.setFavoriteId(rs.getInt("favorite_id"));
        favorites.setBusinessId(rs.getString("business_id"));
        favorites.setBusinessName(rs.getString("business_name"));
        favorites.setBusinessAddress1(rs.getString("business_address1"));
        favorites.setBusinessAddress2(rs.getString("business_address2"));
        favorites.setBusinessCity(rs.getString("business_city"));
        favorites.setBusinessState(rs.getString("business_state"));
        favorites.setBusinessZipcode(rs.getString("business_zipcode"));
        favorites.setBusinessImage(rs.getString("business_image"));
        favorites.setBusinessUrl(rs.getString("business_url"));

        // Fetch the user object associated with the favorite (from UserDao)
        User user = userDao.getUserById(rs.getInt("user_id"));
        favorites.setUser(user);  // Set the User object for this favorite
        return favorites;
    }
}