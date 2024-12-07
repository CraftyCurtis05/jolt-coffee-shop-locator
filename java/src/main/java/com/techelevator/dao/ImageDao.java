package com.techelevator.dao;

import com.techelevator.model.Image;

public interface ImageDao {

    // Get image for a user
    Image getImageByUserId(int userId);

    // Save new image for a user
    Image saveImage(Image image, int userId);

    // Delete an image by user
    void deleteImage(int userId);
}