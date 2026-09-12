package com.jolt.controller;

import com.jolt.dao.ImageDao;
import com.jolt.dao.UserDao;
import com.jolt.model.User;
import com.jolt.model.Image;
import com.jolt.exception.DaoException;

import java.security.Principal;
import java.util.Map;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class ImageControllerTests {

    private ImageController sut;

    @Mock
    private ImageDao imageDao;

    @Mock
    private UserDao userDao;

    @Mock
    private Principal principal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new ImageController(
                imageDao,
                userDao
        );
    }

    @Test
    public void getUserImage_given_no_existing_image_returns_empty_image_response() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(imageDao.getImageByUserId(1)).thenReturn(null);

        Map<String, Object> response =
                sut.getUserImage(principal);

        Assert.assertNull(response.get("imageId"));
        Assert.assertEquals(
                "No image uploaded",
                response.get("imageName")
        );
        Assert.assertEquals(
                "",
                response.get("imageUrl")
        );
    }

    @Test
    public void getUserImage_given_jpeg_image_returns_jpeg_data_url() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Image image = new Image();
        image.setImageId(10);
        image.setImageName("profile.jpg");
        image.setImage(new byte[]{1, 2, 3});

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(imageDao.getImageByUserId(1)).thenReturn(image);

        Map<String, Object> response =
                sut.getUserImage(principal);

        Assert.assertEquals(
                10,
                response.get("imageId")
        );
        Assert.assertEquals(
                "profile.jpg",
                response.get("imageName")
        );
        Assert.assertEquals(
                "data:image/jpeg;base64,AQID",
                response.get("imageUrl")
        );
    }

    @Test
    public void getUserImage_given_png_image_returns_png_data_url() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Image image = new Image();
        image.setImageId(11);
        image.setImageName("profile.png");
        image.setImage(new byte[]{1, 2, 3});

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(imageDao.getImageByUserId(1)).thenReturn(image);

        Map<String, Object> response =
                sut.getUserImage(principal);

        Assert.assertEquals(
                "data:image/png;base64,AQID",
                response.get("imageUrl")
        );
    }

    @Test
    public void getUserImage_given_webp_image_returns_webp_data_url() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Image image = new Image();
        image.setImageId(12);
        image.setImageName("profile.webp");
        image.setImage(new byte[]{1, 2, 3});

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(imageDao.getImageByUserId(1)).thenReturn(image);

        Map<String, Object> response =
                sut.getUserImage(principal);

        Assert.assertEquals(
                "data:image/webp;base64,AQID",
                response.get("imageUrl")
        );
    }

    @Test
    public void saveImage_given_empty_file_throws_bad_request() {
        MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(true);

        try {
            sut.saveImage(file, principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void saveImage_given_invalid_content_type_throws_bad_request() {
        MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getContentType()).thenReturn("text/plain");

        try {
            sut.saveImage(file, principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void saveImage_given_valid_image_saves_image_for_authenticated_user()
            throws Exception {

        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getContentType()).thenReturn("image/png");
        when(file.getOriginalFilename()).thenReturn("profile.png");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        sut.saveImage(file, principal);

        org.mockito.Mockito.verify(imageDao)
                .saveImage(
                        org.mockito.ArgumentMatchers.argThat(image ->
                                "profile.png".equals(image.getImageName()) &&
                                java.util.Arrays.equals(
                                        new byte[]{1, 2, 3},
                                        image.getImage()
                                )
                        ),
                        org.mockito.ArgumentMatchers.eq(1)
                );
    }

    @Test
    public void saveImage_given_file_read_error_throws_internal_server_error()
            throws Exception {

        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getContentType()).thenReturn("image/png");
        when(file.getOriginalFilename()).thenReturn("profile.png");
        when(file.getBytes()).thenThrow(new IOException("Unable to read file"));

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        try {
            sut.saveImage(file, principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    500,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void deleteImage_given_existing_image_deletes_image() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        sut.deleteImage(principal);

        org.mockito.Mockito.verify(imageDao)
                .deleteImage(1);
    }

    @Test
    public void deleteImage_given_missing_or_unauthorized_image_throws_not_found() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        org.mockito.Mockito.doThrow(
                new DaoException("Image not found or not authorized to delete")
        ).when(imageDao).deleteImage(1);

        try {
            sut.deleteImage(principal);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    404,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void deleteImage_given_other_dao_exception_rethrows_exception() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        DaoException expectedException =
                new DaoException("Database error");

        org.mockito.Mockito.doThrow(expectedException)
                .when(imageDao)
                .deleteImage(1);

        try {
            sut.deleteImage(principal);
            Assert.fail("Expected DaoException");

        } catch (DaoException e) {
            Assert.assertSame(
                    expectedException,
                    e
            );
        }
    }
}
