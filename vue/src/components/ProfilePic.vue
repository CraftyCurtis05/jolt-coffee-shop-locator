<template>
  <div>
    <h1>Profile Pic</h1>
    <div class="image-container">
      <!-- Display the profile picture if available -->
      <div v-if="image">
        <img :src="image" alt="Profile Picture" class="profile-picture"/>
      </div>
      <!-- File input for selecting an image -->
      <input type="file" @change="handleFileChange" accept="image/*" />
      <!-- Button to upload the selected image -->
      <button @click="uploadProfilePicture" :disabled="!selectedFile">Upload Profile Picture</button>
    </div>
  </div>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      selectedFile: null, // To hold the selected file
      image: '' // To hold the image URL after upload
    };
  },

  methods: {
    // Handle file selection
    handleFileChange(event) {
      this.selectedFile = event.target.files[0]; // Store the selected file
    },
    
    // Upload the selected image
    async uploadProfilePicture() {
      try {
        if (this.selectedFile) {
          const response = await ProfileService.saveImage(this.selectedFile);
          this.image = response.profilePictureUrl; // Assuming the response contains the URL or image data
        }
      } catch (error) {
        console.error("Error saving image:", error);
        alert("There was an error uploading your profile picture.");
      }
    },
    
    // Fetch the current profile picture (if any) when the component is created
    async loadProfilePicture() {
      try {
        const response = await ProfileService.getImage();
        this.image = response.profilePictureUrl; // Assuming response contains the profile picture URL
      } catch (error) {
        console.error("Error fetching profile picture:", error);
      }
    }
  },

  mounted() {
    this.loadProfilePicture(); // Fetch the profile picture when the component is mounted
  }
};
</script>

<style scoped>
.image-container img {
    width: 6vw;
}
</style>