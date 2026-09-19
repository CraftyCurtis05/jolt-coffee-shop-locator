<!-- Favorites.vue -->

<template>
  <article class="favorites-container">

    <!-- Favorites Count -->
    <p
      class="favorites-count"
      v-if="results.length > 0"
      role="status"
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
            :aria-label="'View ' + result.businessName + ' on Yelp'"
          >
            {{ result.businessName }}
          </a>
        </div>

        <!-- Coffee Shop Location -->
        <div class="location-container">
          <div class="top">
            <a
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
              :aria-label="'Get directions to ' + result.businessName"
            >
              {{ result.businessAddress1 }}
            </a>

            <a
              v-if="result.businessAddress2"
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
              :aria-label="'Get directions to ' + result.businessName"
            >
              {{ result.businessAddress2 }}
            </a>
          </div>

          <div class="bottom">
            <a
              :href="getDirectionsUrl(result)"
              target="_blank"
              rel="noopener noreferrer"
              :aria-label="'Get directions to ' + result.businessName"
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
            :aria-label="'View ' + result.businessName + ' on Yelp'"
          >
            <img
              :src="result.businessImage || defaultImage"
              alt=""
              loading="lazy"
            />
          </a>
        </div>

        <!-- Delete Favorite -->
        <div class="button-container">
          <button
            type="button"
            @click="deleteFavorite(result.favoriteId)"
            :disabled="removingFavoriteId !== null"
            :aria-label="'Remove ' + result.businessName + ' from saved coffee shops'"
            aria-live="polite"
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
        :to="{ name: 'locator' }"
        class="favorites-action"
      >
        Find More Coffee Shops →
      </router-link>
    </div>

    <!-- No Favorites -->
    <section
      class="no-favorites"
      v-if="results.length === 0"
      role="status"
    >
      <p>
        No coffee shops saved yet. Find one to start your list.
        <router-link
          :to="{ name: 'locator' }"
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
      // Store the favorite currently being removed
      removingFavoriteId: null,

      // Default image used when a coffee shop has no image
      defaultImage: defaultImage
    };
  },

  computed: {

    // Get the user's favorite coffee shops from the store
    results() {
      return this.$store.state.favorites;
    }
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

    // Delete a favorite coffee shop
    deleteFavorite(favoriteId) {

      // Prevent multiple delete requests at the same time
      if (this.removingFavoriteId !== null) {
        return;
      }

      const confirmDelete = confirm(
        'Are you sure you want to remove this coffee shop from your saved list?'
      );

      if (!confirmDelete) {
        return;
      }

      // Show that this favorite is being removed
      this.removingFavoriteId = favoriteId;

      FavoriteService.deleteFavorite(favoriteId)
        .then(() => {

          // Remove the deleted favorite from the shared favorites
          this.$store.commit(
            'SET_FAVORITES',
            this.$store.state.favorites.filter(
              favorite => favorite.favoriteId !== favoriteId
            )
          );

        })
        .catch(error => {
          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'There was a problem removing this coffee shop. Please try again.',
              type: 'error'
            }
          }));

          console.error('Error deleting coffee shop:', error);
        })
        .finally(() => {
          this.removingFavoriteId = null;
        });
    }
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
  color: var(--color-text-muted);
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
  background-color: var(--color-surface);
  border: .15rem var(--color-accent) solid;
  border-radius: var(--border-radius);
  box-shadow: 0 .25rem .5rem rgba(53, 37, 19, .1);
  padding: 1rem;
  transition:
    transform var(--transition-speed) ease-in-out,
    border-color var(--transition-speed) ease-in-out,
    box-shadow var(--transition-speed) ease-in-out;
}

.result:hover,
.result:focus-within {
  border-color: var(--color-coffee);
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
  color: var(--color-coffee);
  text-align: center;
  text-decoration: none;
  transition: color var(--transition-speed) ease-in-out;
}

.name a:hover {
  color: var(--color-accent-dark);
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
  color: var(--color-text-muted);
  text-align: center;
  text-decoration: none;
}

.location-container a:hover {
  color: var(--color-accent-dark);
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
  color: var(--color-text-muted);
  text-align: center;
  text-decoration: none;
}


/* Coffee Shop Image */

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  border-top: .1rem var(--color-accent) solid;
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
  border: .1rem var(--color-coffee) solid;
  border-radius: .2rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.image-container a:hover img {
  transform: scale(1.02);
}


/* Delete Favorite */

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
  border-top: .1rem var(--color-accent) solid;
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
    background-color var(--transition-speed) ease-in-out,
    color var(--transition-speed) ease-in-out,
    border-color var(--transition-speed) ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

.button-container button:hover:not(:disabled) {
  color: var(--color-white);
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

.button-container button:active:not(:disabled) {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .35);
  transform: translateY(.05rem);
}

.remove-icon {
  font-size: .9rem;
  margin-right: .35rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.button-container button:hover:not(:disabled) .remove-icon {
  transform: scale(1.1);
}

.name a:focus-visible,
.location-container a:focus-visible,
.image-container a:focus-visible,
.button-container button:focus-visible {
  outline: var(--focus-outline-width) var(--color-accent) solid;
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
  color: var(--color-coffee);
  text-decoration: underline;
  text-decoration-color: var(--color-accent);
  text-underline-offset: .2rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.favorites-action:hover {
  transform: translateX(.2rem);
}

.favorites-action:focus-visible {
  outline: .1rem var(--color-accent) solid;
  outline-offset:var(--focus-outline-offset);
}


/* No Favorite */

.no-favorites {
  width: 100%;
  text-align: center;
  background-color: rgba(232, 187, 100, .2);
  border-left: .2rem rgb(156, 105, 33, .8) solid;
  padding: 1rem 1.25rem;
}

.no-favorites p {
  font-size: .9rem;
  color: var(--color-text-muted);
  margin: 0;
}

.no-favorites a {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  color: var(--color-coffee);
  font-weight: 600;
  text-decoration-color: var(--color-accent);
  text-underline-offset: .15rem;
  transition: color var(--transition-speed) ease-in-out;
}

.no-favorites a:hover {
  color: var(--color-accent-dark);
}

.no-favorites a:focus-visible {
  outline: var(--focus-outline-width) var(--color-accent) solid;
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
