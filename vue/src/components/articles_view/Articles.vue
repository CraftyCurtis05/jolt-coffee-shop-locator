<!-- Articles.vue Component -->

<template>
    <article class="articles-container">

        <section class="articles">
            <a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank">
                <img src="@/assets/articles_view/articles_01.png" title="Learn More About Health Benefits of Coffee">
            </a>

            <a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank" title="Learn More About Health Benefits of Coffee">
                <h3>Health Benefits of Coffee</h3>
            </a>
        </section>

        <section class="articles">
            <a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank">
                <img src="@/assets/articles_view/articles_02.png" title="Learn More About Brain Boosting Effects of Coffee">
            </a>

            <a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank" title="Learn More About Brain Boosting Effects of Coffee">
                <h3>Brain Boosting Effects of Coffee</h3>
            </a>
        </section>

        <section class="articles">
            <a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank">
                <img src="@/assets/articles_view/articles_03.png" title="Learn More About The Best Time to Drink Coffee">
            </a>

            <a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank" title="Learn More About The Best Time to Drink Coffee">
                <h3>Best Time to Drink Coffee</h3>
            </a>
        </section>

        <section class="articles">
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
.articles-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
    padding: 1vw 0;
}

.articles {
    display: flex;
    flex-direction: column;
    max-width: 39vw;
    background-color: #e8bb64;
    border-radius: .1rem;
    padding-bottom: .5rem;
    margin: 1rem 1rem;
    transition: all 0.5s;
}

.articles:hover {
    transform: scale(1.1);
    filter: grayscale(70%);
}

.articles:hover h3 {
    font-weight: bold;
}

.articles img {
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
</style>