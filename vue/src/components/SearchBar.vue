<template>
  <div class="location-container">

    <!-- Search Bar -->
    <div class="search-bar">
      <input id="search" type="text" v-model="locationID" name="user-location" placeholder="Enter Your Location"/>
      <button id="search-button" v-on:click="search()">Search</button>
    </div>  

    <!-- Search Results -->
    <div class="results-container">
      <h1>List of local coffee shops near you:</h1>
      <div class="results" v-for="result in results" v-bind:key="result.id">
        <a class="name" v-bind:href="result.url" target="_blank">{{ result.name }}</a>
        <div class="address">
          <a id="address1" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">{{ result.location.address1 }}</a>
          <a id="address2" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">{{ result.location.address2}}</a>
        </div>
        <div class="address">  
          <a id="city" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">{{ result.location.city}}</a>
          <a id="state" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">, {{ result.location.state}}</a>
          <a id="zip-code" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">{{ result.location.zip_code}}</a>
        </div>  
        <div class="result-image">  
          <a v-bind:href="result.url" target="_blank"><img v-bind:src="result.image_url"/></a>
        </div>
        <button class="favorite" v-on:click="setFavorite(result)">Favorite Coffee Shop</button>  
      </div>  
    </div>

    <!-- No Search Results -->
    <div classv-if="results.length === 0">
      <p>No results found. Please try a different location.</p>
    </div>
  </div>
</template>
  
<script>
import YelpService from '../services/YelpService';
import FavoriteService from '../services/FavoriteService';

export default {
  name: "SearchBar",
  data() {
    return {
      locationID: '',
      results: [] // Add the results array to hold the Yelp search results
    };
  },
  methods: {
    // Update search method to fetch data and populate results
    search() {
      this.clearResults();
      setTimeout(() => {
        this.$store.state.locationID = this.locationID;
        this.getResults(this.locationID); // Call the method to fetch results
      }, 500);
    },
    // Clear previous search results
    clearResults() {
      this.results = [];
      this.$store.state.locationID = null;
    },
    // Fetch the results from YelpService
    getResults(locationID) {
      YelpService.getCoffee(locationID)
        .then(response => {
          this.results = response.data.businesses; // Assign the response to results
          console.log(this.results);
        })
        .catch(error => {
          console.error('Error fetching Yelp results:', error);
        });
    },
    setFavorite(result) {
      // Sending the favorite shop details to the backend
      FavoriteService.createFavorite({
        businessId: result.id,
        businessName: result.name,
        businessAddress: result.location.address1,
        businessWebsite: result.url
      }).then(() => {
          console.log('Favorite added successfully');
      }).catch(error => {
          console.error('Error adding favorite:', error);
      });
    }
  }
};
</script>

<style scoped>
.location-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}



</style>