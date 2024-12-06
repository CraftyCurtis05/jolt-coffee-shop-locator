package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;

import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://localhost:5173")  // Allow this controller to accept requests from the frontend
public class ProfileContoller {

    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileContoller(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    /**
     * GET Profile Details
     *
     * @param principal The authenticated user's principal object
     * @return Profile details of the authenticated user
     */
    @GetMapping("/profile")
    public Profile getProfile(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.getProfileByUserId(userId);
    }

    /**
     * PUT Profile Details
     *
     * @param profile The profile data to be updated or created
     * @param principal The authenticated user's principal object
     * @return The updated or created profile
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/profile")
    public Profile saveProfile(@RequestBody Profile profile, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.saveProfile(profile, userId);
    }

    /**
     * DELETE Profile
     *
     * @param principal The authenticated user's principal object
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/profile")
    public void deleteProfile(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        profileDao.deleteProfile(userId);
    }
}