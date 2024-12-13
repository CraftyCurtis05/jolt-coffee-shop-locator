<!-- Favorites.vue -->

<template>
    <article class="favorites-container">
        <header>
            <h3>Favorites</h3> 
            <h4>Coffee Shop Delights: My Personal Selection</h4>
        </header>

        <section class="results-container" v-for="result in results" :key="result.favoriteId"> 
            <div class="result">
                <div class="name">
                    <a v-bind:href="result.businessUrl" target="_blank">{{ result.businessName }}</a>
                </div>
                <div class="location-container">
                    <a id="address1" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessAddress1 }}</a>
                    <a id="address2" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessAddress2 }}</a>
                    <a id="city" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessCity }}</a>
                    <a id="state" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessState }}</a>
                    <a id="zipcode" :href="'https://www.google.com/maps/dir/?api=1&destination=' + encodeURIComponent(result.businessAddress1)" target="_blank">{{ result.businessZipcode }}</a>
                </div>
                <div class="image-container">
                    <a v-bind:href="result.businessUrl" target="_blank">
                        <img :src="result.businessImage">
                    </a>
                </div>
                <div class="button-container">
                    <button id="delete" @click="deleteFavorite(result.favoriteId)">Delete</button>
                </div>
            </div>
        </section>

        <!-- No Search Results -->
        <section v-if="results.length === 0">
            <p>You have no favorites coffee shops saved yet!</p>
        </section>

    </article>
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

</style>