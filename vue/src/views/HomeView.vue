<!-- HomeView.vue -->

<template>
  <body>
    <header>
      <h1>Welcome to <img src="@/assets/app/logo/jolt_logo.png"></h1>
      <h2>Discover the best coffee shops with friends and savor your favorite brews together!</h2>
    </header>

    <section class="description-container">
        <p>
          Welcome to Jolt, your ultimate coffee locator hub! With us, you can easily locate and explore the best coffee shops near you, 
          read reviews from fellow coffee enthusiasts, organize meet-ups with friends over a cup of your favorite brew, and embark on 
          exciting adventures to discover new coffee destinations. Join our vibrant coffee-loving community and let Jolt guide you to 
          your next perfect coffee spot. Start your coffee journey with us today!
        </p>
      </section>

    <main>
      <section class="carousel-container">
        <Carousel />
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
  margin-bottom: 1rem;
}

header img {
  width: 5.5rem;
}

.carousel-container {
  margin: 0 auto;
}

.description-container {
  font-size: 1rem;
  max-width: 75%;
  line-height: 1.5;
  margin: 0 auto;
}

@media screen and (max-width: 768px) {

  header img {
    width: 4.5rem;
  }

  .description-container {
    font-size: .9rem;
    max-width: 90%;
  }

}

@media screen and (max-width: 425px) {

  .description-container {
    font-size: .78rem;
    margin-left: .8rem;
  }

}
</style>
