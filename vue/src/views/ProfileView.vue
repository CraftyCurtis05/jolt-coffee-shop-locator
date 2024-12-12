<!-- ProfileView.vue -->

<template>
  <body>
    <header>
      <h1>Profile</h1>
      <p>Jolt Up Your Profile</p>
    </header>

    <main>
      <section class="user-details">

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
          <button v-if="showUpdateButton" @click="showForm">Update Profile</button>
        </article>

      </section>

      <!-- User Favorites Section -->
      <section class="favorites">
        <Favorites />
      </section>

    </main>
  </body>
</template>

<script>
import ProfileService from '../services/ProfileService'; // Service for fetching profile data from API
import ProfilePic from '../components/profile_view/ProfilePic.vue'; // ProfilePic component to display user's profile picture
import ProfileForm from '../components/profile_view/ProfileForm.vue'; // ProfileForm component for editing user profile
import ProfileDetails from '../components/profile_view/ProfileDetails.vue'; // ProfileDetails component to display user info
import Favorites from '../components/profile_view/Favorites.vue'; // Favorites component to show user’s favorite items

export default {
  name: 'ProfileView',
  components: {
    ProfilePic,
    ProfileForm,
    ProfileDetails,
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