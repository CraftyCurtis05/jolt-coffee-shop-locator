<!-- Shop.vue Component -->

<template>
  <article class="shop-container">

    <!-- Purchase Coffee -->
    <section class="shop-content" id="purchase-coffee">
      <a
        href="https://store.coffeebean.com/collections/coffee"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Shop for coffee at The Coffee Bean & Tea Leaf - opens in a new tab"
      >
        <img
          src="@/assets/shop/shop_01.webp"
          alt="Coffee available for purchase"
          title="Shop for Coffee"
        />
      </a>

      <a
        href="https://store.coffeebean.com/collections/coffee"
        target="_blank"
        rel="noopener noreferrer"
        title="Shop for Coffee"
        aria-label="Shop for coffee at The Coffee Bean & Tea Leaf - opens in a new tab"
      >
        <h3>Purchase Coffee</h3>
        <p class="shop-source">
          The Coffee Bean & Tea Leaf <span aria-hidden="true">↗</span>
        </p>
      </a>
    </section>

    <!-- Purchase Equipment -->
    <section class="shop-content" id="purchase-equipment">
      <a
        href="https://www.ozocoffee.com/brew-equipment/"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Shop for coffee equipment at OZO Coffee - opens in a new tab"
      >
        <img
          src="@/assets/shop/shop_02.webp"
          alt="Coffee brewing equipment available for purchase"
          title="Shop for Coffee Equipment"
        />
      </a>

      <a
        href="https://www.ozocoffee.com/brew-equipment/"
        target="_blank"
        rel="noopener noreferrer"
        title="Shop for Coffee Equipment"
        aria-label="Shop for coffee equipment at OZO Coffee - opens in a new tab"
      >
        <h3>Purchase Equipment</h3>
        <p class="shop-source">
          OZO Coffee <span aria-hidden="true">↗</span>
        </p>
      </a>
    </section>

    <!-- Purchase Merchandise -->
    <section class="shop-content" id="purchase-merchandise">
      <a
        href="https://www.etsy.com/market/coffee_novelties"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Shop for coffee merchandise on Etsy - opens in a new tab"
      >
        <img
          src="@/assets/shop/shop_03.webp"
          alt="Coffee merchandise available for purchase"
          title="Shop for Coffee Merchandise"
        />
      </a>

      <a
        href="https://www.etsy.com/market/coffee_novelties"
        target="_blank"
        rel="noopener noreferrer"
        title="Shop for Coffee Merchandise"
        aria-label="Shop for coffee merchandise on Etsy - opens in a new tab"
      >
        <h3>Purchase Merchandise</h3>
        <p class="shop-source">
          Etsy <span aria-hidden="true">↗</span>
        </p>
      </a>
    </section>

    <!-- Purchase Snacks -->
    <section class="shop-content" id="purchase-snacks">
      <a
        href="https://shop.shatila.com/default.aspx"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Shop for snacks at Shatila Bakery - opens in a new tab"
      >
        <img
          src="@/assets/shop/shop_04.webp"
          alt="Snacks available for purchase"
          title="Shop for Snacks"
        />
      </a>

      <a
        href="https://shop.shatila.com/default.aspx"
        target="_blank"
        rel="noopener noreferrer"
        title="Shop for Snacks"
        aria-label="Shop for snacks at Shatila Bakery - opens in a new tab"
      >
        <h3>Purchase Snacks</h3>
        <p class="shop-source">
          Shatila Bakery <span aria-hidden="true">↗</span>
        </p>
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
  width: 90%;
  max-width: 70rem;
  margin: 0 auto;
}

.shop-content {
  display: flex;
  flex-direction: column;
  width: 45%;
  max-width: 32rem;
  background-color: #e8bb64;
  border-radius: .1rem;
  padding-bottom: .5rem;
  margin: 1rem;
  transition: all 0.5s ease-in-out;
}

.shop-content:hover {
  transform: scale(1.03);
  filter: grayscale(40%);
}

.shop-content img {
  display: block;
  width: 100%;
  height: 16rem;
  object-fit: cover;
  border: .5rem #e8bb64 solid;
}

a {
  color: #333437;
  text-decoration: none;
}

a:focus-visible {
  outline: .15rem rgb(53, 37, 19) solid;
  outline-offset: .15rem;
  z-index: 2;
}

h3 {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 1.5rem;
  font-size: 1.2rem;
  font-weight: 500;
  line-height: 1.2;
  color: rgb(53, 37, 19);
  padding: 0 .5rem;
  margin: .5rem auto;
}

.shop-source {
  font-size: .75rem;
  color: rgb(53, 37, 19);
  margin: -.25rem auto .25rem;
}


@media screen and (max-width: 711px) {

  .shop-content {
    width: 100%;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .shop-container {
    width: 90%;
  }

  .shop-content {
    width: 100%;
    max-width: 22rem;
    margin: .75rem auto;
  }

  .shop-content img {
    height: auto;
  }

}
</style>
