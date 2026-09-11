package com.jolt.dao;

import com.jolt.exception.DaoException;
import com.jolt.model.Favorites;
import com.jolt.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcFavoritesDao implements FavoritesDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcFavoritesDao(DataSource dataSource, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userDao = userDao;
    }

    @Override
    public List<Favorites> getFavorites(int userId) {
        List<Favorites> favorites = new ArrayList<>();

        String sql =
                "SELECT favorite_id, user_id, business_id, business_name, " +
                "business_address1, business_address2, business_city, business_state, " +
                "business_zipcode, business_image, business_url " +
                "FROM favorites " +
                "WHERE user_id = ? " +
                "ORDER BY favorite_id ASC";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

            while (results.next()) {
                favorites.add(mapRowToFavorites(results));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return favorites;
    }

    @Override
    public Favorites getFavoriteById(int favoriteId, int userId) {
        Favorites favorite = null;

        String sql =
                "SELECT favorite_id, user_id, business_id, business_name, " +
                "business_address1, business_address2, business_city, business_state, " +
                "business_zipcode, business_image, business_url " +
                "FROM favorites " +
                "WHERE favorite_id = ? AND user_id = ?";

        try {

            // Include the user ID so users can only retrieve their own favorites
            SqlRowSet results = jdbcTemplate.queryForRowSet(
                    sql,
                    favoriteId,
                    userId
            );

            if (results.next()) {
                favorite = mapRowToFavorites(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return favorite;
    }

    @Override
    public Favorites createFavorite(Favorites favorite, int userId) {
        Favorites newFavorite = null;

        String sql =
                "INSERT INTO favorites " +
                "(user_id, business_id, business_name, business_address1, business_address2, " +
                "business_city, business_state, business_zipcode, business_image, business_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING favorite_id";

        try {
            int newFavoriteId = jdbcTemplate.queryForObject(
                    sql,
                    int.class,
                    userId,
                    favorite.getBusinessId(),
                    favorite.getBusinessName(),
                    favorite.getBusinessAddress1(),
                    favorite.getBusinessAddress2(),
                    favorite.getBusinessCity(),
                    favorite.getBusinessState(),
                    favorite.getBusinessZipcode(),
                    favorite.getBusinessImage(),
                    favorite.getBusinessUrl()
            );

            newFavorite = getFavoriteById(newFavoriteId, userId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {
            throw new DaoException(
                    "Unable to create favorite due to a database constraint",
                    e
            );
        }

        return newFavorite;
    }

    @Override
    public void deleteFavorite(int favoriteId, int userId) {
        String sql =
                "DELETE FROM favorites WHERE favorite_id = ? AND user_id = ?";

        try {

            // Include the user ID so users can only delete their own favorites
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    favoriteId,
                    userId
            );

            if (rowsAffected == 0) {
                throw new DaoException("Favorite not found or not authorized to delete");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public boolean isFavoriteExists(int userId, String businessId) {
        String sql =
                "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND business_id = ?";

        try {
            Integer count = jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    userId,
                    businessId
            );

            return count != null && count > 0;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private Favorites mapRowToFavorites(SqlRowSet results) {
        Favorites favorite = new Favorites();

        favorite.setFavoriteId(results.getInt("favorite_id"));
        favorite.setBusinessId(results.getString("business_id"));
        favorite.setBusinessName(results.getString("business_name"));
        favorite.setBusinessAddress1(results.getString("business_address1"));
        favorite.setBusinessAddress2(results.getString("business_address2"));
        favorite.setBusinessCity(results.getString("business_city"));
        favorite.setBusinessState(results.getString("business_state"));
        favorite.setBusinessZipcode(results.getString("business_zipcode"));
        favorite.setBusinessImage(results.getString("business_image"));
        favorite.setBusinessUrl(results.getString("business_url"));

        User user = userDao.getUserById(results.getInt("user_id"));
        favorite.setUser(user);

        return favorite;
    }

}
