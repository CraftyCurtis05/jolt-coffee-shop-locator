// ProfileService.js
import axios from 'axios';

export default {

    // Method to add additional details to logged-in user
    saveProfile(details) {
        return axios.put('/profile', details)
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
    }
};