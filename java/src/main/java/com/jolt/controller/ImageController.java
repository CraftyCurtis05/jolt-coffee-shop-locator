package com.jolt.controller;

import com.jolt.dao.ImageDao;
import com.jolt.dao.UserDao;
import com.jolt.exception.DaoException;
import com.jolt.model.Image;
import com.jolt.model.User;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/image")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://jolt.jennifercurtis.me"
})
public class ImageController {

    private final ImageDao imageDao;
    private final UserDao userDao;

    public ImageController(ImageDao imageDao, UserDao userDao) {
        this.imageDao = imageDao;
        this.userDao = userDao;
    }

    @GetMapping
    public Map<String, Object> getUserImage(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        Image image = imageDao.getImageByUserId(userId);
        Map<String, Object> response = new HashMap<>();

        if (image != null) {
            String base64Image =
                    Base64.getEncoder().encodeToString(image.getImage());

            String imageType = "image/jpeg";
            String imageName = image.getImageName();

            if (imageName != null) {
                String lowerCaseName = imageName.toLowerCase();

                if (lowerCaseName.endsWith(".png")) {
                    imageType = "image/png";

                } else if (lowerCaseName.endsWith(".webp")) {
                    imageType = "image/webp";
                }
            }

            response.put("imageId", image.getImageId());
            response.put("imageName", image.getImageName());
            response.put(
                    "imageUrl",
                    "data:" + imageType + ";base64," + base64Image
            );

        } else {
            response.put("imageId", null);
            response.put("imageName", "No image uploaded");
            response.put("imageUrl", "");
        }

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public Image saveImage(
            @RequestParam("image") MultipartFile file,
            Principal principal) {

        if (file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Please select an image to upload"
            );
        }

        String contentType = file.getContentType();

        if (!"image/jpeg".equals(contentType) &&
                !"image/png".equals(contentType) &&
                !"image/webp".equals(contentType)) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only JPG, PNG and WEBP images are allowed"
            );
        }

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        try {
            Image image = new Image();

            image.setImageName(file.getOriginalFilename());
            image.setImage(file.getBytes());

            return imageDao.saveImage(image, userId);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to read image file",
                    e
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteImage(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        try {
            imageDao.deleteImage(userId);

        } catch (DaoException e) {
            if ("Image not found or not authorized to delete".equals(e.getMessage())) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Image not found",
                        e
                );
            }

            throw e;
        }
    }

}