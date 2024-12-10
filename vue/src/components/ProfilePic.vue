<!-- ProfilePic.vue Component -->

<template>
  <section class="profile-pic-container">
    <header>
      <h1>Profile Picture</h1>
    </header>

    <article class="profile-pic">
      <!-- Display the profile image if available -->
      <div v-if="imageUrl">
        <img :src="imageUrl" alt="Uploaded Profile Picture" />
      </div>

      <!-- Form to upload a new profile picture -->
      <form @submit.prevent="uploadImage">
        <!-- Label hidden for accessibility purposes -->
        <label for="file-upload" class="visually-hidden">Choose an image</label>
        <!-- Input for selecting a file, restricted to image types -->
        <input type="file" id="file-upload" @change="handleFileUpload" accept="image/*" aria-labelledby="file-upload"/>
        <button type="submit">Upload/Update Image</button>
      </form>
    </article>
  </section>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      // File selected by the user for uploading
      selectedFile: null,
      // URL of the uploaded profile image
      imageUrl: null
    };
  },

  methods: {
    /**
     * Handles file selection, validates file type, and sets the selected file.
     * @param {Event} event - The input change event triggered by file selection.
     */
    handleFileUpload(event) {
      const file = event.target.files[0];
      
      if (file) {
        // Valid image types for profile picture upload
        const validImageTypes = ['image/jpeg', 'image/png', 'image/jpg', 'image/webp'];

        // Ensure the selected file is of a valid type
        if (!validImageTypes.includes(file.type)) {
          alert("Unsupported file type. Please upload a .jpg, .jpeg, .png or .webp image.");
          this.selectedFile = null;
          return;
        }
        // Store the selected file
        this.selectedFile = file;
      }
    },

    /**
     * Uploads the selected image to the server.
     * Sends the image as FormData to be processed by the backend service.
     */
    async uploadImage() {
      if (!this.selectedFile) {
        // Alert user to select a file before uploading
        alert("Please select an image to upload.");
        return;
      }
      const formData = new FormData();
      formData.append("image", this.selectedFile);

      try {
        // Attempt to upload the image through the ProfileService
        const response = await ProfileService.saveImage(formData);
        // Set the URL of the uploaded image after a successful upload
        this.imageUrl = response.imageUrl;
      } catch (error) {
        console.error("Error uploading image:", error);
        alert("Error uploading profile picture!")
      }
    },

    /**
     * Fetches the current user's profile image URL from the backend.
     * This is typically called on component creation to load the user's image.
     */
    async fetchUserImage() {
      try {
        const imageUrl = await ProfileService.getImage();
        // Set the fetched image URL to be displayed in the component
        this.imageUrl = imageUrl;
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    }
  },

  created() {
    // Fetch the user's profile image when the component is created
    this.fetchUserImage();
  },

  watch: {
    /**
     * Watches for changes to the image URL and refetches the image when it changes.
     * This ensures the image is updated correctly when the URL changes.
     */
    imageUrl(newImageUrl, oldImageUrl) {
      if (newImageUrl !== oldImageUrl) {
        this.fetchUserImage();
      }
    }
  }
};
</script>

<style scoped>
.profile-pic form {
  display: flex;
  flex-direction: column;
}

img {
    width: 10vw;
}
</style>