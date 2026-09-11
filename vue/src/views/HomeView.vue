<!-- HomeView.vue -->

<template>
  <div class="view-container">

    <!-- Home Page Header -->
    <header>
      <h1>
        Welcome to
        <img
          src="@/assets/app/logo/jolt_logo.webp"
          alt="Jolt logo"
        />
      </h1>

      <h2>
        Discover the best coffee shops with friends and savor your favorite brews together!
      </h2>
    </header>

    <!-- Jolt Invitation -->
    <section class="invitation-container">
      <router-link
        v-bind:to="{ name: 'locator' }"
        title="Click to Find a Coffee Shop"
      >
        Start your coffee journey with us today!
      </router-link>
    </section>

    <!-- Jolt Description -->
    <section class="description-container">
      <p>
        Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you,
        read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on
        exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to
        your next perfect coffee spot.
      </p>
    </section>

    <!-- Home Page Content -->
    <main>

      <!-- Coffee Shop Image Carousel -->
      <section class="carousel-container">
        <Carousel />
      </section>

    </main>

  </div>
</template>

<script>
import Carousel from '../components/home/Carousel.vue';

export default {
  name: 'HomeView',

  // Components used on the home page
  components: {
    Carousel
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 3vh;
}

header img {
  width: 5rem;
  max-width: 100%;
  height: auto;
}

.carousel-container {
  margin: .5vh auto;
}

.invitation-container {
  padding-bottom: .5rem;
}

.invitation-container a {
  display: inline-block;
  font-size: .9rem;
  font-weight: 600;
  color: #333437;
  text-decoration: underline;
  text-decoration-color: #e8bb64;
  text-underline-offset: .25rem;
  transition: transform 0.3s ease-in-out;
}

.invitation-container a:hover {
  transform: scale(1.05);
}

.invitation-container a:focus-visible {
  color: #e8bb64;
  outline: .1rem #e8bb64 solid;
  outline-offset: .2rem;
}

.description-container {
  width: 90%;
  max-width: 60rem;
  font-size: 1rem;
  line-height: 1.5;
  margin: auto;
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .view-container {
    display: flex;
    flex-direction: column;
  }

  header {
    order: 1;
  }

  .invitation-container {
    padding-bottom: .75rem;
    order: 2;
  }

  main {
    order: 3;
  }

  .description-container {
    margin-top: -1.75rem;
    order: 4;
  }

}
</style>
