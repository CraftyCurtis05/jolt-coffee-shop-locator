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
/* Laptop L - 1440px */
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
}

.shop-container {
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