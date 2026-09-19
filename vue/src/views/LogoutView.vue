<!-- LogoutView.vue -->

<template>

  <!-- Logout Page -->
  <h1>Logout</h1>

</template>

<script>
export default {
  name: 'LogoutView',

  created() {

    // Remove the user's authentication information
    this.$store.commit('LOGOUT');

    // Send the user back to the login page
    this.$router.replace('/login');
  }
};
</script>
