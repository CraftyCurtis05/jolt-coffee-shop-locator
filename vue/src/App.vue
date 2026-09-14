<!-- npm install -->
<!-- npm install axios -->
<!-- npm run dev -->

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

export default {
  name: 'App',

  // Components used throughout the application
  components: {
    NavBar,
    Footer,
    AppNotification,
    BackToTop
  }
};
</script>
