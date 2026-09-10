<!-- NavBar.vue Component -->

<template>
  <nav v-if="!$route.meta.hideNavBar">

    <!-- Navigation Container -->
    <article class="nav-container">

      <!-- Jolt Logo -->
      <section class="logo-container">
        <router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          title="Click to Go to Home"
        >
          <img
            :src="logo"
            class="logo"
            alt="Jolt logo"
          />

          <img
            :src="logoResponsive"
            class="logo-responsive"
            alt="Jolt logo"
          />
        </router-link>
      </section>

      <!-- Mobile Navigation Toggle -->
      <section class="toggle-container">
        <button
          type="button"
          @click="isNavOpen = !isNavOpen"
          v-if="$store.state.token != ''"
          title="Open Navigation Menu"
        >
          ☰
        </button>
      </section>

      <!-- Navigation Links -->
      <section
        class="link-container"
        :class="{ 'nav-open': isNavOpen }"
      >

        <!-- Home -->
        <router-link
          class="link"
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          title="Click to Go to Home"
        >
          <h3 class="open">HOME</h3>
        </router-link>

        <div class="separator">|</div>

        <!-- Locator -->
        <router-link
          class="link"
          v-bind:to="{ name: 'locator' }"
          v-if="$store.state.token != ''"
          title="Click to Go to Locator"
        >
          <h3 class="open">LOCATOR</h3>
        </router-link>

        <div class="separator">|</div>

        <!-- Shop -->
        <router-link
          class="link"
          v-bind:to="{ name: 'shop' }"
          v-if="$store.state.token != ''"
          title="Click to Go to Shop"
        >
          <h3 class="open">SHOP</h3>
        </router-link>

        <div class="separator">|</div>

        <!-- Articles -->
        <router-link
          class="link"
          v-bind:to="{ name: 'articles' }"
          v-if="$store.state.token != ''"
          title="Click to Go to Articles"
        >
          <h3 class="open">ARTICLES</h3>
        </router-link>

        <div class="separator">|</div>

        <!-- About Us -->
        <router-link
          class="link"
          v-bind:to="{ name: 'aboutUs' }"
          v-if="$store.state.token != ''"
          title="Click to Go to About Us"
        >
          <h3 class="open">ABOUT US</h3>
        </router-link>

        <!-- Mobile Logout -->
        <router-link
          class="link mobile-logout"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          title="Click to Logout"
        >
          <h3 class="open">LOGOUT</h3>
        </router-link>
      </section>

      <!-- User Profile -->
      <section
        class="nav-profile-container"
        :class="{ 'nav-open': isNavOpen }"
      >

        <!-- Logout -->
        <router-link
          class="link"
          id="logout"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          title="Click to Logout"
        >
          <h3>LOGOUT</h3>
        </router-link>

        <!-- Profile Picture -->
        <div
          class="image-container"
          :class="{ 'nav-open': isNavOpen }"
        >
          <router-link
            v-bind:to="{ name: 'profile' }"
            v-if="$store.state.token != ''"
            title="Click to Go to Profile"
          >
            <img
              :src="imageUrl || defaultProfileImage"
              alt="Profile Picture"
            />
          </router-link>
        </div>

      </section>

    </article>

  </nav>
</template>

<script>
import ProfileService from '../../services/ProfileService.js';

import joltLogo from '../../assets/app/logo/jolt_logo.webp';
import responsiveLogo from '../../assets/app/logo/logo_responsive.webp';
import defaultProfileImage from '../../assets/profile/profile_pic.webp';

export default {
  name: 'NavBar',

  data() {
    return {
      // Track whether the mobile navigation menu is open
      isNavOpen: false,

      // Store the user's profile image
      imageUrl: null,

      // Navigation images
      logo: joltLogo,
      logoResponsive: responsiveLogo,
      defaultProfileImage: defaultProfileImage
    };
  },

  methods: {

    // Get the user's saved profile image
    async fetchImage() {
      try {
        const imageUrl = await ProfileService.getImage();
        this.imageUrl = imageUrl;

        // *DEBUG* Log the profile image for debugging
        // console.log('Profile image:', imageUrl);

      } catch (error) {

        // Use the default profile image if the user does not have a saved image
        this.imageUrl = null;

        // *DEBUG* Log the profile image error for debugging
        // console.log('Profile image not found:', error);
      }
    }
  },

  mounted() {

    // Watch for route changes throughout the application
    this.$router.afterEach(() => {

      // Close the mobile navigation menu after changing pages
      this.isNavOpen = false;

      // Get the profile image if the user is logged in
      if (this.$store.state.token) {
        this.fetchImage();
      }
    });
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

@font-face {
  font-family: 'Vonique 43';
  src: url('@/assets/app/fonts/vonique_43/Vonique 43_D.otf') format('truetype');
  font-weight: normal;
  font-style: normal;
}

nav {
  width: 100%;
  background-color: rgb(160, 153, 145);
  padding: 0;
  margin: 0;
}

.nav-container {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: center;
  position: relative;
  width: 100%;
  height: 4rem;
  padding: 0 1rem;
  margin: 0;
}

.logo-container {
  display: flex;
  justify-content: flex-start;
  flex-shrink: 0;
  margin: 0;
}

.logo {
  width: 8rem;
  height: auto;
}

.logo-responsive {
  display: none;
  width: 3.5rem;
  margin: 0;
  padding: 0;
}

.toggle-container button {
  display: none; /* Initially hidden for larger screens */
}

.link-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  position: absolute;
  gap: 1.25rem;
  left: 50%;
  transform: translateX(-50%);
  margin: 0;
}

.mobile-logout {
  display: none;
}

h3,
.link {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'Vonique 43', sans-serif;
  font-weight: bolder;
  font-size: 1rem;
  color: rgb(245, 242, 242);
  text-decoration: none;
  transition: all 0.3s ease-in-out;
}

h3:hover,
.link:hover {
  color: #525459;
  transform: scale(1.03);
}

.separator {
  color: #333437;
}

.nav-profile-container {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  gap: .5rem;
  margin-left: auto;
  position: relative;
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  width: 3.1rem;
  height: 3.1rem;
  overflow: hidden;
  border-radius: 50%;
  border: .25rem #e8bb64 solid;
  transition: all 0.3s ease-in-out;
}

.image-container a {
  display: block;
  width: 100%;
  height: 100%;
}

.image-container img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.image-container:hover {
  border: .25rem rgb(53, 37, 19) solid;
  transform: scale(1.05);
  filter: grayscale(70%);
}
</style>