<template>
  <body>
    <img src="@/assets/jolt_logo.png" alt="logo"/>
    <div class="form-container-opacity">
    </div>
    <div class="login-form">
      <form v-on:submit.prevent="login">
        <h1>Please Sign In</h1>
        <div class="form-input-container">
          <div class="form-input">
            <label for="username">Username</label>
            <input id="username"  type="text" v-model="user.username" required autofocus/>
          </div>
          <div class="form-input">
            <label for="password">Password </label>
            <input id="password" type="password" v-model="user.password" required/>
          </div>
        </div>
        <div class="alert-container">
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering!<br>Please sign in.
          </div>
          <div role="alert" v-if="invalidCredentials">
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
body {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  height: 100vh;
  background: url('@/assets/beans-coffee.gif');
  background-size: cover;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  z-index: 0;
}

img {
  display: flex;
  width: 23vw;
  height: auto;
  padding-top: 15vh;
  z-index: 10;
}

.form-container-opacity {
  position: absolute;
  width: 18vw;
  height: 55vh;
  background-color: rgb(160, 153, 145);
  border: .3vw #3a2f2e solid;
  border-radius: .2vw;
  margin-top: 35vh;
  opacity: .7;
  z-index: 1;
}

.login-form {
  display: flex;
  flex-direction: column;
  align-content: start;
  justify-content: start ;
  padding-top: 1vw;
  z-index: 10;
}

h1 {
  text-align: center;
  font-size: 1.6vw;
  padding-bottom: 1vh;
}

.form-input {
  display: flex;
  flex-direction: column;
  font-size: 1.2vw;
  width: 15.5vw;
  height: 8vh;
  padding: .5vw;
}

.form-input input {
  height: 4vh;
  width: 15vw;
  font-size: 1.2vw;
  border: .1vw #3a2f2e solid;;
}

.alert-container {
  text-align: center;
  font-size: 1.1vw;
  font-weight: bold;
  padding-top: 2vh;
}

.button-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding-top: 2vw;
}

button {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  object-fit: contain;
  background-color: #3a2f2e;
  width: 5vw;
  height: 4vh;
  font-size: 1vw;
  color: #ffffff;
  border: .1vw solid #e8bb64;
  border-radius: .2vw;
  transition: all 0.5s; /* add this line */
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
}

button:hover {
  background-color: #e8bb64;
  color: #3a2f2e;
  text-decoration: underline;
  border: .1vw solid #ffffff;
}

#sign-in {
  margin-right: 2vw;
}
</style>