<!-- ArticlesView.vue -->

<template>
  <div class="view-container content-view">

    <!-- Articles Header -->
    <header>
      <h1>Articles</h1>
      <h2>Dive into the Coffee Chronicles: A World of Brew-tiful Stories</h2>
      <p class="articles-intro">Explore a few curated reads for coffee lovers.</p>
    </header>

    <!-- Articles Content -->
    <main>
      <section class="articles-container">
        <Articles />
      </section>
    </main>

  </div>
</template>

<script>
import Articles from '../components/articles/Articles.vue';

export default {
  name: 'ArticlesView',

  // Components used on the articles page
  components: {
    Articles
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.articles-container {
  margin: 0 auto;
}

.articles-intro {
  font-size: .85rem;
  margin: .25rem auto 0;
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  header {
    max-width: 90%;
    margin: .75rem auto;
  }

}
</style>
