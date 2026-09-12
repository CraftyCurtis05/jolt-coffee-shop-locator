<!-- HomeView.vue -->

<template>
  <div class="view-container">

    <!-- Home Page Header -->
    <header>
      <h1>
        Welcome to
        <img
          src="@/assets/app/logo/jolt_logo.webp"
          alt="Jolt logo"
        />
      </h1>

      <h2>
        Discover the best coffee shops with friends and savor your favorite brews together!
      </h2>
    </header>

    <!-- Verify User Has Created a Profile -->
    <section
      v-if="!hasProfile"
      class="profile-setup"
    >
      <p>
        Create your profile to personalize your Jolt experience.
      </p>
      <RouterLink
        to="/profile"
        class="profile-setup-link"
      >
        Create Profile
      </RouterLink>
    </section>

    <!-- Jolt Invitation -->
    <section class="invitation-container">
      <router-link
        v-bind:to="{ name: 'locator' }"
        title="Click to Find a Coffee Shop"
      >
        Start your coffee journey with us today!
      </router-link>
    </section>

    <!-- Home Page Content -->
    <main>

      <!-- Coffee Shop Image Carousel -->
      <section class="carousel-container">
        <Carousel />
      </section>

    </main>

    <!-- Jolt Description -->
    <section class="description-container">
      <p>
        Find coffee shops near you, discover new favorites, read reviews, and plan meet-ups with friends. 
        Whether you're searching for your daily brew or your next coffee adventure, let Jolt guide you to the perfect spot.
      </p>
    </section>

    <!-- Explore Jolt -->
    <section class="explore-container">
      <div class="explore-heading">
        <span class="explore-accent"></span>

        <div>
          <h2>
            Explore Jolt
          </h2>

          <p>
            Discover more ways to enjoy your coffee journey.
          </p>
        </div>

        <span class="explore-accent"></span>
      </div>

      <div class="explore-links">

        <!-- Find Coffee -->
        <router-link
          v-bind:to="{ name: 'locator' }"
          class="explore-card"
        >
          <span class="explore-card-accent"></span>
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
          v-bind:to="{ name: 'shop' }"
          class="explore-card"
        >
          <span class="explore-card-accent"></span>
          <h3>
            Browse the Shop
          </h3>

          <p>
            Explore coffee-inspired products and Jolt favorites.
          </p>
          <span class="explore-action">
            Visit Shop →
          </span>
        </router-link>

        <!-- Read Articles -->
        <router-link
          v-bind:to="{ name: 'articles' }"
          class="explore-card"
        >
          <span class="explore-card-accent"></span>
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

  </div>
</template>

<script>
import ProfileService from '../services/ProfileService';
import Carousel from '../components/home/Carousel.vue';

export default {
  name: 'HomeView',

  data() {
    return {
      hasProfile: true
    }
  },

  // Components used on the home page
  components: {
    Carousel
  },

  methods: {

    // Check whether the user already has a profile
    async fetchProfileStatus() {
      try {
        this.hasProfile = await ProfileService.getStatus();

      } catch (error) {
        console.error('Error fetching profile status:', error);
      }
    }
  },

  mounted() {
    this.fetchProfileStatus();
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
  margin: 1rem auto 1.25rem;
}

header h1 {
  font-size: 1.6rem;
}

header img {
  width: 5rem;
  max-width: 100%;
  height: auto;
}

.carousel-container {
  margin: .5vh auto;
}

.invitation-container {
  padding-bottom: .5rem;
}

.invitation-container a {
  display: inline-block;
  font-size: .9rem;
  font-weight: 400;
  color: #333437;
  text-decoration: underline;
  text-decoration-color: #e8bb64;
  text-underline-offset: .25rem;
  transition: transform 0.3s ease-in-out;
}

.invitation-container a:hover {
  font-weight: 600;
  transform: scale(1.05);
}

.invitation-container a:focus-visible {
  color: #e8bb64;
  outline: .1rem #e8bb64 solid;
  outline-offset: .2rem;
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
  border-radius: .25rem;
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
  color: rgb(53, 37, 19);
  text-decoration: underline;
  text-decoration-color: #e8bb64;
  text-underline-offset: .2rem;
  margin-left: .5rem;
  transition: transform 0.3s ease-in-out;
}

.profile-setup-link:hover {
  transform: scale(1.05);
}

.profile-setup-link:focus-visible {
  color: #e8bb64;
  outline: .1rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* Explore Jolt */

.explore-container {
  width: 90%;
  max-width: 65rem;
  margin: 1.5rem auto 2.5rem;
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
  background-color: #e8bb64;
}

.explore-links {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
}

.explore-card-accent {
  display: block;
  width: 2.5rem;
  height: .15rem;
  background-color: #e8bb64;
  margin: 0 auto .75rem;
  transition: width 0.3s ease-in-out;
}

.explore-card:hover .explore-card-accent {
  width: 4rem;
}

.explore-card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 7rem;
  color: #333437;
  text-decoration: none;
  background-color: rgb(245, 242, 242);
  border: .1rem solid rgb(53, 37, 19);
  border-radius: .25rem;
  padding: 1rem;
  transition:
    transform 0.3s ease-in-out,
    border-color 0.3s ease-in-out;
}

.explore-card:hover {
  border-color: #e8bb64;
  transform: translateY(-.2rem);
}

.explore-card:focus-visible {
  outline: .15rem solid #e8bb64;
  outline-offset: .2rem;
}

.explore-card h3 {
  font-size: 1rem;
  font-weight: 600;
  color: rgb(53, 37, 19);
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
  color: rgb(53, 37, 19);
  margin-top: .75rem;
  transition: transform 0.3s ease-in-out;
}

.explore-card:hover .explore-action {
  color: #9b6a20;
  transform: translateX(.25rem);
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .view-container {
    display: flex;
    flex-direction: column;
  }

  header {
    order: 1;
  }

  .profile-setup {
    order: 2;
  }

  .invitation-container {
    padding-bottom: .75rem;
    order: 3;
  }

  main {
    order: 4;
  }

  .description-container {
    order: 5;
  }

  .explore-container {
    width: 90%;
    margin: 1.75rem auto 2rem;
    order: 6;
  }

  .explore-heading {
    gap: .75rem;
  }

  .explore-accent {
    width: 2rem;
    margin: 0 0 .75rem;
  }

  .explore-links {
    align-items: flex-start;
    text-align: left;
    grid-template-columns: 1fr;
  }

  .explore-card {
    min-height: auto;
    padding: .9rem;
  }

}
</style>
