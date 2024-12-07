package com.techelevator.dao;

import com.techelevator.model.Image;

public interface ImageDao {

    Image getImageByUserId(int userId);

    Image saveImage(Image image, int userId);

    void deleteImage(int userId);
}