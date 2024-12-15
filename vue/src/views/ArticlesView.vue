<!-- ArticlesView.vue -->

<template>
  <body>
    <header>
      <h1>Articles</h1>
      <h2>Dive into the Coffee Chronicles: A World of Brew-tiful Stories</h2>
    </header>

    <main>
      <section class="articles-container">
        <Articles />
      </section>
    </main>

  </body>
</template>

<script>
import Articles from '../components/articles_view/Articles.vue';

export default {
    name: 'ArticlesView',
    components: {
        Articles
    }
};
</script>

<style scoped>
/* Laptop L - 1440px */
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
}

.articles-container {
  margin: 0 auto;
}

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