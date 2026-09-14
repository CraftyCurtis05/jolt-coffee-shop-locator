package com.jolt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
/*
 * DTO stands for "Data Transfer Object".
 * This type of class transfers data between the client and server.
 *
 * RegisterUserDto contains the information sent by the client
 * when creating a new user account.
 */

public class RegisterUserDto {

    @NotBlank(message = "Username is required")
    @Size(
        min = 3,
        max = 30,
        message = "Username must be between 3 and 30 characters"
    )
    @Pattern(
        regexp = "^[A-Za-z0-9._-]+$",
        message = "Username can only contain letters, numbers, periods, underscores and hyphens"
    )
    private String username;

    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        max = 72,
        message = "Password must be between 8 and 72 characters"
    )
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
