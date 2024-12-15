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
          your next perfect coffee spot. <br> Start your coffee journey with us today!
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
/* Laptop L - 1440px */
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
  margin: .5rem auto;
}

.description-container {
  font-size: 1rem;
  max-width: 80%;
  line-height: 1.5;
  margin: -1rem auto;
}

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  header img {
    width: 10rem;
  }

  .description-container {
    font-size: 1.5rem;
    max-width: 70%;
  }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  header img {
    width: 5.5rem;
  }

  .description-container {
    font-size: .85rem;
    max-width: 92%;
  }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

  header img {
    width: 4.8rem;
  }

  .description-container {
    font-size: .80rem;
    max-width: 90%;
  }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  header img {
    width: 4.5rem;
  }

  .description-container {
    font-size: .7rem;
    max-width: 93%
  }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

  header img {
    width: 4.2rem;
  }

  .description-container {
    font-size: .67rem;
    max-width: 91%
  }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

  header img {
    width: 4rem;
  }

  .description-container {
    font-size: .68rem;
    max-width: 93%
  }
}
</style>
