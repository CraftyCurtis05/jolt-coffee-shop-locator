<!-- LocatorView.vue -->

<template>
  <div class="view-container">

    <!-- Locator Header -->
    <header>
      <h1>Coffee Shop Locator</h1>
      <h2>Find Your Next Favorite Coffee Shop</h2>
      <p class="locator-intro">
        Search by city, ZIP code or address, or use your saved profile to find coffee shops near home.
      </p>
    </header>

    <!-- Coffee Shop Locator -->
    <main>
      <section class="locator-container">
        <Locator :user="user" />
      </section>
    </main>

  </div>
</template>

<script>
import Locator from '../components/locator/Locator.vue';
import ProfileService from '../services/ProfileService.js';

export default {
  name: 'LocatorView',

  components: {
    Locator
  },

  data() {
    return {
      // Store the user's profile information
      user: null
    };
  },

  methods: {

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

header {
  margin-top: 1.5rem;
}

.locator-intro {
  font-size: .85rem;
  margin: .25rem auto 0;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin: 1.5rem auto;
}

.locator-container {
  width: 100%;
  margin: 0 auto;
}
</style>
