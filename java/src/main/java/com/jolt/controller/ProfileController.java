package com.jolt.controller;

import com.jolt.dao.ProfileDao;
import com.jolt.dao.UserDao;
import com.jolt.exception.DaoException;
import com.jolt.model.Profile;
import com.jolt.model.User;

import java.security.Principal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import javax.validation.Valid;

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
            @Valid @RequestBody Profile profile,
            Principal principal) {

        String username = principal.getName();
        User user = userDao.getUserByUsername(username);
        int userId = user.getId();

        // Validate birth month
        if (profile.getBirthMonth() == null ||
                !java.util.List.of(
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                ).contains(profile.getBirthMonth())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid birth month"
            );
        }

        // Validate birth day
        if (profile.getBirthDay() < 1 || profile.getBirthDay() > 31) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid birth day"
            );
        }

        int currentYear = java.time.Year.now().getValue();

        // Validate birth year
        if (profile.getBirthYear() < 1900 ||
                profile.getBirthYear() > currentYear) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid birth year"
            );
        }

        // Verify legitimate dates
        try {
            int birthMonth = Month
                    .valueOf(profile.getBirthMonth().toUpperCase())
                    .getValue();

            LocalDate.of(
                    profile.getBirthYear(),
                    birthMonth,
                    profile.getBirthDay()
            );

        } catch (DateTimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid birthday"
            );
        }

        // Validate state abbreviation
        if (!java.util.Set.of(
                "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
                "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
                "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
                "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
                "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
                "WI", "WY"
        ).contains(profile.getState())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid state abbreviation"
            );
        }

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

        // Validate first name only when it was included in the update
        if (profile.getFirstName() != null) {

            String firstName = profile.getFirstName();

            if (firstName.isBlank() ||
                    firstName.length() > 50 ||
                    !firstName.matches("^[A-Za-z][A-Za-z' -]*$")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid first name"
                );
            }
        }

        // Validate last name only when it was included in the update
        if (profile.getLastName() != null) {

            String lastName = profile.getLastName();

            if (lastName.isBlank() ||
                    lastName.length() > 50 ||
                    !lastName.matches("^[A-Za-z][A-Za-z' -]*$")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid last name"
                );
            }
        }

        // Validate address only when it was included in the update
        if (profile.getAddress1() != null) {

            String address1 = profile.getAddress1();

            if (address1.isBlank() ||
                    address1.length() < 3 ||
                    address1.length() > 100) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid address"
                );
            }
        }

        // Validate address2 only when it was included in the update
        if (profile.getAddress2() != null) {

            String address2 = profile.getAddress2().trim();

            if (address2.length() > 50) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Address line 2 is too long"
                );
            }
        }

        // Validate city only when it was included in the update
        if (profile.getCity() != null) {

            String city = profile.getCity();

            if (city.isBlank() ||
                    city.length() < 2 ||
                    city.length() > 100 ||
                    !city.matches("^[A-Za-z][A-Za-z .'-]*$")) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid city"
                );
            }
        }

        // Validate state only when it was included in the update
        if (profile.getState() != null) {

            String state = profile.getState();

            if (!java.util.Set.of(
                    "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
                    "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
                    "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
                    "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
                    "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                    "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
                    "WI", "WY"
            ).contains(state)) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid state abbreviation"
                );
            }
        }

        // Validate ZIP code only when it was included in the update
        if (profile.getZipcode() != null) {

            String zipcode = profile.getZipcode();

            if (!zipcode.matches("^\\d{5}$")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid ZIP code"
                );
            }
        }

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
