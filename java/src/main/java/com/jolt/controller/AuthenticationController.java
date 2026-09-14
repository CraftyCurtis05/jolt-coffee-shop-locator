package com.jolt.controller;

import com.jolt.dao.UserDao;
import com.jolt.dto.LoginDto;
import com.jolt.dto.LoginResponseDto;
import com.jolt.dto.RegisterUserDto;
import com.jolt.exception.DaoException;
import com.jolt.model.User;
import com.jolt.security.jwt.JWTFilter;
import com.jolt.security.jwt.TokenProvider;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://jolt.jennifercurtis.me"
})
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDao userDao;

    public AuthenticationController(
            TokenProvider tokenProvider,
            AuthenticationManagerBuilder authenticationManagerBuilder,
            UserDao userDao) {

        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                );

        Authentication authentication =
                authenticationManagerBuilder
                        .getObject()
                        .authenticate(authenticationToken);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication, false);

        User user;

        try {
            user = userDao.getUserByUsername(loginDto.getUsername());

        } catch (DaoException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Username or password is incorrect."
            );
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                JWTFilter.AUTHORIZATION_HEADER,
                "Bearer " + jwt
        );

        return new ResponseEntity<>(
                new LoginResponseDto(jwt, user),
                httpHeaders,
                HttpStatus.OK
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(
            @Valid @RequestBody RegisterUserDto newUser) {

        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords do not match."
            );
        }

        try {
            User user = userDao.createUser(newUser);

            if (user == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "User registration failed."
                );
            }

        } catch (DaoException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Username is already taken.",
                        e
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "User registration failed.",
                    e
            );
        }
    }

    // Return controller error messages to the client
        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<Map<String, String>> handleResponseStatusException(
                ResponseStatusException e) {

        return ResponseEntity
                .status(e.getStatus())
                .body(Collections.singletonMap(
                        "message",
                        e.getReason()
                ));
        }

}
