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

    <!-- Jolt Description -->
    <section class="description-container">
      <p>
        Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you,
        read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on
        exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to
        your next perfect coffee spot.
        <br />
        Start your coffee journey with us today!
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
  width: 6vw;
}

.carousel-container {
  margin: 1vh auto;
}

.description-container {
  font-size: 1rem;
  max-width: 80%;
  line-height: 1.5;
  margin: -2.5vh auto;
}
</style>