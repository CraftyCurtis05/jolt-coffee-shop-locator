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

header {
  margin-top: 1rem;
}

h1 {
  font-size: 1.8rem;
  margin: 0 auto;
}

h2 {
  font-size: 1rem;
  margin-top: .4rem;
}

/* Scrollbar */
body::-webkit-scrollbar {
  width: 20px;
}
        
body::-webkit-scrollbar-track {
  background: url("@/assets/app/scrollbar/scrollbar_track.png");
  background-size: 20px;
}
        
body::-webkit-scrollbar-thumb {
  background-image: url("@/assets/app/scrollbar/scrollbar_thumb.png");
  background-position:center center;
  background-repeat: no-repeat;
  background-size: 20px;
}

@media screen and (max-width: 768px) {

  header {
    margin-top: .7rem;
  }

  h1 {
    font-size: 1.4rem;
  }

  h2 {
    font-size: .9rem;
  }

  body::-webkit-scrollbar {
    width: 10px;
  }
          
  body::-webkit-scrollbar-track {
    background-size: 16px;
  }
          
  body::-webkit-scrollbar-thumb {
    background-size: 10px;
  }
}

@media screen and (max-width: 425px) {

  header {
    width: 90%;
    margin-top: .4rem;
    margin-left: .8rem;
  }

  h1 {
    font-size: 1.2rem;
  }

  h2 {
    font-size: .81rem;
  }

  body::-webkit-scrollbar {
    width: 7px;
  }
          
  body::-webkit-scrollbar-track {
    background-size: 13px;
  }
          
  body::-webkit-scrollbar-thumb {
    background-size: 7px;
  }
}
</style>