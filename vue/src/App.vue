<template>
  <body id="app">
    <div id="header">
      <NavBar/>
    </div>
    <main>
      <router-view />
    </main>  
    <div id="footer">
      <Footer />
    </div>
  </body>
</template>

<script>
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';

export default {
  components: {
    NavBar,
    Footer
  },

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

<style>
html, body {
  width: 100vw;
  height: 100%;
  padding: 0;
  margin: 0;
  font-size: 18px;
  overflow-x: hidden;
}

#header {
  position: fixed;
  width: 100%;
  z-index: 100;
}

#footer {

}

html::-webkit-scrollbar {
  width: 20px;
}
        
html::-webkit-scrollbar-track {
  border-radius: 8px;
  background-color: red;
  /* background-image: url("https://i.ibb.co/5150925/tracks.png"); */
  background-position: -5px;
  background-repeat: repeat-y;
  background-size: 50px;
}
        
html::-webkit-scrollbar-thumb {
  border-radius: 8px;
  /* background-image: url("https://i.ibb.co/q95CBdZ/train.png"); */
  background-position:center center;
  background-repeat: no-repeat;
  background-size: 35px;
}
</style>