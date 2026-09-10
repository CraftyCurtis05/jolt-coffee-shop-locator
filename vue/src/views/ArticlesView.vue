<!-- ArticlesView.vue -->

<template>
  <div class="view-container content-view">

    <!-- Articles Header -->
    <header>
      <h1>Articles</h1>
      <h2>Dive into the Coffee Chronicles: A World of Brew-tiful Stories</h2>
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
</style>