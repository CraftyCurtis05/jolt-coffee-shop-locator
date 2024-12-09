package com.techelevator.controller;

import com.techelevator.dao.ImageDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Image;

import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowCredentials = "true")  // Allow this controller to accept requests from the frontend
public class ImageController {

    private final ImageDao imageDao;
    private final UserDao userDao;

    public ImageController(ImageDao imageDao, UserDao userDao) {
        this.imageDao = imageDao;
        this.userDao = userDao;
    }

    // Endpoint to retrieve the current user's image
    @GetMapping()
    public Map<String, Object> getUserImage(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        // Attempt to fetch the user's image from the database
        Image image = imageDao.getImageByUserId(userId);
        Map<String, Object> response = new HashMap<>();

        if (image != null) {
            // Image found, convert byte[] to base64
            String base64Image = Base64.getEncoder().encodeToString(image.getImage());
            response.put("imageId", image.getImageId());
            response.put("imageName", image.getImageName());
            response.put("imageUrl", "data:image/jpeg;base64," + base64Image);
        } else {
            // No image found, return a default response or handle the case as needed
            response.put("imageId", null);  // Or provide a default ID if you prefer
            response.put("imageName", "No image uploaded");
            response.put("imageUrl", "");  // You can provide a placeholder or default image URL
        }
        return response;
    }

    // Endpoint to upload/update image for the authenticated user
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public Image saveImage(@RequestParam("image") MultipartFile file, Principal principal) {
        try {
            String username = principal.getName();  // Get the username from the authenticated user
            User user = userDao.getUserByUsername(username); // Get User object from username
            int userId = user.getId(); // Get userId from User object

            // Prepare the image data
            Image image = new Image();
            image.setImageName(file.getOriginalFilename());
            image.setImage(file.getBytes());  // This might throw an IOException

            // Save or update the image in the database
            Image newImage = imageDao.saveImage(image, userId);

            return newImage;
        } catch (IOException e) {
            // Handle the exception (log it or return a custom error response)
            throw new RuntimeException("Failed to read image file", e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    public void deleteImage(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        imageDao.deleteImage(userId);
    }
}