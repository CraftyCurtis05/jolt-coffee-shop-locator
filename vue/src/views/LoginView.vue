<template>
  <div id="login-container">
    <div class="logo-company-container">
      <div class="logo-container">
        <img src="src\assets\Jolt Logo.png" alt="Logo"/>
      </div>
    </div>
    <div class="form-container">
      <div class="login-form">
        <form v-on:submit.prevent="login">
          <h1 class="sign-in-text">Please Sign In</h1>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button class="sign-in-button" type="submit">Sign in</button>
          <div class="button-container">
            <router-link v-bind:to="{ name: 'register' }">
              <button class="register-button">Register</button>
            </router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
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
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

h1 {
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 28px; /* Adjust the font size as needed */
  margin: 0;
}
#login-container{
  background: url('src\\assets\\beans-coffee.gif') no-repeat center center fixed;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  width: 100%;
}
.logo-container{
  display: flex;
  flex-direction:row;
  justify-content: center;
  position: relative;
  top: -200px;
}
.login-form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
  top: -475px;
}
.sign-in-text {
  color: white;
  display: flex;
  flex-direction: row;
  justify-content: center;
  font-size: 24px;
}
.form-input-group {
  color: white;
  text-align: start;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 14px;
}
#username {
  display: flex;
}
#password {
  display: flex;
}
.register-button {
  position: relative;
  right: -80px;
  top: -28.5px;
}
.sign-in-button {
  position: relative;
}
button {
  display: flex;
  justify-content: center;
  position: relative;
  background-color: #3a2f2e;
  width: 80px;
  color: #ffffff;
  text-align: center;
  border: 1.5px solid #cccccc;
  border-radius: 5px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 14px;
  margin: 5px;
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
  transition: all 0.5s; /* add this line */
}
button:hover {
  background-color: #e8bb64;
}
</style>