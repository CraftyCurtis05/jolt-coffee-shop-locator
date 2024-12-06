<template>
    <main class="favorites-container">
        <h1>Favorites</h1> 
        <p>Coffee Shop Delights: My Personal Selection</p> 
        <div class="results-container"> 
            <div class="result" v-for="result in results" :key="result.favoriteId">
                <div class="name">
                    <a v-bind:href="result.businessWebsite" target="_blank">{{ result.businessName }}</a>
                </div>
                <div class="address">
                    <a id="address1" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress)" target="_blank">{{ result.businessAddress }}</a>
                </div>
                <div>
                    <button id="delete" v-on:click="deleteFavorite()">delete</button>
                </div>
            </div>
        </div>
            <!-- No Search Results -->
        <div v-if="results.length === 0">
            <p>You have no favorites coffee shops saved yet!</p>
        </div>
    </main>
  </template>
  
<script>
import FavoriteService from '../services/FavoriteService.js';

export default {
    data() {
        return {
            results: []  // To store the user's favorites
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

        deleteFavorite(favorite) {
        FavoriteService.deleteFavorite(favorite)
            .then(() => {
                console.log('Favorite deleted successfully');
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
main {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    text-align: center;
    font-family: 'Ubuntu', sans-serif;
    width: 40vw;
}

h1 {
    font-size: 1.7vw;
    padding: 0;
    margin: 0;
}

.results-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    width: 40vw;
    border: .1vw black solid;
}

.result {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    text-align: center;
    font-size: 1vw;
    width: 10vw;
    height: 10vw;
    margin: 1vw;
    border: .1vw black solid;
    background-color: rgb(160, 153, 145);
}
</style>