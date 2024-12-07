// ProfileService.js
import axios from 'axios';

export default {

    // Method to add additional details to logged-in user
    saveProfile(profile) {
        return axios.put('/profile', profile)
        .then(response => response.data)
        .catch(error => {
            console.error("There was an error saving profile!");
            throw error;
        })
    },

    // Method to delete details to logged-in user
    getProfile() {
        return axios.get('/profile')
        .then(response => response.data)
        .catch(error => {
            console.error("There was an error fetching profile!", error);
            throw error;
        })
    },

    // Method to save profile image
    saveImage(image) {
        return axios.put('/image', image)
        .then(response => {
            console.log(response.data); // Make sure the response contains the imageUrl
            return response.data; // Return the entire response object so we can access imageUrl in the component
        })
        .catch(error => {
            console.error("There was an error saving profile image!", error);
            throw error;
        });
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