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
        const formData = new FormData();
        formData.append("image", image); // Append the file with the key "image" (which matches @RequestParam("image") in the backend)

        return axios.put('http://localhost:9000/image', formData, {
            headers: {
                'Content-Type': 'multipart/form-data', // Axios should handle this automatically, but it's good to confirm
                // You can also add the Authorization header if needed:
                // 'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => response.data)
        .catch(error => {
            console.error("There was an error saving profile image!", error);
            throw error;
        });
    },

    // Method to get the current profile image
    getImage() {
        return axios.get('http://localhost:9000/image')
        .then(response => response.data)
        .catch(error => {
            console.error("There was an error fetching profile image!", error);
            throw error;
        });
    }
};