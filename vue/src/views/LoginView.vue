<!-- LoginView.vue -->

<template>
  <div class="login-view">

    <!-- Background Video -->
    <video
      class="background-video"
      autoplay
      muted
      loop
      playsinline
    >
      <source
        src="@/assets/login/beans-coffee.webm"
        type="video/webm"
      />

      <source
        src="@/assets/login/beans-coffee.mp4"
        type="video/mp4"
      />
    </video>

    <!-- Jolt Logo -->
    <img
      class="jolt-logo"
      src="@/assets/app/logo/jolt_logo.webp"
      alt="Jolt logo"
    />

    <!-- Background opacity for login form -->
    <div class="form-container-opacity">
    </div>

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
            v-if="this.$route.query.registration"
            id="alert1"
          >
            Thank you for registering!<br>Please sign in.
          </div>

          <!-- Invalid Login Message -->
          <div
            role="alert"
            v-if="invalidCredentials"
            id="alert2"
          >
            Invalid username and password!
          </div>

        </div>

        <!-- Login and Registration Buttons -->
        <div class="button-container">
          <button id="sign-in" type="submit">Sign in</button>

          <router-link
            id="register"
            v-bind:to="{ name: 'register' }"
          >
            Register
          </router-link>
        </div>

      </form>
    </div>

  </div>
</template>

<script>
import authService from '../services/AuthService.js';

export default {
  name: 'LoginView',

  data() {
    return {
      // Store the information entered into the login form
      user: {
        username: '',
        password: ''
      },

      // Track invalid login information
      invalidCredentials: false
    };
  },

  methods: {

    // Log in an existing user
    login() {

      // Clear previous login errors before trying again
      this.invalidCredentials = false;

      // Send the user's login information to the server
      authService
      .login(this.user)
      .then((response) => {

        // Save the authentication information after successful login
        if (response.status == 200) {
          this.$store.commit('SET_AUTH_TOKEN', response.data.token);
          this.$store.commit('SET_USER', response.data.user);

          // Send the user to the home page
          this.$router.push('/');
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
  align-items: center;
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  z-index: 0;
  caret-color: transparent; /* Hides the caret */
}

/* Login and registration background video */
.background-video {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  object-fit: cover;
  pointer-events: none; /* Prevents the video from blocking page clicks */
  z-index: 0;
}

.jolt-logo {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20rem;
  height: auto;
  padding-top: 15vh;
  z-index: 10;
}

.form-container-opacity {
  position: absolute;
  width: 16rem;
  height: 20rem;
  background-color: rgb(160, 153, 145);
  border: .2rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  margin-top: 14rem;
  opacity: .7;
  z-index: 1;
}

.login-form {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  padding-top: .2rem;
  z-index: 10;
}

h1 {
  text-align: center;
  font-size: 1.4rem;
  padding-bottom: .8rem;
}

.form-input {
  display: flex;
  flex-direction: column;
  align-items: start;
  font-size: 1.1rem;
  width: 14rem;
  height: 3.5rem;
  caret-color: black; /* Shows the caret inside form inputs */
}

.form-input input {
  height: 4rem;
  width: 13.5rem;
  font-size: 1.1rem;
  border: .1rem rgb(53, 37, 19) solid;
}

.password {
  padding-top: .5rem;
}

.alert-container {
  text-align: center;
  font-size: .9rem;
  font-weight: bold;
  color: #681c29;
  padding-top: .5rem;
}

#alert1 {
  font-weight: 500;
  color: rgb(234,189,99);
}

.button-container {
  position: absolute;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 14rem;
  padding-top: 1rem;
}

button,
#register {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  object-fit: contain;
  background-color: rgb(53, 37, 19);
  width: 5rem;
  height: 1.7rem;
  font-size: .9rem;
  color: #ffffff;
  border: .1rem solid #e8bb64;
  border-radius: .1rem;
  transition: all 0.5s;
  -webkit-transition: all 0.5s; /* Chrome, Safari, etc. */
  -moz-transition: all 0.5s; /* Firefox */
  -o-transition: all 0.5s; /* Opera */
}

button:hover,
#register:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1rem solid #ffffff;
}

#sign-in {
  margin-right: 2rem;
}

#register {
  text-decoration: none;
}
</style>
