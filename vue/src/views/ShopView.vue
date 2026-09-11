<!-- ShopView.vue -->

<template>
  <div class="view-container content-view">

    <!-- Shop Header -->
    <header>
      <h1>Shop</h1>
      <h2>Shop for More Than Just Coffee: Discover Equipment, Merchandise and Snacks</h2>
      <p class="shop-intro">Explore a few curated finds for coffee lovers.</p>
    </header>

    <!-- Shop Content -->
    <main>
      <section class="shop-container">
        <Shop />
      </section>
    </main>

  </div>
</template>

<script>
import Shop from '../components/shop/Shop.vue';

export default {
  name: 'ShopView',

  // Components used on the shop page
  components: {
    Shop
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.shop-container {
  margin: 0 auto;
}

.shop-intro {
  font-size: .85rem;
  margin: .25rem auto 0;
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  header {
    max-width: 90%;
    margin: .75rem auto;
  }

}
</style>
