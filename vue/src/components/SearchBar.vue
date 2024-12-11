<template>
  <div class="locator-container">

    <!-- Search Bar -->
    <div class="search-bar">
      <input type="text" v-model="locationID" name="user-location" placeholder="Enter Your Location"/>
      <button @click="search()">Search</button>
    </div>

    <!-- Near Home Search -->
     <div class="search-home">
        <button @click="searchHome()">Search Near Home</button>
     </div>

    <h1>List of local coffee shops near you:</h1>

    <!-- Search Results -->
    <div class="results-container">
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
        <button id="favorite" v-on:click="setFavorite(result)"><img src="@/assets/favorite_btn.png"></button>  
      </div>  
    </div>

    <!-- No Search Results -->
    <div classv-if="results.length === 0">
      <p>No results found. Please try a different location.</p>
    </div>
  </div>
</template>
  
<script>
import YelpService from '../services/YelpService.js';
import FavoriteService from '../services/FavoriteService.js';
import ProfileService from '../services/ProfileService.js';

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
        businessAddress1: result.location.address1,
        businessAddress2: result.location.address2,
        businessCity: result.location.city,
        businessState: result.location.state,
        businessZipcode: result.location.zip_code,
        businessImage: result.image_url,
        businessUrl: result.url
      }).then(() => {
          alert('Favorite added successfully!');
      }).catch(error => {
          alert("You've already favorited this shop. Try again!")
          console.error('Error adding favorite:', error);
      });
    }
  }
};
</script>

<style scoped>
.locator-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

h1{
  font-weight: bolder;
  font-size: 1.4rem;
  text-align: center;
  padding: 1rem;
  margin: 0;
}

.search-bar {
  display: flex;
  flex-direction: row;
  padding: 2vw;
}

.search-bar input {
  width: 20rem;
  height: 1.4rem;
  font-size: 1.1rem;
  border: .1rem rgb(53, 37, 19) solid;
}

.search-bar button {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  align-items: center;
  object-fit: contain;
  background-color: rgb(53, 37, 19);
  width: 5rem;
  height: 1.7rem;
  font-size: .9rem;
  color: #ffffff;
  border: .1rem solid #e8bb64;
  border-radius: .1rem;
  transition: all 0.5s; /* add this line */
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
}

.search-bar button:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  border: .1rem solid #ffffff;
}

.results-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
}

.results {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 3vw;
}

.results a {
  color: #525459;
  text-decoration: none;
}

.results:hover {
  background-color: #e8bb64;
  font-size: 1.2vw;
  border-radius: .2vw;
  transition: all 0.5s; /* add this line */
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
}

.results .name {
  font-size: 1.3vw;
  font-weight: bold;
}

.results img {
  width: 20vw;
  height: 20vw;
  border: .2vw rgb(53, 37, 19) solid;
  border-radius: .2vw;
}

#favorite {
  text-align: center;
  background-color: transparent;
  border: none;
}

#favorite img {
  width: 2.9vw;
  height: 2.5vw;
  border: none;
}

#favorite img:hover {
  width: 3.2vw;
  height: 2.8vw;
  cursor: pointer;
}
</style>