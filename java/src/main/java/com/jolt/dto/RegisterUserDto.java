package com.jolt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
 * DTO stands for "Data Transfer Object".
 * This type of class transfers data between the client and server.
 *
 * RegisterUserDto contains the information sent by the client
 * when creating a new user account.
 */

public class RegisterUserDto {

    @NotBlank
    @Size(
        max = 50,
        message = "Username cannot be longer than 50 characters"
    )
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
