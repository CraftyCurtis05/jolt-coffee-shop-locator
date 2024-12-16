package com.jolt.dao;

import com.jolt.exception.DaoException;
import com.jolt.model.Image;
import com.jolt.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class JdbcImageDao implements ImageDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcImageDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public Image saveImage(Image image, int userId) {
        Image savedImage = null;

        try {
            // First, check if the user already has an image
            String sql = "SELECT COUNT(*) FROM image WHERE user_id = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);

            if (count > 0) {
                // User already has an image, update it
                String updateSql = "UPDATE image SET image = ?, image_name = ? WHERE user_id = ?";
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(updateSql);
                    ps.setBytes(1, image.getImage());  // Use setBytes to store image data
                    ps.setString(2, image.getImageName());
                    ps.setInt(3, userId);
                    return ps;
                });
                savedImage = getImageByUserId(userId);
            } else {
                // No image exists for the user, insert a new one
                String insertSql = "INSERT INTO image (user_id, image_name, image) VALUES (?, ?, ?)";
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSql);
                    ps.setInt(1, userId);
                    ps.setString(2, image.getImageName());
                    ps.setBytes(3, image.getImage());  // Use setBytes to store image data
                    return ps;
                });
                savedImage = getImageByUserId(userId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Database error occurred while saving the image", e);
        }
        return savedImage;
    }

    @Override
    public Image getImageByUserId(int userId) {
        Image image = null;

        String sql = "SELECT * FROM image WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                image = mapRowToImage(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return image;
    }

    @Override
    public void deleteImage(int userId) {
        String sql = "DELETE FROM image WHERE user_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, userId);
            if (rowsAffected == 0) {
                throw new DaoException("Image not found or not authorized to delete");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    public Image mapRowToImage(SqlRowSet rs) {
        Image image = new Image();
        image.setImageId(rs.getInt("image_id"));
        image.setImageName(rs.getString("image_name"));

        // Retrieve the image data as an Object (byte[]).
        Object imageObject = rs.getObject("image");

        if (imageObject instanceof byte[]) {
            byte[] imageBytes = (byte[]) imageObject;
            image.setImage(imageBytes);
        }

        // Retrieve the associated user
        User user = userDao.getUserById(rs.getInt("user_id"));
        image.setUser(user);  // Set the User object for this image

        return image;
    }
}