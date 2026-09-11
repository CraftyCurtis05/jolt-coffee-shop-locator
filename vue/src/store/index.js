// index.js Store

import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {

  // Create the Vuex store with any saved authentication data
  const store = _createStore({
    state: {
      token: currentToken,
      user: currentUser || {}
    },

    mutations: {

      // Store the authentication token
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);

        // Add the token to future Axios requests
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },

      // Store the logged-in user's information
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },

      // Clear the user's authentication data
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');

        state.token = '';
        state.user = {};

        // Remove the authorization header from future Axios requests
        delete axios.defaults.headers.common['Authorization'];
      }
    }
  });

  return store;
}
