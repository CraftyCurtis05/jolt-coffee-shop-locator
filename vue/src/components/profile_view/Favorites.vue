<!-- Favorites.vue -->

<template>
    <section class="favorites-container">
        <h1>Favorites</h1> 
        <p>Coffee Shop Delights: My Personal Selection</p> 
        <div class="results-container" v-for="result in results" :key="result.favoriteId"> 
            <div class="result">
                <div class="name">
                    <a v-bind:href="result.businessUrl" target="_blank">{{ result.businessName }}</a>
                </div>
                <div class="address">
                    <a id="address1" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessAddress1 }}</a>
                    <a id="address2" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessAddress2 }}</a>
                    <a id="city" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessCity }}</a>
                    <a id="state" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessState }}</a>
                    <a id="zipcode" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessZipcode }}</a>
                </div>
                <div class="image">
                    <a v-bind:href="result.businessUrl" target="_blank"><img :src="result.businessImage"></a>
                </div>
                <div>
                    <button id="delete" @click="deleteFavorite(result.favoriteId)">Delete</button>
                </div>
            </div>
        </div>
            <!-- No Search Results -->
        <div v-if="results.length === 0">
            <p>You have no favorites coffee shops saved yet!</p>
        </div>
    </section>
  </template>
  
<script>
import FavoriteService from '../../services/FavoriteService.js';

export default {
    name: 'Favorites',
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
section {
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

.image img {
    width: 5vw;
}
</style>