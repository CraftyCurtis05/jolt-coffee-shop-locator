<!-- ProfileView.vue -->

<template>
  <div class="view-container">

    <!-- Profile Header -->
    <header>
      <h1>Your Profile</h1>
      <h2>Manage your personal details and profile picture</h2>
    </header>

    <main>

      <!-- User Profile -->
      <section class="profile-content">

        <!-- Profile Section Header -->
        <header class="section-header">
          <h2>Profile Information</h2>
          <p>Your personal details and profile picture.</p>
        </header>

        <!-- Profile Card -->
        <div class="profile-container">

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

        </div>

      </section>

      <!-- User Favorites -->
      <section class="favorites-content">

        <!-- Favorites Section Header -->
        <header class="section-header">
          <h2>Saved Coffee Shops</h2>
          <p>Your favorite places, all in one spot.</p>
        </header>

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
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'There was an error fetching your profile!',
            type: 'error'
          }
        }));
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
  align-items: flex-start;
  width: 95%;
  max-width: 100rem;
  margin: 1.5rem auto;
}


/* Profile Page Sections */

.profile-content {
  width: 22rem;
  flex-shrink: 0;
}

.favorites-content {
  flex: 1;
  min-width: 0;
  margin-left: 1.5rem;
}

.section-header {
  width: 100%;
  text-align: left;
  border-bottom: .1rem #e8bb64 solid;
  padding-bottom: .75rem;
  margin-bottom: 1rem;
}

.section-header h2 {
  font-size: 1.3rem;
  font-weight: 600;
  color: rgb(53, 37, 19);
  margin: 0;
}

.section-header p {
  font-size: .85rem;
  color: #525459;
  margin: .2rem 0 0;
}


/* User Profile */

.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  background-color: rgb(53, 37, 19);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  box-shadow: 0 .35rem .75rem rgba(53, 37, 19, .15);
  padding: 1.25rem;
}

.profile-pic,
.profile-details,
.profile-form {
  width: 100%;
}

.profile-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}


/* Update Profile Button */

.profile-form button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 12rem;
  min-height: 2.2rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .6rem;
  margin: 1rem;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

.profile-form button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  border-color: #e8bb64;
  box-shadow:
    inset 0 .15rem .3rem rgba(0, 0, 0, .35),
    0 0 .35rem rgba(232, 187, 100, .35);
  transform: translateY(.05rem);
}

.profile-form button:active {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .45),
    0 0 .25rem rgba(232, 187, 100, .3);
  transform: translateY(.1rem);
}

.profile-form button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* Tablet - 900px */
@media screen and (max-width: 900px) {

  main {
    flex-direction: column;
    width: 92%;
  }

  .profile-content {
    width: 100%;
    max-width: 36rem;
    margin: 0 auto;
  }

  .favorites-content {
    width: 100%;
    margin-left: 0;
    margin-top: 1.5rem;
  }

  .profile-container {
    width: 100%;
    max-width: 32rem;
    margin: 0 auto;
  }

}


/* Mobile - 700px */
@media screen and (max-width: 700px) {

  main {
    width: 95%;
  }

  .profile-content {
    max-width: 30rem;
  }

  .profile-container {
    max-width: 28rem;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .profile-content {
    width: 100%;
    max-width: none;
  }

  .profile-container {
    width: 94%;
    max-width: none;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  main {
    width: 96%;
  }

  .profile-content {
    width: 100%;
  }

  .section-header {
    padding-bottom: .5rem;
    margin-bottom: .75rem;
  }

}
</style>
