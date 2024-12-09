<!-- ProfileView.vue -->
<template>
  <body>
    <h1>Profile</h1>
    <p>Jolt Up Your Profile</p>
    <main>
      <div class="user-details">
        <div class="profile-pic">
          <ProfilePic/>
        </div>
        <div class="profile-details">
          <button @click="showForm">Update Profile</button>
          <!-- Modal Form Component -->
          <ProfileForm ref="profileForm" @profile-updated="updateProfile"/>
          <ProfileDetails v-if="user" :user="user"/>
        </div>
      </div> 
      <div class="favorites">
        <Favorites/>
      </div>
    </main>
  </body>
</template>

<script>
import ProfileService from '../services/ProfileService.js';
import ProfilePic from '../components/ProfilePic.vue';
import ProfileForm from '../components/ProfileForm.vue';
import ProfileDetails from '../components/ProfileDetails.vue';
import Favorites from '../components/Favorites.vue';

export default {
  components: {
    ProfilePic,
    ProfileForm,
    ProfileDetails,
    Favorites
  },

  data() {
    return {
      user:null // Initially null until data is fetched
    };
  },

  methods: {

    showForm() {
      // Call the `openForm` method defined in ProfileForm
      this.$refs.profileForm.openForm();
    },

    updateProfile(updatedUser) {
      this.user = updatedUser; // Update the user data with the new profile information
    },

    async loadProfile() {
      try {
        const profile = await ProfileService.getProfile();
        this.user = profile; // Store the fetched data in 'user'
      } catch (error) {
        console.error("Error fetching user profile:", error);
        alert("There was an error fetching your profile!");
      }
    },

    async fetchUser() {
      try {
        const profile = await ProfileService.getProfile();
        this.user = profile; // Once the profile is fetched, update 'user'
      } catch (error) {
        console.error("Error fetching user profile:", error);
      }
    }
  },

  mounted() {

    this.fetchUser();  // Fetch the profile data when the component is mounted
    // Fetch the initial user profile when the parent component mounts
    this.loadProfile();
  },
};
</script>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
}

h1{
  font-weight: bolder;
  font-size: 1.8rem;
  text-align: center;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: .5vw;
}

main {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  padding: 0vw 3vw;
}

.user-details {
  display: flex;
  flex-direction: column;
}
</style>