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
  font-family: 'Ubuntu', sans-serif;
  font-size: 18px;
  overflow-x: hidden;
}

#header {
  position: fixed;
  width: 100%;
  z-index: 100;
}

header {
  text-align: center;
}

body::-webkit-scrollbar {
  width: 20px;
}
        
body::-webkit-scrollbar-track {
  background: url("@/assets/scrollbar/scrollbar_track.png");
  background-position: -5px;
  background-repeat: repeat-y;
  background-size: 50px;
}
        
body::-webkit-scrollbar-thumb {
  background-image: url("@/assets/scrollbar/scrollbar_thumb.png");
  background-position:center center;
  background-repeat: no-repeat;
  background-size: 20px;
}
</style>