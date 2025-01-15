<!-- ProfileView.vue -->

<template>
  <body>

    <main>
      <section class="profile-container">

        <!-- User Profile Picture Section -->
        <article class="profile-pic">
          <ProfilePic />
        </article>
        
        <!-- Display Profile Details if User Data is Available -->
        <article class="profile-details">
          <ProfileDetails v-if="user" :user="user" />
        </article>

        <!-- Profile Form Section for Editing User Data -->
        <article class="profile-form"> 
          <!-- Profile Form with Event Listeners for Update and Toggle -->
          <ProfileForm @profile-updated="updateProfile" @form-visible="toggleUpdateButton" ref="profileForm" />

          <!-- Button to Trigger Profile Form (Visible When Form is Hidden) -->
          <button v-if="showUpdateButton" @click="showForm" title="Click to Create or Update Profile">Update Profile</button>
        </article>

      </section>

      <!-- User Favorites Section -->
      <section class="favorites-container">
        <Favorites />
      </section>

    </main>
  </body>
</template>

<script>
import ProfileService from '../services/ProfileService';
import ProfilePic from '../components/profile_view/ProfilePic.vue';
import ProfileDetails from '../components/profile_view/ProfileDetails.vue';
import ProfileForm from '../components/profile_view/ProfileForm.vue';
import Favorites from '../components/profile_view/Favorites.vue';

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
      user: null, // Holds user profile data (null initially)
      showUpdateButton: true  // Controls visibility of the "Update Profile" button
    };
  },

  methods: {
    /**
     * Opens the profile form for editing when "Update Profile" button is clicked.
     * Delegates the opening action to the ProfileForm component.
     */
    showForm() {
      this.$refs.profileForm.openForm(); // Calls openForm method in ProfileForm component
    },

    /**
     * Toggles the visibility of the "Update Profile" button based on the form's visibility.
     * @param {boolean} isFormVisible - Indicates if the profile form is visible
     */
    toggleUpdateButton(isFormVisible) {
      this.showUpdateButton = !isFormVisible; // Hides button when form is visible, otherwise shows it
    },

    /**
     * Updates the local user data with the newly updated profile information.
     * @param {Object} updatedUser - Contains updated profile data from the form submission
     */
    updateProfile(updatedUser) {
      this.user = updatedUser; // Sets the updated user data
    },

    /**
     * Fetches the user’s profile from the server on component mount.
     * Handles any errors that occur during the fetch operation.
     */
    async fetchProfile() {
      try {
        const profile = await ProfileService.getProfile(); // Fetch profile data from API
        this.user = profile; // Stores fetched profile data in the user variable
      } catch (error) {
        console.error("Error fetching user profile:", error); 
        alert("There was an error fetching your profile!");
      }
    }
  },

  mounted() {
    // Fetches the user's profile data when the component is mounted
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

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .profile-container {
    width: 30%;
  }
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