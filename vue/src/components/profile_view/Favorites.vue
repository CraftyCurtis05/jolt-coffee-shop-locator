<!-- Favorites.vue -->

<template>
    <article class="favorites-container">
        <header>
            <h3>Favorites</h3> 
            <h4>Coffee Shop Delights: My Personal Selection</h4>
        </header>

        <section class="results-container"> 
            <div class="result" v-for="result in results" :key="result.favoriteId">

                <div class="name">
                    <a :href="result.businessUrl" target="_blank" title="Click for Yelp Page">{{ result.businessName }}</a>
                </div>

                <div class="location-container" title="Click for Directions">
                    <div class="top">
                        <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">
                            {{ result.businessAddress1 }}
                        </a>
                        <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">
                            {{ result.businessAddress2 }}
                        </a>
                    </div>

                    <div class="bottom">
                        <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">
                            {{ result.businessCity }},
                        </a>
                        <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">
                            {{ result.businessState }}&nbsp;
                        </a>
                        <a :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">
                            {{ result.businessZipcode }}
                        </a>
                    </div>
                </div>

                <div class="image-container">
                    <a :href="result.businessUrl" target="_blank">
                        <img :src="result.businessImage || defaultImage" alt="Yelp Coffee Shop Image" title="Click for Yelp Page">
                    </a>
                </div>

                <div class="button-container">
                    <button @click="deleteFavorite(result.favoriteId)" title="Delete Favorite">Delete</button>
                </div>

            </div>
        </section>

        <!-- No Search Results -->
        <section v-if="results.length === 0">
            <p>You have no favorite coffee shops saved yet!</p>
        </section>

    </article>
  </template>
  
<script>
import FavoriteService from '../../services/FavoriteService.js';

export default {
    name: 'Favorites',
    data() {
        return {
            results: [],  // To store the user's favorites
            defaultImage: 'src/assets/locator_view/default_image.png'
        };
    },
    methods: {
        getUserFavorites() {
        FavoriteService.getFavorites()
            .then(response => {
                console.log('API Response:', response); // Confirm API connection
                this.results = response; // Ensure it's an array
                console.log('Favorites Updated:', this.results); // Confirm update
            })
            .catch(error => {
                console.error('Error fetching favorites:', error); // Log error
            });
        },

        deleteFavorite(favoriteId) {
        FavoriteService.deleteFavorite(favoriteId)
            .then(() => {
                console.log('Favorite deleted successfully');
                // Update the UI by removing the favorite
                this.results = this.results.filter(result => result.favoriteId !== favoriteId);
            })
            .catch(error => {
                console.error('Error deleting favorite:', error);
            });
        },
    },
    mounted() {
        this.getUserFavorites();  // Fetch favorites when the component is mounted
    }  
};
</script>
  
<style scoped>
.favorites-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

header {
    width: 100%;
    color: rgb(245, 242, 242);
    background-color: rgb(53, 37, 19);
    border-bottom: .4vw  #e8bb64 solid;
}

h3 {
  font-weight: bolder;
  font-size: 1.3rem;
  margin: 0;
  margin-top: 1vw;
}

h4 {
    font-size: 1rem;
    font-weight: 200;
    margin: .5vw;
}

.results-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    margin-top: 1vw;
}

.result {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    width: 20vw;
    height: 20vw;
    padding: .8rem;
}

.result:hover {
  font-size: 1.1rem;
  background-color: #e8bb64;
  border-radius: .2vw;
  transition: all 0.5s;
}

.result a {
  color: #525459;
  text-decoration: none;
}

.result .name {
  font-size: .9rem;
  font-weight: bold;
}

.result .location-container {
    font-size: .8rem;
}

.result img {
    width: 15vw;
    height: 15vw;
    margin-top: .5vw;
    border: .2vw rgb(53, 37, 19) solid;
    border-radius: .2vw;
}

button {
    width: 5rem;
    height: 1.3rem;
    font-size: .7rem;
    color: rgb(53, 37, 19);
    background-color: #e8bb64;
    border-radius: 4px;
    margin-inline: .3vw;
}

button:hover {
    color: #e8bb64;
    background-color: rgb(53, 37, 19);
    cursor: pointer;
}
</style>