<template>
  <div class="favorites-container">
    <h1>Favorites</h1> 
    <p>Coffee Shop Delights: My Personal Selection</p> 
      <div class="favorites-display">
        {{ this.$store.state.favorites }}      <!--TODO: DISPLAY FAVORITE SHOP LIST -->
      </div>
  </div>
</template>

<script>
export default {
  computed: {
    notification() {
      return this.$store.state.notification;
    },
    notificationClass() {
      return {
        'status-message': true,
        error: this.notification?.type?.toLowerCase() === 'error',
        success: this.notification?.type?.toLowerCase() === 'success'
      };
    }
  },
  methods: {
    clearNotification() {
      this.$store.commit('CLEAR_NOTIFICATION');
    }
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
.favorites-container {
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-color: #f4f4f4;
}
.favorites-container {
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
}
.favorites-display {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  padding: 20px;
  margin-top: 20px;
}
</style>