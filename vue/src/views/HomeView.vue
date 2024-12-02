<template>
  <div class="home">
    <h1>Home</h1>
    <p>Discover the best coffee shops with friends and savor your favorite brews together</p>
    <Carousel />
    <div class="company-description">
      <p>Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you, read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to your next perfect coffee spot. Start your coffee journey with us today!</p>
    </div>
  </div>
</template>

<script>
import Carousel from '../components/Carousel.vue';
import YelpService from '../services/YelpService';

export default {
  components: { Carousel },
  created() {
    YelpService.getCoffee();
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 900px;
}

h1 {
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 35px; /* Adjust the font size as needed */
  text-align: center;
  margin: 0;
}

p {
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Nunito Sans', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  margin-top: 10px; /* Add some spacing */
}

/* Add styles for the company description */
.company-description {
  margin-top: 20px;
  text-align: center;
  font-size: 16px; /* Adjust the font size as needed */
  color: #333; /* Adjust the color as needed */
  max-width: 800px; /* Adjust the max width as needed */
  line-height: 1.5; /* Adjust the line height as needed */
}

/* Add styles for the footer */
.footer {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
  color: #888; /* Adjust the color as needed */
}

.footer-text {
  margin: 0;
}
</style>
