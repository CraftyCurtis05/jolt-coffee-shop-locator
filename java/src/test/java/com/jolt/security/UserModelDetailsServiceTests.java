package com.jolt.security;

import com.jolt.model.User;
import com.jolt.dao.UserDao;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.springframework.security.core.userdetails.UserDetails;

public class UserModelDetailsServiceTests {

    private UserModelDetailsService sut;

    @Mock
    private UserDao userDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        sut = new UserModelDetailsService(userDao);
    }

    @Test
    public void loadUserByUsername_given_activated_user_returns_user_details() {
        User user = new User(
                1,
                "testuser",
                "encoded-password",
                "USER"
        );

        when(userDao.getUserByUsername("testuser"))
                .thenReturn(user);

        UserDetails result =
                sut.loadUserByUsername("testuser");

        Assert.assertEquals(
                "testuser",
                result.getUsername()
        );

        Assert.assertEquals(
                "encoded-password",
                result.getPassword()
        );

        Assert.assertTrue(
                result.getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority.getAuthority().equals("ROLE_USER")
                        )
        );
    }

    @Test
    public void loadUserByUsername_given_mixed_case_login_queries_lowercase_username() {
        User user = new User(
                1,
                "testuser",
                "encoded-password",
                "USER"
        );

        when(userDao.getUserByUsername("testuser"))
                .thenReturn(user);

        sut.loadUserByUsername("TestUser");

        verify(userDao)
                .getUserByUsername("testuser");
    }

    @Test
    public void loadUserByUsername_given_inactive_user_throws_user_not_activated_exception() {
        User user = new User(
                1,
                "testuser",
                "encoded-password",
                "USER"
        );

        user.setActivated(false);

        when(userDao.getUserByUsername("testuser"))
                .thenReturn(user);

        try {
            sut.loadUserByUsername("testuser");
            Assert.fail("Expected UserNotActivatedException");

        } catch (UserNotActivatedException e) {
            Assert.assertEquals(
                    "User testuser was not activated",
                    e.getMessage()
            );
        }
    }
}
