// ProfileService.js
import axios from 'axios';

export default {

  /**
   * Save or update the user's profile.
   * 
   * @param {Object} profile - The profile object containing the updated user data.
   * @returns {Promise<Object>} - A promise that resolves with the response data of the updated profile.
   * @throws {Error} - Throws an error if the request fails.
   */
  saveProfile(profile) {
    return axios.put('/profile', profile)
      .then(response => response.data)
      .catch(error => {
        console.error("Error saving profile:", error);
        throw error;
      });
  },

  /**
   * Retrieve the user's profile.
   * 
   * @returns {Promise<Object>} - A promise that resolves with the response data containing the user's profile.
   * @throws {Error} - Throws an error if the request fails.
   */
  getProfile() {
    return axios.get('/profile')
      .then(response => response.data)
      .catch(error => {
        console.error("Error fetching profile:", error);
        throw error;
      });
  },

  /**
   * Upload a new profile image.
   * 
   * @param {FormData} image - The FormData object containing the image file to be uploaded.
   * @returns {Promise<Object>} - A promise that resolves with the response data containing the uploaded image's details.
   * @throws {Error} - Throws an error if the request fails.
   */
  saveImage(image) {
    return axios.post("/image", image, {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    })
      .then(response => response.data)
      .catch(error => {
        console.error("Error uploading image:", error);
        throw error;
      });
  },

  /**
   * Fetch the current profile image.
   * 
   * @returns {Promise<string>} - A promise that resolves with the URL of the current profile image.
   * @throws {Error} - Throws an error if the request fails.
   */
  getImage() {
    return axios.get("/image")
      .then(response => response.data.imageUrl) // Extract and return the image URL from the response data.
      .catch(error => {
        console.error("Error fetching image:", error);
        throw error;
      });
  }
};