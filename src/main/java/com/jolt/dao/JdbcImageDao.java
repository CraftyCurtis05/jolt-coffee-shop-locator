package com.jolt.dao;

import com.jolt.exception.DaoException;
import com.jolt.model.Image;
import com.jolt.model.User;

import java.sql.PreparedStatement;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcImageDao implements ImageDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcImageDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
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
    public Image saveImage(Image image, int userId) {
        Image savedImage = null;

        try {
            String sql = "SELECT COUNT(*) FROM image WHERE user_id = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);

            if (count > 0) {
                String updateSql =
                        "UPDATE image SET image = ?, image_name = ? WHERE user_id = ?";

                jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection.prepareStatement(updateSql);

                    // Store the uploaded image as binary data
                    statement.setBytes(1, image.getImage());
                    statement.setString(2, image.getImageName());
                    statement.setInt(3, userId);

                    return statement;
                });

            } else {
                String insertSql =
                        "INSERT INTO image (user_id, image_name, image) VALUES (?, ?, ?)";

                jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection.prepareStatement(insertSql);

                    statement.setInt(1, userId);
                    statement.setString(2, image.getImageName());

                    // Store the uploaded image as binary data
                    statement.setBytes(3, image.getImage());

                    return statement;
                });
            }

            savedImage = getImageByUserId(userId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Database error occurred while saving the image", e);
        }

        return savedImage;
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

    private Image mapRowToImage(SqlRowSet results) {
        Image image = new Image();

        image.setImageId(results.getInt("image_id"));
        image.setImageName(results.getString("image_name"));

        Object imageObject = results.getObject("image");

        if (imageObject instanceof byte[]) {
            byte[] imageBytes = (byte[]) imageObject;
            image.setImage(imageBytes);
        }

        User user = userDao.getUserById(results.getInt("user_id"));
        image.setUser(user);

        return image;
    }

}