<!-- Favorites.vue -->

<template>
  <article class="favorites-container">

    <!-- Favorites Count -->
    <p
      class="favorites-count"
      v-if="results.length > 0"
    >
      {{ results.length }}
      {{ results.length === 1 ? 'saved coffee shop' : 'saved coffee shops' }}
    </p>

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
              :alt="
                result.businessImage
                  ? result.businessName + ' coffee shop'
                  : 'No photo available for ' + result.businessName
              "
              title="Click for Yelp Page"
            />
          </a>
        </div>

        <!-- Delete Favorite -->
        <div class="button-container">
          <button
            type="button"
            @click="deleteFavorite(result.favoriteId)"
            :disabled="removingFavoriteId === result.favoriteId"
            :aria-label="'Delete ' + result.businessName + ' from favorites'"
            title="Delete Favorite"
          >
            <span
              class="remove-icon"
              aria-hidden="true"
            >
              🗑
            </span>

            <span>
              {{
                removingFavoriteId === result.favoriteId
                  ? 'Removing...'
                  : 'Remove'
              }}
            </span>
          </button>
        </div>

      </div>
    </section>

    <!-- Find More Coffee -->
    <div
      class="favorites-action-container"
      v-if="results.length > 0"
    >
      <router-link
        v-bind:to="{ name: 'locator' }"
        class="favorites-action"
      >
        Find More Coffee →
      </router-link>
    </div>

    <!-- No Favorites -->
    <section
      class="no-favorites"
      v-if="results.length === 0"
    >
      <p>
        Your coffee list is waiting for its first favorite.
        <router-link
          :to="{ name: 'locator' }"
          title="Find Coffee Shops"
        >
          Find a Coffee Shop →
        </router-link>
      </p>
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

      // Store the favorite currently being removed
      removingFavoriteId: null,

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
        })
        .catch(error => {
          console.error('Error fetching favorites:', error);
        });
    },

    // Delete a favorite coffee shop
    deleteFavorite(favoriteId) {
      const confirmDelete = confirm(
        'Are you sure you want to remove this coffee shop from your favorites?'
      );

      if (!confirmDelete) {
        return;
      }

      // Show that this favorite is being removed
      this.removingFavoriteId = favoriteId;

      FavoriteService.deleteFavorite(favoriteId)
        .then(() => {

          // Remove the deleted favorite from the page
          this.results = this.results.filter(
            result => result.favoriteId !== favoriteId
          );

        })
        .catch(error => {
          console.error('Error deleting favorite:', error);
        })
        .finally(() => {
          this.removingFavoriteId = null;
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

.favorites-count {
  width: 100%;
  text-align: right;
  font-size: .8rem;
  color: #525459;
  margin: 0 0 .75rem;
}


/* Favorites Grid */

.results-container {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 1rem;
  width: 100%;
}


/* Favorite Card */

.result {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-width: 0;
  background-color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  box-shadow: 0 .25rem .5rem rgba(53, 37, 19, .1);
  padding: 1rem;
  transition:
    transform 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.3s ease-in-out;
}

.result:hover {
  border-color: rgb(53, 37, 19);
  box-shadow: 0 .4rem .75rem rgba(53, 37, 19, .16);
  transform: translateY(-.2rem);
}


/* Coffee Shop Name */

.name {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 2.75rem;
}

.name a {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 2.75rem;
  font-size: 1.05rem;
  font-weight: bold;
  line-height: 1.25;
  color: rgb(53, 37, 19);
  text-align: center;
  text-decoration: none;
  transition: color 0.3s ease-in-out;
}

.name a:hover {
  color: #9b6a20;
  text-decoration: underline;
}


/* Coffee Shop Location */

.location-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-height: 3.5rem;
  margin-top: .25rem;
  color: #525459;
  text-align: center;
  text-decoration: none;
}

.location-container:hover {
  color: #9b6a20;
  text-decoration: underline;
}

.location-container .top,
.location-container .bottom {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.location-container a {
  font-size: .85rem;
  line-height: 1.4;
  color: #525459;
  text-align: center;
  text-decoration: none;
}


/* Coffee Shop Image */

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  border-top: .1rem #e8bb64 solid;
  padding-top: .75rem;
  margin-top: .5rem;
  overflow: hidden;
}

.image-container a {
  display: block;
  width: 100%;
}

.image-container img {
  display: block;
  width: 100%;
  aspect-ratio: 1 / 1;
  object-fit: cover;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  transition: transform 0.3s ease-in-out;
}

.image-container a:hover img {
  transform: scale(1.02);
}


/* Delete Favorite */

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  border-top: .1rem #e8bb64 solid;
  padding-top: .75rem;
  margin-top: .75rem;
}

.button-container button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 7rem;
  min-height: 2.75rem;
  font-size: .75rem;
  color: #8b1e1e;
  background-color: #f8eeee;
  border: .1rem #8b1e1e solid;
  border-radius: .2rem;
  padding: .4rem .6rem;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

.button-container button:hover:not(:disabled) {
  color: #ffffff;
  background-color: #8b1e1e;
  border-color: #8b1e1e;
  box-shadow:
    inset 0 .15rem .3rem rgba(0, 0, 0, .25),
    0 0 .3rem rgba(139, 30, 30, .25);
  transform: translateY(-.05rem);
}

.button-container button:disabled {
  cursor: wait;
  opacity: .75;
  transform: none;
}

.button-container button:active {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .35);
  transform: translateY(.05rem);
}

.remove-icon {
  font-size: .9rem;
  margin-right: .35rem;
  transition: transform 0.3s ease-in-out;
}

.button-container button:hover .remove-icon {
  transform: scale(1.1);
}

.name a:focus-visible,
.location-container a:focus-visible,
.image-container a:focus-visible,
.button-container button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}


/* Favorites Action */

.favorites-action-container {
  display: flex;
  justify-content: center;
  margin: 1.25rem auto .5rem;
}

.favorites-action {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  font-size: .85rem;
  font-weight: 600;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  text-decoration-color: #e8bb64;
  text-underline-offset: .2rem;
  transition: transform 0.3s ease-in-out;
}

.favorites-action:hover {
  transform: translateX(.2rem);
}

.favorites-action:focus-visible {
  outline: .1rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* No Favorite */

.no-favorites {
  width: 100%;
  text-align: center;
  background-color: rgb(245, 242, 242);
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  padding: 1rem 1.25rem;
}

.no-favorites p {
  font-size: .9rem;
  color: #525459;
  margin: 0;
}

.no-favorites a {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  color: rgb(53, 37, 19);
  font-weight: 600;
  text-decoration-color: #e8bb64;
  text-underline-offset: .15rem;
  transition: color 0.3s ease-in-out;
}

.no-favorites a:hover {
  color: #9b6a20;
}

.no-favorites a:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}


/* Tablet - 1000px */
@media screen and (max-width: 1000px) {

  .results-container {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

}


/* Tablet - 900px */
@media screen and (max-width: 900px) {

  .results-container {
    grid-template-columns: repeat(3, minmax(0, 1fr));
    gap: .75rem;
  }

}


/* Small Tablet - 700px */
@media screen and (max-width: 700px) {

  .results-container {
    grid-template-columns: repeat(2, minmax(0, 1fr));
    gap: .75rem;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .results-container {
    grid-template-columns: 1fr;
  }

  .result {
    width: 88%;
    max-width: 20rem;
    margin: 0 auto;
  }

  .name,
  .name a {
    min-height: 2.75rem;
  }

  .location-container {
    min-height: 2.75rem;
    margin-top: 0;
  }

  .image-container a {
    max-width: 13rem;
  }

}
</style>
