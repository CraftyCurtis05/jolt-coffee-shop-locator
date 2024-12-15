<!-- RegisterView.vue -->

<template>
  <body>
    <img src="@/assets/app/logo/jolt_logo.png" alt="logo"/>
    <div class="form-container-opacity">
    </div>
    <div class="register-form">
      <form v-on:submit.prevent="register">
        <h1>Create Account</h1>
        <div class="form-input-container">
          <div class="form-input username">
            <label for="username">Username</label>
            <input type="text" v-model="user.username" required autofocus/>
          </div>
          <div class="form-input password">
            <label for="password">Password</label>
            <input type="password" v-model="user.password" required/>
          </div>
          <div class="form-input confirm">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" v-model="user.confirmPassword" required/>
          </div>
        </div>
        <div class="alert-container">
          <div role="alert" v-if="registrationErrors">{{ registrationErrorMsg }}</div>
        </div>
        <div class="button-container">
          <button id="create" type="submit">Create Account</button>
          <router-link id="account" v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import authService from '../services/AuthService.js';

export default {
  name: 'RegisterView',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
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
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
/* Laptop L - 1440px */
body {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  background: url('@/assets/log_view/beans-coffee.gif');
  background-size: cover;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  z-index: 0;
}

img {
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
  transition: all 0.5s; /* add this line */
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
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

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>
