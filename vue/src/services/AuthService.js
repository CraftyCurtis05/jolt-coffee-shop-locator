// AuthService.js

import axios from 'axios';

export default {

  // POST the user's login credentials
  login(user) {
    return axios.post('/login', user);
  },

  // POST a new user registration
  register(user) {
    return axios.post('/register', user);
  }
};
