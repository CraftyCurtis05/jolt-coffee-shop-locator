package com.jolt.dao;

import com.jolt.dto.RegisterUserDto;
import com.jolt.exception.DaoException;
import com.jolt.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role FROM users";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return users;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE user_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

            if (results.next()) {
                user = mapRowToUser(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE username = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);

            if (results.next()) {
                user = mapRowToUser(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;

        String sql =
                "INSERT INTO users (username, password_hash, role) " +
                "VALUES (?, ?, ?) RETURNING user_id";

        // Hash the password before storing it in the database
        String passwordHash =
                new BCryptPasswordEncoder().encode(user.getPassword());

        // New accounts are created with the standard user role
        String role = "ROLE_USER";

        try {
            int newUserId = jdbcTemplate.queryForObject(
                    sql,
                    int.class,
                    user.getUsername(),
                    passwordHash,
                    role
            );

            newUser = getUserById(newUserId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newUser;
    }

    private User mapRowToUser(SqlRowSet results) {
        User user = new User();

        user.setId(results.getInt("user_id"));
        user.setUsername(results.getString("username"));
        user.setPassword(results.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(results.getString("role")));
        user.setActivated(true);

        return user;
    }

}
