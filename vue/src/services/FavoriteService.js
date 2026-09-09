// FavoriteService.js

import axios from 'axios';

export default {

  // POST a new favorite
  createFavorite(favorite) {
    return axios.post('/favorites', favorite)
      .then(response => response.data);
  },

  // GET all favorites for the logged-in user
  getFavorites() {
    return axios.get('/favorites')
      .then(response => response.data);
  },

  // DELETE a favorite from the user's profile
  deleteFavorite(favoriteId) {
    return axios.delete(`/favorites/${favoriteId}`);
  }
};