// LocatorService.js

import axios from 'axios';

export default {

  // GET coffee shops based on searched location
  getCoffee(locationId) {
    return axios.get('/coffee', {
      params: {
        locationId: locationId
      }
    })
    .then(response => response.data);
  }
};
