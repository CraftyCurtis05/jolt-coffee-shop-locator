<!-- LoginView.vue -->

<template>
  <div class="login-view">

    <!-- Background Video -->
    <div class="background-video-container">
      <video
        class="background-video"
        autoplay
        muted
        loop
        playsinline
        :poster="fallbackImage"
        aria-hidden="true"
      >
        <source
          src="@/assets/login/jolt-personalized-falling-beans.webm"
          type="video/webm"
        />

        <source
          src="@/assets/login/jolt-personalized-falling-beans.mp4"
          type="video/mp4"
        />

      </video>
    </div>

    <!-- Jolt Logo -->
    <img
      class="jolt-logo"
      src="@/assets/app/logo/jolt_logo.webp"
      alt="Jolt logo"
    />

    <!-- Login Form -->
    <div class="login-form">
      <form v-on:submit.prevent="login">

        <h1>Please Sign In</h1>

        <!-- User Login Information -->
        <div class="form-input-container">

          <!-- Username -->
          <div class="form-input username">
            <label for="username">Username</label>
            <input
              type="text"
              id="username"
              v-model="user.username"
              autocomplete="username"
              required
              autofocus
            />
          </div>

          <!-- Password -->
          <div class="form-input password">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              v-model="user.password"
              autocomplete="current-password"
              required
            />
          </div>

        </div>

        <!-- Login Messages -->
        <div class="alert-container">

          <!-- Successful Registration Message -->
          <div
            role="alert"
            v-if="$route.query.registration"
            id="alert1"
          >
            Your account has been created.<br>Please sign in.
          </div>

          <!-- Invalid Login Message -->
          <p
            v-if="invalidCredentials"
            role="alert"
          >
            Invalid username or password.
          </p>

        </div>

        <!-- Demo Account Information -->
        <div class="demo-account">
          <p class="demo-title">Demo Account</p>

          <p>
            Want to explore Jolt without creating an account?
          </p>

          <!-- Demo Account Button -->
          <button
            type="button"
            class="demo-button"
            @click="useDemoAccount"
            :disabled="isLoggingIn"
          >
            {{ isLoggingIn && loginType === 'demo'
              ? 'Signing in...'
              : 'Use Demo Account'
            }}
          </button>

          <p class="demo-server-message">
            <template v-if="isLoggingIn">
              <strong>Thanks for your patience!</strong>
              Jolt is signing you in. The demo server may take a moment to wake up.
            </template>

            <template v-else>
              <strong>Please Note:</strong>
              Jolt uses a free-tier demo server, so the first sign-in may take
              1–2 minutes while the server wakes up after being inactive.
            </template>
          </p>
        </div>

        <!-- Sign in and Registration Buttons -->
        <div class="button-container">

          <button
            type="submit"
            :disabled="isLoggingIn"
          >
            {{ isLoggingIn && loginType === 'regular'
              ? 'Signing in...'
              : 'Sign In'
            }}
          </button>

          <RouterLink
            :to="{ name: 'register' }"
            class="register-link"
          >
            Don't have an account? Create one.
          </RouterLink>
        </div>

      </form>
    </div>

  </div>
</template>

<script>
import authService from '../services/AuthService.js';
import ProfileService from '../services/ProfileService.js';
import FavoriteService from '../services/FavoriteService.js';

import fallbackImage from '../assets/login/jolt-personalized-falling-beans-fallback.jpg';

export default {
  name: 'LoginView',

  data() {
    return {
      // Background image used while the video is loading
      fallbackImage,

      // Store the information entered into the login form
      user: {
        username: '',
        password: ''
      },

      // Track whether the login request is being processed
      isLoggingIn: false,

      // Track which login button started the request
      loginType: null,

      // Track invalid login information
      invalidCredentials: false
    };
  },

  methods: {

    // Fill in the login form with the demo account
    useDemoAccount() {
      this.user.username = 'joltdemo';
      this.user.password = 'DemoPass123';
      this.loginType = 'demo';

      this.login();
    },

    // Log in an existing user
    login() {

      // Track a regular login unless the demo account started the request
      if (this.loginType !== 'demo') {
        this.loginType = 'regular';
      }

      // Show that the login request is being processed
      this.isLoggingIn = true;

      // Clear previous login errors before trying again
      this.invalidCredentials = false;

      // Send the user's login information to the server
      authService
        .login(this.user)
        .then((response) => {

          // Save the authentication information after successful login
          if (response.status === 200) {
            this.$store.commit('SET_AUTH_TOKEN', response.data.token);
            this.$store.commit('SET_USER', response.data.user);

            // Send the user to the home page
            this.$router.push('/');

            // Get the logged-in user's profile
            ProfileService.getProfile()
              .then((profile) => {

                // Store the profile for use throughout the application
                this.$store.commit('SET_PROFILE_STATUS', true);
                this.$store.commit('SET_PROFILE', profile);
              })
              .catch(() => {

                // Store that the user does not have a profile
                this.$store.commit('SET_PROFILE_STATUS', false);
                this.$store.commit('SET_PROFILE', null);
              });

            // Get the logged-in user's saved profile image
            ProfileService.getImage()
              .then((imageUrl) => {

                // Store the profile image for use throughout the application
                this.$store.commit('SET_PROFILE_IMAGE', imageUrl);
              })
              .catch(() => {

                // Use the default profile image if the user does not have one
                this.$store.commit('SET_PROFILE_IMAGE', null);
              });

            // Get the logged-in user's saved favorite coffee shops
            FavoriteService.getFavorites()
              .then((favorites) => {

                // Store the favorites for use throughout the application
                this.$store.commit('SET_FAVORITES', favorites || []);
              })
              .catch(() => {

                // Use an empty list if the user does not have any favorites
                this.$store.commit('SET_FAVORITES', []);
              });
          }
        })
        .catch((error) => {

          const response = error.response;

          // Display a message for an incorrect username or password
          if (response && response.status === 401) {
            this.invalidCredentials = true;

          } else {
            window.dispatchEvent(new CustomEvent('app-notification', {
              detail: {
                message: 'There was a problem signing in. Please try again.',
                type: 'error'
              }
            }));
          }
        })
        .finally(() => {
          // Allow another login attempt if needed
          this.isLoggingIn = false;
          this.loginType = null;
        });
    }
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.login-view {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  width: 100%;
  height: 100vh;
  height: 100dvh;
  overflow: hidden;
  box-sizing: border-box;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding: 1rem;
  z-index: 0;
  caret-color: transparent;
}


/* Login and registration background video */
.background-video-container {
  position: fixed;
  inset: 0;
  overflow: hidden; /* Clips the part of the video outside the screen */
  background-color: #1a0e08;
  pointer-events: none; /* Prevents the video from blocking page clicks */
  z-index: 0;
}

.background-video {
  position: absolute;
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* Fills the screen without stretching the video */
  object-position: right center; /* Keeps the right side and crops from the left */
}


/* Jolt Logo */
.jolt-logo {
  width: 24rem;
  max-width: 70%;
  height: auto;
  margin-bottom: -1rem;
  z-index: 100;
}


/* Login Form */
.login-form {
  position: relative;
  width: 22rem;
  max-width: 90%;
  max-height: 75vh;
  overflow-y: auto;
  background-color: rgba(160, 153, 145, .82);
  border: .15rem rgb(53, 37, 19) solid;
  border-radius: .25rem;
  box-shadow: 0 .4rem 1rem rgba(0, 0, 0, .3);
  padding: 1.25rem;
  z-index: 10;
}

.login-form form {
  width: 100%;
}

h1 {
  text-align: center;
  font-size: 1.3rem;
  padding: .5rem;
}


/* User Login Information */
.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  font-size: .95rem;
  margin-bottom: .6rem;
  caret-color: black; /* Shows the caret inside form inputs */
}

.form-input input {
  width: 100%;
  min-height: 2.5rem;
  font-size: 1rem;
  background-color: #ffffff;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .55rem;
}

.form-input label {
  width: 100%;
  text-align: left;
  font-weight: 500;
  margin-bottom: .2rem;
}


/* Login Messages */
#alert1 {
  font-weight: 500;
  color: rgb(234, 189, 99);
}

.alert-container {
  width: 100%;
  font-size: .8rem;
  font-weight: 600;
  line-height: 1.35;
  text-align: center;
  color: #681c29;
  padding-top: .4rem;
  margin-top: .2rem;
}

.alert-container p {
  margin: 0;
}


/* Demo Account */
.demo-account {
  width: 100%;
  background-color: rgba(232, 187, 100, .25);
  border: .1rem solid rgba(53, 37, 19, .6);
  border-radius: .2rem;
  text-align: center;
  color: rgb(35, 24, 13);
  margin: auto;
  margin-top: .5rem;
  padding: .5rem;
}

.demo-title {
  font-size: .9rem;
  font-weight: 600;
  margin: .5rem;
}

.demo-account p {
  margin: .5rem auto;
}

.demo-button {
  min-width: 8.5rem;
  min-height: 2.25rem;
  font-size: .8rem;
  padding: .3rem .7rem;
  margin: .5rem auto;
}

.demo-server-message {
  font-size: .75rem;
  line-height: 1.35;
  color: rgb(35, 24, 13);
  padding-top: .1rem;
}


/* Login and Register Buttons */
.button-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin-top: 1rem;
  padding-top: .4rem;
}

button {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 7.5rem;
  min-height: 2.5rem;
  background-color: rgb(53, 37, 19);
  font-size: .9rem;
  font-weight: 600;
  color: #ffffff;
  border: .1rem solid #e8bb64;
  border-radius: .2rem;
  padding: .4rem .9rem;
  transition:
    background-color .2s ease-in-out,
    color .2s ease-in-out,
    border-color .2s ease-in-out,
    transform .2s ease-in-out;
}

button:hover:not(:disabled) {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  border-color: rgb(53, 37, 19);
  transform: translateY(-.1rem);
}

button:disabled {
  cursor: wait;
  opacity: .75;
  transform: none;
}

.register-link {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.5rem;
  font-size: .9rem;
  color: #333437;
  text-decoration: underline;
  text-underline-offset: .15rem;
  padding: .35rem .25rem;
  margin-top: .3rem;
}

.register-link:hover {
  color: #e8bb64;
  font-weight: 500;
}

.register-link:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}


/* Laptop - 1200px */
@media screen and (max-width: 1200px) {

  .jolt-logo {
    width: 21rem;
  }

}


/* Tablet - 900px */
@media screen and (max-width: 900px) {

  .jolt-logo {
    width: 19rem;
  }

  .login-form {
    width: 21rem;
  }

}


/* Small Tablet - 700px */
@media screen and (max-width: 700px) {

  .jolt-logo {
    width: 18rem;
  }

  .login-form {
    width: 20rem;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .login-view {
    padding: .5rem;
  }

  .jolt-logo {
    width: 17rem;
    max-width: 80%;
    margin-bottom: -.5rem;
  }

  .login-form {
    width: 20rem;
    max-width: 94%;
    padding: 1rem;
  }

  h1 {
    font-size: 1.2rem;
  }

  .form-input {
    font-size: .9rem;
  }

}


/* Small Mobile - 375px */
@media screen and (max-width: 375px) {

  .jolt-logo {
    width: 15rem;
  }

  .login-form {
    max-width: 96%;
    padding: .75rem;
  }

}


/* Short Screens */
@media screen and (max-height: 750px) {

  .login-view {
    padding: .35rem;
  }

  .jolt-logo {
    width: 16rem;
    margin-bottom: -.5rem;
  }

  .login-form {
    max-height: 80vh;
    max-height: 80dvh;
    padding: .75rem 1rem;
  }

  h1 {
    padding: .35rem;
  }

  .form-input {
    margin-bottom: .35rem;
  }

  .demo-account {
    padding: .3rem .45rem;
  }

  .demo-button {
    margin: .3rem auto;
  }

  .button-container {
    margin-top: .5rem;
    padding-top: .2rem;
  }

  .register-link {
    margin-top: .2rem;
  }

}


/* Very Short Screens */
@media screen and (max-height: 600px) {

  .jolt-logo {
    width: 14rem;
  }

  .login-form {
    max-height: 84vh;
    max-height: 84dvh;
  }

}


/* Reduced Motion */
@media (prefers-reduced-motion: reduce) {

  .background-video {
    display: none;
  }

  .login-view {
    background:
      #1a0e08
      url("@/assets/login/jolt-personalized-falling-beans-fallback.jpg")
      center / cover no-repeat;
  }

}
</style>
