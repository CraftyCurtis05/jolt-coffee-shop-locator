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
};
</script>

<style scoped>
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
    margin: 1rem 1rem;
    transition: all 0.5s ease-in-out;
}

.shop:hover {
    transform: scale(1.1);
    filter: grayscale(70%);
}

.shop:hover h3 {
    font-weight: bold;
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
    font-weight: lighter;
    color: rgb(53, 37, 19);
    margin: .5rem auto;
}

/* Laptop L - 1440px */

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