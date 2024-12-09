package com.techelevator.model;

public class Profile {

    private int profileId;
    private User user;  // Assuming you have a User entity
    private String firstName;
    private String lastName;
    private String birthMonth;
    private int birthDay;
    private int birthYear;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private boolean isFormSubmitted;

    // Constructor to create a Profile object with all necessary fields.
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

    // Default constructor for creating an empty Profile object.
    public Profile() {}

    // Getters and Setters
    public int getProfileId() { return profileId; }
    public void setProfileId(int profileId) { this.profileId = profileId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthMonth() { return birthMonth; }
    public void setBirthMonth(String birthMonth) { this.birthMonth = birthMonth; }

    public int getBirthDay() { return birthDay; }
    public void setBirthDay(int birthDay) { this.birthDay = birthDay; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public boolean isFormSubmitted() { return isFormSubmitted; }
    public void setIsFormSubmitted(boolean isFormSubmitted) { isFormSubmitted = isFormSubmitted; }
}