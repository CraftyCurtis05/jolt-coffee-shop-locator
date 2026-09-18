package com.jolt.dao;

import com.jolt.model.Image;

import com.jolt.exception.DaoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcImageDaoTests extends BaseDaoTests {

    private JdbcImageDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcImageDao(jdbcTemplate);
    }

    @Test
    public void getImageByUserId_given_user_with_no_image_returns_null() {
        Image image = sut.getImageByUserId(1);

        Assert.assertNull(image);
    }

    @Test
    public void saveImage_given_new_image_saves_and_returns_image() {
        Image image = new Image();

        byte[] imageData = new byte[] { 1, 2, 3, 4, 5 };

        image.setImageName("test-image.png");
        image.setImage(imageData);

        Image savedImage = sut.saveImage(image, 1);

        Assert.assertNotNull(savedImage);
        Assert.assertTrue(savedImage.getImageId() > 0);
        Assert.assertEquals("test-image.png", savedImage.getImageName());
        Assert.assertArrayEquals(imageData, savedImage.getImage());
    }

    @Test
    public void saveImage_given_existing_image_updates_and_returns_image() {
        Image firstImage = new Image();

        byte[] firstImageData = new byte[] { 1, 2, 3 };

        firstImage.setImageName("first-image.png");
        firstImage.setImage(firstImageData);

        Image savedFirstImage = sut.saveImage(firstImage, 1);

        Image secondImage = new Image();

        byte[] secondImageData = new byte[] { 4, 5, 6, 7 };

        secondImage.setImageName("second-image.webp");
        secondImage.setImage(secondImageData);

        Image savedSecondImage = sut.saveImage(secondImage, 1);

        Assert.assertNotNull(savedSecondImage);
        Assert.assertEquals(
                savedFirstImage.getImageId(),
                savedSecondImage.getImageId()
        );
        Assert.assertEquals(
                "second-image.webp",
                savedSecondImage.getImageName()
        );
        Assert.assertArrayEquals(
                secondImageData,
                savedSecondImage.getImage()
        );
    }

    @Test
    public void getImageByUserId_given_existing_image_returns_image() {
        Image image = new Image();

        byte[] imageData = new byte[] { 9, 8, 7, 6 };

        image.setImageName("profile-image.jpg");
        image.setImage(imageData);

        Image savedImage = sut.saveImage(image, 1);

        Image retrievedImage = sut.getImageByUserId(1);

        Assert.assertNotNull(retrievedImage);
        Assert.assertEquals(
                savedImage.getImageId(),
                retrievedImage.getImageId()
        );
        Assert.assertEquals(
                "profile-image.jpg",
                retrievedImage.getImageName()
        );
        Assert.assertArrayEquals(
                imageData,
                retrievedImage.getImage()
        );
    }

    @Test
    public void deleteImage_deletes_existing_image() {
        Image image = new Image();

        image.setImageName("delete-image.png");
        image.setImage(new byte[] { 1, 2, 3 });

        sut.saveImage(image, 1);

        sut.deleteImage(1);

        Image deletedImage = sut.getImageByUserId(1);

        Assert.assertNull(deletedImage);
    }

    @Test(expected = DaoException.class)
    public void deleteImage_given_no_existing_image_throws_exception() {
        sut.deleteImage(1);
    }
}
