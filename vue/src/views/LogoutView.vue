<!-- LogoutView.vue -->

<template>

  <!-- Logout Page -->
  <h1>Logout</h1>

</template>

<script>
export default {
  name: 'LogoutView',

  created() {

    // *DEBUG* Log when the logout page is loaded for debugging
    // console.log('Logging out current user.');

    // Remove the user's authentication information
    this.$store.commit('LOGOUT');

    // Send the user back to the login page
    this.$router.push('/login');
  }
};
</script>
