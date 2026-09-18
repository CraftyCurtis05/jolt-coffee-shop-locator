<!-- Locator.vue Component -->

<template>
  <article class="locator-container">

    <!-- Search Controls -->
    <section class="search-controls">

      <!-- Search Heading -->
      <div class="search-heading">
        <span class="search-heading-accent"></span>

        <h3>
          Find Coffee Shops Near You
        </h3>
      </div>

      <!-- Location Search -->
      <form class="search-bar" @submit.prevent="search">
        <label for="location-search">
          Location:
        </label>

        <input
          id="location-search"
          type="text"
          v-model="locationId"
          placeholder="City, state or ZIP code"
          title="Enter a city, state or ZIP code"
        />

        <button
          type="submit"
          :disabled="isSearching"
          :title="
            isSearching
              ? 'Searching for Coffee Shops'
              : 'Click to Search for Coffee Shops'
          "
        >
          {{ isSearching ? 'Searching...' : 'Search' }}
        </button>
      </form>

      <!-- Search Near Home -->
      <section class="search-home" v-if="user">
        <button
          class="home-button"
          type="button"
          @click="searchHome()"
          :disabled="isSearching"
          :title="
            isSearching
              ? 'Searching for Coffee Shops Near Home'
              : 'Click to Search for Coffee Shops Near Home'
          "
        >
          <img
            :src="houseIcon"
            alt=""
          />
          {{ isSearching ? 'Searching...' : 'Search Near Home' }}
        </button>
      </section>

      <!-- Profile Required for Home Search -->
      <p
        v-else
        class="profile-search-hint"
      >
        <RouterLink to="/profile">
          Create a profile
        </RouterLink>
        to search for coffee shops near home.
      </p>

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

        <p class="results-source">
          Results by Yelp
        </p>

        <!-- Search Results Count -->
        <p class="results-count">
          {{ results.length }}
          {{ results.length === 1 ? 'result found' : 'results found' }}
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
              title="View on Yelp"
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
                title="Get Directions"
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
                title="View on Yelp"
              />
            </a>
          </section>

          <!-- Favorite Button -->
          <section class="favorite">
            <button
              type="button"
              @click="setFavorite(result)"
              :disabled="
                userFavorites.includes(result.id) ||
                savingFavoriteId === result.id
              "
              :aria-label="
                userFavorites.includes(result.id)
                  ? result.name + ' is saved'
                  : 'Save ' + result.name
              "
              :title="userFavorites.includes(result.id)
                ? 'Saved Coffee Shop'
                : 'Click to Save Coffee Shop'"
            >
              <img
                :src="userFavorites.includes(result.id)
                  ? favoriteAddedButton 
                  : favoriteButton"
                alt=""
              />

              <span>
                <span>
                  {{
                    savingFavoriteId === result.id
                      ? 'Saving...'
                      : userFavorites.includes(result.id)
                        ? 'Saved'
                        : 'Save Coffee Shop'
                  }}
                </span>
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
        <strong>No coffee shops found.</strong>
        Try another city, state or ZIP code.
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
      isSearching: false,

      // Locator images
      houseIcon: houseIcon,
      defaultImage: defaultImage,
      favoriteButton: favoriteButton,
      favoriteAddedButton: favoriteAddedButton,

      // Store the coffee shop currently being added to favorites
      savingFavoriteId: null
    };
  },

  computed: {

    // Get the user's saved favorite business IDs from the store
    userFavorites() {
      return this.$store.state.favorites.map(
        favorite => favorite.businessId
      );
    }
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
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Please enter a location.',
            type: 'info'
          }
        }));
        return;
      }

      const isZipCode = /^\d{5}$/.test(location);
      const isCityOrState = /^[A-Za-z][A-Za-z\s.'-]*$/.test(location);

      // Only allow a city, state or 5-digit ZIP code
      if (!isZipCode && !isCityOrState) {
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Please enter a city, state or 5-digit ZIP code.',
            type: 'warning'
          }
        }));
        return;
      }

      // Format multi-word locations for the Yelp search
      const searchLocation = isZipCode
        ? location
        : location.replace(/\s+/g, '_');

      // Clear the previous search and get new results
      this.clearResults();
      this.getResults(searchLocation);
    },

    // Clear previous search results
    clearResults() {
      this.results = [];
      this.hasSearched = false;
    },

    // Get coffee shops for a searched location
    getResults(locationId, fallbackLocation = null) {
      // Show that a coffee shop search is in progress
      this.isSearching = true;

      LocatorService
        .getCoffee(locationId)
        .then((response) => {

          // Store the coffee shops returned by Yelp
          this.results = response.businesses || [];
          this.hasSearched = true;
          this.isSearching = false;

        })
        .catch((error) => {

          // If the full home address cannot be found, try the saved ZIP code
          if (
            fallbackLocation &&
            error.response &&
            error.response.status === 400
          ) {

            this.getResults(fallbackLocation);
            return;
          }

          // Display an error if the search fails
          this.hasSearched = false;
          this.isSearching = false;

          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'There was a problem finding coffee shops. Please try again.',
              type: 'error'
            }
          }));
          console.error('Error fetching Yelp results:', error);
        });
    },

    // Add a coffee shop to the user's favorites
    setFavorite(result) {

      // Check if the shop is already favorited by the user
      if (this.userFavorites.includes(result.id)) {
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: "You've already saved this coffee shop.",
            type: 'warning'
          }
        }));
        return;
      }

      // Show that this favorite is being saved
      this.savingFavoriteId = result.id;

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
        .then((favorite) => {

          // Add the saved favorite to the shared favorites
          this.$store.commit(
            'SET_FAVORITES',
            [...this.$store.state.favorites, favorite]
          );

        })
        .catch((error) => {

          // Handle a favorite that already exists in the database
          if (error.response && error.response.status === 409) {

            window.dispatchEvent(new CustomEvent('app-notification', {
              detail: {
                message: "You've already saved this coffee shop.",
                type: 'warning'
              }
            }));

          } else {

            // Display an error if the favorite cannot be saved
            window.dispatchEvent(new CustomEvent('app-notification', {
              detail: {
                message: 'There was a problem saving this coffee shop. Please try again.',
                type: 'error'
              }
            }));
            console.error('Error adding coffee shop:', error);
          }
        })
        .finally(() => {
          // Allow the favorite button to be used again
          this.savingFavoriteId = null;
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
        window.dispatchEvent(new CustomEvent('app-notification', {
          detail: {
            message: 'Your home address is missing or incomplete. Please complete your profile.',
            type: 'warning'
          }
        }));
        return;
      }

      // Clear the manual search location when searching near home
      this.locationId = '';

      // Create the full address from the user's saved profile
      const fullAddress =
        `${address1}${address2 ? ', ' + address2 : ''}, ${city}, ${state} ${zipcode}`;

      // Clear previous results and search using the user's saved address
      this.clearResults();
      this.getResults(fullAddress, zipcode);
    }
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
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto .75rem;
}

.search-heading h3 {
  font-size: 1rem;
  color: rgb(53, 37, 19);
  margin: 0;
}

.search-heading-accent {
  width: 2.5rem;
  height: .15rem;
  background-color: #e8bb64;
  margin-bottom: .5rem;
}

.search-controls form {
  max-width: 90%;
}


/* Search Bar */

.search-bar label {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 6rem;
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


/* Search Near Home */

.search-home {
  width: 12rem;
  margin: .5rem auto;
}

.search-home button {
  flex-direction: row;
  gap: .4rem;
  width: 12rem;
}

.home-button img {
  flex-shrink: 0;
  width: 1.75rem;
  height: 1.75rem;
  object-fit: contain;
}


.profile-search-hint {
  font-size: .8rem;
  line-height: 1.4;
  background-color: rgba(232, 187, 100, .2);
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  padding-left: .75rem;
  margin: .5rem auto;
}

.profile-search-hint a {
  display: inline-block;
  font-weight: 600;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  text-decoration-color: #e8bb64;
  text-underline-offset: .2rem;
  transition:
    color 0.3s ease-in-out,
    transform 0.3s ease-in-out;
}

.profile-search-hint a:hover {
  color: #9b6a20;
  transform: scale(1.03);
}

.profile-search-hint a:focus-visible {
  color: #9b6a20;
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* Search Buttons */

.search-bar button {
  width: 7rem;
}

/* Connect the Search button to the location input */
.search-bar button {
  border-radius: 0 .2rem .2rem 0;
}

.search-bar button,
.search-home button {
  display: flex;
  flex-shrink: 0;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .75rem;
  margin: 0;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

.search-bar button:hover:not(:disabled),
.search-home button:hover:not(:disabled) {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  border-color: #e8bb64;
  box-shadow:
    inset 0 .15rem .3rem rgba(0, 0, 0, .35),
    0 0 .35rem rgba(232, 187, 100, .35);
  transform: translateY(.05rem);
}

.search-bar button:active,
.search-home button:active {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .45),
    0 0 .25rem rgba(232, 187, 100, .3);
  transform: translateY(.1rem);
}

.search-bar button:disabled,
.search-home button:disabled {
  cursor: wait;
  opacity: .75;
  transform: none;
}

.search-bar button:focus-visible,
.search-home button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}

.search-home button img {
  transition:
    filter 0.3s ease-in-out,
    transform 0.3s ease-in-out;
}

.search-home button:hover:not(:disabled) img {
  transform: scale(1.08);
  filter:
    brightness(0)
    saturate(100%)
    invert(77%)
    sepia(55%)
    saturate(600%)
    hue-rotate(358deg)
    brightness(100%)
    contrast(90%);
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

.results-header {
  width: 100%;
  text-align: center;
  border-bottom: .1rem #e8bb64 solid;
  margin-bottom: 1rem;
}

.results-header h3 {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  font-size: 1.2rem;
  color: rgb(245, 242, 242);
  margin: .25rem auto;
}

.results-source {
  font-size: .75rem;
  color: #e8bb64;
  margin: 0 auto 1rem;
}

.results-count {
  width: 100%;
  text-align: right;
  font-size: .8rem;
  color: #b4b8c2;
  margin: 0 0 .75rem;
}


/* Search Results Grid */

.results-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1rem;
  width: 100%;
  max-width: 96rem;
  margin: 0 auto;
  padding-bottom: 1.25rem;
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
  border: .15rem rgb(53, 37, 19) solid;
  border-radius: .25rem;
  box-shadow: 0 .25rem .5rem rgba(0, 0, 0, .18);
  padding: 1rem;
  transition:
    transform 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.3s ease-in-out;
}

.result:hover {
  border-color: #e8bb64;
  box-shadow: 0 .4rem .75rem rgba(0, 0, 0, .25);
  transform: translateY(-.2rem);
}

.result:focus-within {
  border-color: #e8bb64;
  box-shadow: 0 .4rem .75rem rgba(0, 0, 0, .25);
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

.search-bar input:focus-visible {
  border-color: #e8bb64;
  box-shadow: 0 0 .35rem rgba(232, 187, 100, .35);
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
  text-underline-offset: .15rem;
  transition:
    transform 0.3s ease-in-out,
    color 0.3s ease-in-out;
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
  width: 100%;
  min-height: 3.5rem;
  text-underline-offset: .15rem;
  transition:
    transform 0.3s ease-in-out,
    color 0.3s ease-in-out;
}

.name:hover {
  color: #9b6a20;
  text-decoration: underline;
  transform: scale(1.02);
}

.location-container a:hover {
  color: #9b6a20;
  text-decoration: underline;
  transform: scale(1.02);
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
  transition:
    border-color 0.3s ease-in-out,
    transform 0.3s ease-in-out;
}

.image a:hover img {
  transform: scale(1.02);
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
  color: #9b6a20;
  text-decoration: underline;
  transform: scale(1.05);
}

.favorite button:disabled {
  color: #6b6d72;
  cursor: default;
  opacity: .8;
}

.favorite img {
  width: 2rem;
  height: 1.6rem;
  object-fit: contain;
  border: none;
  padding: 0;
  margin: 0;
}

.favorite button:hover:not(:disabled) img {
  transform: scale(1.08);
}

.favorite span {
  font-size: .85rem;
}


/* No Search Results */

.no-results {
  width: 90%;
  max-width: 32rem;
  text-align: center;
  background-color: rgb(245, 242, 242);
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  padding: 1rem 1.25rem;
  margin: 0 auto 1.5rem;
}

.no-results strong {
  color: rgb(53, 37, 19);
  font-weight: 600;
}

.no-results p {
  font-size: .9rem;
  color: #525459;
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
    min-height: 2.75rem;
    border-radius: .2rem;
    padding: .25rem .5rem;
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

  .results-header {
    padding-top: .5rem;
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
    min-height: auto;
    padding: .75rem;
  }

  .result-info {
    margin-bottom: .25rem;
  }

  .image {
    padding-top: .4rem;
    margin-top: .25rem;
  }

  .image img {
    max-width: 10rem;
    margin: 0 auto .4rem;
  }

  .name {
    min-height: 2.75rem;
    font-size: 1rem;
  }

  .location-container {
    min-height: 2.75rem;
    font-size: .78rem;
    margin-top: 0;
  }

  .location-container a {
    min-height: 2.75rem;
  }

  .favorite {
    padding-top: .4rem;
  }

  .favorite span {
    font-size: .8rem;
  }

  .favorite button {
    min-height: 2.75rem;
    padding: .15rem .25rem;
  }

}
</style>
