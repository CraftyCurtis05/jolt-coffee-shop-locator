<!-- Articles.vue Component -->

<template>
  <article class="articles-container">

    <!-- Health Benefits of Coffee -->
    <section class="articles-content" id="health-benefits">
      <a
        href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Read Health Benefits of Coffee on Riordan Clinic - opens in a new tab"
      >
        <img
          src="@/assets/articles/articles_01.webp"
          alt="Coffee representing the health benefits of coffee"
          title="Read Health Benefits of Coffee"
        />
      </a>

      <a
        href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/"
        target="_blank"
        rel="noopener noreferrer"
        title="Read Health Benefits of Coffee"
        aria-label="Read Health Benefits of Coffee on Riordan Clinic - opens in a new tab"
      >
        <h3>Health Benefits of Coffee</h3>
        <p class="article-source">Riordan Clinic <span aria-hidden="true">↗</span></p>
      </a>
    </section>

    <!-- Brain Boosting Effects of Coffee -->
    <section class="articles-content" id="brain-boosting">
      <a
        href="https://www.medscape.com/viewarticle/994049"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Read Brain Boosting Effects of Coffee on Medscape - opens in a new tab"
      >
        <img
          src="@/assets/articles/articles_02.webp"
          alt="Coffee representing the brain boosting effects of coffee"
          title="Read Brain Boosting Effects of Coffee"
        />
      </a>

      <a
        href="https://www.medscape.com/viewarticle/994049"
        target="_blank"
        rel="noopener noreferrer"
        title="Read Brain Boosting Effects of Coffee"
        aria-label="Read Brain Boosting Effects of Coffee on Medscape - opens in a new tab"
      >
        <h3>Brain Boosting Effects of Coffee</h3>
        <p class="article-source">Medscape <span aria-hidden="true">↗</span></p>
      </a>
    </section>

    <!-- Best Time to Drink Coffee -->
    <section class="articles-content" id="best-time">
      <a
        href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Read Best Time to Drink Coffee on Inc. - opens in a new tab"
      >
        <img
          src="@/assets/articles/articles_03.webp"
          alt="Coffee representing the best time to drink coffee"
          title="Read Best Time to Drink Coffee"
        />
      </a>

      <a
        href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html"
        target="_blank"
        rel="noopener noreferrer"
        title="Read Best Time to Drink Coffee"
        aria-label="Read Best Time to Drink Coffee on Inc. - opens in a new tab"
      >
        <h3>Best Time to Drink Coffee</h3>
        <p class="article-source">Inc. <span aria-hidden="true">↗</span></p>
      </a>
    </section>

    <!-- How Much Coffee is Too Much -->
    <section class="articles-content" id="how-much">
      <a
        href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Read How Much Coffee is Too Much on The New York Times - opens in a new tab"
      >
        <img
          src="@/assets/articles/articles_04.webp"
          alt="Coffee representing how much coffee is too much"
          title="Read How Much Coffee is Too Much"
        />
      </a>

      <a
        href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html"
        target="_blank"
        rel="noopener noreferrer"
        title="Read How Much Coffee is Too Much"
        aria-label="Read How Much Coffee is Too Much on The New York Times - opens in a new tab"
      >
        <h3>How Much is Too Much Coffee?</h3>
        <p class="article-source">The New York Times <span aria-hidden="true">↗</span></p>
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
  width: 90%;
  max-width: 70rem;
  margin: 0 auto;
}

.articles-content {
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

.articles-content:hover {
  transform: scale(1.03);
  filter: grayscale(40%);
}

.articles-content img {
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

.article-source {
  font-size: .75rem;
  color: rgb(53, 37, 19);
  margin: -.25rem auto .25rem;
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .articles-container {
    width: 90%;
  }

  .articles-content {
    width: 100%;
    max-width: 22rem;
    margin: .75rem auto;
  }

  .articles-content img {
    height: auto;
  }

}
</style>
