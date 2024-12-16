package com.jolt.dao;

import com.jolt.model.Profile;
import com.jolt.model.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.jolt.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

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
     * Get isFormSubmitted for a user profile
     *
     * @param userId The ID of the user of the profile.
     * @return The boolean isFormSubmitted of the profile by user ID.
     */
    @Override
    public boolean isFormSubmittedByUserId(int userId) {
        try {
            // First, check if the user exists in the database
            String userCheckSql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
            int userCount = jdbcTemplate.queryForObject(userCheckSql, Integer.class, userId);

            // Check if the profile exists for the user
            String profileCheckSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int profileCount = jdbcTemplate.queryForObject(profileCheckSql, Integer.class, userId);

            // If the user doesn't exist, return false immediately
            // If no profile is found, return false (profile does not exist)
            if (userCount == 0 || profileCount == 0) {
                return false;  // User does not exist, so the form cannot be submitted
            }

            // If the profile exists, fetch the value of is_form_submitted
            String sql = "SELECT is_form_submitted FROM profile WHERE user_id = ?";
            Boolean isFormSubmitted = jdbcTemplate.queryForObject(sql, Boolean.class, userId);

            return isFormSubmitted != null && isFormSubmitted;

        } catch (CannotGetJdbcConnectionException e) {
            // Handle database connection issues
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity issues (e.g., foreign key violations)
            throw new DaoException("Data integrity violation", e);
        }
    }

    /**
     * Creates a profile for a user.
     *
     * @param profile The Profile object containing details about the user.
     * @param userId The ID of the user saving the profile.
     * @return The newly created profile object, including the generated profile_id.
     */
    @Override
    public Profile createProfile(Profile profile, int userId) {

        Profile newProfile = null;

        try {
            // Step 1: Check if the profile exists for the given userId
            String checkSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

            if (count == 0) {
                // Profile does not exist, perform an INSERT for a new profile
                String insertSql = "INSERT INTO profile (user_id, first_name, last_name, birth_month, birth_day, birth_year, " +
                        "address1, address2, city, state_abbr, zipcode, is_form_submitted) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                jdbcTemplate.update(insertSql, userId, profile.getFirstName(), profile.getLastName(),
                profile.getBirthMonth(), profile.getBirthDay(), profile.getBirthYear(),
                profile.getAddress1(), profile.getAddress2(), profile.getCity(),
                profile.getState(), profile.getZipcode(), true);

                // Retrieve the newly created profile
                newProfile = getProfileByUserId(userId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newProfile;
    }

    /**
     * Updates a profile for a user.
     *
     * @param profile The Profile object containing details about the user.
     * @param userId The ID of the user saving the profile.
     * @return The newly created profile object, including the generated profile_id.
     */
    @Override
    public Profile updateProfile(Profile profile, int userId) {
        Profile updatedProfile = null;

        try {
            // Step 1: Check if the profile exists for the given userId
            String checkSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

            // Prepare SQL for update
            StringBuilder updateSql = new StringBuilder("UPDATE profile SET ");
            List<Object> parameters = new ArrayList<>();

            // Dynamically append fields that have been changed
            if (profile.getFirstName() != null) {
                updateSql.append("first_name = ?, ");
                parameters.add(profile.getFirstName());
            }
            if (profile.getLastName() != null) {
                updateSql.append("last_name = ?, ");
                parameters.add(profile.getLastName());
            }
            if (profile.getBirthMonth() != null) {
                updateSql.append("birth_month = ?, ");
                parameters.add(profile.getBirthMonth());
            }
            if (profile.getBirthDay() > 0) {  // Check if birthDay is provided
                updateSql.append("birth_day = ?, ");
                parameters.add(profile.getBirthDay());
            }
            if (profile.getBirthYear() > 0) {  // Check if birthYear is provided
                updateSql.append("birth_year = ?, ");
                parameters.add(profile.getBirthYear());
            }
            if (profile.getAddress1() != null) {
                updateSql.append("address1 = ?, ");
                parameters.add(profile.getAddress1());
            }
            if (profile.getAddress2() != null) {
                updateSql.append("address2 = ?, ");
                parameters.add(profile.getAddress2());
            }
            if (profile.getCity() != null) {
                updateSql.append("city = ?, ");
                parameters.add(profile.getCity());
            }
            if (profile.getState() != null) {
                updateSql.append("state_abbr = ?, ");
                parameters.add(profile.getState());
            }
            if (profile.getZipcode() != null) {
                updateSql.append("zipcode = ?, ");
                parameters.add(profile.getZipcode());
            }

            // Remove the last comma and space
            updateSql.setLength(updateSql.length() - 2);

            // Add the userId condition at the end of the query
            updateSql.append(" WHERE user_id = ?");
            parameters.add(userId);

            if (count > 0) {
                // Profile exists, perform an UPDATE
                jdbcTemplate.update(updateSql.toString(), parameters.toArray());

                // Retrieve the updated profile
                updatedProfile = getProfileByUserId(userId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedProfile;
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