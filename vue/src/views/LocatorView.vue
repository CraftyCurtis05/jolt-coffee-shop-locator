<!-- LocatorView.vue -->

<template>
  <body>

    <!-- Locator Header -->
    <header>
      <h1>Locator</h1>
      <h2>Uncover your next coffee adventure. Locate the perfect brew near you</h2>
    </header>

    <!-- Coffee Shop Locator -->
    <main>
      <section class="locator-container">
        <Locator :user="user" />
      </section>
    </main>

  </body>
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

header {
  margin-top: 2.5vh;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2vw auto;
}

.locator-container {
  margin: 0 auto;
}


/* 4K - 2560px */
@media screen and (min-width: 2560px) {

}


/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

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