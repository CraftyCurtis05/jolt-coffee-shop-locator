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
          :aria-expanded="isNavOpen"
          aria-label="Toggle navigation menu"
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
          :class="{
            'nav-open': isNavOpen,
            'profile-active': $route.name === 'profile'
          }"
        >
          <router-link
            v-bind:to="{ name: 'profile' }"
            v-if="$store.state.token != ''"
            aria-label="Go to your profile"
            title="Click to Go to Profile"
          >
            <img
              :src="imageUrl || defaultProfileImage"
              alt=""
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

      } catch (error) {

        // Use the default profile image if the user does not have a saved image
        this.imageUrl = null;

      }
    },

    // Update the profile image after the user changes it
    handleProfileImageUpdate() {
      this.fetchImage();
    }
  },

  mounted() {

    // Update the navigation profile image when it changes
    window.addEventListener(
      'profile-image-updated',
      this.handleProfileImageUpdate
    );

    // Watch for route changes throughout the application
    this.$router.afterEach(() => {

      // Close the mobile navigation menu after changing pages
      this.isNavOpen = false;

      // Get the profile image if the user is logged in
      if (this.$store.state.token) {
        this.fetchImage();
      }
    });
  },

  beforeUnmount() {

    // Remove the profile image update listener
    window.removeEventListener(
      'profile-image-updated',
      this.handleProfileImageUpdate
    );
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
  min-width: 0;
  min-height: 4rem;
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
  display: block;
  width: 8rem;
  max-width: 100%;
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
  flex: 1;
  min-width: 0;
  gap: 1.25rem;
  margin: 0 .5rem;
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
  white-space: nowrap;
  transition: all 0.3s ease-in-out;
}

h3:hover,
.link:hover {
  color: #525459;
  transform: scale(1.03);
}

.link.router-link-active h3 {
  color: #525459;
  border-bottom: .15rem #e8bb64 solid;
}

.separator {
  color: #333437;
}

.mobile-logout {
  display: none;
}

#logout {
  min-height: 2.75rem;
  padding: .5rem .5rem;
}

.nav-profile-container {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  flex-shrink: 0;
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

.image-container a:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
  border-radius: 50%;
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

.image-container:focus-within {
  border: .25rem rgb(53, 37, 19) solid;
  transform: scale(1.05);
}

.image-container.profile-active {
  border: .25rem rgb(53, 37, 19) solid;
}

/* Laptop - 900px */
@media screen and (max-width: 900px) {

  .nav-container {
    padding: 0 .75rem;
  }

  .logo {
    width: 7rem;
  }

  .link-container {
    gap: .75rem;
    margin: 0 .25rem;
  }

  h3,
  .link {
    font-size: .9rem;
  }

}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .nav-container {
    justify-content: space-between;
    padding: 0 .75rem;
  }

  .toggle-container {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }

  .toggle-container button {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 2.75rem;
    height: 2.75rem;
    background-color: transparent;
    font-size: 2rem;
    line-height: 1;
    color: rgb(245, 242, 242);
    border: none;
    padding: 0;
    margin: 0;
    transition: all 0.3s ease-in-out;
  }

  .toggle-container button:hover {
    color: #525459;
    transform: scale(1.05);
  }

  .link-container {
    display: none;
    position: absolute;
    flex-direction: column;
    align-items: center;
    gap: .5rem;
    width: 10rem;
    background-color: rgb(160, 153, 145);
    top: 4rem;
    left: 50%;
    transform: translateX(-50%);
    padding: 1rem;
    margin: 0;
    z-index: 3;
  }

  .link-container .link {
    width: 100%;
    min-height: 2.75rem;
    padding: .5rem .75rem;
  }

  .link-container.nav-open {
    display: flex;
  }

  .separator {
    display: none;
  }
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .logo {
    display: none;
  }

  .logo-responsive {
    display: block;
  }

  .nav-profile-container #logout {
    display: none;
  }

  .mobile-logout {
    display: flex;
  }

}
</style>
