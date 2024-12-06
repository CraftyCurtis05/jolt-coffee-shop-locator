package com.techelevator.model;

public class Profile {

    private int profileId;
    private User user;  // Assuming you have a User entity
    private String firstName;
    private String lastName;
    private String birthMonth;
    private int birthDay;
    private int birthYear;
    private String profileAddress1;
    private String profileAddress2;
    private String profileCity;
    private String profileState;
    private String profileZipcode;

    // Constructor to create a Profile object with all necessary fields.
    public Profile(int profileId,
                   User user,
                   String firstName,
                   String lastName,
                   String birthMonth,
                   int birthDay,
                   int birthYear,
                   String profileAddress1,
                   String profileAddress2,
                   String profileCity,
                   String profileState,
                   String profileZipcode) {
        this.profileId = profileId;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
        this.profileAddress1 = profileAddress1;
        this.profileAddress2 = profileAddress2;
        this.profileCity = profileCity;
        this.profileState = profileState;
        this.profileZipcode = profileZipcode;
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

    public String getProfileAddress1() { return profileAddress1; }
    public void setProfileAddress1(String profileAddress1) { this.profileAddress1 = profileAddress1; }

    public String getProfileAddress2() { return profileAddress2; }
    public void setProfileAddress2(String profileAddress2) { this.profileAddress2 = profileAddress2; }

    public String getProfileCity() { return profileCity; }
    public void setProfileCity(String profileCity) { this.profileCity = profileCity; }

    public String getProfileState() { return profileState; }
    public void setProfileState(String profileState) { this.profileState = profileState; }

    public String getProfileZipcode() { return profileZipcode; }
    public void setProfileZipcode(String profileZipcode) { this.profileZipcode = profileZipcode; }
}