package com.jolt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jolt.model.User;

/*
 * DTO stands for "Data Transfer Object".
 * This type of class transfers data between the client and server.
 *
 * LoginDto contains the information sent by the client when logging in,
 * while LoginResponseDto contains the information returned to the client.
 */

public class LoginResponseDto {

    private String token;
    private User user;

    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
