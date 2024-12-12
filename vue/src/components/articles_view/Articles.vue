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
}

.articles {
    display: flex;
    flex-direction: column;
    font-size: 1rem;
    max-width: 70%;
    line-height: 1.2;
    padding-bottom: 1.5vw;
}

.articles img {
    width: 85%;
    height: auto;
}

.articles img:hover {
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