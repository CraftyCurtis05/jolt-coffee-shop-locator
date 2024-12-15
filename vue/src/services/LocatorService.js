// LocatorService.js

import axios from 'axios';

export default {

  getCoffee(locationId) {
    const queryURL = `http://localhost:9000/coffee?locationId=${locationId}`;
    
    return axios.get(queryURL)
      .then(response => {
        console.log("Full API Response:", response.data);  // Log the full response
        return response.data;
      })
      .catch(error => {
        console.error('Error fetching coffee locations:', error);
      });
  }
};