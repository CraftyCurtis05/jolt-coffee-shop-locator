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
      <section
        ref="profileContent"
        class="profile-content"
        :class="{ 'profile-content-editing': isFormVisible }"
      >

        <!-- Profile Header -->
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

            <!-- Create / Update Profile Button -->
            <button
              v-if="showUpdateButton"
              type="button"
              @click="showForm"
              :title="user ? 'Click to Update Profile' : 'Click to Create Profile'"
            >
              {{ user ? 'Update Profile' : 'Create Profile' }}
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
      showUpdateButton: true,

      // Track whether the profile form is open
      isFormVisible: false
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
      this.isFormVisible = isFormVisible;
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

      } catch (error) {
        console.error('Error fetching user profile:', error);
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: "We couldn't load your profile. Please refresh and try again.",
            type: 'error'
          }
        }));
      }
    },

    // Close the profile form when clicking outside of the profile section
    handleOutsideClick(event) {

      if (
        this.isFormVisible &&
        this.$refs.profileContent &&
        !this.$refs.profileContent.contains(event.target)
      ) {
        this.$refs.profileForm.cancelChanges();
      }
    }
  },

  mounted() {

    // Close the profile form when clicking outside of the profile section
    document.addEventListener(
      'pointerdown',
      this.handleOutsideClick
    );

    // Get the user's profile when the page loads
    this.fetchProfile();
  },

  beforeUnmount() {

    // Remove the outside click listener
    document.removeEventListener(
      'pointerdown',
      this.handleOutsideClick
    );
  }
};
</script>

<style scoped>

.view-container {
  overflow: visible;
}


/* Laptop L - 1440px */

main {
  display: flex;
  align-items: flex-start;
  width: 95%;
  max-width: 100rem;
  margin: .75rem auto 1.5rem;
}

header {
  max-width: 90%;
  margin: 1rem auto 1.25rem;
}

header h1 {
  font-size: 1.6rem;
}


/* Profile Page Sections */

.profile-content {
  width: 22rem;
  flex-shrink: 0;
  align-self: flex-start;
  position: sticky;
  top: 1.6rem;
}

/* Allow the page to scroll normally while editing the profile */
.profile-content-editing {
  position: static;
  max-height: none;
  overflow: visible;
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


/* Profile Page Header */

.profile-page-header {
  margin: 1rem auto .75rem;
}

.profile-page-header h1 {
  margin-bottom: .25rem;
}

.profile-page-header h2 {
  margin-bottom: 0;
}


/* User Profile */

.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-height: 32rem;
  background-color: rgb(53, 37, 19);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  box-shadow: 0 .35rem .75rem rgba(53, 37, 19, .15);
  padding: 1.7rem;
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
  min-height: 2.75rem;
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
    max-width: 48rem;
    margin: 0 auto;
    position: static;
  }

  .favorites-content {
    width: 100%;
    margin-left: 0;
    margin-top: 1.5rem;
  }

  .profile-container {
    display: grid;
    grid-template-columns: repeat(2, minmax(0, 1fr));
    grid-template-areas:
      "picture details"
      "picture form";
    align-items: center;
    column-gap: 1.5rem;
    width: 100%;
    max-width: 48rem;
    min-height: auto;
    padding: 1.25rem;
    margin: 0 auto;
  }

  .profile-pic {
    grid-area: picture;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .profile-details {
    grid-area: details;
  }

  .profile-form {
    grid-area: form;
  }

}


/* Mobile - 700px */
@media screen and (max-width: 700px) {

  main {
    width: 95%;
  }

  .profile-content {
    max-width: 100%;
  }

  .profile-container {
    max-width: 100%;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  main {
    width: 95%;
  }

  .profile-content {
    width: 100%;
    max-width: none;
  }

  .profile-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 94%;
    max-width: 26rem;
    padding: .5rem;
  }

  .profile-pic,
  .profile-details,
  .profile-form {
    width: 100%;
  }

  .profile-form button {
    margin: .2rem;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  main {
    width: 96%;
  }

  .profile-container {
    padding: .75rem;
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
