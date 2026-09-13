package com.jolt.controller;

import com.jolt.dao.ProfileDao;
import com.jolt.dao.UserDao;
import com.jolt.model.Profile;
import com.jolt.model.User;
import com.jolt.exception.DaoException;

import java.security.Principal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class ProfileControllerTests {

    private ProfileController sut;

    @Mock
    private ProfileDao profileDao;

    @Mock
    private UserDao userDao;

    @Mock
    private Principal principal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new ProfileController(
                profileDao,
                userDao
        );
    }

    @Test
    public void getProfile_returns_profile_for_authenticated_user() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.getProfileByUserId(1)).thenReturn(profile);

        Profile actualProfile =
                sut.getProfile(principal);

        Assert.assertEquals(
                profile,
                actualProfile
        );
    }

    @Test
    public void createProfile_given_valid_profile_returns_created_profile() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setState("OH");

        Profile createdProfile = new Profile();

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.createProfile(profile, 1))
                .thenReturn(createdProfile);

        Profile actualProfile =
                sut.createProfile(profile, principal);

        Assert.assertEquals(
                createdProfile,
                actualProfile
        );
    }

    @Test
    public void updateProfile_given_valid_profile_returns_updated_profile() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();

        Profile updatedProfile = new Profile();

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.updateProfile(profile, 1))
                .thenReturn(updatedProfile);

        Profile actualProfile =
                sut.updateProfile(profile, principal);

        Assert.assertEquals(
                updatedProfile,
                actualProfile
        );
    }

    @Test
    public void deleteProfile_given_existing_profile_deletes_profile() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);

        sut.deleteProfile(principal);

        org.mockito.Mockito.verify(profileDao)
                .deleteProfile(1);
    }

    @Test
    public void getIsFormSubmittedByUserId_returns_status_for_authenticated_user() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.isFormSubmittedByUserId(1))
                .thenReturn(true);

        boolean isFormSubmitted =
                sut.getIsFormSubmittedByUserId(principal);

        Assert.assertTrue(isFormSubmitted);
    }

    @Test
    public void createProfile_given_invalid_profile_data_throws_bad_request() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();

        DaoException daoException = new DaoException(
                "Unable to create profile",
                new DataIntegrityViolationException("Invalid profile data")
        );

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.createProfile(profile, 1))
                .thenThrow(daoException);

        try {
                sut.createProfile(profile, principal);
                Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
                Assert.assertEquals(
                        400,
                        e.getStatus().value()
                );
        }
    }

    @Test
    public void updateProfile_given_invalid_profile_data_throws_bad_request() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();

        DaoException daoException = new DaoException(
                "Unable to update profile",
                new DataIntegrityViolationException("Invalid profile data")
        );

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.updateProfile(profile, 1))
                .thenThrow(daoException);

        try {
                sut.updateProfile(profile, principal);
                Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
                Assert.assertEquals(
                        400,
                        e.getStatus().value()
                );
        }
    }

    @Test
    public void createProfile_given_other_dao_exception_rethrows_exception() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();
        profile.setBirthMonth("January");
        profile.setBirthDay(15);
        profile.setBirthYear(1990);
        profile.setState("OH");

        DaoException expectedException =
                new DaoException("Database error");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.createProfile(profile, 1))
                .thenThrow(expectedException);

        try {
                sut.createProfile(profile, principal);
                Assert.fail("Expected DaoException");

        } catch (DaoException e) {
                Assert.assertSame(
                        expectedException,
                        e
                );
        }
    }

    @Test
    public void updateProfile_given_other_dao_exception_rethrows_exception() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Profile profile = new Profile();

        DaoException expectedException =
                new DaoException("Database error");

        when(principal.getName()).thenReturn("testuser");
        when(userDao.getUserByUsername("testuser")).thenReturn(user);
        when(profileDao.updateProfile(profile, 1))
                .thenThrow(expectedException);

        try {
                sut.updateProfile(profile, principal);
                Assert.fail("Expected DaoException");

        } catch (DaoException e) {
                Assert.assertSame(
                        expectedException,
                        e
                );
        }
    }
}
