<!-- ProfileDetails.vue Component -->

<template>
  <article class="profile-details-container">

    <!-- User Profile Details -->
    <section class="profile-details">
      <p class="name">
        {{ user.firstName }} {{ user.lastName }}
      </p>

      <p class="birthday">
        {{ user.birthMonth }} {{ user.birthDay }}, {{ user.birthYear }}
      </p>

      <!-- User Address -->
      <div class="location-container">
        <p class="address1">
          {{ user.address1 }}
          <template v-if="user.address2">
            {{ user.address2 }}
          </template>
        </p>

        <p class="city-state-zip">
          {{ user.city }}, {{ user.state }} {{ user.zipcode }}
        </p>
      </div>
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
  color: var(--color-surface);
  padding: .75rem 0 0;
  margin-top: .75rem;
}

.profile-details {
  width: 100%;
}


/* User Name */

.name {
  font-size: 1.3rem;
  font-weight: bold;
  margin: 0 auto .15rem;
}


/* User Birthday */

.birthday {
  font-size: .8rem;
  color: var(--color-accent);
  margin: 0 auto;
}


/* User Address */

.location-container {
  width: 100%;
  border-top: .1rem var(--color-accent) solid;
  padding-top: .75rem;
  margin: .8rem auto;
}

.address1,
.city-state-zip {
  font-size: .9rem;
  line-height: 1.4;
  margin: .1rem auto;
}
</style>
