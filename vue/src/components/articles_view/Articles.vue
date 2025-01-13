<!-- Articles.vue Component -->

<template>
    <article class="articles-container">

        <section class="article" id="health-benefits">
            <a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank">
                <img src="@/assets/articles_view/articles_01.png" title="Learn More About Health Benefits of Coffee">
            </a>

            <a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank" title="Learn More About Health Benefits of Coffee">
                <h3>Health Benefits of Coffee</h3>
            </a>
        </section>

        <section class="article" id="brain-boosting">
            <a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank">
                <img src="@/assets/articles_view/articles_02.png" title="Learn More About Brain Boosting Effects of Coffee">
            </a>

            <a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank" title="Learn More About Brain Boosting Effects of Coffee">
                <h3>Brain Boosting Effects of Coffee</h3>
            </a>
        </section>

        <section class="article" id="best-time">
            <a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank">
                <img src="@/assets/articles_view/articles_03.png" title="Learn More About The Best Time to Drink Coffee">
            </a>

            <a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank" title="Learn More About The Best Time to Drink Coffee">
                <h3>Best Time to Drink Coffee</h3>
            </a>
        </section>

        <section class="article" id="how-much">
            <a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank">
                <img src="@/assets/articles_view/articles_04.png" title="Learn More About How Much Coffee is Too Much">
            </a>

            <a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank" title="Learn More About How Much Coffee is Too Much">
                <h3>How Much is Too Much Coffee?</h3>
            </a>
        </section>

    </article>
</template>

<script>
export default {
    name: 'Articles'
};
</script>

<style scoped>
/* Laptop L - 1440px */
.articles-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
}

.article {
    display: flex;
    flex-direction: column;
    max-width: 39vw;
    background-color: #e8bb64;
    border-radius: .1rem;
    padding-bottom: .5rem;
    margin: 1rem 1rem;
    transition: all 0.5s ease-in-out;
}

.article:hover {
    transform: scale(1.1);
    filter: grayscale(70%);
}

.article:hover h3 {
    font-weight: 700;
}

.article img {
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

    .article {
        margin: 1.5rem;
    }

    h3 {
        font-size: 2rem;
    }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

    .article {
        margin: .7rem;
    }

    h3 {
        font-size: 1rem;
    }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

    .article {
        padding-bottom: .4rem;
        margin: .6rem;
    }

    h3 {
        font-size: .9rem;
    }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

    .article {
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

    .article {
        padding-bottom: .3rem;
        margin: .4rem;
    }

    .article img {
        border: .4rem #e8bb64 solid;
    }

    h3 {
        font-size: .75rem;
    }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

    .article {
        padding-bottom: .3rem;
        margin: .3rem;
    }

    .article img {
        border: .3rem #e8bb64 solid;
    }

    h3 {
        font-size: .65rem;
    }
}
</style>