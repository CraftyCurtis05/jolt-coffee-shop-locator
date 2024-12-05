<template>
  <body>
    <h1>Shop</h1>
    <p>Shop for More Than Just Coffee: Discover Equipment, Snacks, and Unique Merchandise</p>
    <main>
      <section class="shop-container">
        <div class="shop">
          <div class="shop1"><a href="https://store.coffeebean.com/collections/coffee" target="_blank"><img src="@/assets/shop/shop_01.png"></a></div>
          <div class="shop1"><a href="https://store.coffeebean.com/collections/coffee" target="_blank">Purchase Coffee</a></div>      
        </div>
        <div class="shop">
          <div class="shop2"><a href="https://www.ozocoffee.com/brew-equipment/" target="_blank"><img src="@/assets/shop/shop_02.png"></a></div>
          <div class="shop2"><a href="https://www.ozocoffee.com/brew-equipment/" target="_blank">Purchase Equipment</a></div>
        </div>
        <div class="shop">
          <div class="shop3"><a href="https://www.etsy.com/market/coffee_novelties" target="_blank"><img src="@/assets/shop/shop_03.png"></a></div>
          <div class="shop3"><a href="https://www.etsy.com/market/coffee_novelties" target="_blank">Purchase Merchandise</a></div>
        </div>
        <div class="shop">
          <div class="shop4"><a href="https://shop.shatila.com/default.aspx" target="_blank"><img src="@/assets/shop/shop_04.png"></a></div>
          <div class="shop4"><a href="https://shop.shatila.com/default.aspx" target="_blank">Purchase Snacks</a></div>
        </div>
        </section>
    </main>
  </body>
</template>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
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

.shop-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}

.shop {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: .5vw;
}
</style>