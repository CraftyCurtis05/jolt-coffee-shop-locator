package com.jolt.dto;

/*
 * DTO stands for "Data Transfer Object".
 * This type of class transfers data between the client and server.
 *
 * LoginDto contains the information sent by the client when logging in,
 * while LoginResponseDto contains the information returned to the client.
 */

public class LoginDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                '}';
    }

}