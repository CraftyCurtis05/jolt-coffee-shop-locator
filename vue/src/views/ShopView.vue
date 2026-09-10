<!-- ShopView.vue -->

<template>
  <div class="view-container content-view">

    <!-- Shop Header -->
    <header>
      <h1>Shop</h1>
      <h2>
        Shop for More Than Just Coffee: Discover Equipment, Unique Merchandise and Snacks
      </h2>
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
</style>