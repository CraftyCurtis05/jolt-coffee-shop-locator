<!-- RegisterView.vue -->

<template>
  <div class="register-view">

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

    <!-- Background opacity for registration form -->
    <div class="form-container-opacity">
    </div>

    <!-- Registration Form -->
    <div class="register-form">
      <form v-on:submit.prevent="register">

        <h1>Create Account</h1>

        <!-- User Registration Information -->
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
              autocomplete="new-password"
              required
            />
          </div>

          <!-- Confirm Password -->
          <div class="form-input confirm">
            <label for="confirmPassword">Confirm Password</label>
            <input
              type="password"
              id="confirmPassword"
              v-model="user.confirmPassword"
              autocomplete="new-password"
              required
            />
          </div>

        </div>

        <!-- Registration Error Message -->
        <div class="alert-container">
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
        </div>

        <!-- Registration Button and Login Link -->
        <div class="button-container">
          <button id="create" type="submit">Create Account</button>

          <router-link id="account" v-bind:to="{ name: 'login' }">
            Already have an account? Log in.
          </router-link>
        </div>

      </form>
    </div>

  </div>
</template>

<script>
import authService from '../services/AuthService.js';

export default {
  name: 'RegisterView',

  data() {
    return {
      // Store the information entered into the registration form
      user: {
        username: '',
        password: '',
        confirmPassword: ''
      },

      // Registration error information
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },

  methods: {

    // Register a new user
    register() {

      // Check that the password and confirmation password match
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';

      } else {

        // Send the new user information to the server
        authService
        .register(this.user)
        .then((response) => {

          // *DEBUG* Log the registration response for debugging
          // console.log('Registration response:', response);

          // Send the user to the login page after successful registration
          if (response.status == 201) {
            this.$router.push({
              path: '/login',
              query: { registration: 'success' },
            });
          }
        })
        .catch((error) => {

          // *DEBUG* Log the registration error for debugging
          // console.log('Registration error:', error);

          const response = error.response;
          this.registrationErrors = true;

          // Display an error message based on the server response
          if (response && response.status === 400) {
            this.registrationErrorMsg = 'Bad Request: Validation Errors';
          } else {
            this.registrationErrorMsg = 'There were problems registering this user.';
          }
        });
      }
    },

    // Clear registration errors
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
/* Laptop L - 1440px */

.register-view {
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
  width: 18rem;
  height: 25rem;
  background-color: rgb(160, 153, 145);
  border: .2rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  margin-top: 14rem;
  opacity: .7;
  z-index: 1;
}

.register-form {
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
  align-items: center;
  font-size: 1.1rem;
  width: 18rem;
  height: 3.5rem;
  caret-color: black; /* Shows the caret inside form inputs */
}

.form-input input {
  height: 4rem;
  width: 13.5rem;
  font-size: 1.1rem;
  border: .1rem rgb(53, 37, 19) solid;
}

.password,
.confirm {
  padding-top: .5rem;
}

.alert-container {
  font-size: .9rem;
  font-weight: bold;
  color: #681c29;
  padding-top: .5rem;
}

.button-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 1rem;
}

button {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  object-fit: contain;
  background-color: rgb(53, 37, 19);
  width: 8rem;
  height: 1.8rem;
  font-size: .9rem;
  color: #ffffff;
  border: .1rem solid #e8bb64;
  border-radius: .1rem;
  transition: all 0.5s;
  -webkit-transition: all 0.5s; /* Chrome, Safari, etc. */
  -moz-transition: all 0.5s; /* Firefox */
  -o-transition: all 0.5s; /* Opera */
}

button:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1vw solid #ffffff;
}

#account {
  text-align: center;
  color: #333437;
  font-size: 1rem;
  padding-top: 1rem;
}

#account:hover {
  color: #e8bb64;
  font-weight: bold;
}
</style>