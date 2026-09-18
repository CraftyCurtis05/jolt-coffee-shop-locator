<!-- RegisterView.vue -->

<template>
  <div class="register-view">

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
              minlength="3"
              maxlength="30"
              pattern="[A-Za-z0-9._\-]+"
              title="Username must be 3 to 30 characters and can only contain letters, numbers, periods, underscores and hyphens"
              required
              autofocus
            />

            <p class="input-hint">
              3–30 characters using letters, numbers, periods, underscores or hyphens.
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
              minlength="8"
              maxlength="72"
              required
            />

            <p class="input-hint">
              Password must be 8–72 characters.
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
                minlength="8"
                maxlength="72"
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

          <router-link
            :to="{ name: 'login' }"
            class="register-link"
          >
            Already have an account? Sign in.
          </router-link>
        </div>

      </form>
    </div>

  </div>
</template>

<script>
import authService from '../services/AuthService.js';
import fallbackImage from '../assets/login/jolt-personalized-falling-beans-fallback.jpg';

export default {
  name: 'RegisterView',

  data() {
    return {
      // Background image used while the video is loading
      fallbackImage,

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

      // Check that the username follows the required format
      const usernamePattern = /^[A-Za-z0-9._-]+$/;

      if (
        this.user.username.length < 3 ||
        this.user.username.length > 30 ||
        !usernamePattern.test(this.user.username)
      ) {
        this.registrationErrors = true;
        this.registrationErrorMsg =
          'Username must be 3–30 characters and can only contain letters, numbers, periods, underscores and hyphens.';
        return;
      }

      // Check that the password follows the required length
      if (
        this.user.password.length < 8 ||
        this.user.password.length > 72
      ) {
        this.registrationErrors = true;
        this.registrationErrorMsg =
          'Password must be between 8 and 72 characters.';
        return;
      }

      // Check that the password and confirmation password match
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Passwords do not match.';

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
          if (
            response &&
            response.status === 400 &&
            response.data.message === 'Username is already taken.'
          ) {
            this.registrationErrorMsg =
              'Username is already taken. Please choose a different username.';

          } else if (response && response.status === 400) {
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
    }
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.register-view {
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


/* Registration Form */
.register-form {
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

.register-form form {
  width: 100%;
}

h1 {
  text-align: center;
  font-size: 1.3rem;
  padding: .5rem;
}


/* User Registration Information */
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


/* Registration Input Hints */
.input-hint {
  width: 100%;
  font-size: .7rem;
  line-height: 1.3;
  color: #3f4144;
  text-align: left;
  margin: .2rem 0 0;
}


/* Registration Error Message */
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


/* Registration Button and Login Link */
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

  .register-form {
    width: 21rem;
  }

}


/* Small Tablet - 700px */
@media screen and (max-width: 700px) {

  .jolt-logo {
    width: 18rem;
  }

  .register-form {
    width: 20rem;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .register-view {
    padding: .5rem;
  }

  .jolt-logo {
    width: 17rem;
    max-width: 80%;
    margin-bottom: -.5rem;
  }

  .register-form {
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

  .register-form {
    max-width: 96%;
    padding: .75rem;
  }

}


/* Short Screens */
@media screen and (max-height: 750px) {

  .register-view {
    padding: .35rem;
  }

  .jolt-logo {
    width: 16rem;
    margin-bottom: -.5rem;
  }

  .register-form {
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

  .register-form {
    max-height: 84vh;
    max-height: 84dvh;
  }

}


/* Reduced Motion */
@media (prefers-reduced-motion: reduce) {

  .background-video {
    display: none;
  }

  .register-view {
    background:
      #1a0e08
      url("@/assets/login/jolt-personalized-falling-beans-fallback.jpg")
      center / cover no-repeat;
  }

}
</style>
