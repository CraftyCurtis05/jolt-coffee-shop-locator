<!-- ProfileDetails.vue -->
<template>
  <div>
    <h1>User Info</h1>
    <div v-if="user">
      <p><strong>First Name:</strong> {{ user.firstName }}</p>
      <p><strong>Last Name:</strong> {{ user.lastName }}</p>
      <p><strong>Birth Month:</strong> {{ user.birthMonth }}</p>
      <p><strong>Birth Day:</strong> {{ user.birthDay }}</p>
      <p><strong>Birth Year:</strong> {{ user.birthYear }}</p>
      <p><strong>Address 1:</strong> {{ user.address1 }}</p>
      <p><strong>Address 2:</strong> {{ user.address2 }}</p>
      <p><strong>City:</strong> {{ user.city }}</p>
      <p><strong>State:</strong> {{ user.state }}</p>
      <p><strong>Zipcode:</strong> {{ user.zipcode }}</p>
    </div>
    <div v-else>
      <p>Loading user profile...</p>
    </div>
  </div>
</template>
  
<script>
export default {
  props: {
    user: {
      type: Object,
      required: true, // Make sure it's required if you always want it to be passed
      default: () => ({}) // Provide an empty object as the default
    }
  }
};
</script>

<style scoped>

</style>