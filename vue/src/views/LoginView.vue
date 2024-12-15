<!-- LoginView.vue -->

<template>
  <body>
    <img src="@/assets/app/logo/jolt_logo.png" alt="logo"/>
    <div class="form-container-opacity">
    </div>
    <div class="login-form">
      <form v-on:submit.prevent="login">
        <h1>Please Sign In</h1>
        <div class="form-input-container">
          <div class="form-input username">
            <label for="username">Username</label>
            <input type="text" v-model="user.username" required autofocus/>
          </div>
          <div class="form-input password">
            <label for="password">Password </label>
            <input type="password" v-model="user.password" required/>
          </div>
        </div>
        <div class="alert-container">
          <div role="alert" v-if="this.$route.query.registration" id="alert1">
            Thank you for registering!<br>Please sign in.
          </div>
          <div role="alert invalid" v-if="invalidCredentials" id="alert2">
            Invalid username and password!
          </div>
        </div>
        <div class="button-container">
          <button id="sign-in" type="submit">Sign in</button>
          <router-link v-bind:to="{ name: 'register' }"><button id="register">Register</button></router-link>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: 'LoginView',
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
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

button {
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
  transition: all 0.5s; /* add this line */
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
}

button:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1rem solid #ffffff;
}

#sign-in {
  margin-right: 2rem;
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