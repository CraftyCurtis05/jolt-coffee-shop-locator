<!-- Favorites.vue -->

<template>
    <article class="favorites-container">
        <header>
            <h1>Favorites</h1> 
            <h2>Coffee Shop Delights: My Personal Selection</h2>
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
/* Laptop L - 1440px */
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
    border-bottom: .4rem  #e8bb64 solid;
    padding: .5rem 0;
    margin-top: 0;
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
    width: 14rem;
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

.result .name {
    font-weight: bold;
    transition: all 0.5s ease-in-out;
}

.result:hover .name {
    font-size: 1.04rem;
}

.result .location-container {
    font-size: .9rem;
    transition: all 0.5s ease-in-out;
}

.result:hover .location-container {
    font-size: .94rem;
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

.result:hover img {
    border: .12rem rgb(53, 37, 19) solid;
    filter: grayscale(70%);
}

.result a {
    color: #525459;
    text-decoration: none;
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

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

    .result {
        width: 20rem;
        height: 21.5rem;
    }

    .result .name {
        font-size: 1.45rem;
    }

    .result .location-container {
        font-size: 1.3rem;
    }

    .result img {
        width: 14rem;
        height: 14rem;
    }

    button {
        width: 6rem;
        height: 2rem;
        font-size: 1.1rem;
        margin-top: .5vw;
    }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

    .result {
        width: 9rem;
        height: 10.5rem;
    }

    .result .name {
        font-size: .75rem;
    }

    .result .location-container {
        font-size: .65rem;
    }

    .result img {
        width: 7rem;
        height: 7rem;
        margin-top: .2vw;
    }

    button {
        width: 3.5rem;
        height: 1.1rem;
        font-size: .6rem;
        margin-top: .1vw;
    }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

    .result {
        width: 10rem;
        height: 11rem;
    }

    .result .name {
        font-size: .75rem;
    }

    .result .location-container {
        font-size: .65rem;
    }

    .result img {
        width: 7.5rem;
        height: 7.5rem;
        margin-top: .5vw;
    }

    button {
        width: 3.5rem;
        height: 1.1rem;
        font-size: .6rem;
        margin-top: 0;
        margin-bottom: .5vw;
    }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>