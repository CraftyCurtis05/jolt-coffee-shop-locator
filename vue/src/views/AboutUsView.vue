<!-- AboutUsView.vue -->

<template>
  <div class="view-container">

    <!-- About Header -->
    <header>
      <h1>Who We Are</h1>
      <h2>Meet Our Developers</h2>
    </header>

    <!-- Developer Profiles -->
    <main>

      <!-- Jennifer -->
      <section class="jennifer">
        <Jennifer />
      </section>

      <!-- Steven -->
      <section class="steven">
        <Steven />
      </section>

      <!-- Naomi -->
      <section class="naomi">
        <Naomi />
      </section>

      <!-- David -->
      <section class="david">
        <David />
      </section>

    </main>

  </div>
</template>

<script>
import Jennifer from '../components/about/Jennifer.vue';
import Steven from '../components/about/Steven.vue';
import Naomi from '../components/about/Naomi.vue';
import David from '../components/about/David.vue';

export default {
  name: 'AboutUsView',

  components: {
    Jennifer,
    Steven,
    Naomi,
    David
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

main {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  width: 95vw;
  margin: 0 auto 2vw;
}


/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  main {
    width: 98vw;
  }
}
</style>