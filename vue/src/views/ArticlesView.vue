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
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 1rem;
}

.articles-container {
  margin: 0 auto;
}
</style>