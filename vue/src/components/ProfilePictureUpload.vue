<template>
    <div>
      <img :src="imageUrl" alt="Current Profile Picture" v-if="imageUrl" />
      <input type="file" @change="handleFileChange" accept="image/*" />
      <button @click="uploadProfilePicture">Change Profile Picture</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedFile: null,
        imageUrl: 'src\\assets\\profile (2).png' // This is the default picture for all new users
      };
    },
    methods: {
      handleFileChange(event) {
        this.selectedFile = event.target.files[0];
        this.imageUrl = URL.createObjectURL(this.selectedFile);
      },
      uploadProfilePicture() {
        if (this.selectedFile) {
          const formData = new FormData();
          formData.append('profilePicture', this.selectedFile);
          // We need to have an API endpoint to handle profile picture uploads, then we plug in our endpoint in our fetch params where 'profile' is.
          fetch('profile', {
            method: 'POST',
            body: formData
          })
          .then(response => response.json())
          .then(data => {
            console.log('Profile picture uploaded successfully:', data);
  
            // Optionally, we can update the user's profile picture URL in our app state if we decide we want to by doing...
            // this.$store.dispatch('updateUserProfilePicture', data.profilePictureUrl);
          })
          .catch(error => {
            console.error('Error uploading profile picture:', error);
          });
        }
      }
    }
  };
  </script>