package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    // Get profile by user ID
    Profile getProfileByUserId(int userId);

    // Create or update a profile
    Profile saveProfile(Profile profile, int userId);

    // Delete a profile
    void deleteProfile(int userId);
}