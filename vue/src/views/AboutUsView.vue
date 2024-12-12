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
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

main {
  display: flex;
  flex-direction: row;
}
</style>

