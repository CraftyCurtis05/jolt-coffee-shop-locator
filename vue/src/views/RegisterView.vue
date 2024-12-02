<template>
  <div id="register-container">
    <div class="logo-company-container">
      <div class="logo-container">
        <img src="src\assets\Jolt Logo.png" alt="Logo"/>
      </div>
    </div>
    <div class="form-container">
      <div class="register-form">
        <form v-on:submit.prevent="register">
          <h1 class="create-account-text">Create Account</h1>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <div class="username-form-input">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="password-form-input">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <div class="confirm-form-input">
            <label class="confirm-password-text" for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
          </div>
          <div class="submit-button" >
            <button type="submit">Create Account</button>
            <p class="have-account-text"><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
          </div>
        </form>
      </div>
    </div>
  </div>  
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
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

body {
  text-align: center;
  align-items: center;
}
h1 {
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 28px; /* Adjust the font size as needed */
  margin: 0;
}
#register-container{
  background: url('src\\assets\\beans-coffee.gif') no-repeat center center fixed;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  width: 100%;
}
.logo-container{
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
  top: -200px;
}
.register-form {
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
  top: -475px;
  color: white;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 14px;
}
.create-account-text {
  color: white;
  display: flex;
  flex-direction: row;
  justify-content: center;
  font-size: 24px;
}
#username {
  display: flex;
}
#password {
  display: flex;
}
#confirm-password {
  display: flex;
}
.confirm-password-text {
  display: flex;
}
button {
  display: flex;
  justify-content: center;
  position: relative;
  background-color: #3a2f2e;
  width: 125px;
  color: #ffffff;
  text-align: center;
  border: 1.5px solid #cccccc;
  border-radius: 5px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 14px;
  margin:0px;
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
  transition: all 0.5s; /* add this line */
}
button:hover {
  background-color: #e8bb64;
}
a:link, a:visited {
  color: white;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 700;
  font-size: 12px; 
}
a:hover{
  color:#e8bb64;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 12px; 
}
</style>
