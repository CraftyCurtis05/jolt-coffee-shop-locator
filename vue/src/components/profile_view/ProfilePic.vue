<!-- ProfilePic.vue Component -->

<template>
  <article class="profile-pic-container">

    <section class="profile-pic">

      <!-- Display the profile image if available, use the defaultImage if not -->
      <div class="image-container">
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
import defaultImage from '../../assets/profile_view/profile_pic.webp';

export default {

  data() {
    return {
      // File selected by the user for uploading
      selectedFile: null,
      // URL of the uploaded profile image
      imageUrl: null,
      // Used as default profile image
      defaultImage: defaultImage
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
          alert("Unsupported file type. Please upload a .jpg, .jpeg, .webp or .webp image.");
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
        await ProfileService.saveImage(formData);

        // Fetch the newly saved image from the backend
        await this.fetchImage();

        // Reset selected file so the button switches back
        this.selectedFile = null;
      } catch (error) {
        console.error("Error uploading image:", error);
        alert("Error uploading profile picture!");
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
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    }
  },

  async created() {
    // Fetch the user's profile image when the component is created
    this.fetchImage();
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */
.profile-pic-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  color: rgb(245, 242, 242); 
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
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

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  .image-container {
    border: .6vw #e8bb64 solid;
  }

  form button {
    width: 10rem;
    height: 2.5rem;
    font-size: 1.25rem;
    margin-top: .5vw;
  }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>