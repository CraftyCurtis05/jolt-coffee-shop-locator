<!-- App.vue -->

<template>
  <div class="app-container">

    <!-- Application Notifications -->
    <AppNotification />

    <!-- Application Navigation -->
    <NavBar />

    <!-- Application Page Content -->
    <div class="app-content">
      <router-view />
    </div>

    <BackToTop />

    <!-- Application Footer -->
    <Footer />

  </div>
</template>

<script>
import NavBar from './components/layout/NavBar.vue';
import Footer from './components/layout/Footer.vue';
import AppNotification from './components/layout/AppNotification.vue';
import BackToTop from './components/layout/BackToTop.vue';

import ProfileService from './services/ProfileService.js';
import FavoriteService from './services/FavoriteService.js';

export default {
  name: 'App',

  // Components used throughout the application
  components: {
    NavBar,
    Footer,
    AppNotification,
    BackToTop
  },

  methods: {

    // Get the logged-in user's profile
    async fetchProfile() {
      try {
        const profile = await ProfileService.getProfile();

        // Store the profile for use throughout the application
        this.$store.commit('SET_PROFILE_STATUS', true);
        this.$store.commit('SET_PROFILE', profile);

      } catch (error) {

        // Store that the user does not have a profile
        this.$store.commit('SET_PROFILE_STATUS', false);
        this.$store.commit('SET_PROFILE', null);
      }
    },

    // Get the logged-in user's saved profile image
    async fetchProfileImage() {
      try {
        const imageUrl = await ProfileService.getImage();

        // Store the profile image for use throughout the application
        this.$store.commit('SET_PROFILE_IMAGE', imageUrl);

      } catch (error) {

        // Use the default profile image if the user does not have one
        this.$store.commit('SET_PROFILE_IMAGE', null);
      }
    },

    // Get the logged-in user's saved favorite coffee shops
    async fetchFavorites() {
      try {
        const favorites = await FavoriteService.getFavorites();

        // Store the favorites for use throughout the application
        this.$store.commit('SET_FAVORITES', favorites || []);

      } catch (error) {

        // Use an empty list if the user does not have any favorites
        this.$store.commit('SET_FAVORITES', []);
      }
    }
  },

  mounted() {

    // Restore user information when an authenticated user refreshes the application
    if (this.$store.state.token) {
      this.fetchProfile();
      this.fetchProfileImage();
      this.fetchFavorites();
    }
  }
};
</script>
