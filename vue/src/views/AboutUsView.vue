<!-- AboutUsView.vue -->

<template>
  <body>
    <header>
      <h1>Who We Are</h1>
      <h2>Meet Our Developers</h2>
    </header>
    
    <main>
      <section class="jennifer">
        <Jennifer/>
      </section>
      
      <section class="steven">
        <Steven/>
      </section>

      <section class="naomi">
        <Naomi/>
      </section>

      <section class="david">
        <David/>
      </section>
    </main>

  </body>  
</template>

<script>
import Jennifer from '../components/about_view/Jennifer.vue';
import Steven from '../components/about_view/Steven.vue';
import Naomi from '../components/about_view/Naomi.vue';
import David from '../components/about_view/David.vue';

export default {
  name: 'AboutUsView',
  components: {
    Jennifer,
    Steven,
    Naomi,
    David
  }
}
</script>
  
<style scoped>
/* Laptop L - 1440px */
main {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: 1.1rem auto;
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

