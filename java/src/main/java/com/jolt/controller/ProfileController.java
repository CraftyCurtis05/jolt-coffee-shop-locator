package com.jolt.controller;

import com.jolt.dao.ProfileDao;
import com.jolt.dao.UserDao;
import com.jolt.exception.DaoException;
import com.jolt.model.Profile;
import com.jolt.model.User;

import java.security.Principal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://jolt.jennifercurtis.me"
})
public class ProfileController {

    private final ProfileDao profileDao;
    private final UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Profile createProfile(
            @RequestBody Profile profile,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        try {
            return profileDao.createProfile(profile, userId);

        } catch (DaoException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid profile data",
                        e
                );
            }

            throw e;
        }
    }

    @GetMapping
    public Profile getProfile(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        return profileDao.getProfileByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Profile updateProfile(
            @RequestBody Profile profile,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        try {
            return profileDao.updateProfile(profile, userId);

        } catch (DaoException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid profile data",
                        e
                );
            }

            throw e;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteProfile(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        profileDao.deleteProfile(userId);
    }

    @GetMapping("/status")
    public boolean getIsFormSubmittedByUserId(Principal principal) {
        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        return profileDao.isFormSubmittedByUserId(userId);
    }

}
