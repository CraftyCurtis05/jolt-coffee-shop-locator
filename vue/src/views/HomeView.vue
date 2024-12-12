<!-- HomeView.vue -->

<template>
  <body>
    <header>
      <h1>Home</h1>
      <h2>Discover the best coffee shops with friends and savor your favorite brews together</h2>
    </header>

    <main>
      <section class="carousel-container">
        <Carousel />
      </section>

      <section class="description-container">
        <p>
          Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you, 
          read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on 
          exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to 
          your next perfect coffee spot. Start your coffee journey with us today!
        </p>
      </section>
    </main>

  </body>
</template>

<script>
import Carousel from '../components/home_view/Carousel.vue';

export default {
  name: 'HomeView',
  components: { Carousel }
};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* Add styles for the company description */
.description-container {
  text-align: center;
  font-size: .9rem; /* Adjust the font size as needed */
  max-width: 70%; /* Adjust the max width as needed */
  line-height: 1.5; /* Adjust the line height as needed */
}
</style>
