<!-- ShopView.vue -->

<template>
  <body>
    <header>
      <h1>Shop</h1>
      <h2>Shop for More Than Just Coffee: Discover Equipment, Unique Merchandise and Snacks</h2>
    </header>

    <main>
      <section class="shop-container">
        <Shop />
      </section>
    </main>

  </body>
</template>

<script>
import Shop from '../components/shop_view/Shop.vue';

export default {
  name: 'ShopView',
  components: {
    Shop
  }
};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
}

.shop-container {
  margin: 0 auto;
}
</style>