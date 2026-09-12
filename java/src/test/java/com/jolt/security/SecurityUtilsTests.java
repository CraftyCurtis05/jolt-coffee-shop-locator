package com.jolt.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

public class SecurityUtilsTests {

    @Before
    public void setup() {
        SecurityContextHolder.clearContext();
    }

    @After
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void getCurrentUsername_given_no_authentication_returns_empty_optional() {
        Optional<String> result =
                SecurityUtils.getCurrentUsername();

        Assert.assertFalse(
                result.isPresent()
        );
    }

    @Test
    public void getCurrentUsername_given_user_details_principal_returns_username() {
        User principal = new User(
                "testuser",
                "encoded-password",
                java.util.Collections.emptyList()
        );

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        principal.getAuthorities()
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        Optional<String> result =
                SecurityUtils.getCurrentUsername();

        Assert.assertTrue(
                result.isPresent()
        );

        Assert.assertEquals(
                "testuser",
                result.get()
        );
    }

    @Test
    public void getCurrentUsername_given_string_principal_returns_username() {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        "testuser",
                        null
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        Optional<String> result =
                SecurityUtils.getCurrentUsername();

        Assert.assertTrue(
                result.isPresent()
        );

        Assert.assertEquals(
                "testuser",
                result.get()
        );
    }
}
