// ProfileService.js
import axios from 'axios';

export default {

    // Method to add additional details to logged-in user
    saveProfile(profile) {
        return axios.put('/profile', profile)
        .then(response => response.data)
        .catch(error => {
            console.error("Error saving profile:", error);
            throw error;
        })
    },

    // Method to delete details to logged-in user
    getProfile() {
        return axios.get('/profile')
        .then(response => response.data)
        .catch(error => {
            console.error("Error fetching profile:", error);
            throw error;
        })
    },

    // Method to save profile image
    async saveImage(image) {
      try {
        const response = axios.post("/image", image, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        });
        return (await response).data;
      } catch (error) {
        console.error("Error uploading image:", error);
        throw error;
      }
    },

    // Method to get the current profile image
    getImage() {
        try {
            const response = axios.get("/image");
            this.imageUrl = response.data.imageUrl;
            return this.imageUrl;

          } catch (error) {
            console.error("Error fetching image:", error);
            throw error;
          }
    }
};