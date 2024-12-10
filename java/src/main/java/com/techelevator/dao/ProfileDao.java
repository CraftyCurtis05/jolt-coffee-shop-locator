package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    // Get isFormSubmitted by User ID
    boolean isFormSubmittedByUserId(int userId);

    // Create a profile by user ID
    Profile createProfile(Profile profile, int userId);

    // Update a profile by user ID
    Profile updateProfile(Profile profile, int userId);

    // Get profile by user ID
    Profile getProfileByUserId(int userId);

    // Delete a profile by user ID
    void deleteProfile(int userId);
}