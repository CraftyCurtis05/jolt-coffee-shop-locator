<!-- ProfilePic.vue Component -->

<template>
  <article class="profile-pic-container">

    <!-- Profile Picture -->
    <section class="profile-pic">

      <!-- Current Profile Picture -->
      <div class="image-container">
        <img
          :src="previewUrl || $store.state.profileImage || defaultImage"
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
          :disabled="isUploading || isRemoving"
          :title="
            isUploading
              ? 'Uploading Profile Picture'
              : selectedFile
                ? 'Click to Save Profile Picture'
                : 'Click to Change Profile Picture'
          "
        >
          {{
            isUploading
              ? 'Uploading...'
              : selectedFile
                ? 'Save New Picture'
                : 'Change Picture'
          }}
        </button>

        <!-- Remove Profile Picture -->
        <button
          v-if="$store.state.profileImage"
          type="button"
          class="remove-image-button"
          @click="deleteImage"
          :disabled="isUploading || isRemoving"
          :title="isRemoving ? 'Removing Profile Picture' : 'Click to Remove Profile Picture'"
        >
          {{ isRemoving ? 'Removing...' : 'Remove Picture' }}
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

      // Track profile picture requests
      isUploading: false,
      isRemoving: false,

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
          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'Unsupported file type. Please upload a .jpg, .jpeg, .png or .webp image.',
              type: 'warning'
            }
          }));
          this.selectedFile = null;
          return;
        }

        // Store the selected image until it is saved
        this.selectedFile = file;

        // Clear the previous image preview
        if (this.previewUrl) {
          URL.revokeObjectURL(this.previewUrl);
        }

        // Preview the selected image before it is saved
        this.previewUrl = URL.createObjectURL(file);

      }
    },

    // Upload the selected profile image
    async uploadImage() {

      // Make sure an image was selected
      if (!this.selectedFile) {
        return;
      }

      // Show that the profile picture is being uploaded
      this.isUploading = true;

      const formData = new FormData();
      formData.append('image', this.selectedFile);

      try {

        // Save the image to the server
        await ProfileService.saveImage(formData);

        // Get the newly saved image
        await this.fetchImage();

        // Confirm the profile image was updated
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Profile picture updated successfully!',
            type: 'success'
          }
        }));

        // Clear the selected image and preview after a successful upload
        this.selectedFile = null;

        if (this.previewUrl) {
          URL.revokeObjectURL(this.previewUrl);
          this.previewUrl = null;
        }

      } catch (error) {
        console.error('Error uploading image:', error);
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Error uploading profile picture!',
            type: 'error'
          }
        }));
      } finally {
        // Allow profile picture actions again
        this.isUploading = false;
      }
    },

    // Delete the user's current profile image
    async deleteImage() {
      // Show that the profile picture is being removed
      this.isRemoving = true;

      try {

        // Delete the image from the server
        await ProfileService.deleteImage();

        // Clear the current profile image
        this.$store.commit('SET_PROFILE_IMAGE', null);

        // Confirm the profile image was removed
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Profile picture removed successfully!',
            type: 'success'
          }
        }));

      } catch (error) {
        console.error('Error deleting image:', error);
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Error removing profile picture!',
            type: 'error'
          }
        }));
      } finally {
        // Allow profile picture actions again
        this.isRemoving = false;
      }
    },

    // Get the user's current profile image
    async fetchImage() {
      try {
        const imageUrl = await ProfileService.getImage();

        // Store the updated profile image
        this.$store.commit('SET_PROFILE_IMAGE', imageUrl);

      } catch (error) {

        // Use the default profile image if the saved image cannot be loaded
        this.$store.commit('SET_PROFILE_IMAGE', null);

        console.error('Error fetching image:', error);
        throw error;
      }
    }
  },

  beforeUnmount() {
    // Clear the temporary image preview
    if (this.previewUrl) {
      URL.revokeObjectURL(this.previewUrl);
    }
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
  gap: .5rem;
  width: 100%;
  margin-top: .75rem;
}

form input {
  display: none;
}

form button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 8rem;
  min-height: 2.75rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .6rem;
  margin: 0;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

form button:hover:not(:disabled) {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  border-color: #e8bb64;
  box-shadow:
    inset 0 .15rem .3rem rgba(0, 0, 0, .35),
    0 0 .35rem rgba(232, 187, 100, .35);
  transform: translateY(.05rem);
}

form button:active:not(:disabled) {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .45),
    0 0 .25rem rgba(232, 187, 100, .3);
  transform: translateY(.1rem);
}

form button:disabled {
  cursor: wait;
  opacity: .75;
  transform: none;
}

form button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .image-container {
    width: 8.5rem;
    height: 8.5rem;
  }

}
</style>
