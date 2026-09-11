<!-- Locator.vue Component -->

<template>
  <article class="locator-container">

    <!-- Search Controls -->
    <section class="search-controls">

      <!-- Search Heading -->
      <h3 class="search-heading">
        Find Coffee Near You
      </h3>

      <!-- Location Search -->
      <form class="search-bar" @submit.prevent="search">
        <label for="location-search">
          Search Location:
        </label>

        <input
          id="location-search"
          type="text"
          v-model="locationId"
          placeholder="City, ZIP code or address"
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
          class="home-button"
          type="button"
          @click="searchHome()"
          title="Click to Get Coffee Shops Near Home"
        >
          <img
            :src="houseIcon"
            alt=""
          />
          Search Near Home
        </button>
      </section>

    </section>

    <!-- Search Results -->
    <section
      class="results-section"
      v-if="results.length > 0"
    >

      <!-- Search Results Heading -->
      <header class="results-header">
        <h3>
          Coffee Shops Near You
        </h3>

        <p class="results-count">
          {{ results.length }} coffee shops found
        </p>

        <p class="results-source">
          Results provided by Yelp
        </p>
      </header>

      <!-- Search Results Grid -->
      <section class="results-container">

        <!-- Coffee Shop Result -->
        <article
          class="result"
          v-for="result in results"
          :key="result.id"
        >

          <!-- Coffee Shop Information -->
          <section class="result-info">

            <!-- Coffee Shop Name -->
            <a
              class="name"
              :href="result.url"
              target="_blank"
              rel="noopener noreferrer"
              :aria-label="'View ' + result.name + ' on Yelp'"
              title="Click for Yelp Page"
            >
              {{ result.name }}
            </a>

            <!-- Coffee Shop Address -->
            <div class="location-container">
              <a
                :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(getDirectionsAddress(result))"
                target="_blank"
                rel="noopener noreferrer"
                :aria-label="'Get directions to ' + result.name"
                title="Click for Directions"
              >
                <span>
                  {{ result.location.address1 }}
                  <template v-if="result.location.address2">
                    , {{ result.location.address2 }}
                  </template>
                </span>

                <span>
                  {{ result.location.city }},
                  {{ result.location.state }}
                  {{ result.location.zip_code }}
                </span>
              </a>
            </div>

          </section>

          <!-- Coffee Shop Image -->
          <section class="image">
            <a
              :href="result.url"
              target="_blank"
              rel="noopener noreferrer"
              :aria-label="'View ' + result.name + ' on Yelp'"
            >
              <img
                :src="result.image_url || defaultImage"
                :alt="result.image_url ? result.name + ' coffee shop' : 'No photo available for ' + result.name"
                title="Click for Yelp Page"
              />
            </a>
          </section>

          <!-- Favorite Button -->
          <section class="favorite">
            <button
              type="button"
              @click="setFavorite(result)"
              :disabled="userFavorites.includes(result.id)"
              :aria-label="
                userFavorites.includes(result.id)
                  ? result.name + ' is already in favorites'
                  : 'Add ' + result.name + ' to favorites'
              "
            >
              <img
                :src="userFavorites.includes(result.id) ? favoriteAddedButton : favoriteButton"
                alt=""
                :title="userFavorites.includes(result.id) ? 'Added to Favorites' : 'Click to Add to Favorites'"
              />

              <span>
                {{ userFavorites.includes(result.id) ? 'Added to Favorites' : 'Add to Favorites' }}
              </span>
            </button>
          </section>

        </article>
      </section>

    </section>

    <!-- No Search Results -->
    <section
      class="no-results"
      v-if="hasSearched && results.length === 0"
    >
      <p>
        No coffee shops were found for that location.
        Try another city, ZIP code or address.
      </p>
    </section>

  </article>
</template>

<script>
import LocatorService from '../../services/LocatorService.js';
import FavoriteService from '../../services/FavoriteService.js';

import houseIcon from '../../assets/locator/house_icon.webp';
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

      // Locator images
      houseIcon: houseIcon,
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
      const {
        address1,
        address2,
        city,
        state,
        zip_code
      } = result.location;

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
      const {
        address1,
        address2,
        city,
        state,
        zipcode
      } = this.user;

      // Make sure the user's address is complete
      if (!address1 || !city || !state || !zipcode) {
        alert('User address is missing or incomplete. Please complete your profile.');
        return;
      }

      // Create the full address from the user's saved profile
      const fullAddress =
        `${address1}${address2 ? ', ' + address2 : ''}, ${city}, ${state} ${zipcode}`;

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

/* Locator */

.locator-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.locator-container,
.search-controls,
.results-section,
.results-container,
.result {
  min-width: 0;
}

/* Search Controls */

.search-controls {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 76rem;
  max-width: 92%;
  min-height: 10rem;
  background-color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem 1.25rem;
  margin: 0 auto 1.25rem;
}

.search-heading {
  font-size: 1rem;
  color: rgb(53, 37, 19);
  margin: 0 auto .75rem;
}

.search-controls form {
  max-width: 90%;
}


/* Search Bar */

.search-bar label {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 9rem;
  flex-shrink: 0;
  text-align: center;
  margin: 0;
}

.search-bar {
  display: flex;
  width: 90%;
  max-width: 45rem;
  margin-bottom: .75rem;
}

.search-bar input {
  width: 100%;
  min-width: 0;
  font-size: 1rem;
  padding: .5rem .75rem;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem 0 0 .2rem;
}

.search-bar button {
  flex-shrink: 0;
  width: 5.5rem;
  color: rgb(245, 242, 242);
  background-color: rgb(53, 37, 19);
  border-radius: 0 .2rem .2rem 0;
}


/* Search Near Home */

.search-home {
  margin: .5rem auto;
}

.search-home button {
  display: flex;
  flex-direction: row;
  gap: .4rem;
  width: 12rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
}

.home-button img {
  flex-shrink: 0;
  width: 1.75rem;
  height: 1.75rem;
  object-fit: contain;
}


/* Search Buttons */

.search-bar button,
.search-home button {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 2.2rem;
  font-size: .9rem;
  border: .1rem rgb(53, 37, 19) solid;
  padding: .4rem .75rem;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out;
}

.search-bar button:hover {
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
}

.search-home button:hover {
  color: rgb(245, 242, 242);
  background-color: rgb(53, 37, 19);
}

.search-home button img {
  transition:
    filter 0.3s ease-in-out;
}

.search-home button:hover img {
  filter: invert(100%);
}


/* Search Results */

.results-section {
  width: 94%;
  max-width: 100rem;
  background-color: rgb(53, 37, 19);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 0 1.25rem;
  margin: 0 auto 1.5rem;
}

.results-header h3 {
  font-size: 1.2rem;
  color: rgb(245, 242, 242);
  margin: .25rem auto;
}

.results-count {
  font-size: .8rem;
  color: #e8bb64;
  margin: 0 auto .2rem;
}

.results-source {
  font-size: .75rem;
  color: #b4b8c2;
  margin: 0 auto 1.5rem;
}


/* Search Results Grid */

.results-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1rem;
  width: 100%;
  max-width: 96rem;
  margin: 0 auto;
}


/* Coffee Shop Result */

.result {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-width: 0;
  min-height: 22rem;
  background-color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem;
  transition:
    transform 0.3s ease-in-out,
    border-color 0.3s ease-in-out;
}

.result:hover {
  border-color: rgb(53, 37, 19);
  transform: translateY(-.2rem);
}

.result a {
  color: #525459;
  text-decoration: none;
}

.result a:focus-visible,
.favorite button:focus-visible,
.search-bar input:focus-visible,
.search-bar button:focus-visible,
.search-home button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}


/* Coffee Shop Information */

.result-info {
  width: 100%;
}

.name {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 2.75rem;
  font-size: 1.05rem;
  font-weight: bold;
  line-height: 1.25;
  color: rgb(53, 37, 19);
}

.location-container {
  width: 100%;
  min-height: 3.5rem;
  font-size: .8rem;
  line-height: 1.4;
  margin-top: .25rem;
}

.location-container a {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.name:hover,
.location-container a:hover {
  text-decoration: underline;
}


/* Coffee Shop Image */

.image {
  width: 100%;
  border-top: .1rem #e8bb64 solid;
  padding-top: .75rem;
  margin-top: .5rem;
}

.image a {
  display: block;
}

.image img {
  display: block;
  width: 100%;
  max-width: 14rem;
  aspect-ratio: 1 / 1;
  object-fit: cover;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .25rem;
  margin: 0 auto .75rem;
  transition: border-color 0.3s ease-in-out;
}

.result:hover .image img {
  border-color: #e8bb64;
}


/* Favorite Button */

.favorite {
  width: 100%;
  border-top: .1rem #e8bb64 solid;
  margin-top: auto;
  padding-top: .75rem;
}

.favorite button {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: .4rem;
  width: 100%;
  color: rgb(53, 37, 19);
  background-color: transparent;
  border: none;
  padding: .25rem .5rem;
  transition:
    transform 0.3s ease-in-out,
    color 0.3s ease-in-out;
}

.favorite button:hover:not(:disabled) {
  color: #e8bb64;
  transform: scale(1.05);
}

.favorite button:disabled {
  cursor: default;
  opacity: .65;
}

.favorite img {
  width: 2rem;
  height: 1.6rem;
  object-fit: contain;
  border: none;
  padding: 0;
  margin: 0;
}

.favorite span {
  font-size: .85rem;
}


/* No Search Results */

.no-results {
  width: 90%;
  max-width: 40rem;
  background-color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem;
  margin: 0 auto 1.5rem;
}

.no-results p {
  font-size: .9rem;
  margin: 0;
}


/* Laptop - 1200px */
@media screen and (max-width: 1200px) {

  .results-container {
    grid-template-columns: repeat(3, 1fr);
  }

}


/* Tablet - 960px */
@media screen and (max-width: 960px) {

  .search-controls {
    width: 96%;
  }

  .results-section {
    width: 98%;
    padding: 0 .75rem;
  }

  .results-container {
    grid-template-columns: repeat(2, 1fr);
    gap: .75rem;
  }

}


/* Mobile - 700px */
@media screen and (max-width: 700px) {

  .search-controls {
    width: 96%;
    min-height: auto;
    padding: .85rem;
  }

  .search-bar {
    flex-direction: column;
    width: 100%;
    max-width: 28rem;
    margin-bottom: .5rem;
  }

  .search-bar label {
    width: 100%;
    margin: 0 0 .25rem;
  }

  .search-bar input {
    width: 100%;
    border-radius: .2rem;
  }

  .search-bar button {
    width: 7rem;
    align-self: center;
    margin-top: .4rem;
    border-radius: .2rem;
  }

  .search-home {
    margin: .35rem auto;
  }

  .results-section {
    width: 98%;
    padding: 0 .4rem;
  }

  .results-container {
    grid-template-columns: 1fr;
    gap: .5rem;
  }

  .result {
    width: 100%;
    max-width: none;
    margin: 0;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .search-controls {
    width: 98%;
    padding: .75rem;
  }

  .search-heading {
    font-size: .95rem;
  }

  .results-section {
    width: 100%;
    padding: 0 .3rem;
  }

  .result {
    padding: .75rem;
  }

  .name {
    font-size: 1rem;
  }

  .location-container {
    font-size: .78rem;
  }

  .favorite span {
    font-size: .8rem;
  }

}
</style>