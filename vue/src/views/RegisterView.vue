<template>
  <body>
    <img src="@/assets/jolt_logo.png" alt="logo"/>
    <div class="form-container-opacity">
    </div>
    <div class="register-form">
      <form v-on:submit.prevent="register">
        <h1>Create Account</h1>
        <div class="form-input-container">
          <div class="form-input">
            <label for="username">Username</label>
            <input id="username" type="text" v-model="user.username" required autofocus/>
          </div>
          <div class="form-input">
            <label for="password">Password</label>
            <input id="password" type="password" v-model="user.password" required/>
          </div>
          <div class="form-input">
            <label for="confirmPassword">Confirm Password</label>
            <input id="confirm" type="password" v-model="user.confirmPassword" required/>
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
import authService from '../services/AuthService';

export default {
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

.register-form {
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
}

.form-input {
  display: flex;
  flex-direction: column;
  font-size: 1.2vw;
  width: 15.5vw;
  height: 8vh;
  padding: .3vw;
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
  flex-direction: column;
  justify-content: center;
  align-items: center;

}

button {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  object-fit: contain;
  background-color: #3a2f2e;
  width: 8vw;
  height: 4vh;
  font-size: 1vw;
  color: #ffffff;
  border: .1vw solid #e8bb64;
  border-radius: .2vw;
  margin-top: 1vh;
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

#account {
  text-align: center;
  color: #333437;
  font-size: 1.1vw;
  padding-top: 2.5vh;
}

#account:hover {
  color: #e8bb64;
  font-weight: bold;
}
</style>
