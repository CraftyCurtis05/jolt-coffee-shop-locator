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
/* Laptop L - 1440px */
html, body {
  width: 100vw;
  height: 100%;
  padding: 0;
  margin: 0;
  text-align: center;
  font-family: 'Raleway', sans-serif;
  font-size: 18px;
  color: #333437;
  overflow-x: hidden;
}

body {
  display: flex;
  flex-direction: column;
}

main {
  flex: 1;
  width: 100%;
  overflow-y: hidden;
}

header {
  margin-top: .5rem;
}

h1 {
  font-family: 'Ubuntu', sans-serif;
  font-size: 1.8rem;
  margin: 0 auto;
}

h2 {
  font-size: 1rem;
  margin-top: .4rem;
}

footer {
  
}

/* Scrollbar */
body::-webkit-scrollbar {
  width: 15px;
}
        
body::-webkit-scrollbar-track {
  background: url("@/assets/app/scrollbar/scrollbar_track.png");
  background-size: 15px;
}
        
body::-webkit-scrollbar-thumb {
  background-image: url("@/assets/app/scrollbar/scrollbar_thumb.png");
  background-position:center center;
  background-repeat: no-repeat;
  background-size: 15px;
}

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  
  header {
    margin-top: .9rem;
  }

  h1 {
    font-size: 3rem;
  }

  h2 {
    font-size: 1.7rem;
  }

  body::-webkit-scrollbar {
    width: 23px;
  }
          
  body::-webkit-scrollbar-track {
    background-size: 20px;
  }
          
  body::-webkit-scrollbar-thumb {
    background-size: 23px;
  }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  header {
    margin-top: .5rem;
  }

  h1 {
    font-size: 1.6rem;
  }

  h2 {
    font-size: .9rem;
  }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

  header {
    margin-top: .4rem;
  }

  h1 {
    font-size: 1.5rem;
  }

  h2 {
    font-size: .85rem;
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

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  header {
    width: 98%;
    margin-top: .35rem;
  }

  h1 {
    font-size: 1.4rem;
  }

  h2 {
    font-size: .80rem;
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

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

  header {
    margin-top: .30rem;
  }

  h1 {
    font-size: 1.3rem;
  }

  h2 {
    font-size: .75rem;
    max-width: 98%;
  }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

  h1 {
    font-size: 1.25rem;
  }

  h2 {
    font-size: .72rem;
  }
}
</style>