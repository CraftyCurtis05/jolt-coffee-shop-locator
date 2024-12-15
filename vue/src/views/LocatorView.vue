<!-- LocatorView.vue -->

<template>
  <body>
    <header>
      <h1>Locator</h1>
      <h2>Uncover your next coffee adventure. Locate the perfect brew near you</h2>
    </header>

    <main>
      <section class="locator-container">
        <Locator />
      </section>
    </main>
    
  </body>
</template>
  
<script>
import Locator from '../components/locator_view/Locator.vue';

export default {
  name: 'LocatorView',
  components: {
    Locator
  }
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

.locator-container {
  margin: 0 auto;
}

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

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