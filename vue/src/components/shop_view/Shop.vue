<!-- Shop.vue Component -->

<template>
    <article class="shop-container">

        <section class="shop" id="purchase-coffee">
            <a href="https://store.coffeebean.com/collections/coffee" target="_blank">
                <img src="@/assets/shop_view/shop_01.png" title="Click to Purchase Coffee">
            </a>

            <a href="https://store.coffeebean.com/collections/coffee" target="_blank" title="Click to Purchase Coffee">
                <h3>Purchase Coffee</h3>
            </a>
        </section>      

        <section class="shop" id="purchase-equipment">
            <a href="https://www.ozocoffee.com/brew-equipment/" target="_blank">
                <img src="@/assets/shop_view/shop_02.png" title="Click to Purchase Coffee Equipment">
            </a>

            <a href="https://www.ozocoffee.com/brew-equipment/" target="_blank" title="Click to Purchase Coffee Equipment">
                <h3>Purchase Equipment</h3>
            </a>
        </section>

        <section class="shop" id="purchase-merchandise">
            <a href="https://www.etsy.com/market/coffee_novelties" target="_blank">
                <img src="@/assets/shop_view/shop_03.png" title="Click to Purchase Merchandise">
            </a>

            <a href="https://www.etsy.com/market/coffee_novelties" target="_blank" title="Click to Purchase Merchandise">
                <h3>Purchase Merchandise</h3>
            </a>
        </section>

        <section class="shop" id="purchase-snacks">
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

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

    .shop {
        margin: 1.5rem;
    }

    h3 {
        font-size: 2rem;
    }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

    .shop {
        margin: .7rem;
    }

    h3 {
        font-size: 1rem;
    }

}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

    .shop {
        padding-bottom: .4rem;
        margin: .6rem;
    }

    h3 {
        font-size: .9rem;
    }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

    .shop {
        width: 45vw;
        padding-bottom: .4rem;
        margin: .5rem;
    }

    h3 {
        font-size: .85rem;
    }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

    .shop {
        padding-bottom: .3rem;
        margin: .4rem;
    }

    .shop img {
        border: .4rem #e8bb64 solid;
    }

    h3 {
        font-size: .75rem;
    }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

    .shop {
        padding-bottom: .3rem;
        margin: .3rem;
    }

    .shop img {
        border: .3rem #e8bb64 solid;
    }

    h3 {
        font-size: .65rem;
    }
}
</style>