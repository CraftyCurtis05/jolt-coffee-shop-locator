<template>
  <div>
    <input type="file" @change="handleFileUpload" accept="image/*" />
    <button @click="uploadImage">Upload/Update Image</button>
    <div v-if="imageUrl">
      <img :src="imageUrl" alt="Uploaded Image" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';

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
    async uploadImage() {
      if (!this.selectedFile) {
        alert("Please select an image to upload.");
        return;
      }

      const formData = new FormData();
      formData.append("image", this.selectedFile);

      try {
        const response = await axios.post("/image", formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        });

        // On success, show the uploaded image
        this.imageUrl = response.data.imageUrl;
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
  }
};
</script>

<style scoped>
.image-container img {
    width: 6vw;
}
</style>