<template>
  <body>
    <h1>Articles</h1>
    <p>Dive into the Coffee Chronicles: A World of Brew-tiful Stories</p>
    <main>
      <section class="articles-container">
        <div class="articles">
          <div class="articles1"><a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank"><img src="@/assets/articles/articles_01.png"></a></div>
          <div class="articles1"><a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank">Health Benefits of Coffee</a></div>
        </div>
        <div class="articles">
          <div class="articles2"><a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank"><img src="@/assets/articles/articles_02.png"></a></div>
          <div class="articles2"><a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank">Coffee Brain Boosting Effect</a></div>
        </div>
        <div class="articles">
          <div class="articles3"><a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank"><img src="src/assets/articles/articles_03.png"></a></div>
          <div class="articles3"><a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank">Best Time to Drink Coffee</a></div>
        </div>
        <div class="articles">
          <div class="articles4"><a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank"><img src="src/assets/articles/articles_04.png"></a></div>
          <div class="articles4"><a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank">How much is too much Coffee?</a></div>
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

.articles-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}

.articles {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: .5vw;
}
</style>