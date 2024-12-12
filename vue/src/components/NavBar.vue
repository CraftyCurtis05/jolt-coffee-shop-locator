<!-- NavBar.vue Component -->

<template>
    <nav v-if="!$route.meta.hideNavBar">
        <div class="nav-header">

            <!-- Logo -->
            <router-link id="logo" v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''"><img src="@/assets/app/logo/jolt_logo.png"></router-link>

            <!-- Toggle Button -->
            <button class="toggle-btn" @click="isNavOpen = !isNavOpen">☰</button>
       

            <!-- Links -->
            <ul class="nav-container" :class="{ 'nav-open': isNavOpen }">
                <li><router-link class="link" v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">HOME</router-link></li>
                    <div class="separator">|</div>
                <li><router-link class="link" v-bind:to="{ name: 'locator' }" v-if="$store.state.token != ''">LOCATOR</router-link></li>
                    <div class="separator">|</div>
                <li><router-link class="link" v-bind:to="{ name: 'shop' }" v-if="$store.state.token != ''">SHOP</router-link></li>
                    <div class="separator">|</div>
                <li><router-link class="link" v-bind:to="{ name: 'articles' }" v-if="$store.state.token != ''">ARTICLES</router-link></li>
                    <div class="separator">|</div>
                <li><router-link class="link" v-bind:to="{ name: 'aboutUs' }" v-if="$store.state.token != ''">ABOUT US</router-link></li>
            </ul>

            <!-- Profile -->
            <div class="profile-container">
                <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">LOGOUT</router-link>

                <!-- Profile Picture with dynamic src -->
                 <div class="image-container">
                    <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''">
                        <!-- Fallback image used if no profile image is fetched -->
                        <img :src="imageUrl || 'src/assets/app/profile_pic.png'" alt="Profile Picture" />
                    </router-link>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
// Importing the ProfileService to fetch the profile image
import ProfileService from '../services/ProfileService.js';

export default {
    name: 'NavBar',
  data() {
    return {
        // Tracks if the navbar is open
        isNavOpen: false,
        // URL of the uploaded profile image
        imageUrl: null
    };
  },

  methods: {

    async fetchImage() {
      try {
        const imageUrl = await ProfileService.getImage();
        this.imageUrl = imageUrl;
      } catch (error) {
        console.error("Error fetching image:", error);
      }
    }
  },

  mounted() {
    this.fetchImage();
  }
};
</script>

<style scoped>
/* General Styling */
nav {
    width: 100%;
    background-color: rgb(160, 153, 145);
    margin: 0;
    padding: 0;
}

/* Nav Header */
.nav-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 4rem;
}

/* Logo Image */
#logo img {
    width: 8rem;
    height: auto;
    padding-left: 1rem;
}

/* Toggle Button */
.toggle-btn {
    display: none;
    font-size: 1.5rem;
    background: none;
    border: none;
    cursor: pointer;
    color: white;
}

/* Nav Container */
.nav-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    list-style: none;
    gap: 2rem;
    transition: max-height 0.3s ease-in-out;
    max-height: none; /* For larger screens */
    overflow: hidden; /* Hide content when collapsed */
}

/* Nav Links */
.link {
    font-family: 'Ubuntu', sans-serif;
    font-weight: bold;
    font-size: 1rem;
    color: rgb(245, 242, 242);
    text-decoration: none;
}

.separator {
    color: #333437;
}

.profile-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    gap: .5rem;
    margin-right: 1.6rem;
}

/* Profile Image */
.image-container {
  position: relative; 
  width: 3.3rem; 
  height: 3.3rem; 
  overflow: hidden; 
  border-radius: 50%;
  border: .2rem #e8bb64 solid;
}

img { 
  width: 100%; 
  height: auto;
}

a:hover {
    font-size: 1.1rem;
    font-weight: 800;
    color: #525459;
}

/* !!!!!!! REDO BELOW !!!!!!! */

/* Responsive Styles */
@media screen and (max-width: 1107px) {
    .toggle-btn {
        display: flex; /* Show toggle button */
        justify-content: center;
        text-align: center;
    }

    .nav-container {
        align-items: flex-start;
        flex-direction: column; /* Stack items */
        max-height: 0; /* Initially collapsed */
        padding-top: 4rem;
        gap: .1rem;
    }

    a:hover {
        font-weight: bold;
        font-size: 1rem;
    }

    .link {
        color: #333437;
    }

    .separator {
        visibility: hidden;
    }

    .nav-container.nav-open {
        max-height: 500px; /* Arbitrary large height to show all items */
    }

    #logo img {
        width: 6rem; /* Scale down logo */
    }

    #profile img {
        width: 2rem;
    }
}
</style>