<!-- AboutUsView.vue -->

<template>
  <body>
    <header>
      <h1>Who We Are</h1>
      <p>Meet Our Software Engineers</p>
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
import Jennifer from '../components/about/Jennifer.vue';
import Steven from '../components/about/Steven.vue';
import Naomi from '../components/about/Naomi.vue';
import David from '../components/about/David.vue';

export default {
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
  text-align: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
}

main {
  display: flex;
  flex-direction: row;
}

h1 {
  font-weight: bolder;
  font-size: 1.8rem;
  text-align: center;
  margin: 0;
}

p {
  margin-top: .5vw;
}
</style>

