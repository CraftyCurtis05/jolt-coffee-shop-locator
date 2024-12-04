<template>
  <body>
    <h1>Home</h1>
    <p>Discover the best coffee shops with friends and savor your favorite brews together</p>
    <Carousel />
    <div class="company-description">
      <p>Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you, read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to your next perfect coffee spot. Start your coffee journey with us today!</p>
    </div>
    <router-link class="link" v-bind:to="{ name: 'development' }" v-if="$store.state.token != ''">DEVELOPMENT</router-link>
  </body>
</template>

<script>
import Carousel from '../components/Carousel.vue';

export default {
  components: { Carousel }
};
</script>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
}

h1 {
  font-weight: bolder;
  font-size: 1.8rem; /* Adjust the font size as needed */
  text-align: center;
  margin: 0;
}

p {
  font-family: 'Ubuntu', sans-serif;
  margin-top: .5vw; /* Add some spacing */
}

/* Add styles for the company description */
.company-description {
  text-align: center;
  font-size: .9rem; /* Adjust the font size as needed */
  max-width: 70%; /* Adjust the max width as needed */
  line-height: 1.5; /* Adjust the line height as needed */
}
</style>
