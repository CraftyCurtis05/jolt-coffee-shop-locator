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
main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.locator-container {
  margin: 0 auto;
}
</style>