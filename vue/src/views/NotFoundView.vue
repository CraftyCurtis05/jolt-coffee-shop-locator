<!-- NotFoundView.vue -->

<template>
  <main class="not-found-view">
    <section class="not-found-content">
      <h1>Page Not Found</h1>

      <p>
        Looks like this page needs another shot of espresso.
      </p>

      <router-link
        :to="{ name: 'home' }"
        class="home-link"
      >
        Return Home →
      </router-link>
    </section>
  </main>
</template>

<style scoped>
.not-found-view {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: calc(100vh - 10rem);
  padding: 2rem 1rem;
}

.not-found-content {
  width: 90%;
  max-width: 34rem;
  background-color: var(--color-surface);
  border-left: .25rem rgb(156, 105, 33, .8) solid;
  border-radius: var(--border-radius);
  text-align: left;
  padding: 2rem;
}

.not-found-content h1 {
  font-family: 'Ubuntu', sans-serif;
  color: var(--color-coffee);
  margin-bottom: .75rem;
}

.not-found-content p {
  font-size: 1rem;
  line-height: 1.5;
  margin-bottom: 1.25rem;
}

.home-link {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  font-weight: 600;
  color: var(--color-coffee);
  text-decoration: none;
  transition: color var(--transition-speed) ease-in-out;
}

.home-link:hover,
.home-link:focus-visible {
  color: rgb(156, 105, 33);
}

.home-link:focus-visible {
  outline: var(--focus-outline-width) rgb(156, 105, 33) solid;
  outline-offset:var(--focus-outline-offset);
}
</style>
