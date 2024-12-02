<template>
  <div>
    <h1>Profile</h1>
    <p>Jolt Up Your Profile</p>
    <div class="favorites-list">
      <h3>Your Favorite Coffee Shops</h3>
      <ul>
        <li v-for="favorite in favorites" :key="favorite.id">
          <a :href="favorite.url" target="_blank">{{ favorite.name }}</a> - {{ favorite.address }}
        </li>
      </ul>
    </div>
    <div class="favorites-link"><a href="/favorites">Go to Favorites</a></div>
    <div id="change-profile-pic">
        <h3>Change Profile Picture</h3>
        <ProfilePictureUpload />
    </div>
  </div>
</template>

<script>
import ProfilePictureUpload from '../components/ProfilePictureUpload.vue';
import JavaService from '../services/FavoriteService';

export default {
  components: {
    ProfilePictureUpload
  },
  data() {
    return {
      favorites: []  // To store the user's favorites
    };
  },
  methods: {
    getUserFavorites() {
      JavaService.getFavorites()  // Assuming you have this service method
        .then(response => {
          this.favorites = response.data;  // Store the favorites in the data
        })
        .catch(error => {
          console.log('Error fetching favorites:', error);
        });
    }
  },
  mounted() {
    this.getUserFavorites();  // Fetch favorites when the component is mounted
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

h1{
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 35px; /* Adjust the font size as needed */
  margin: 0;
}
p {
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Nunito Sans', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  margin-top: 10px; /* Add some spacing */
}
#change-profile-pic {
  display: flex;
  position: relative;
  left: 100px;
}
h3 {
  display: flex;
  position: relative;
  left: 425px;
  bottom: -30px;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
}
a {
  text-decoration: none;
  font-size: 20px;
  display: flex;
  position: relative;
  left: 500px;
}
</style>