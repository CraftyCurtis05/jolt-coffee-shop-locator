// main.js

import { createApp } from 'vue';
import axios from 'axios';

import CapstoneApp from './App.vue';
import { createStore } from './store';
import router from './router';

import './global.css';


// Set the base URL for server API communication
axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;


// Get any saved authentication data
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

// Restore the authorization header after a page refresh
if (currentToken) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}


// Create the Vuex store using any saved authentication data
const store = createStore(currentToken, currentUser);


/*
 * If a saved token has expired, log the user out and
 * return them to the login page.
 */
axios.interceptors.response.use(
  response => response,

  error => {
    if (
      error.response &&
      error.response.status === 401 &&
      store.state.token
    ) {
      store.commit('LOGOUT');

      // Tell the user why they were returned to the login page
      window.dispatchEvent(new CustomEvent('app-notification', {
        detail: {
          message: 'Your session has expired. Please sign in again.',
          type: 'info'
        }
      }));

      router.push('/login');
    }

    return Promise.reject(error);
  }
);


// Create and mount the Vue application
const app = createApp(CapstoneApp);

app.use(store);
app.use(router);

app.mount('#app');
