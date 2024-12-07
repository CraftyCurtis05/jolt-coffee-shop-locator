package com.techelevator.controller;

import com.techelevator.dao.ImageDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Image;
import com.techelevator.model.User;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:5173")  // Allow this controller to accept requests from the frontend
public class ImageController {

    private ImageDao imageDao;
    private UserDao userDao;

    public ImageController(ImageDao imageDao, UserDao userDao) {
        this.imageDao = imageDao;
        this.userDao = userDao;
    }

    // Get image
    @GetMapping
    public Image getImage(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return imageDao.getImageByUserId(userId);
    }

    // Save image
    @PutMapping
    public Image saveImage(@RequestParam("image") MultipartFile file, Principal principal) throws IOException {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        byte[] imageBytes = file.getBytes(); // Convert file to byte array
        Image image = new Image();
        image.setImage(imageBytes); // Set image bytes in the Image object

        return imageDao.saveImage(image, userId); // Save image to database and return the saved image
    }

    // Delete image
    @DeleteMapping
    public void deleteImage(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        imageDao.deleteImage(userId);
    }
}