<!-- Favorites.vue -->

<template>
  <article class="favorites-container">

    <!-- Favorites Header -->
    <header>
      <h1>Favorites</h1>
      <h2>Coffee Shop Delights: My Personal Selection</h2>
    </header>

    <!-- Favorite Coffee Shops -->
    <section class="results-container">
      <div
        class="result"
        v-for="result in results"
        :key="result.favoriteId"
      >

        <!-- Coffee Shop Name -->
        <div class="name">
          <a
            :href="result.businessUrl"
            target="_blank"
            rel="noopener noreferrer"
            title="Click for Yelp Page"
          >
            {{ result.businessName }}
          </a>
        </div>

        <!-- Coffee Shop Location -->
        <div
          class="location-container"
          title="Click for Directions"
        >
          <div class="top">
            <a
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.businessAddress1 }}
            </a>

            <a
              v-if="result.businessAddress2"
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.businessAddress2 }}
            </a>
          </div>

          <div class="bottom">
            <a
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
            >
              {{ result.businessCity }},
              {{ result.businessState }}
              {{ result.businessZipcode }}
            </a>
          </div>
        </div>

        <!-- Coffee Shop Image -->
        <div class="image-container">
          <a
            :href="result.businessUrl"
            target="_blank"
            rel="noopener noreferrer"
          >
            <img
              :src="result.businessImage || defaultImage"
              alt="Yelp Coffee Shop Image"
              title="Click for Yelp Page"
            />
          </a>
        </div>

        <!-- Delete Favorite -->
        <div class="button-container">
          <button
            type="button"
            @click="deleteFavorite(result.favoriteId)"
            title="Delete Favorite"
          >
            Delete
          </button>
        </div>

      </div>
    </section>

    <!-- No Favorites -->
    <section v-if="results.length === 0">
      <p>You have no favorite coffee shops saved yet!</p>
    </section>

  </article>
</template>

<script>
import FavoriteService from '../../services/FavoriteService.js';
import defaultImage from '../../assets/locator/default_image.webp';

export default {
  name: 'Favorites',

  data() {
    return {
      // Store the user's favorite coffee shops
      results: [],

      // Default image used when a coffee shop has no image
      defaultImage: defaultImage
    };
  },

  methods: {

    // Build the full coffee shop address for Google Maps
    getDirectionsAddress(result) {
      return [
        result.businessAddress1,
        result.businessAddress2,
        result.businessCity,
        result.businessState,
        result.businessZipcode
      ]
        .filter(Boolean)
        .join(', ');
    },

    // Build the Google Maps directions URL
    getDirectionsUrl(result) {
      const address = this.getDirectionsAddress(result);

      return 'https://www.google.com/maps/dir/?api=1&destination=' +
        encodeURIComponent(address);
    },

    // Get the user's saved favorites
    getUserFavorites() {
      FavoriteService.getFavorites()
        .then(response => {
          this.results = response || [];

          // *DEBUG* Log the user's favorites for debugging
          // console.log('Favorites:', this.results);
        })
        .catch(error => {
          console.error('Error fetching favorites:', error);
        });
    },

    // Delete a favorite coffee shop
    deleteFavorite(favoriteId) {
      FavoriteService.deleteFavorite(favoriteId)
        .then(() => {

          // Remove the deleted favorite from the page
          this.results = this.results.filter(
            result => result.favoriteId !== favoriteId
          );

          // *DEBUG* Log the deleted favorite for debugging
          // console.log('Favorite deleted:', favoriteId);
        })
        .catch(error => {
          console.error('Error deleting favorite:', error);
        });
    }
  },

  mounted() {
    // Get the user's favorites when the component loads
    this.getUserFavorites();
  }
};
</script>
  
<style scoped>
/* Laptop L - 1440px */

.favorites-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

header {
  text-align: center;
}

header h1 {
  font-size: 1.8rem;
  color: #e8bb64;
  margin-bottom: 0;
}

header h2 {
  font-size: 1rem;
  font-weight: 400;
  color: rgb(245, 242, 242);
  margin-top: .3rem;
}

.results-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 100%;
}

.result {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20vw;
  background-color: rgb(160, 153, 145);
  border: .5vw rgb(160, 153, 145) solid;
  margin: 1vw;
}

.name {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: .5rem;
}

.name a {
  font-size: 1.1rem;
  font-weight: bold;
  color: rgb(53, 37, 19);
  text-align: center;
  text-decoration: none;
}

.name a:hover {
  color: #e8bb64;
}

.location-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: .5rem;
}

.location-container .top,
.location-container .bottom {
  display: flex;
  justify-content: center;
}

.location-container a {
  font-size: .8rem;
  color: rgb(53, 37, 19);
  text-align: center;
  text-decoration: none;
}

.location-container a:hover {
  color: #e8bb64;
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 18vw;
  height: 18vw;
  overflow: hidden;
}

.image-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

.button-container button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 6rem;
  height: 1.5rem;
  font-size: .7rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border-radius: .1rem;
  margin: .5rem auto;
  transition: all 0.5s ease-in-out;
}

.button-container button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
}
</style>