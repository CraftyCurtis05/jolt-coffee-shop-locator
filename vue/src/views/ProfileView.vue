<!-- ProfileView.vue -->

<template>
  <div class="view-container">

    <main>

      <!-- User Profile -->
      <section class="profile-container">

        <!-- Profile Picture -->
        <article class="profile-pic">
          <ProfilePic />
        </article>

        <!-- Profile Details -->
        <article class="profile-details">
          <ProfileDetails
            v-if="user"
            :user="user"
          />
        </article>

        <!-- Profile Form -->
        <article class="profile-form">
          <ProfileForm
            ref="profileForm"
            @profile-updated="updateProfile"
            @form-visible="toggleUpdateButton"
          />

          <!-- Update Profile Button -->
          <button
            v-if="showUpdateButton"
            type="button"
            @click="showForm"
            title="Click to Create or Update Profile"
          >
            Update Profile
          </button>
        </article>

      </section>

      <!-- User Favorites -->
      <section class="favorites-container">
        <Favorites />
      </section>

    </main>

  </div>
</template>

<script>
import ProfileService from '../services/ProfileService.js';

import ProfilePic from '../components/profile/ProfilePic.vue';
import ProfileDetails from '../components/profile/ProfileDetails.vue';
import ProfileForm from '../components/profile/ProfileForm.vue';
import Favorites from '../components/profile/Favorites.vue';

export default {
  name: 'ProfileView',

  components: {
    ProfilePic,
    ProfileDetails,
    ProfileForm,
    Favorites
  },

  data() {
    return {
      // Store the user's profile information
      user: null,

      // Control the visibility of the Update Profile button
      showUpdateButton: true
    };
  },

  methods: {

    // Open the profile form
    showForm() {
      this.$refs.profileForm.openForm();
    },

    // Hide the Update Profile button while the form is visible
    toggleUpdateButton(isFormVisible) {
      this.showUpdateButton = !isFormVisible;
    },

    // Update the displayed profile information
    updateProfile(updatedUser) {
      this.user = updatedUser;
    },

    // Get the user's profile information
    async fetchProfile() {
      try {

        // Get the user's profile from the server
        const profile = await ProfileService.getProfile();
        this.user = profile;

        // *DEBUG* Log the user's profile for debugging
        // console.log('User profile:', profile);

      } catch (error) {
        console.error('Error fetching user profile:', error);
        alert('There was an error fetching your profile!');
      }
    }
  },

  mounted() {
    // Get the user's profile when the page loads
    this.fetchProfile();
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

main {
  display: flex;
  flex-direction: row;
}

.profile-container {
  display: flex;
  flex-direction: column;
  width: 30%;
  background-color: rgb(53, 37, 19);
  box-sizing: border-box;
  padding: 4% 5%;
}

.profile-form button {
  width: 6rem;
  height: 1.5rem;
  font-size: .7rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border-radius: .1rem;
  margin-top: 1rem;
  transition: all 0.5s ease-in-out;
}

.profile-form button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  cursor: pointer;
}

.favorites-container {
  width: 100%;
}


/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  .profile-form button {
    width: 10rem;
    height: 2.5rem;
    font-size: 1.25rem;
    margin-top: .5vw;
  }
}
</style>