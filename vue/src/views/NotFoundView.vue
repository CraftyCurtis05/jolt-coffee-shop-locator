<!-- NotFoundView.vue -->

<template>
  <main class="not-found-view">
    <section class="not-found-content">
      <h1>Page Not Found</h1>

      <p>
        Looks like this page needs another shot of espresso.
      </p>

      <RouterLink
        :to="{ name: 'home' }"
        class="home-link"
      >
        Return Home →
      </RouterLink>
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
  background-color: rgb(245, 242, 242);
  border-left: .25rem rgb(156, 105, 33, .8) solid;
  border-radius: .25rem;
  text-align: left;
  padding: 2rem;
}

.not-found-content h1 {
  font-family: 'Ubuntu', sans-serif;
  color: rgb(53, 37, 19);
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
  color: rgb(53, 37, 19);
  text-decoration: none;
  transition: color .3s ease-in-out;
}

.home-link:hover,
.home-link:focus-visible {
  color: rgb(156, 105, 33);
}

.home-link:focus-visible {
  outline: .15rem rgb(156, 105, 33) solid;
  outline-offset: .2rem;
}
</style>
