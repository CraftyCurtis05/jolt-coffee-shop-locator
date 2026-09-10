<!-- Shop.vue Component -->

<template>
  <article class="shop-container">

    <!-- Purchase Coffee -->
    <section class="shop" id="purchase-coffee">
      <a
        href="https://store.coffeebean.com/collections/coffee"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/shop/shop_01.webp"
          alt="Coffee available for purchase"
          title="Click to Purchase Coffee"
        />
      </a>

      <a
        href="https://store.coffeebean.com/collections/coffee"
        target="_blank"
        rel="noopener noreferrer"
        title="Click to Purchase Coffee"
      >
        <h3>Purchase Coffee</h3>
      </a>
    </section>

    <!-- Purchase Equipment -->
    <section class="shop" id="purchase-equipment">
      <a
        href="https://www.ozocoffee.com/brew-equipment/"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/shop/shop_02.webp"
          alt="Coffee brewing equipment available for purchase"
          title="Click to Purchase Coffee Equipment"
        />
      </a>

      <a
        href="https://www.ozocoffee.com/brew-equipment/"
        target="_blank"
        rel="noopener noreferrer"
        title="Click to Purchase Coffee Equipment"
      >
        <h3>Purchase Equipment</h3>
      </a>
    </section>

    <!-- Purchase Merchandise -->
    <section class="shop" id="purchase-merchandise">
      <a
        href="https://www.etsy.com/market/coffee_novelties"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/shop/shop_03.webp"
          alt="Coffee merchandise available for purchase"
          title="Click to Purchase Merchandise"
        />
      </a>

      <a
        href="https://www.etsy.com/market/coffee_novelties"
        target="_blank"
        rel="noopener noreferrer"
        title="Click to Purchase Merchandise"
      >
        <h3>Purchase Merchandise</h3>
      </a>
    </section>

    <!-- Purchase Snacks -->
    <section class="shop" id="purchase-snacks">
      <a
        href="https://shop.shatila.com/default.aspx"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/shop/shop_04.webp"
          alt="Snacks available for purchase"
          title="Click to Purchase Snacks"
        />
      </a>

      <a
        href="https://shop.shatila.com/default.aspx"
        target="_blank"
        rel="noopener noreferrer"
        title="Click to Purchase Snacks"
      >
        <h3>Purchase Snacks</h3>
      </a>
    </section>

  </article>
</template>

<script>
export default {
  name: 'Shop'
};
</script>

<style scoped>
/* Laptop L - 1440px */

.shop-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
}

.shop {
  display: flex;
  flex-direction: column;
  max-width: 39vw;
  background-color: #e8bb64;
  border-radius: .1rem;
  padding-bottom: .5rem;
  margin: 1rem;
  transition: all 0.5s ease-in-out;
}

.shop:hover {
  transform: scale(1.1);
  filter: grayscale(70%);
}

.shop:hover h3 {
  font-weight: 700;
}

.shop img {
  width: 35vw;
  border: .5rem #e8bb64 solid;
}

a {
  color: #333437;
  text-decoration: none;
}

h3 {
  font-size: 1.2rem;
  font-weight: 500;
  color: rgb(53, 37, 19);
  margin: .5rem auto;
}
</style>