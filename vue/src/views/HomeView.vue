<!-- HomeView.vue -->

<template>
  <div class="view-container">

    <!-- Home Page Header -->
    <header>
      <h1>
        Welcome to
        <img
          src="@/assets/app/logo/jolt_logo.webp"
          alt="Jolt"
        />
      </h1>

      <h2>
        Discover the best coffee shops with friends and savor your favorite brews together!
      </h2>
    </header>

    <!-- Verify User Has Created a Profile -->
    <div
      v-if="$store.state.profileStatus === false"
      class="profile-setup"
    >
      <p>
        Create your profile to search near home and personalize your Jolt experience.
      </p>

      <router-link
        :to="{ name: 'profile' }"
        class="profile-setup-link"
      >
        Create Profile
      </router-link>
    </div>

    <!-- Jolt Invitation -->
    <div class="invitation-container">
      <router-link
        :to="{ name: 'locator' }"
        title="Click to Find Coffee Shops"
      >
        Start your coffee journey with us today!
      </router-link>
    </div>

    <!-- Home Page Content -->
    <main>

    <!-- Coffee Shop Image Carousel -->
    <div class="carousel-section">
      <Carousel />
    </div>

      <!-- Jolt Description -->
      <div class="description-container">
        <p>
          Find coffee shops near you, discover new favorites, read reviews, and plan meet-ups with friends.
          Whether you're searching for your daily brew or your next coffee adventure, let Jolt guide you to the perfect spot.
        </p>
      </div>

      <!-- Explore Jolt -->
      <section class="explore-container">

        <div class="explore-heading">
          <span
            class="explore-accent"
            aria-hidden="true"
          ></span>

          <div>
            <h2>
              Explore Jolt
            </h2>

            <p>
              Discover more ways to enjoy your coffee journey.
            </p>
          </div>

          <span
            class="explore-accent"
            aria-hidden="true"
          ></span>
        </div>

        <div class="explore-links">

          <!-- Find Coffee -->
          <router-link
            :to="{ name: 'locator' }"
            class="explore-card"
            title="Click to Find Coffee Shops"
          >
            <span
              class="explore-card-accent"
              aria-hidden="true"
            ></span>

            <h3>
              Find Coffee
            </h3>

            <p>
              Discover coffee shops near you and find your next favorite spot.
            </p>

            <span class="explore-action">
              Explore Locator →
            </span>
          </router-link>

          <!-- Browse the Shop -->
          <router-link
            :to="{ name: 'shop' }"
            class="explore-card"
            title="Click to Browse the Shop"
          >
            <span
              class="explore-card-accent"
              aria-hidden="true"
            ></span>

            <h3>
              Browse the Shop
            </h3>

            <p>
              Explore coffee-inspired products and Jolt picks.
            </p>

            <span class="explore-action">
              Visit Shop →
            </span>
          </router-link>

          <!-- Read Articles -->
          <router-link
            :to="{ name: 'articles' }"
            class="explore-card"
            title="Click to Read Articles"
          >
            <span
              class="explore-card-accent"
              aria-hidden="true"
            ></span>

            <h3>
              Read Articles
            </h3>

            <p>
              Discover coffee tips, stories, and inspiration from the Jolt community.
            </p>

            <span class="explore-action">
              Browse Articles →
            </span>
          </router-link>

        </div>
      </section>

    </main>

  </div>
</template>

<script>
import Carousel from '../components/home/Carousel.vue';

export default {
  name: 'HomeView',

  // Components used on the home page
  components: {
    Carousel
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

main {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 3vh;
}

header {
  max-width: 90%;
  margin: 1rem auto .5rem;
}

header h1 {
  font-size: 1.6rem;
}

header img {
  width: 5rem;
  max-width: 100%;
  height: auto;
}

.carousel-section {
  margin: .5rem auto;
}

.invitation-container {
  padding-bottom: .5rem;
}

.invitation-container a {
  display: inline-block;
  font-size: .9rem;
  font-weight: 400;
  color: var(--color-text);
  text-decoration: underline;
  text-decoration-color: var(--color-accent);
  text-underline-offset: .25rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.invitation-container a:hover,
.invitation-container a:focus-visible {
  font-weight: 600;
  transform: scale(1.05);
}

.invitation-container a:focus-visible {
  color: var(--color-accent);
  outline: .1rem var(--color-accent) solid;
  outline-offset:var(--focus-outline-offset);
}

.description-container {
  width: 90%;
  max-width: 65rem;
  font-size: 1rem;
  line-height: 1.5;
  text-align: left;
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  padding-left: 1rem;
  margin: 1rem auto 1.5rem;
}

.description-container p {
  margin: 0;
}


/* Profile Setup */

.profile-setup {
  width: 90%;
  max-width: 40rem;
  background-color: rgba(232, 187, 100, .2);
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  border-radius: var(--border-radius);
  padding: .6rem 1rem;
  margin: .75rem auto 1rem;
}

.profile-setup p {
  display: inline;
  font-size: .9rem;
  margin: 0;
}

.profile-setup-link {
  display: inline-block;
  font-size: .9rem;
  font-weight: 600;
  color: var(--color-coffee);
  text-decoration: underline;
  text-decoration-color: var(--color-accent);
  text-underline-offset: .2rem;
  margin-left: .5rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.profile-setup-link:hover,
.profile-setup-link:focus-visible {
  transform: scale(1.05);
}

.profile-setup-link:focus-visible {
  color: var(--color-accent);
  outline: .1rem var(--color-accent) solid;
  outline-offset:var(--focus-outline-offset);
}


/* Explore Jolt */

.explore-container {
  width: 90%;
  max-width: 65rem;
}

.explore-heading {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 1rem;
}

.explore-heading h2 {
  font-size: 1.1rem;
  margin: 0;
}

.explore-heading p {
  font-size: .85rem;
  margin: .15rem 0 0;
}

.explore-accent {
  width: 4rem;
  height: .1rem;
  background-color: var(--color-accent);
}

.explore-links {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
  margin: 1rem;
}

.explore-card-accent {
  display: block;
  width: 2.5rem;
  height: .15rem;
  background-color: var(--color-accent);
  margin: 0 auto .75rem;
  transition: width var(--transition-speed) ease-in-out;
}

.explore-card:hover .explore-card-accent,
.explore-card:focus-visible .explore-card-accent {
  width: 4rem;
}

.explore-card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 7rem;
  color: var(--color-text);
  text-decoration: none;
  background-color: var(--color-surface);
  border: .1rem solid var(--color-coffee);
  border-radius: var(--border-radius);
  padding: 1rem;
  transition:
    transform var(--transition-speed) ease-in-out,
    border-color var(--transition-speed) ease-in-out;
}

.explore-card:hover,
.explore-card:focus-visible {
  border-color: var(--color-accent);
  transform: translateY(-.2rem);
}

.explore-card:focus-visible {
  outline: var(--focus-outline-width) solid var(--color-accent);
  outline-offset:var(--focus-outline-offset);
}

.explore-card h3 {
  font-size: 1rem;
  font-weight: 600;
  color: var(--color-coffee);
  margin: 0 0 .5rem;
}

.explore-card p {
  font-size: .9rem;
  line-height: 1.4;
  margin: 0;
}

.explore-action {
  display: inline-block;
  font-size: .8rem;
  font-weight: 600;
  color: var(--color-coffee);
  margin-top: .75rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.explore-card:hover .explore-action,
.explore-card:focus-visible .explore-action {
  color: var(--color-accent-dark);
  transform: translateX(.25rem);
}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .explore-links {
    grid-template-columns: 1fr;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  header {
    margin-bottom: 0;
  }

  .invitation-container a {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    min-height: 2.75rem;
  }

  .profile-setup-link {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    min-height: 2.75rem;
    padding: .4rem .25rem;
  }

  .description-container {
    margin: 0 auto;
  }

  .explore-container {
    margin: 1.5rem auto 2rem;
  }

  .explore-accent {
    width: 2rem;
    margin: 0 0 .75rem;
  }

  .explore-links {
    text-align: left;
  }

}
</style>
