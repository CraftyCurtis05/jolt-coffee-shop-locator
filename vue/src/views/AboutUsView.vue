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
header {
  margin-top: 1.8vw;
}

main {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 95vw;
  margin: 0 auto;
  margin-bottom: 2vw;
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {
  
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  main {
    width: 98vw;
  }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>

