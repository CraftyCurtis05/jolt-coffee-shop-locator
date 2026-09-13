<!-- RegisterView.vue -->

<template>
  <div class="register-view">

    <!-- Background Video -->
    <video
      class="auth-background"
      autoplay
      muted
      loop
      playsinline
      poster="/assets/jolt-personalized-falling-beans-fallback.jpg"
      aria-hidden="true"
    >
      <source src="../assets/login/jolt-personalized-falling-beans.webm" type="video/webm">
      <source src="../assets/login/jolt-personalized-falling-beans.mp4" type="video/mp4">
    </video>

    <!-- Jolt Logo -->
    <img
      class="jolt-logo"
      src="@/assets/app/logo/jolt_logo.webp"
      alt="Jolt logo"
    />

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
                maxlength="50"
                required
                autofocus
              />

              <p class="input-hint">
                Username can be up to 50 characters.
              </p>
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

            <p class="input-hint">
              Choose a password you'll remember.
            </p>
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
          <button
            id="create"
            type="submit"
            :disabled="isRegistering"
            :title="isRegistering ? 'Creating Account' : 'Click to Create Account'"
          >
            {{ isRegistering ? 'Creating Account...' : 'Create Account' }}
          </button>

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
      registrationErrorMsg: 'There was a problem creating your account.',

      // Track whether the registration request is being processed
      isRegistering: false,
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

        // Show that the registration request is being processed
        this.isRegistering = true;

        // Send the new user information to the server
        authService
        .register(this.user)
        .then((response) => {

          // Send the user to the login page after successful registration
          if (response.status == 201) {
            this.$router.push({
              path: '/login',
              query: { registration: 'success' },
            });
          }
        })
        .catch((error) => {

          const response = error.response;
          this.registrationErrors = true;

          // Display an error message based on the server response
          if (response && response.status === 400) {
            this.registrationErrorMsg =
              'Please check your account information and try again.';
          } else {
            this.registrationErrorMsg =
              'There was a problem creating your account. Please try again.';
          }
        })
        .finally(() => {
          // Allow another registration attempt if needed
          this.isRegistering = false;
        });
      }
    },

    // Clear registration errors
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There was a problem creating your account.';
    },
  },
};
</script>

<style scoped>
/* Laptop L - 1440px */

.register-view {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  width: 100%;
  min-height: 100%;
  overflow-x: hidden;
  font-family: 'Ubuntu', sans-serif;
  padding: 0;
}

/* Login and registration background video */
.background-video {
  position: fixed;
  inset: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  pointer-events: none; /* Prevents the video from blocking page clicks */
  z-index: -1;
}

.jolt-logo {
  width: clamp(12rem, 24vw, 20rem);
  height: auto;
  margin-bottom: -.75rem;
  z-index: 100;
}

.register-form {
  position: relative;
  width: min(90%, 24rem);
  background-color: rgba(160, 153, 145, .82);
  border: .15rem rgb(53, 37, 19) solid;
  border-radius: .25rem;
  box-shadow: 0 .4rem 1rem rgba(0, 0, 0, .3);
  padding: 1.5rem;
  z-index: 10;
}

.register-form form {
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
  caret-color: black;
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

.input-hint {
  width: 100%;
  font-size: .7rem;
  line-height: 1.3;
  color: #3f4144;
  text-align: left;
  margin: .25rem 0 0;
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

.button-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: .5rem;
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

button:hover:not(:disabled) {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1vw solid #ffffff;
}

button:disabled {
  cursor: wait;
  opacity: .75;
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


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .register-view {
    justify-content: flex-start;
    padding: 2rem 1rem;
  }

  .jolt-logo {
    width: 13rem;
    margin-top: 1rem;
    margin-bottom: -.5rem;
  }

  .register-form {
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

  #account {
    font-size: .9rem;
  }

}


@media (prefers-reduced-motion: reduce) {
  .auth-background {
    display: none;
  }

  body {
    background:
      #1a0e08
      url("/assets/login/jolt-personalized-falling-beans-fallback.jpg")
      center / cover no-repeat fixed;
  }
}
</style>
