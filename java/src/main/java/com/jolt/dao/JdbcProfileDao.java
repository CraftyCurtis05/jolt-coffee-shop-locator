package com.jolt.dao;

import com.jolt.exception.DaoException;
import com.jolt.model.Profile;
import com.jolt.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDao implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public Profile createProfile(Profile profile, int userId) {
        Profile newProfile = null;

        try {

            // Format profile information before saving
            String capitalizedFirstName = capitalizeName(profile.getFirstName());
            String capitalizedLastName = capitalizeName(profile.getLastName());
            String capitalizedAddress1 = capitalizeAddress(profile.getAddress1());
            // Trim optional address information before formatting
            String address2 = profile.getAddress2();

            if (address2 != null) {
                address2 = address2.trim();
            }

            String capitalizedAddress2 = capitalizeAddress(address2);
            String capitalizedCity = capitalizeCity(profile.getCity());
            String capitalizedState = capitalizeState(profile.getState());

            // Check if the user already has a profile
            String checkSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

            if (count == 0) {
                String insertSql =
                        "INSERT INTO profile " +
                        "(user_id, first_name, last_name, birth_month, birth_day, birth_year, " +
                        "address1, address2, city, state_abbr, zipcode, is_form_submitted) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                jdbcTemplate.update(
                        insertSql,
                        userId,
                        capitalizedFirstName,
                        capitalizedLastName,
                        profile.getBirthMonth(),
                        profile.getBirthDay(),
                        profile.getBirthYear(),
                        capitalizedAddress1,
                        capitalizedAddress2,
                        capitalizedCity,
                        capitalizedState,
                        profile.getZipcode(),
                        true
                );
            }

            // Return the newly created or existing profile
            newProfile = getProfileByUserId(userId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newProfile;
    }

    @Override
    public Profile getProfileByUserId(int userId) {
        Profile profile = null;

        String sql =
                "SELECT profile_id, user_id, first_name, last_name, birth_month, birth_day, birth_year, " +
		"address1, address2, city, state_abbr, zipcode, is_form_submitted " +
                "FROM profile " +
                "WHERE user_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

            if (results.next()) {
                profile = mapRowToProfile(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return profile;
    }

    @Override
    public Profile updateProfile(Profile profile, int userId) {
        Profile updatedProfile = null;

        try {

            // Format profile information before updating
            String capitalizedFirstName = capitalizeName(profile.getFirstName());
            String capitalizedLastName = capitalizeName(profile.getLastName());
            String capitalizedAddress1 = capitalizeAddress(profile.getAddress1());
            String capitalizedAddress2 = capitalizeAddress(profile.getAddress2());
            String capitalizedCity = capitalizeCity(profile.getCity());
            String capitalizedState = capitalizeState(profile.getState());

            // Check if the user already has a profile
            String checkSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

            StringBuilder updateSql = new StringBuilder("UPDATE profile SET ");
            List<Object> parameters = new ArrayList<>();

            // Add only the profile fields that were provided
            if (profile.getFirstName() != null) {
                updateSql.append("first_name = ?, ");
                parameters.add(capitalizedFirstName);
            }

            if (profile.getLastName() != null) {
                updateSql.append("last_name = ?, ");
                parameters.add(capitalizedLastName);
            }

            if (profile.getAddress1() != null) {
                updateSql.append("address1 = ?, ");
                parameters.add(capitalizedAddress1);
            }

            if (profile.getAddress2() != null) {
                updateSql.append("address2 = ?, ");
                parameters.add(capitalizedAddress2);
            }

            if (profile.getCity() != null) {
                updateSql.append("city = ?, ");
                parameters.add(capitalizedCity);
            }

            if (profile.getState() != null) {
                updateSql.append("state_abbr = ?, ");
                parameters.add(capitalizedState);
            }

            if (profile.getZipcode() != null) {
                updateSql.append("zipcode = ?, ");
                parameters.add(profile.getZipcode());
            }

            // Return the existing profile if no fields were provided
            if (parameters.isEmpty()) {
                return getProfileByUserId(userId);
            }

            // Remove the final comma and space from the query
            updateSql.setLength(updateSql.length() - 2);

            // Update the profile belonging to the current user
            updateSql.append(" WHERE user_id = ?");
            parameters.add(userId);

            if (count > 0) {
                jdbcTemplate.update(updateSql.toString(), parameters.toArray());
                updatedProfile = getProfileByUserId(userId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedProfile;
    }

    @Override
    public void deleteProfile(int userId) {
        String sql = "DELETE FROM profile WHERE user_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, userId);

            if (rowsAffected == 0) {
                throw new DaoException("Profile not found or not authorized to delete");
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public boolean isFormSubmittedByUserId(int userId) {

        try {

            // Check that both the user and profile exist
            String userCheckSql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
            int userCount = jdbcTemplate.queryForObject(userCheckSql, Integer.class, userId);

            String profileCheckSql = "SELECT COUNT(*) FROM profile WHERE user_id = ?";
            int profileCount = jdbcTemplate.queryForObject(profileCheckSql, Integer.class, userId);

            if (userCount == 0 || profileCount == 0) {
                return false;
            }

            String sql = "SELECT is_form_submitted FROM profile WHERE user_id = ?";
            Boolean isFormSubmitted = jdbcTemplate.queryForObject(
                    sql,
                    Boolean.class,
                    userId
            );

            return isFormSubmitted != null && isFormSubmitted;

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        return name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();
    }

    private String capitalizeAddress(String address) {
        if (address == null || address.isEmpty()) {
            return address;
        }

        String[] words = address.split("\\s+");
        StringBuilder capitalizedAddress = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedAddress
                        .append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedAddress.toString().trim();
    }

    private String capitalizeCity(String city) {
        if (city == null || city.isEmpty()) {
            return city;
        }

        String[] words = city.split("\\s+");
        StringBuilder capitalizedCity = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedCity
                        .append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedCity.toString().trim();
    }

    private String capitalizeState(String state) {
        if (state == null || state.isEmpty()) {
            return state;
        }

        return state.toUpperCase();
    }

    private Profile mapRowToProfile(SqlRowSet results) {
        Profile profile = new Profile();

        profile.setProfileId(results.getInt("profile_id"));
        profile.setFirstName(results.getString("first_name"));
        profile.setLastName(results.getString("last_name"));
        profile.setBirthMonth(results.getString("birth_month"));
        profile.setBirthDay(results.getInt("birth_day"));
        profile.setBirthYear(results.getInt("birth_year"));
        profile.setAddress1(results.getString("address1"));
        profile.setAddress2(results.getString("address2"));
        profile.setCity(results.getString("city"));
        profile.setState(results.getString("state_abbr"));
        profile.setZipcode(results.getString("zipcode"));
	profile.setIsFormSubmitted(results.getBoolean("is_form_submitted"));

        User user = userDao.getUserById(results.getInt("user_id"));
        profile.setUser(user);

        return profile;
    }

}
