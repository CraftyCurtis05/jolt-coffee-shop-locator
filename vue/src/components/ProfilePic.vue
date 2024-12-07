<!-- ProfilePic.vue -->
<template>
  <div>
    <h1>Profile Pic</h1>
    <div v-if="imageUrl">
      <img :src="imageUrl" alt="Uploaded Image" />
    </div>
    <input type="file" @change="handleFileUpload" accept="image/*" />
    <button @click="uploadImage">Upload/Update Image</button>
  </div>
</template>

<script>
import axios from 'axios';
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      selectedFile: null,
      imageUrl: null
    };
  },
  methods: {
    handleFileUpload(event) {
      this.selectedFile = event.target.files[0];
    },

  // Upload the selected image
  async uploadImage() {
      if (!this.selectedFile) {
        alert("Please select an image to upload.");
        return;
      }

      const formData = new FormData();
      formData.append("image", this.selectedFile);

      try {
        // Use ProfileService's saveImage method
        const response = await ProfileService.saveImage(formData);
        
        // On success, update the imageUrl with the response from saveImage
        this.imageUrl = response.imageUrl;
      } catch (error) {
        console.error("Error uploading image:", error);
      }
    },

    async fetchUserImage() {
      try {
        const response = await axios.get("/image");
        this.imageUrl = response.data.imageUrl;
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    }
  },
  created() {
    this.fetchUserImage();
  },
  // Watch for changes to imageUrl and re-fetch the user image
  watch: {
    imageUrl(newImageUrl, oldImageUrl) {
      if (newImageUrl !== oldImageUrl) {
        // Re-fetch the image whenever imageUrl is updated
        this.fetchUserImage();
      }
    }
  } 
};
</script>

<style scoped>
img {
    width: 10vw;
}
</style>