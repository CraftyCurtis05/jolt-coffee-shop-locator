<!-- ProfileDetails.vue Component -->

<template>
  <article class="profile-details-container">

    <!-- User Profile Details -->
    <section
      class="profile-details"
      v-if="user"
    >
      <p
        class="name"
        title="Your Name"
      >
        {{ user.firstName }} {{ user.lastName }}
      </p>

      <p
        class="birthday"
        title="Your Birthday"
      >
        {{ user.birthMonth }} {{ user.birthDay }}, {{ user.birthYear }}
      </p>

      <!-- User Address -->
      <div
        class="location-container"
        title="Your Address"
      >
        <p class="address1">
          {{ user.address1 }} {{ user.address2 }}
        </p>

        <p class="address2">
          {{ user.city }}, {{ user.state }} {{ user.zipcode }}
        </p>
      </div>
    </section>

    <!-- Loading Profile -->
    <section v-else>
      <p>Loading user profile...</p>
    </section>

  </article>
</template>

<script>
export default {
  name: 'ProfileDetails',

  props: {
    user: {
      type: Object,
      required: true
    }
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.profile-details-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  color: rgb(245, 242, 242);
  border: .1rem #e8bb64 solid;
  padding-top: 1vw;
  margin-top: 1vw;
}

.name {
  font-size: 1.3rem;
  font-weight: bold;
  margin: 0 auto;
}

.birthday {
  font-size: .8rem;
  font-style: italic;
  margin: 0 auto;
}

.location-container {
  margin: 1.8rem auto;
}

.address1,
.address2 {
  font-size: .9rem;
  margin: .1rem auto;
}
</style>