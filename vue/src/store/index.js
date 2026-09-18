// index.js Store

import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {

  // Create the Vuex store with any saved authentication data
  const store = _createStore({
    state: {
      token: currentToken,
      user: currentUser || {},

      // Store profile information used throughout the application
      profileStatus: null,
      profile: null,
      profileImage: null,

      // Store the user's favorite coffee shops
      favorites: []
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

      // Store whether the logged-in user has a profile
      SET_PROFILE_STATUS(state, status) {
        state.profileStatus = status;
      },

      // Store the logged-in user's profile
      SET_PROFILE(state, profile) {
        state.profile = profile;
      },

      // Store the logged-in user's profile image
      SET_PROFILE_IMAGE(state, image) {
        state.profileImage = image;
      },

      // Store the user's favorite coffee shops
      SET_FAVORITES(state, favorites) {
        state.favorites = favorites;
      },

      // Clear the user's authentication data
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');

        state.token = '';
        state.user = {};
        state.profileStatus = null;
        state.profile = null;
        state.profileImage = null;
        state.favorites = [];

        // Remove the authorization header from future Axios requests
        delete axios.defaults.headers.common['Authorization'];
      }
    }
  });

  return store;
}
