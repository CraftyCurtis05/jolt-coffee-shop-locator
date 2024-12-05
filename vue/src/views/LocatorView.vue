<template>
  <body>
    <h1>Locator</h1>
    <p>Uncover your next coffee adventure. Locate the perfect brew near you</p>  
    <SearchBar/>
  </body>
</template>
  
<script>
import SearchBar from '../components/SearchBar.vue';

export default {
  components: {
    SearchBar
  }
};
</script>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
}

h1{
  font-weight: bolder;
  font-size: 1.8rem;
  text-align: center;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: .5vw;
}
</style>