<template>
    <h1>Profile Pic</h1>
    <div class="image-container">
      <div v-if="imageUrl">
        <img :src="imageUrl" alt="Profile Picture" class="profile-picture"/>
      </div>
      <input type="file" @change="handleFileChange" accept="image/*" />
      <button @click="uploadProfilePicture" :disabled="!selectedFile">Upload Profile Picture</button>
    </div>
</template>

<script>
export default {
  data() {
    return {
      selectedFile: null,
      imageUrl: ''  // To hold the image URL
    };
  },
  methods: {
    handleFileChange(event) {
      this.selectedFile = event.target.files[0];
    },
    uploadProfilePicture() {
      const formData = new FormData();
      formData.append("profilePicture", this.selectedFile);

      fetch('/api/user/profile-picture', {
        method: 'POST',
        body: formData,
        headers: {
          Authorization: `Bearer ${this.$store.state.token}`  // Assuming you are using Vuex to manage authentication state
        }
      })
      .then(response => response.json())
      .then(data => {
        this.imageUrl = data.profilePictureUrl; // Update the image URL after upload
      })
      .catch(error => {
        console.error('Error uploading profile picture:', error);
      });
    }
  }
};
</script>

<style scoped>

</style>