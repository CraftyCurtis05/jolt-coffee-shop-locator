package com.jolt.dao;

import com.jolt.model.Image;

public interface ImageDao {

    Image getImageByUserId(int userId);

    Image saveImage(Image image, int userId);

    void deleteImage(int userId);
}