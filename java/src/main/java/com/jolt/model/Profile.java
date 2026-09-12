package com.jolt.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Profile {

    private int profileId;
    private User user;

    // First name
    @NotBlank
    @Size(max = 50)
    @Pattern(
        regexp = "^[A-Za-z][A-Za-z' -]*$",
        message = "First name contains invalid characters"
    )
    private String firstName;

    // Last name
    @NotBlank
    @Size(max = 50)
    @Pattern(
        regexp = "^[A-Za-z][A-Za-z' -]*$",
        message = "Last name contains invalid characters"
    )
    private String lastName;

    // Birth Month
    @NotBlank
    private String birthMonth;

    // Birth Day
    @Min(value = 1, message = "Birth day must be at least 1")
    @Max(value = 31, message = "Birth day cannot be greater than 31")
    private int birthDay;

    // Birth Year
    @Min(value = 1900, message = "Birth year cannot be earlier than 1900")
    private int birthYear;

    // Address 1
    @NotBlank
    @Size(
        min = 3,
        max = 100,
        message = "Address must be between 3 and 100 characters"
    )
    private String address1;

    // Address 2
    @Size(max = 50)
    private String address2;

    // City
    @NotBlank
    @Size(
        min = 2,
        max = 100,
        message = "City must be between 2 and 100 characters"
    )
    @Pattern(
        regexp = "^[A-Za-z][A-Za-z .'-]*$",
        message = "City contains invalid characters"
    )
    private String city;

    // State
    @NotBlank
    @Pattern(
        regexp = "^[A-Z]{2}$",
        message = "State must be a 2-letter abbreviation"
    )
    private String state;
    
    // Zipcode
    @NotBlank
    @Pattern(
        regexp = "^\\d{5}$",
        message = "ZIP code must be 5 digits"
    )
    private String zipcode;

    private boolean isFormSubmitted;

    public Profile() {
    }

    public Profile(int profileId,
                   User user,
                   String firstName,
                   String lastName,
                   String birthMonth,
                   int birthDay,
                   int birthYear,
                   String address1,
                   String address2,
                   String city,
                   String state,
                   String zipcode,
                   boolean isFormSubmitted) {

        this.profileId = profileId;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.isFormSubmitted = isFormSubmitted;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isFormSubmitted() {
        return isFormSubmitted;
    }

    public void setIsFormSubmitted(boolean isFormSubmitted) {
        this.isFormSubmitted = isFormSubmitted;
    }

}
