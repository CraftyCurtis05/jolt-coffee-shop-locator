<!-- npm install -->
<!-- npm install axios -->
<!-- npm run dev -->

<!-- App.vue -->

<template>
  <div class="app-container">

    <!-- Application Navigation -->
    <NavBar />

    <!-- Application Page Content -->
    <div class="app-content">
      <router-view />
    </div>

    <!-- Application Footer -->
    <Footer />

  </div>
</template>

<script>
import NavBar from './components/layout/NavBar.vue';
import Footer from './components/layout/Footer.vue';

export default {
  name: 'App',

  // Components used throughout the application
  components: {
    NavBar,
    Footer
  }
};
</script>