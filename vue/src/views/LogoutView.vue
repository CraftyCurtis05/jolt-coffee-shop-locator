<!-- LogoutView.vue -->

<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  name: 'LogoutView',
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

</style>
