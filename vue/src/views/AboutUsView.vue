<!-- AboutUsView.vue -->

<template>
  <div class="view-container">

    <!-- About Header -->
    <header>
      <h1>Who We Are</h1>
      <h2>Meet the Original Jolt Development Team</h2>
      <p class="about-intro">Jolt began as a four-person capstone project and continues to evolve through independent development.</p>
    </header>

    <!-- Developer Profiles -->
    <main>

      <!-- Jennifer -->
      <section class="jennifer">
        <Jennifer />
      </section>

      <!-- Steven -->
      <section class="steven">
        <Steven />
      </section>

      <!-- Naomi -->
      <section class="naomi">
        <Naomi />
      </section>

      <!-- David -->
      <section class="david">
        <David />
      </section>

    </main>

    <!-- Project Evolution -->
    <section class="project-evolution">
      <h2>Project Evolution</h2>

      <p>
        Jolt began as a collaborative four-person capstone project. During its
        original development, Jennifer integrated and configured the Yelp API,
        contributed across application setup and functionality, and helped shape
        the logo, links and visual design.
      </p>

      <p>
        Since the capstone, Jennifer has continued developing Jolt independently,
        expanding the profile and favorites functionality, redesigning the
        interface and imagery, improving application behavior and error handling,
        and rebuilding the application for responsive use across devices.
      </p>
    </section>

  </div>
</template>

<script>
import Jennifer from '../components/about/Jennifer.vue';
import Steven from '../components/about/Steven.vue';
import Naomi from '../components/about/Naomi.vue';
import David from '../components/about/David.vue';

export default {
  name: 'AboutUsView',

  components: {
    Jennifer,
    Steven,
    Naomi,
    David
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

main {
  display: grid;
  grid-template-columns: repeat(4, auto);
  justify-content: center;
  align-items: stretch;
  width: 95%;
  max-width: 90rem;
  margin: 0 auto 2rem;
}

main > section {
  display: flex;
  justify-content: center;
}

header {
  max-width: 90%;
  margin: 1rem auto 1.25rem;
}

header h1 {
  font-size: 1.6rem;
}

.about-intro {
  font-size: .85rem;
  margin: .25rem auto 0;
}

.project-evolution {
  width: 90%;
  max-width: 60rem;
  background-color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem 1.5rem;
  margin: 0 auto 2rem;
}

.project-evolution h2 {
  color: rgb(53, 37, 19);
  margin-top: 0;
}

.project-evolution p {
  font-size: .9rem;
  line-height: 1.5;
  margin: .75rem auto;
}


/* Laptop - 1400px */

@media screen and (max-width: 1400px) {

  main {
    grid-template-columns: repeat(2, auto);
    max-width: 46rem;
  }

}


/* Mobile L - 700px */

@media screen and (max-width: 700px) {

  main {
    grid-template-columns: 1fr;
    width: 100%;
    max-width: 24rem;
  }

  main > section {
    width: 100%;
  }

}


/* Mobile L - 426px */

@media screen and (max-width: 426px) {

  header {
    max-width: 90%;
    margin: .75rem auto 1rem;
  }

  .about-intro {
    font-size: .8rem;
    line-height: 1.4;
  }

  main {
    grid-template-columns: 1fr;
    width: 100%;
    max-width: 24rem;
    margin-bottom: 1rem;
  }

  main > section {
    width: 100%;
  }

  .project-evolution {
    width: 90%;
    padding: .85rem 1rem;
    margin-bottom: 1rem;
  }

  .project-evolution h2 {
    font-size: 1rem;
  }

  .project-evolution p {
    font-size: .8rem;
    line-height: 1.5;
  }

}
</style>
