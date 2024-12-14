<!-- ProfilePic.vue Component -->

<template>
  <article class="profile-pic-container">

    <section class="profile-pic">

      <!-- Display the profile image if available, use the defaultImage if not -->
      <div v-if="imageUrl" class="image-container">
        <img :src="imageUrl || defaultImage" alt="Profile Picture" title="Profile Picture"/>
      </div>

      <!-- Form to upload a new profile picture -->
      <form @submit.prevent="uploadImage">

         <!-- Hidden input for selecting a file -->
        <input type="file" id="fileInput" @change="handleFileUpload" accept="image/*"/>
        
        <!-- Button to either trigger file selection or submit the form -->
        <button 
          type="button" 
          @click="handleButtonClick" 
          :title="selectedFile ? 'Click to Save Profile Picture' : 'Click to Change Profile Picture'">
          {{ selectedFile ? 'Save Picture' : 'Change Picture' }}
        </button>

      </form>
    </section>

  </article>
</template>

<script>
import ProfileService from '../../services/ProfileService.js';

export default {

  data() {
    return {
      // File selected by the user for uploading
      selectedFile: null,
      // URL of the uploaded profile image
      imageUrl: null,
      // Used as default profile image
      defaultImage: 'src/assets/profile_view/profile_pic.png'
    };
  },

  methods: {
      /**
     * Handles button click, either triggers file input or uploads the image.
     */
     handleButtonClick() {
      if (this.selectedFile) {
        this.uploadImage();  // If a file is selected, trigger the upload
      } else {
        this.triggerFileInput();  // Otherwise, open the file input dialog
      }
    },

    /**
     * Opens the file input dialog when the button is clicked.
     */
     triggerFileInput() {
      document.getElementById('fileInput').click();  // Triggers the file input click event
    },

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
 
      const formData = new FormData();
      formData.append("image", this.selectedFile);

      try {
        // Attempt to upload the image through the ProfileService
        const profileImage = await ProfileService.saveImage(formData);
        // Set the URL of the uploaded image after a successful upload
        this.imageUrl = profileImage.imageUrl;
        // Emit the updated image URL to the parent
        this.$emit('update-image', this.imageUrl);
        // Reset the selected file and button text
        this.selectedFile = null;  // Reset selected file so the button switches back
      } catch (error) {
        console.error("Error uploading image:", error);
        alert("Error uploading profile picture!")
      }
    },

    /**
     * Fetches the current user's profile image URL from the backend.
     * This is typically called on component creation to load the user's image.
     */
    async fetchImage() {
      try {
        const imageUrl = await ProfileService.getImage();
        // Set the fetched image URL to be displayed in the component
        this.imageUrl = imageUrl;
        // Emit the fetched image URL to the parent
        this.$emit('update-image', this.imageUrl);
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    }
  },

  async created() {
    // Fetch the user's profile image when the component is created
    this.fetchImage();
  },

  watch: {
    /**
     * Watches for changes to the image URL and refetches the image when it changes.
     * This ensures the image is updated correctly when the URL changes.
     */
    imageUrl(newImageUrl, oldImageUrl) {
      if (newImageUrl !== oldImageUrl) {
        this.fetchImage();
      }
    }
  }
};
</script>

<style scoped>
.profile-pic-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  color: rgb(245, 242, 242); 
}

.image-container {
  position: relative; 
  width: 16vw; 
  height: 16vw; 
  overflow: hidden; 
  border-radius: 50%;
  border: .7vw #e8bb64 solid;
  margin: 0 auto;
}

img,
form { 
  width: 18vw; 
  height: auto;
}

form input {
  display: none;
}

form button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 6rem;
  height: 1.5rem;
  font-size: .7rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border-radius:.1rem;
  margin: .5rem auto;
  transition: all 0.5s ease-in-out;
}

form button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  cursor: pointer;
}
</style>