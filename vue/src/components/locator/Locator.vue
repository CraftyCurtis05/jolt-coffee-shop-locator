<!-- Locator.vue Component -->

<template>
  <article class="locator-container">

    <!-- Location Search -->
    <form class="search-bar" @submit.prevent="search">
      <label for="location-search" class="visually-hidden">
        Search Location
      </label>

      <input
        id="location-search"
        type="text"
        v-model="locationId"
        placeholder="Enter Your Location"
        title="Enter Your Search Location"
      />

      <button
        type="submit"
        title="Click to Get Coffee Shops"
      >
        Search
      </button>
    </form>

    <!-- Search Near Home -->
    <section class="search-home" v-if="user">
      <button
        type="button"
        @click="searchHome()"
        title="Click to Get Coffee Shops Near Home"
      >
        Search Near Home
      </button>
    </section>

    <!-- Search Results Heading -->
    <h3 v-if="results.length > 0">
      List of Coffee Shops Near You:
    </h3>

    <!-- Search Results -->
    <section class="results-container">

      <!-- Coffee Shop Result -->
      <div
        class="result"
        v-for="result in results"
        :key="result.id"
      >

        <!-- Coffee Shop Name -->
        <a
          class="name"
          :href="result.url"
          target="_blank"
          rel="noopener noreferrer"
          title="Click for Yelp Page"
        >
          {{ result.name }}
        </a>

        <!-- Coffee Shop Address -->
        <div
          class="location-container"
          title="Click for Directions"
        >

          <div class="top">
            <a
              :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.location.address1 }},&nbsp;
            </a>

            <a
              :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.location.address2 }}
            </a>
          </div>

          <div class="bottom">
            <a
              :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.location.city }},&nbsp;
            </a>

            <a
              :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.location.state }}&nbsp;
            </a>

            <a
              :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.location.zip_code }}
            </a>
          </div>

        </div>

        <!-- Coffee Shop Image -->
        <div class="image">
          <a
            :href="result.url"
            target="_blank"
            rel="noopener noreferrer"
          >
            <img
              :src="result.image_url || defaultImage"
              :alt="result.name + ' coffee shop'"
              title="Click for Yelp Page"
            />
          </a>
        </div>

        <!-- Favorite Button -->
        <div class="favorite">
          <button
            type="button"
            @click="setFavorite(result)"
            :disabled="userFavorites.includes(result.id)"
          >
            <img
              :src="userFavorites.includes(result.id) ? favoriteAddedButton : favoriteButton"
              alt=""
              :title="userFavorites.includes(result.id) ? 'Added to Favorites' : 'Click to Add to Favorites'"
            />

            <h4>
              {{ userFavorites.includes(result.id) ? 'Added to Favorites' : 'Add to Favorites' }}
            </h4>
          </button>
        </div>

      </div>
    </section>

    <!-- No Search Results -->
    <section
      class="no-caret"
      v-if="hasSearched && results.length === 0"
    >
      <p>
        No results found. Please try a different location.
      </p>
    </section>

  </article>
</template>

<script>
import LocatorService from '../../services/LocatorService.js';
import FavoriteService from '../../services/FavoriteService.js';

import defaultImage from '../../assets/locator/default_image.webp';
import favoriteButton from '../../assets/locator/favorite_btn.webp';
import favoriteAddedButton from '../../assets/locator/favorite_added_btn.webp';

export default {
  name: 'Locator',

  props: {
    user: {
      type: Object,
      default: null
    }
  },

  data() {
    return {
      // Search information
      locationId: '',
      results: [],
      hasSearched: false,

      // Search result images
      defaultImage: defaultImage,
      favoriteButton: favoriteButton,
      favoriteAddedButton: favoriteAddedButton,

      // Store the user's saved favorite business IDs
      userFavorites: []
    };
  },

  methods: {

    // Create the full address for Google Maps directions
    getDirectionsAddress(result) {
      const { address1, address2, city, state, zip_code } = result.location;

      return `${address1}${address2 ? ', ' + address2 : ''}, ${city}, ${state} ${zip_code}`;
    },

    // Search for coffee shops using the entered location
    search() {
      const location = this.locationId.trim();

      // Make sure a location was entered
      if (!location) {
        alert('Please enter a location.');
        return;
      }

      // Clear the previous search and get new results
      this.clearResults();
      this.getResults(location);
    },

    // Clear previous search results
    clearResults() {
      this.results = [];
      this.hasSearched = false;
    },

    // Get coffee shops for a searched location
    getResults(locationId, fallbackLocation = null) {
      LocatorService
      .getCoffee(locationId)
      .then((response) => {

        // Store the coffee shops returned by Yelp
        this.results = response.businesses || [];
        this.hasSearched = true;

        // *DEBUG* Log the search results for debugging
        // console.log('Coffee shop search results:', this.results);
      })
      .catch((error) => {

        // If the full home address cannot be found, try the saved ZIP code
        if (
          fallbackLocation &&
          error.response &&
          error.response.status === 400
        ) {

          // *DEBUG* Log the ZIP code fallback for debugging
          // console.log('Home address not found. Searching saved ZIP code:', fallbackLocation);

          this.getResults(fallbackLocation);
          return;
        }

        // Display an error if the search fails
        this.hasSearched = false;
        alert('There was a problem fetching coffee shops! Please try again.');
        console.error('Error fetching Yelp results:', error);
      });
    },

    // Add a coffee shop to the user's favorites
    setFavorite(result) {

      // Check if the shop is already favorited by the user
      if (this.userFavorites.includes(result.id)) {
        alert("You've already favorited this shop.");
        return;
      }

      // Send the coffee shop information to the server
      FavoriteService
      .createFavorite({
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

        // *DEBUG* Log the added favorite for debugging
        // console.log('Favorite added:', result.id);
      })
      .catch((error) => {

        // Handle a favorite that already exists in the database
        if (error.response && error.response.status === 409) {

          // Update the frontend to match the database
          if (!this.userFavorites.includes(result.id)) {
            this.userFavorites.push(result.id);
          }

          alert("You've already favorited this shop.");

          // *DEBUG* Log the duplicate favorite for debugging
          // console.log('Favorite already exists:', result.id);

        } else {

          // Display an error if the favorite cannot be saved
          alert('There was a problem adding this favorite. Please try again.');
          console.error('Error adding favorite:', error);
        }
      });
    },

    // Get the user's saved favorites
    getUserFavorites() {
      FavoriteService
      .getFavorites()
      .then((response) => {

        // Store the business IDs for the user's saved favorites
        this.userFavorites = response.map((favorite) => favorite.businessId);

        // *DEBUG* Log the user's favorites for debugging
        // console.log('User favorites:', this.userFavorites);
      })
      .catch((error) => {
        console.error('Error fetching favorites:', error);
      });
    },

    // Search for coffee shops near the user's home address
    searchHome() {
      const { address1, address2, city, state, zipcode } = this.user;

      // Make sure the user's address is complete
      if (!address1 || !city || !state || !zipcode) {
        alert('User address is missing or incomplete. Please complete your profile.');
        return;
      }

      // Create the full address from the user's saved profile
      const fullAddress =
        `${address1} ${address2 ? address2 + ', ' : ''}${city}, ${state} ${zipcode}`;

      // Clear previous results and search using the user's saved address
      this.clearResults();
      this.getResults(fullAddress, zipcode);
    }
  },

  mounted() {
    // Get the user's favorite shops when the component loads
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
</style>