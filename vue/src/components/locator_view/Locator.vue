<!-- Locator.vue Components -->

<template>
  <article class="locator-container">

    <!-- Search Bar -->
    <section class="search-bar">
      <input type="text" v-model="locationId" placeholder="Enter Your Location" title="Enter Your Search Location"/>
      <button @click="search()" alt="Search Location Button" title="Click to Get Coffee Shops">Search</button>
    </section>

    <!-- Near Home Search -->
     <section class="search-home">
        <button @click="searchHome()" alt="Search Near Home Button" title="Click to Get Coffee Shops Near Home">Search Near Home</button>
     </section>

    <h3 v-if="results.length > 0">List of Coffee Shops Near You:</h3>

    <!-- Search Results -->
    <section class="results-container">
      <div class="result" v-for="result in results" :key="result.id">

        <a class="name" :href="result.url" target="_blank" title="Click for Yelp Page">{{ result.name }}</a>

        <div class="location-container" title="Click for Directions">
          <div class="top">
            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">
              {{ result.location.address1 }},&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">
              {{ result.location.address2 }}
            </a>
          </div>
            
          <div class="bottom">
            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">
              {{ result.location.city }},&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">
              {{ result.location.state }}&nbsp;
            </a>

            <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.location.address1)" target="_blank">
              {{ result.location.zip_code }}
            </a>
          </div>  
        </div>

        <div class="image">  
          <a :href="result.url" target="_blank">
            <img :src="result.image_url || defaultImage" alt="Yelp Coffee Shop Image" title="Click for Yelp Page"/>
          </a>
        </div>

        <div class="favorite">
          <button @click="setFavorite(result)">
            <!-- Conditionally set the favorite button image -->
            <img :src="userFavorites.includes(result.id) ? 'src/assets/locator_view/favorite_added_btn.png' : 'src/assets/locator_view/favorite_btn.png'" alt="Favorite Button" title="Click to Add to Favorites">
            <h4>{{ userFavorites.includes(result.id) ? 'Added to Favorites' : 'Add to Favorites' }}</h4>
          </button>  
        </div>

      </div> 
    </section>

    <!-- No Search Results -->
    <section v-if="results.length === 0">
      <p>No results found. Please try a different location.</p>
    </section>

  </article>
</template>
  
<script>
import LocatorService from '../../services/LocatorService.js';
import FavoriteService from '../../services/FavoriteService.js';

export default {
  name: "Locator",
  data() {
    return {
      locationId: '', // LocationId variable to hold and pass location
      results: [], // Results array to hold the locator search results
      defaultImage: 'src/assets/locator_view/default_image.png',
      userFavorites: [] // Array to hold the list of favorited shops IDs for the user
    }
  },
  
  methods: {
    // Update search method to fetch data and populate results
    search() {
      this.clearResults();
      setTimeout(() => {
        this.$store.state.locationId = this.locationId;
        this.getResults(this.locationId);
      }, 500);
    },
    // Clear previous search results
    clearResults() {
      this.results = [];
      this.$store.state.locationId = null;
    },
    // Fetch the results from LocatorService
    getResults(locationId) {
      LocatorService.getCoffee(locationId)
        .then(response => {
          this.results = response.businesses;
          console.log(this.results);
        })
        .catch(error => {
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
        // Add the business ID to the userFavorites array after a successful favorite action
        this.userFavorites.push(result.id);
      }).catch(error => {
        console.error('Error adding favorite:', error);
      });
    },

    // Fetch the user's favorites from the database
    getUserFavorites() {
      FavoriteService.getFavorites()
      .then(response => {
        // Populate userFavorites with the list of favorited shop IDs
        this.userFavorites = response.map(favorite => favorite.businessId); // Assuming each favorite has a `businessId`
      })
      .catch(error => {
        console.error('Error fetching favorites:', error);
      });
  },
  },

  mounted() {
    // Fetch the user's favorite shops when the component is mounted
    this.getUserFavorites();
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
  font-size: 1.3rem;
  margin: 0;
  margin-top: 1.5rem;
}

.results-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  padding: 1rem 0;
}

.result {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 18rem;
  height: 20rem;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .1rem;
  padding: .8rem;
  margin: .5rem;
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
  width: 12rem;
  height: 12rem;
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

/* Laptop L - 1440px */

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  .search-bar {
    width: 55vw;
  }

  p {
    font-size: .9rem;
  }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .search-bar {
    width: 65vw;
  }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  .search-bar {
    width: 90vw;
  }

  p {
    font-size: .8rem;
  }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>