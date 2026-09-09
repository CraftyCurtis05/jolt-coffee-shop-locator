<!-- ProfilePic.vue Component -->

<template>
  <article class="profile-pic-container">

    <!-- Profile Picture -->
    <section class="profile-pic">

      <!-- Current Profile Picture -->
      <div class="image-container">
        <img
          :src="imageUrl || defaultImage"
          alt="Profile Picture"
          title="Profile Picture"
        />
      </div>

      <!-- Profile Picture Upload -->
      <form @submit.prevent="uploadImage">

        <!-- Hidden File Input -->
        <input
          type="file"
          id="fileInput"
          @change="handleFileUpload"
          accept="image/jpeg, image/png, image/webp"
        />

        <!-- Change or Save Profile Picture -->
        <button
          type="button"
          @click="handleButtonClick"
          :title="selectedFile ? 'Click to Save Profile Picture' : 'Click to Change Profile Picture'"
        >
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
  name: 'ProfilePic',

  data() {
    return {
      // Store the image selected by the user
      selectedFile: null,

      // Store the user's uploaded profile image
      imageUrl: null,

      // Default profile image
      defaultImage: defaultImage
    };
  },

  methods: {

    // Open the file picker or save the selected image
    handleButtonClick() {
      if (this.selectedFile) {
        this.uploadImage();
      } else {
        this.triggerFileInput();
      }
    },

    // Open the hidden file input
    triggerFileInput() {
      document.getElementById('fileInput').click();
    },

    // Validate and store the selected image
    handleFileUpload(event) {
      const file = event.target.files[0];

      if (file) {
        const validImageTypes = [
          'image/jpeg',
          'image/png',
          'image/webp'
        ];

        // Make sure the selected file is a supported image type
        if (!validImageTypes.includes(file.type)) {
          alert('Unsupported file type. Please upload a .jpg, .jpeg, .png or .webp image.');
          this.selectedFile = null;
          return;
        }

        // Store the selected image until it is saved
        this.selectedFile = file;

        // *DEBUG* Log the selected image for debugging
        // console.log('Selected profile image:', file);
      }
    },

    // Upload the selected profile image
    async uploadImage() {
      const formData = new FormData();
      formData.append('image', this.selectedFile);

      try {

        // Save the image to the server
        await ProfileService.saveImage(formData);

        // Get the newly saved image
        await this.fetchImage();

        // Clear the selected image after a successful upload
        this.selectedFile = null;

        // *DEBUG* Log a successful profile image upload
        // console.log('Profile image uploaded successfully');

      } catch (error) {
        console.error('Error uploading image:', error);
        alert('Error uploading profile picture!');
      }
    },

    // Get the user's current profile image
    async fetchImage() {
      try {
        const imageUrl = await ProfileService.getImage();
        this.imageUrl = imageUrl;

        // *DEBUG* Log the profile image URL for debugging
        // console.log('Profile image:', imageUrl);

      } catch (error) {
        console.error('Error fetching image:', error);
      }
    }
  },

  created() {
    // Get the user's profile image when the component loads
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
  border: .7vw #e8bb64 solid;
  border-radius: 50%;
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
  border-radius: .1rem;
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