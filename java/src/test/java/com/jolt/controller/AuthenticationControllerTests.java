package com.jolt.controller;

import com.jolt.model.User;
import com.jolt.dao.UserDao;
import com.jolt.dto.RegisterUserDto;
import com.jolt.dto.LoginDto;
import com.jolt.security.jwt.TokenProvider;
import com.jolt.exception.DaoException;
import com.jolt.dto.LoginResponseDto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationControllerTests {

    private AuthenticationController sut;

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private UserDao userDao;

    @Mock
    private Authentication authentication;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ObjectPostProcessor<Object> objectPostProcessor;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(objectPostProcessor.postProcess(
                org.mockito.ArgumentMatchers.any()
        )).thenAnswer(invocation ->
                invocation.getArgument(0)
        );

        authenticationManagerBuilder =
                new AuthenticationManagerBuilder(objectPostProcessor);

        authenticationManagerBuilder
                .authenticationProvider(authenticationProvider)
                .build();

        sut = new AuthenticationController(
                tokenProvider,
                authenticationManagerBuilder,
                userDao
        );
    }

    @Test
    public void register_given_mismatched_passwords_throws_bad_request() {
        RegisterUserDto newUser = new RegisterUserDto();

        newUser.setUsername("testuser");
        newUser.setPassword("Password123!");
        newUser.setConfirmPassword("DifferentPassword123!");

        try {
            sut.register(newUser);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void register_given_valid_user_creates_user() {
        RegisterUserDto newUser = new RegisterUserDto();

        newUser.setUsername("testuser");
        newUser.setPassword("Password123!");
        newUser.setConfirmPassword("Password123!");

        User createdUser = new User();
        createdUser.setId(1);
        createdUser.setUsername("testuser");

        when(userDao.createUser(newUser))
                .thenReturn(createdUser);

        sut.register(newUser);

        org.mockito.Mockito.verify(userDao)
                .createUser(newUser);
    }

    @Test
    public void register_given_user_creation_returns_null_throws_bad_request() {
        RegisterUserDto newUser = new RegisterUserDto();

        newUser.setUsername("testuser");
        newUser.setPassword("Password123!");
        newUser.setConfirmPassword("Password123!");

        when(userDao.createUser(newUser))
                .thenReturn(null);

        try {
            sut.register(newUser);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void register_given_duplicate_username_throws_bad_request() {
        RegisterUserDto newUser = new RegisterUserDto();

        newUser.setUsername("testuser");
        newUser.setPassword("Password123!");
        newUser.setConfirmPassword("Password123!");

        DaoException daoException = new DaoException(
                "Unable to create user",
                new DataIntegrityViolationException("Duplicate username")
        );

        when(userDao.createUser(newUser))
                .thenThrow(daoException);

        try {
            sut.register(newUser);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    400,
                    e.getStatus().value()
            );
        }
    }

    @Test
    public void register_given_other_dao_exception_throws_internal_server_error() {
        RegisterUserDto newUser = new RegisterUserDto();

        newUser.setUsername("testuser");
        newUser.setPassword("Password123!");
        newUser.setConfirmPassword("Password123!");

        DaoException daoException =
                new DaoException("Database error");

        when(userDao.createUser(newUser))
                .thenThrow(daoException);

        try {
            sut.register(newUser);
            Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
            Assert.assertEquals(
                    500,
                    e.getStatus().value()
            );
        }
    }
    
    @Test
    public void login_given_valid_credentials_returns_token_and_user() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("Password123!");

        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        when(authenticationProvider.supports(
                org.mockito.ArgumentMatchers.any()
        )).thenReturn(true);

        when(authenticationProvider.authenticate(
                org.mockito.ArgumentMatchers.any(
                        org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class
                )
        )).thenReturn(authentication);

        when(tokenProvider.createToken(authentication, false))
                .thenReturn("test-jwt-token");

        when(userDao.getUserByUsername("testuser"))
                .thenReturn(user);

        ResponseEntity<LoginResponseDto> response =
                sut.login(loginDto);

        Assert.assertEquals(
                200,
                response.getStatusCodeValue()
        );

        Assert.assertEquals(
                "Bearer test-jwt-token",
                response.getHeaders().getFirst("Authorization")
        );

        Assert.assertEquals(
                "test-jwt-token",
                response.getBody().getToken()
        );

        Assert.assertEquals(
                user,
                response.getBody().getUser()
        );
    }

    @Test
    public void login_given_user_lookup_failure_throws_unauthorized() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("Password123!");

        when(authenticationProvider.supports(
                org.mockito.ArgumentMatchers.any()
        )).thenReturn(true);

        when(authenticationProvider.authenticate(
                org.mockito.ArgumentMatchers.any(
                        org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class
                )
        )).thenReturn(authentication);

        when(tokenProvider.createToken(authentication, false))
                .thenReturn("test-jwt-token");

        when(userDao.getUserByUsername("testuser"))
                .thenThrow(new DaoException("User not found"));

        try {
                sut.login(loginDto);
                Assert.fail("Expected ResponseStatusException");

        } catch (ResponseStatusException e) {
                Assert.assertEquals(
                        401,
                        e.getStatus().value()
                );
        }
    }

    @After
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }
}
