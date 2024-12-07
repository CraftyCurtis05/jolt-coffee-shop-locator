package com.techelevator.dao;

import com.techelevator.model.Image;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Component
public class JdbcImageDao implements ImageDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcImageDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public Image getImageByUserId(int userId) {
        String sql = "SELECT profile_image FROM image WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if (results.next()) {
            try {
                // Get binary stream from the database (PostgreSQL BYTEA type)
                byte[] imageBytes = results.getBytes("profile_image"); // This can be used as is

                // If you wanted to read from an InputStream (alternative), use:
                // InputStream inputStream = results.getBinaryStream("profile_image");
                // byte[] imageBytes = IOUtils.toByteArray(inputStream); // You'd need Apache Commons IOUtils to convert stream to byte array.

                // Create and return the Image object
                Image image = new Image();
                image.setImage(imageBytes); // Set the image bytes to the Image object
                return image;
            } catch (Exception e) {
                e.printStackTrace();
                return null; // Handle exception accordingly, might want to return a specific error or message
            }
        }
        return null; // Return null if no image is found
    }

    @Override
    public Image saveImage(Image image, int userId) {
        String sql = "INSERT INTO image (user_id, profile_image) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, image.getImage()); // Save the image bytes to the database
        return image;
    }

    @Override
    public void deleteImage(int userId) {
        String sql = "DELETE FROM image WHERE user_id = ?";
        jdbcTemplate.update(sql, userId); // Delete image by user ID
    }

    private RowMapper<Image> imageMapper = (rs, rowNum) -> {
        int imageId = rs.getInt("image_id");
        int userId = rs.getInt("user_id");
        byte[] profileImage = rs.getBytes("image");
        return new Image(imageId, userDao.getUserById(userId), profileImage);
    };
}