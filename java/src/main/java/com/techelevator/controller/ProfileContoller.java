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
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:5173")  // Allow this controller to accept requests from the frontend
public class ProfileContoller {

    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileContoller(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    /**
     * GET boolean isFormSubmitted from profile
     *
     * @param principal The authenticated user's principal object.
     * @return boolean isFormSubmitted from profile of authenticated user.
     */
    @GetMapping("/status")
    public boolean getIsFormSubmittedByUserId(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.isFormSubmittedByUserId(userId);
    }

    /**
     * POST Profile Details
     *
     * @param profile The profile data to be created
     * @param principal The authenticated user's principal object
     * @return The created profile
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public Profile createProfile(@RequestBody Profile profile, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.createProfile(profile, userId);
    }

    /**
     * PUT Profile Details
     *
     * @param profile The profile data to be updated
     * @param principal The authenticated user's principal object
     * @return The updated profile
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public Profile updateProfile(@RequestBody Profile profile, Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.updateProfile(profile, userId);
    }

    /**
     * GET Profile Details
     *
     * @param principal The authenticated user's principal object.
     * @return Profile details of the authenticated user.
     */
    @GetMapping()
    public Profile getProfile(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        return profileDao.getProfileByUserId(userId);
    }

    /**
     * DELETE Profile
     *
     * @param principal The authenticated user's principal object
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    public void deleteProfile(Principal principal) {

        String username = principal.getName();  // Get the username from the authenticated user
        User user = userDao.getUserByUsername(username); // Get User object from username
        int userId = user.getId(); // Get userId from User object

        profileDao.deleteProfile(userId);
    }
}