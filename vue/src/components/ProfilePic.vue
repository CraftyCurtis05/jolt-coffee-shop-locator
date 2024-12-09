<!-- ProfilePic.vue Component -->
<template>
  <!-- Profile Pic Container Section -->
  <article class="profile-pic-container">
    <!-- Header section containing the title of the component -->
    <header>
      <h1>Profile Picture</h1>
    </header>

    <!-- Section containing the image and the file upload form -->
    <section class="profile-pic">
      <!-- Conditionally render the image if a URL is provided -->
      <div v-if="imageUrl">
        <img :src="imageUrl" alt="Uploaded Profile Picture" />
      </div>

      <!-- Form for uploading a new image -->
      <form @submit.prevent="uploadImage">
        <!-- Visually hidden label for the file input, improving accessibility -->
        <label for="file-upload" class="visually-hidden">Choose an image</label>

        <!-- File input to select an image file, triggering the handleFileUpload method on change -->
        <input type="file" id="file-upload" @change="handleFileUpload" accept="image/*" aria-labelledby="file-upload"/>

        <!-- Submit button to trigger the image upload or update -->
        <button type="submit">Upload/Update Image</button>
      </form>
    </section>
  </article>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      selectedFile: null,  // Holds the file selected by the user for upload
      imageUrl: null       // Holds the URL of the uploaded or fetched image
    };
  },

  methods: {
    // Handles the file selection when the user chooses an image
    handleFileUpload(event) {
      const file = event.target.files[0];
      
      // Check if the file is an image with a valid extension
      if (file) {
        const validImageTypes = ['image/jpeg', 'image/png', 'image/jpg', 'image/webp'];  // Valid image MIME types

        // Check if the selected file's type is valid
        if (!validImageTypes.includes(file.type)) {
          alert("Unsupported file type. Please upload a .jpg, .jpeg, .png or .webp image.");
          this.selectedFile = null; // Reset selected file if invalid
          return;
        }

        // If the file type is valid, set the selectedFile
        this.selectedFile = file;
      }
    },

    // Uploads the selected image to the server
    async uploadImage() {
      // Ensure a file is selected before attempting upload
      if (!this.selectedFile) {
        alert("Please select an image to upload.");
        return;
      }

      // Prepare the file for submission
      const formData = new FormData();
      formData.append("image", this.selectedFile);  // Append the selected file to the form data

      try {
        // Attempt to upload the image using ProfileService's saveImage method
        const response = await ProfileService.saveImage(formData);
        
        // On success, update the imageUrl with the URL returned from the server
        this.imageUrl = response.imageUrl;
      } catch (error) {
        // Handle errors during the upload process
        console.error("Error uploading image:", error);
      }
    },

    // Fetches the user's profile image from the server
    async fetchUserImage() {
      try {
        // Attempt to fetch the image URL using ProfileService's getImage method
        const imageUrl = await ProfileService.getImage();
        this.imageUrl = imageUrl;  // Set the fetched image URL to imageUrl
      } catch (error) {
        // Handle errors during the image fetch process
        console.error("Error fetching image:", error);
      }
    }
  },

  // Lifecycle hook that runs when the component is created
  created() {
    // Fetch the user's image when the component is created
    this.fetchUserImage();
  },

  // Watcher that triggers when imageUrl is updated
  watch: {
    imageUrl(newImageUrl, oldImageUrl) {
      // Only re-fetch the image if the imageUrl has changed
      if (newImageUrl !== oldImageUrl) {
        this.fetchUserImage();  // Re-fetch the user image whenever the imageUrl changes
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