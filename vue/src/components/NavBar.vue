<!-- NavBar.vue Component -->

<template>
    <nav v-if="!$route.meta.hideNavBar">
        <article class="nav-container">

            <section class="logo-container">
                <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">
                    <img src="@/assets/app/logo/jolt_logo.png" alt="Jolt Logo" title="Click to Go to Home">
                </router-link>
            </section>    

            <section class="toggle-container">
                <button @click="isNavOpen = !isNavOpen">☰</button>
            </section>    
       

            <section class="link-container" :class="{ 'nav-open': isNavOpen }">
                <router-link class="link" v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''" title="Click to Go to Home">
                    <h3>HOME</h3>
                </router-link>

                <div class="separator">|</div>

                <router-link class="link" v-bind:to="{ name: 'locator' }" v-if="$store.state.token != ''" title="Click to Go to Locator">
                    <h3>LOCATOR</h3>
                </router-link>

                <div class="separator">|</div>
            
                <router-link class="link" v-bind:to="{ name: 'shop' }" v-if="$store.state.token != ''" title="Click to Go to Shop">
                    <h3>SHOP</h3>
                </router-link>

                <div class="separator">|</div>

                <router-link class="link" v-bind:to="{ name: 'articles' }" v-if="$store.state.token != ''" title="Click to Go to Articles">
                    <h3>ARTICLES</h3>
                </router-link>

                <div class="separator">|</div>
        
                <router-link class="link" v-bind:to="{ name: 'aboutUs' }" v-if="$store.state.token != ''" title="Click to Go to About Us">
                    <h3>ABOUT US</h3>
                </router-link>
            </section>

            <!-- Profile -->
            <section class="profile-container">
                <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" title="Click to Logout">
                    <h3>LOGOUT</h3>
                </router-link>

                <!-- Profile Picture with dynamic src -->
                 <div class="image-container">
                    <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''" title="Click to Go to Profile">
                        <!-- Fallback image used if no profile image is fetched -->
                        <img :src="imageUrl || 'src/assets/app/profile_pic.png'" alt="Profile Picture" />
                    </router-link>
                </div>
            </section>

        </article>
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
    height: 4rem;
}

.logo-container {
    display: flex;
    justify-content: flex-start;
    margin-left: 1.6rem;
}

.logo-container img {
    width: 8rem;
    height: auto;
}

.toggle-container button {
    display: none;
}

.link-container {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    gap: 1.25rem;
    margin: 0 auto;
}

/* Nav Links */
h3,
.link {
    font-weight: bold;
    font-size: 1rem;
    color: rgb(245, 242, 242);
    text-decoration: none;
    transition: all 0.3s ease-in-out;
}

h3:hover,
.link:hover {
    color: #525459;
    transform: scale(1.05);
}

.separator {
    color: #333437;
}

.profile-container {
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    gap: .5rem;
    margin-right: 1.6rem;
}

/* Profile Image */
.image-container {
    display: flex;
    justify-content: flex-end;
    width: 3.1rem; 
    height: 3.1rem; 
    overflow: hidden; 
    border-radius: 50%;
    border: .25rem #e8bb64 solid;
    transition: all 0.3s ease-in-out;
}

.image-container img { 
    width: 100%; 
    height: auto;
}

.image-container:hover {
    border: .25rem rgb(53, 37, 19) solid;
    transform: scale(1.05);
}

/* !!!!!!! REDO BELOW !!!!!!! */

/* Responsive Styles */
@media screen and (max-width: 1107px) {

    /* General Styling */
    nav {

    }

    /* Nav Header */
    .nav-container {

    }

    /* Logo Image */
    .logo-container img {
        width: 8rem;
        height: auto;
        padding-left: 1rem;
    }

    .toggle-container button {
        display: flex; /* Show toggle button */
        justify-content: flex-start;
        text-align: center;
    }

    ul {
        align-items: flex-start;
        flex-direction: column; /* Stack items */
        max-height: 0; /* Initially collapsed */
        padding-top: 4rem;
        gap: .1rem;
    }

    .link:hover {

    }
}
</style>