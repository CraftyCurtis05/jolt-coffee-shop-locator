<template>
  <h1>Shop</h1>
  <p>Shop for More Than Just Coffee: Discover Equipment, Snacks, and Unique Merchandise</p>
    <section class="links-pics">
<div class="links-pic"><a href="https://store.coffeebean.com/collections/coffee" target="_blank"><img src="src\assets\coffeeBeans.jpeg"></a></div>      
      <div class="links-pic"><a href="https://www.ozocoffee.com/brew-equipment/" target="_blank"><img src="src/assets/purchaseEquipment2.avif"></a></div>
      <div class="links-pic"><a href="https://www.etsy.com/market/coffee_novelties" target="_blank"><img src="src/assets/purchaseMerchendise.avif"></a></div>
      <div class="links-pic"><a href="https://shop.shatila.com/default.aspx" target="_blank"><img src="src\assets\purchaseSnacks.avif"></a></div>
    </section>
    <section class="links-texts">
      <div class="links-text1"><a href="https://store.coffeebean.com/collections/coffee" target="_blank">Purchase Coffee</a></div>
      <div class="links-text2"><a href="https://www.ozocoffee.com/brew-equipment/" target="_blank">Purchase Equipment</a></div>
      <div class="links-text3"><a href="https://www.etsy.com/market/coffee_novelties" target="_blank">Purchase Merchandise</a></div>
      <div class="links-text4"><a href="https://shop.shatila.com/default.aspx" target="_blank">Purchase Snacks</a></div>
    </section>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

h1{
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 35px; /* Adjust the font size as needed */
  margin: 0;
}
p {
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Nunito Sans', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  margin-top: 10px; /* Add some spacing */
}
.links-pics {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  position: relative;
}
.links-pic {
  border: 10px solid white;
  resize: both;
}

.links-text1 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 210px;
  bottom: 800px; 
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.links-text2 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 810px;
  bottom: 832px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.links-text3 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 190px;
  bottom: 440px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.links-text4 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 830px;
  bottom: 475px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
a:link, a:visited {
  color: white;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; 
}
a:hover{
  color:#e8bb64;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; 
}
</style>