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


/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  .name {
    font-size: 2rem;
    margin-top: 1vw;
  }

  .birthday {
    font-size: 1.5rem;
  }

  .location-container {
    margin-top: 1.5rem;
  }

  .address1,
  .address2 {
    font-size: 1.5rem;
  }
}


/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

}


/* Mobile L - 425px */
@media screen and (max-width: 425px) {

}


/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}


/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>