package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    // Get isFormSubmitted by User ID
    boolean isFormSubmittedByUserId(int userId);

    // Create or update a profile
    Profile saveProfile(Profile profile, int userId);

    // Get profile by user ID
    Profile getProfileByUserId(int userId);

    // Delete a profile
    void deleteProfile(int userId);
}