<!-- ProfileDetails.vue Component -->
 
<template>
  <section class="profile-details-container">
    <header>
      <h1>User Info</h1>
    </header>
   
    <article class="profile-details" v-if="user">
      <!-- Display user details only when 'user' prop is available -->
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
    </article>

    <article v-else>
      <!-- Show loading message if 'user' prop is not yet available -->
      <p>Loading user profile...</p>
    </article>
  </section>
</template>

<script>
export default {
  name: 'ProfileDetails',
  props: {
    user: {
      // Expecting a 'user' object as a prop that will be passed to the component
      type: Object, // Specifies the expected data type of the prop (Object)
      required: true, // Ensures that the 'user' prop is always provided when the component is used
      default: () => ({}) // Provides a default empty object in case no value is passed (optional fallback)
    }
  }
};
</script>

<style scoped>

</style>