<!-- Shop.vue Component -->

<template>

    <article class="shop-container">

        <section class="shop">
            <a href="https://store.coffeebean.com/collections/coffee" target="_blank">
                <img src="@/assets/shop_view/shop_01.png" title="Click to Purchase Coffee">
            </a>

            <a href="https://store.coffeebean.com/collections/coffee" target="_blank" title="Click to Purchase Coffee">
                <h3>Purchase Coffee</h3>
            </a>
        </section>      

        <section class="shop">
            <a href="https://www.ozocoffee.com/brew-equipment/" target="_blank">
                <img src="@/assets/shop_view/shop_02.png" title="Click to Purchase Coffee Equipment">
            </a>
        
            <a href="https://www.ozocoffee.com/brew-equipment/" target="_blank" title="Click to Purchase Coffee Equipment">
                <h3>Purchase Equipment</h3>
            </a>
        </section>

        <section class="shop">
            <a href="https://www.etsy.com/market/coffee_novelties" target="_blank">
                <img src="@/assets/shop_view/shop_03.png" title="Click to Purchase Merchandise">
            </a>
    
            <a href="https://www.etsy.com/market/coffee_novelties" target="_blank" title="Click to Purchase Merchandise">
                <h3>Purchase Merchandise</h3>
            </a>
        </section>

        <section class="shop">
            <a href="https://shop.shatila.com/default.aspx" target="_blank">
                <img src="@/assets/shop_view/shop_04.png" title="Click to Purchase Snacks">
            </a>

            <a href="https://shop.shatila.com/default.aspx" target="_blank" title="Click to Purchase Snacks">
                <h3>Purchase Snacks</h3>
            </a>
        </section>

    </article>
</template>

<script>
export default {
    name: 'Shop'
}
</script>

<style scoped>
.shop-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
}

.shop {
    display: flex;
    flex-direction: column;
    font-size: 1rem;
    max-width: 70%;
    line-height: 1.2;
    padding-bottom: 1.5vw;
}

.shop img {
    width: 85%;
    height: auto;
}

.shop img:hover {
    border: .3rem #e8bb64 solid;
}

a {
    text-decoration: none;
}

h3 {
    font-size: 1rem;
    font-weight: lighter;
    color: #333437;
    margin: 0;
}

h3:hover {
    font-weight: bolder;
}

/* Media Queries for Responsiveness */
@media (max-width: 768px) {

}

@media (max-width: 425px) {

}
</style>