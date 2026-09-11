<!-- ProfilePic.vue Component -->

<template>
  <article class="profile-pic-container">

    <!-- Profile Picture -->
    <section class="profile-pic">

      <!-- Current Profile Picture -->
      <div class="image-container">
        <img
          :src="previewUrl || imageUrl || defaultImage"
          alt="User profile picture"
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
          {{ selectedFile ? 'Save New Picture' : 'Change Picture' }}
        </button>

      </form>
    </section>

  </article>
</template>

<script>
import ProfileService from '../../services/ProfileService.js';
import defaultImage from '../../assets/profile/profile_pic.webp';

export default {
  name: 'ProfilePic',

  data() {
    return {
      // Store the image selected by the user
      selectedFile: null,

      // Store a preview of the selected image
      previewUrl: null,

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

        // Preview the selected image before it is saved
        this.previewUrl = URL.createObjectURL(file);

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

        // Tell the navigation bar that the profile image changed
        window.dispatchEvent(new Event('profile-image-updated'));

        // Clear the selected image and preview after a successful upload
        this.selectedFile = null;
        this.previewUrl = null;

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


/* Profile Picture */

.profile-pic {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 11rem;
  height: 11rem;
  overflow: hidden;
  border: .45rem #e8bb64 solid;
  border-radius: 50%;
  margin: 0 auto;
}

.image-container img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}


/* Profile Picture Upload */

form {
  display: flex;
  justify-content: center;
  width: 100%;
}

form input {
  display: none;
}

form button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 7rem;
  min-height: 2rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .75rem;
  margin: .75rem auto 0;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out;
}

form button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
}

form button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}
</style>