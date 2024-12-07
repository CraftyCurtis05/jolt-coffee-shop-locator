package com.techelevator.dao;

import com.techelevator.model.Profile;
import com.techelevator.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.exception.DaoException;

@Component
public class JdbcProfileDao implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao; // Inject UserDao to fetch user details

    // Constructor-based injection for JdbcTemplate to interact with the database
    public JdbcProfileDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao; // Initialize UserDao
    }

    /**
     * Retrieves a specific profile by user ID.
     *
     * @param userId The ID of the user whose favorite is to be retrieved.
     * @return The Profile object corresponding to the given userId.
     */
    @Override
    public Profile getProfileByUserId(int userId) {
        Profile profile = null;

        String sql = "SELECT profile_id, user_id, first_name, last_name, birth_month, birth_day, birth_year, " +
                "address1, address2, city, state_abbr, zipcode " +
                "FROM profile " +
                "WHERE user_id = ?";

        try {
            // Execute the query and retrieve the result set
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                // If a row is found, map it to a Favorites object
                profile = mapRowToProfile(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    /**
     * Creates/Saves a profile for a user.
     *
     * @param profile The Profile object containing details about the user.
     * @param userId The ID of the user saving the profile.
     * @return The newly created profile object, including the generated profile_id.
     */
    @Override
    public Profile saveProfile(Profile profile, int userId) {
        Profile savedProfile = null;

        try {
            // Step 1: Check if a profile already exists for the user
            String checkSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

            if (count > 0) {
                // Profile exists, perform an UPDATE

                String updateSql = "UPDATE profile SET first_name = ?, last_name = ?, birth_month = ?, birth_day = ?, birth_year = ?, " +
                                    "address1 = ?, address2 = ?, city = ?, state_abbr = ?, zipcode = ? " +
                                    "WHERE user_id = ?";

                jdbcTemplate.update(updateSql, profile.getFirstName(), profile.getLastName(),
                        profile.getBirthMonth(), profile.getBirthDay(), profile.getBirthYear(),
                        profile.getAddress1(), profile.getAddress2(), profile.getCity(),
                        profile.getState(), profile.getZipcode(), userId);

                // Retrieve the updated profile
                savedProfile = getProfileByUserId(userId);
            } else {
                // Profile does not exist, perform an INSERT

                String insertSql = "INSERT INTO profile (user_id, first_name, last_name, birth_month, birth_day, birth_year, address1, address2, city, state_abbr, zipcode) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                jdbcTemplate.update(insertSql, userId, profile.getFirstName(), profile.getLastName(),
                        profile.getBirthMonth(), profile.getBirthDay(), profile.getBirthYear(),
                        profile.getAddress1(), profile.getAddress2(), profile.getCity(),
                        profile.getState(), profile.getZipcode());

                // Retrieve the newly created profile
                savedProfile = getProfileByUserId(userId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            // Handle any data integrity issues (e.g., foreign key violations)
            throw new DaoException("Data integrity violation", e);
        }

        return savedProfile;
    }

    /**
     * Deletes a favorite for a given user.
     *
     * @param userId The ID of the user who wants to delete the favorite.
     * @throws DaoException if the favorite does not exist or the user is not authorized to delete it.
     */
    @Override
    public void deleteProfile(int userId) {

        String sql = "DELETE FROM profile WHERE user_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, userId);
            if (rowsAffected == 0) {
                throw new DaoException("Profile not found or not authorized to delete");
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profile_id"));
        profile.setFirstName(rs.getString("first_name"));
        profile.setLastName(rs.getString("last_name"));
        profile.setBirthMonth(rs.getString("birth_month"));
        profile.setBirthDay(rs.getInt("birth_day"));
        profile.setBirthYear(rs.getInt("birth_year"));
        profile.setAddress1(rs.getString("address1"));
        profile.setAddress2(rs.getString("address2"));
        profile.setCity(rs.getString("city"));
        profile.setState(rs.getString("state_abbr"));
        profile.setZipcode(rs.getString("zipcode"));

        // Fetch the user object associated with the favorite (from UserDao)
        User user = userDao.getUserById(rs.getInt("user_id"));
        profile.setUser(user);  // Set the User object for this favorite
        return profile;
    }
}