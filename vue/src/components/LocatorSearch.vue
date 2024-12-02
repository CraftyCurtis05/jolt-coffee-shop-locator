<template>
  <div class="location-container">
    <input type="text" v-model="locationID" name="user-location" class="location-input" placeholder="Your Location"/>
    <button v-on:click="search()" class="search-button">Search</button>
  </div>

  <div v-if="results.length === 0">
    <p>No results found. Please try a different location.</p>
  </div>

  <div class="coffee-shop-container">
    <h1 class="coffee-shop-title">List of local coffee shops near you:</h1>
    <div class="coffee-shop-list">
      <div v-for="result in results" v-bind:key="result.id" class="search-result">
        <div class="shop-item">
          <a class="shop-name" v-bind:href="result.url" target="_blank">{{ result.name }}</a>
          <div class="shop-details">
            <a class="shop-address" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">{{ result.location.address1 }}</a>
            <a class="shop-name" v-bind:href="result.url" target="_blank"><img class="shop-image" v-bind:src="result.image_url"/></a>
          </div>
          <button class="favorite-button" v-on:click="setFavorite(result)">Favorite Coffee Shop</button>  
        </div>  
      </div>
    </div>  
  </div>
</template>
  
<script>
import YelpService from '../services/YelpService';
import FavoriteService from '../services/FavoriteService';

export default {
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
    clearResults() {
      this.results = [];  // Clear previous search results
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
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

.location-container {
  display: flex;
  align-items: center;
  background-color: #F9F1DC;
  border-radius: 5px;
  padding: 10px;
  max-width: 800px;
  margin: 0 auto;
}

.location-input {
  flex: 1;
  padding: 10px;
  border: none;
  border-radius: 5px;
  outline: none;
}

.search-button {
  background-color: rgb(223, 204, 145);
  color: rgb(13, 12, 12);
}

.search-button {
  background-color: rgb(223, 204, 145); 
  color: rgb(13, 12, 12); 
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  margin-left: 10px;
  cursor: pointer;
}

.search-button:hover {
  background-color: rgb(105, 100, 100);
  color: rgb(255, 252, 251);
}

.search-button:hover {
  background-color: rgb(105, 100, 100); 
  color: rgb(255, 252, 251);
}

h1.coffee-shop-title {
    font-family: 'Lobster Two', sans-serif;
    font-family: 'Ubuntu', sans-serif;
    font-weight: bolder;
    font-size: 30px;
    text-align: center !important;
    color: #6f6559;
  }
  .coffee-shop-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
  }
  .shop-image {
    width: 250px;
    height: auto;
    border: 3px solid #F4E2B5; 
    border-radius: 10px; 
    transition: transform 0.2s ease-in-out; 
  }
  .shop-image:hover {
    transform: scale(1.1);
  }
  .shop-name {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
    font-size: 30px; 
    font-weight: bolder;
    color: #fbfbf6; 
    margin-top: 10px; 
    background-color: #a4a49a;
    padding: 5px 10px; 
    border-radius: 10px;
  }
.shop-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}
.favorite-button {
  margin-top: 10px;
  margin-bottom: 50px; /* Add margin at the bottom to create space */
  background-color: #F4E2B5;
  color: black;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 50px;
}
a:link, a:visited {
  color: rgb(22, 18, 18);
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px;
}
a:hover{
  color:#e8bb64;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px;
}
</style>