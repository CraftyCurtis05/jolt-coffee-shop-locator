package com.jolt.dao;

import com.jolt.model.Profile;

public interface ProfileDao {

    Profile createProfile(Profile profile, int userId);

    Profile getProfileByUserId(int userId);

    Profile updateProfile(Profile profile, int userId);

    void deleteProfile(int userId);

    boolean isFormSubmittedByUserId(int userId);

}
