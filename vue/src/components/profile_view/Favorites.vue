<!-- Favorites.vue -->

<template>
    <article class="favorites-container">
        <div class="header-container">
            <h3>Favorites</h3> 
            <h4>Coffee Shop Delights: My Personal Selection</h4>
        </div>    

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

.header-container {
    width: 100%;
    color: rgb(245, 242, 242);
    background-color: rgb(53, 37, 19);
    border-bottom: .4rem  #e8bb64 solid;
    padding: .5rem 0;
}

h3 {
    font-weight: bolder;
    font-size: 1.3rem;
    margin: 0;
    margin-top: 1rem;
}

h4 {
    font-size: 1rem;
    font-weight: lighter;
    margin: .5rem;
}

.results-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    margin: 1rem 0;
}

.result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 15rem;
    height: 15rem;
    border: .1rem rgb(53, 37, 19) solid;
    border-radius: .1rem;
    padding: .8rem;
    margin: 1rem;
    transition: all 0.6s ease-in-out;
}

.result:hover {
    background-color: #e8bb64;
    transform: scale(1.1);
}

.result:hover .name {
    font-size: 1.04rem;
}

.result:hover .location-container {
    font-size: .94rem;
}

.result:hover img {
    border: .12rem rgb(53, 37, 19) solid;
    filter: grayscale(70%);
}

.result a {
    color: #525459;
    text-decoration: none;
}

.result .name {
    font-weight: bold;
    transition: all 0.5s ease-in-out;
}

.result .location-container {
    font-size: .9rem;
    transition: all 0.5s ease-in-out;
}

.result .name:hover,
.result .location-container:hover {
    text-decoration: underline;
}

.result img {
    width: 10rem;
    height: 10rem;
    margin-top: .5rem;
    border: .1rem rgb(53, 37, 19) solid;
    border-radius: .1rem;
    transition: all 0.5s ease-in-out;
}

.result img:hover {
    transform: scale(1.05);
}

button {
    width: 5rem;
    height: 1.3rem;
    font-size: .7rem;
    color: rgb(53, 37, 19);
    background-color: #e8bb64;
    border-radius: .1rem;
    transition: all 0.5s ease-in-out;
}

button:hover {
    color: #e8bb64;
    background-color: rgb(53, 37, 19);
    cursor: pointer;
}
</style>