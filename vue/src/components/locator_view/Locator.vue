<!-- Locator.vue Components -->

<template>
  <article class="locator-container">

    <!-- Search Bar -->
    <form class="search-bar" @submit.prevent="search">
      <label for="location-search" class="visually-hidden">Search Location</label>

      <input id="location-search" type="text" v-model="locationId" placeholder="Enter Your Location" title="Enter Your Search Location">

      <button type="submit" title="Click to Get Coffee Shops">Search</button>
    </form>

    <!-- Near Home Search -->
     <section class="search-home" v-if="user">
        <button @click="searchHome()" title="Click to Get Coffee Shops Near Home">Search Near Home</button>
     </section>

    <h3 v-if="results.length > 0">List of Coffee Shops Near You:</h3>

    <!-- Search Results -->
    <section class="results-container">
      <div class="result" v-for="result in results" :key="result.id">

        <a class="name" :href="result.url" target="_blank" rel="noopener noreferrer" title="Click for Yelp Page">{{ result.name }}</a>

        <div class="location-container" title="Click for Directions">
          <div class="top">
            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))" target="_blank" rel="noopener noreferrer">
              {{ result.location.address1 }},&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))" target="_blank" rel="noopener noreferrer">
              {{ result.location.address2 }}
            </a>
          </div>
            
          <div class="bottom">
            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))" target="_blank" rel="noopener noreferrer">
              {{ result.location.city }},&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))" target="_blank" rel="noopener noreferrer">
              {{ result.location.state }}&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))" target="_blank" rel="noopener noreferrer">
              {{ result.location.zip_code }}
            </a>
          </div>  
        </div>

        <div class="image">  
          <a :href="result.url" target="_blank" rel="noopener noreferrer">
            <img :src="result.image_url || defaultImage" :alt="result.name + ' coffee shop'" title="Click for Yelp Page"/>
          </a>
        </div>

        <div class="favorite">
          <button
            type="button"
            @click="setFavorite(result)"
            :disabled="userFavorites.includes(result.id)"
          >
            <!-- Conditionally set the favorite button image -->
            <img
              :src="userFavorites.includes(result.id) ? favoriteAddedButton : favoriteButton"
              alt=""
              :title="userFavorites.includes(result.id) ? 'Added to Favorites' : 'Click to Add to Favorites'"
            >
            <h4>{{ userFavorites.includes(result.id) ? 'Added to Favorites' : 'Add to Favorites' }}</h4>
          </button>
        </div>

      </div> 
    </section>

    <!-- No Search Results -->
    <section class="no-caret" v-if="hasSearched && results.length === 0">
      <p>No results found. Please try a different location.</p>
    </section>

  </article>
</template>
  
<script>
import LocatorService from '../../services/LocatorService.js';
import FavoriteService from '../../services/FavoriteService.js';
import defaultImage from '../../assets/locator_view/default_image.png';
import favoriteButton from '../../assets/locator_view/favorite_btn.png';
import favoriteAddedButton from '../../assets/locator_view/favorite_added_btn.png';

export default {
  name: "Locator",

  props: {
    user: {
      type: Object,
      default: null
    }
  },

  data() {
    return {
      locationId: '',
      results: [],
      defaultImage: defaultImage,
      favoriteButton: favoriteButton,
      favoriteAddedButton: favoriteAddedButton,
      userFavorites: [],
      hasSearched: false
    }
  },
  
  methods: {
  
    // Create the full address for Google Maps directions
    getDirectionsAddress(result) {
      const { address1, address2, city, state, zip_code } = result.location;

      return `${address1}${address2 ? ', ' + address2 : ''}, ${city}, ${state} ${zip_code}`;
    },

    // Search for coffee shops by entered location
    search() {
      const location = this.locationId.trim();

      if (!location) {
        alert("Please enter a location.");
        return;
      }

      this.clearResults();
      this.getResults(location);
    },

    // Clear previous search results
    clearResults() {
      this.results = [];
      this.hasSearched = false;
    },

    // Fetch the results from LocatorService
    getResults(locationId) {
      LocatorService.getCoffee(locationId)
        .then(response => {
          this.results = response.businesses || [];
          this.hasSearched = true;
        })
        .catch(error => {
          this.hasSearched = false;
          alert('There was a problem fetching coffee shops! Please try again.');
          console.error('Error fetching Yelp results:', error);
        });
    },

    // Sending the favorite shop details to the backend
    setFavorite(result) {
      // Check if the shop is already favorited by the user
      if (this.userFavorites.includes(result.id)) {
        alert("You've already favorited this shop.");
        return;
      }

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
      })
      .then(() => {
        // Add the business ID after the favorite is successfully saved
        this.userFavorites.push(result.id);
      })
      .catch(error => {
        if (error.response && error.response.status === 409) {
          // Update the frontend if the favorite already exists in the database
          if (!this.userFavorites.includes(result.id)) {
            this.userFavorites.push(result.id);
          }

          alert("You've already favorited this shop.");
        } else {
          alert("There was a problem adding this favorite. Please try again.");
        }

        console.error('Error adding favorite:', error);
      });
    },

    // Fetch the user's favorites from the database
    getUserFavorites() {
      FavoriteService.getFavorites()
        .then(response => {
          // Store the business IDs for the user's saved favorites
          this.userFavorites = response.map(favorite => favorite.businessId);
        })
        .catch(error => {
          console.error('Error fetching favorites:', error);
        });
    },

    // Search near home
    searchHome() {
      const { address1, address2, city, state, zipcode } = this.user;

      // Check if the address is incomplete
      if (!address1 || !city || !state || !zipcode) {
        alert("User address is missing or incomplete. Please complete your profile.");
        return;
      }

      // If address is complete, proceed with the search
      const fullAddress = `${address1} ${address2 ? address2 + ', ' : ''}${city}, ${state} ${zipcode}`;

      // Clear previous results and search using the user's saved address
      this.clearResults();
      this.getResults(fullAddress);
    }
  },

  mounted() {
    // Fetch the user's favorite shops when the component is mounted
    this.getUserFavorites();
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */
.locator-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.search-bar {
  display: flex;
  flex-direction: row;
  width: 40vw;
  margin-bottom: 1rem;
}

.search-bar input {
  width: 100%;
  font-size: 1.1rem;
}

.search-bar button,
.search-home button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 5rem;
  height: 1.7rem;
  font-size: .9rem;
  color: rgb(245, 242, 242);
  background-color: rgb(53, 37, 19);
  border-radius: .1rem;
  transition: all 0.5s;
}

.search-bar button {
  width: 5rem;
}

.search-home button {
  width: 9rem;
}

.search-bar button:hover,
.search-home button:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
  cursor: pointer;
}

h3 {
  font-weight: bolder;
  font-size: 1.1rem;
  margin: 0;
  margin-top: 1.5rem;
}

.results-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  padding: .2rem 0;
}

.result {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 17rem;
  height: 20rem;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .1rem;
  padding: .8rem;
  margin: .3rem;
  transition: all 0.5s ease-in-out;
}

.result:hover {
  background-color: #e8bb64;
  transform: scale(1.1);
}

.result:hover .name {
    font-size: 1.15rem;
}

.result:hover .location-container {
    font-size: 1.05rem;
}

.result:hover .image img {
    border: .15rem rgb(53, 37, 19) solid;
    filter: grayscale(70%);
}

.result a {
  color: #525459;
  text-decoration: none;
}

.result .name {
  font-size: 1.1rem;
  font-weight: bold;
  transition: all 0.5s ease-in-out;
}

.result .location-container {
  font-size: 1rem;
  transition: all 0.5s ease-in-out;
}

.result .name:hover,
.result .location-container:hover {
  text-decoration: underline;
}

.result img {
  width: 13rem;
  height: 13rem;
  margin-top: .5rem;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .1rem;
  transition: all 0.5s ease-in-out;
}

.result img:hover {
  transform: scale(1.05);
}

.favorite button {
  background-color: transparent;
  border: none;
  transition: all 0.3s ease-in-out;
}

.favorite img {
  width: 2.6rem;
  height: 2rem;
  border: none;
  padding: 0;
  margin: 0;
}

.favorite img:hover {
  transform: scale(1.2);
  border: none;
  cursor: pointer;
}

h4 {
  font-weight: lighter;
  margin: 0;
}

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  .search-bar {
    height: 3.5rem;
  }

  .search-bar button {
    width: 10rem;
  }

  .search-home button {
    width: 17rem;
  }

  .search-bar button,
  .search-home button {
    height: 3.5rem;
    font-size: 1.6rem;
  }

  .search-bar input {
    font-size: 1.9rem;
  }

  h3 {
    font-size: 2rem;
  }

  .results-container {
    padding: .6rem 0;
  }

  .result {
    width: 30rem;
    height: 34rem;
    padding: 1rem;
    margin-inline: 1.2rem;
  }

  .result .name {
    font-size: 1.8rem;
  }

  .result .location-container {
    font-size: 1.6rem;
  }

  .result .image img {
    width: 22rem;
    height: 22rem;
  }

  .favorite img {
    width: 4.5rem;
    height: 3.5rem;
  }

  h4 {
    font-size: 1.3rem;
  }

  p {
    font-size: 1.8rem;
  }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  .search-bar {
    width: 55vw;
  }

  h3 {
    font-size: 1rem;
  }

  .result {
    width: 15rem;
    height: 17.5rem;
    padding: 1.1rem;
    margin-inline: .25rem;
  }

  .result .name {
    font-size: 1rem;
  }

  .result .location-container {
    font-size: .9rem;
  }

  .result .image img {
    width: 12rem;
    height: 12rem;
    padding: 0;
    margin: 0;
    margin-top: .1rem;
  }

  .favorite img {
    width: 2.3rem;
    height: 1.8rem;
  }

  h4 {
    font-size: .7rem;
  }

  p {
    font-size: .9rem;
  }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .search-bar {
    width: 70vw;
  }
  
  .result {
    margin-inline: .5rem;
  }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  .search-bar {
    width: 90vw;
  }

  .search-bar input {
    font-size: 1rem;
  }

  h3 {
    font-size: .9rem;
  }

  .result .name {
    font-size: .9rem;
  }

  .result .location-container {
    font-size: .9rem;
  }

  p {
    font-size: .8rem;
  }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

  .search-bar {
    width: 19rem;
  }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

  .search-bar {
    width: 16rem;
  }

  .result {
    width: 13rem;
    height: 16rem;
  }

  .result .name {
    font-size: .9rem;
  }

  .result .location-container {
    font-size: .8rem;
  }

  .result .image img {
    width: 11rem;
    height: 11rem;
  }

  p {
    width: 95%;
  }
}
</style>