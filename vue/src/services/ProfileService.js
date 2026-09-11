// ProfileService.js

import axios from 'axios';

export default {

  // Store the original profile for update comparisons
  originalProfile: null,

  // GET whether the user already has a profile
  getStatus() {
    return axios.get('/profile/status')
      .then(response => response.data);
  },

  // POST a new user profile
  createProfile(user) {
    return axios.post('/profile', user)
      .then(response => {

        // Store the saved profile for future update comparisons
        this.originalProfile = response.data;

        return response.data;
      });
  },

  // PUT changes to an existing user profile
  updateProfile(profile) {
    if (!this.originalProfile) {
      throw new Error(
        'Original profile data is not set. Cannot perform save operation.'
      );
    }

    // Compare the submitted profile fields to the original profile
    const updatedProfile = {};

    Object.keys(profile).forEach(key => {
      if (profile[key] !== this.originalProfile[key]) {
        updatedProfile[key] = profile[key];
      }
    });

    // Return the existing profile when nothing changed
    if (Object.keys(updatedProfile).length === 0) {

      // *DEBUG* Confirm that no profile changes were detected
      // console.log('No profile changes detected');

      return Promise.resolve(this.originalProfile);
    }

    return axios.put('/profile', updatedProfile)
      .then(response => {

        // Store the newly updated profile
        this.originalProfile = response.data;

        return response.data;
      });
  },

  // GET the user's profile
  getProfile() {
    return axios.get('/profile')
      .then(response => {

        // Store the profile for future update comparisons
        this.originalProfile = response.data;

        return response.data;
      });
  },

  // POST a new or updated profile image
  saveImage(image) {
    return axios.post('/image', image, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
      .then(response => response.data);
  },

  // GET the user's current profile image
  getImage() {
    return axios.get('/image')
      .then(response => response.data.imageUrl);
  },

  // DELETE the user's current profile image
  deleteImage() {
    return axios.delete('/image');
  }
};
