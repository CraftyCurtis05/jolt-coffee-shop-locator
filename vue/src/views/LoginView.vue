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
          <p
            v-if="invalidCredentials"
            class="alert-container"
          >
            Invalid username or password.
          </p>

        </div>

        <!-- Login and Registration Buttons -->
        <div class="button-container">
          <button
            type="submit"
            :disabled="isLoggingIn"
            :title="isLoggingIn ? 'Signing In' : 'Click to Sign In'"
          >
            {{ isLoggingIn ? 'Signing in...' : 'Sign In' }}
          </button>

          <router-link
            :to="{ name: 'register' }"
            class="register-link"
          >
            Don't have an account? Register.
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

      // Track whether the login request is being processed
      isLoggingIn: false,

      // Track invalid login information
      invalidCredentials: false
    };
  },

  methods: {

    // Log in an existing user
    login() {
      // Show that the login request is being processed
      this.isLoggingIn = true;

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
      })
      .finally(() => {
        // Allow another login attempt if needed
        this.isLoggingIn = false;
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
  min-height: 100vh;
  overflow-x: hidden;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding: 2rem 1rem;
  z-index: 0;
  caret-color: transparent;
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
  width: clamp(12rem, 24vw, 20rem);
  height: auto;
  margin-bottom: -.75rem;
  z-index: 100;
}

.login-form {
  position: relative;
  width: min(90%, 24rem);
  background-color: rgba(160, 153, 145, .82);
  border: .15rem rgb(53, 37, 19) solid;
  border-radius: .25rem;
  box-shadow: 0 .4rem 1rem rgba(0, 0, 0, .3);
  padding: 1.5rem;
  z-index: 10;
}

.login-form form {
  width: 100%;
}

h1 {
  text-align: center;
  font-size: 1.4rem;
  padding-bottom: .8rem;
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  font-size: 1rem;
  margin-bottom: .9rem;
  caret-color: black; /* Shows the caret inside form inputs */
}

.form-input input {
  width: 100%;
  min-height: 2.5rem;
  font-size: 1rem;
  background-color: #ffffff;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .45rem .6rem;
}

.form-input label {
  width: 100%;
  text-align: left;
  font-weight: 500;
  margin-bottom: .25rem;
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding-top: .5rem;
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

button:hover:not(:disabled),
#register:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1rem solid #ffffff;
}

button:active:not(:disabled) {

}

button:disabled {
  cursor: wait;
  opacity: .75;
  transform: none;
}

#sign-in {
  margin-right: 2rem;
}

#register {
  text-decoration: none;
}

.register-link {
  font-size: .9rem;
  color: #333437;
  text-decoration: underline;
  text-underline-offset: .15rem;
  margin-top: 1rem;
}

.register-link:hover {
  color: rgb(53, 37, 19);
}

.register-link:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}

.alert-container {
  width: 100%;
  font-size: .8rem;
  font-weight: 600;
  line-height: 1.35;
  text-align: center;
  color: #681c29;
  margin-top: .25rem;
}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .login-view {
    justify-content: flex-start;
    padding: 2rem 1rem;
  }

  .jolt-logo {
    width: 13rem;
    margin-top: 1rem;
    margin-bottom: -.5rem;
  }

  .login-form {
    width: 100%;
    max-width: 22rem;
    padding: 1.25rem;
  }

  h1 {
    font-size: 1.25rem;
  }

  .form-input {
    font-size: .9rem;
  }

  .form-input input {
    min-height: 2.75rem;
    font-size: 1rem;
  }

}
</style>
