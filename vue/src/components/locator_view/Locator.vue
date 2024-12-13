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
        <button @click="searchHome()" alt="Search Near Home Button" title="Click to Get Coffee Shops Near Saved Address">Search Near Home</button>
     </section>

    <h3 v-if="results.length > 0">List of local coffee shops near you:</h3>

    <!-- Search Results -->
    <section class="results-container">
      <div class="result" v-for="result in results" :key="result.id">

        <a class="name" :href="result.url" target="_blank" title="Click for Yelp Page">{{ result.name }}</a>

        <div class="address" title="Click for Directions">
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
            <img src="@/assets/locator_view/favorite_btn.png" alt="Favorite Button" title="Click to Add to Favorites">
            <h4>Add to Favorites</h4>
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
      defaultImage: 'src/assets/locator_view/default_image.png'
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
          this.results = response.data.businesses;
          console.log(this.results);
        })
        .catch(error => {
          alert('There was a problem fetching coffee shops! Please try again.');
          console.error('Error fetching Yelp results:', error);
        });
    },
    // Sending the favorite shop details to the backend
    setFavorite(result) {
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
  width: 100vw;
}

.search-bar {
  display: flex;
  flex-direction: row;
  width: 40%;
  height: 100%;
  font-size: 1.1rem;
  margin-bottom: 1vw;
}

.search-bar input {
  width: 100%;
  height: auto;
  font-size: 1.1rem;
}

.search-bar button,
.search-home button {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(53, 37, 19);
  width: 5rem;
  height: 1.7rem;
  font-size: .9rem;
  color: rgb(245, 242, 242);
  border: .1rem solid #e8bb64;
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
}

h3 {
  font-weight: bolder;
  font-size: 1.3rem;
  margin: 0;
  margin-top: 1vw;
}

.results-container {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
  width: 90%;
  margin-top: .5vw;
}

.result {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 2vw;
}

.result a {
  color: #525459;
  text-decoration: none;
}

.result .name {
  font-size: 1.1rem;
  font-weight: bold;
}

.result:hover {
  font-size: 1.1rem;
  background-color: #e8bb64;
  border-radius: .2vw;
  transition: all 0.5s;
}

.result img {
  width: 20vw;
  height: 20vw;
  margin-top: .5vw;
  border: .2vw rgb(53, 37, 19) solid;
  border-radius: .2vw;
}

.favorite button {
  background-color: transparent;
  border: none;
}

.favorite img {
  width: 2.9vw;
  height: 2.5vw;
  border: none;
}

.favorite img:hover {
  width: 3.2vw;
  height: 2.8vw;
  cursor: pointer;
}

h4 {
  font-weight: lighter;
  margin: 0;
}
</style>