<!-- ProfileDetails.vue Component -->
 
<template>
  <article class="profile-details-container">
   
    <!-- Display user details only when 'user' prop is available -->
    <section class="profile-details" v-if="user">
      <p class="name">{{ user.firstName }} {{ user.lastName }}</p>
      <p class="birthday">{{ user.birthMonth }} {{ user.birthDay }}, {{ user.birthYear }}</p>
      <p class="address1">{{ user.address1 }} {{ user.address2 }}</p>
      <p class="address2">{{ user.city }}, {{ user.state }} {{ user.zipcode }}</p>
    </section>

    <section v-else>
      <!-- Show loading message if 'user' prop is not yet available -->
      <p>Loading user profile...</p>
    </section>

  </article>
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
.profile-details-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  color: rgb(245, 242, 242);
}

.name {
  font-size: 1.2rem;
  font-weight: bold;
}

.birthday {
  font-size: .8rem;
  font-style: italic;
}

.address1,
.address2 {
  font-size: .9rem;
  margin: 0;
}
</style>