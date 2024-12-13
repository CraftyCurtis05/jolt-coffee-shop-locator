<!-- App.vue -->
 <!-- npm run dev -->

<template>
  <body id="app">

    <nav>
      <NavBar/>
    </nav>

    <main>
      <router-view />
    </main>  

    <footer>
      <Footer />
    </footer>

  </body>
</template>

<script>
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
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
  text-align: center;
  font-family: 'Ubuntu', sans-serif;
  font-size: 18px;
  color: #333437;
  overflow-x: hidden;
}

main {
  width: 100vw;
  overflow-y: hidden;
}

h1 {
  font-size: 1.8rem;
  margin: 0;
}

h2 {
  font-size: 1rem;
  margin-top: .5vw;
}

/* Scrollbar */
body::-webkit-scrollbar {
  width: 20px;
}
        
body::-webkit-scrollbar-track {
  background: url("@/assets/app/scrollbar/scrollbar_track.png");
  background-position: -5px;
  background-repeat: repeat-y;
  background-size: 50px;
}
        
body::-webkit-scrollbar-thumb {
  background-image: url("@/assets/app/scrollbar/scrollbar_thumb.png");
  background-position:center center;
  background-repeat: no-repeat;
  background-size: 20px;
}
</style>