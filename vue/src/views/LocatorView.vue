<template>
  <div class="autocomplete-container" id="autocomplete-container"></div>
  <div class="autocomplete-container" id="autocomplete-container-country"></div>
  <div class="autocomplete-container" id="autocomplete-container-city"></div>
  <div id="links" class="text-center">
    <h1>Locator</h1>
    <p>Uncover your next coffee adventure. Locate the perfect brew near you</p>  
      <div class="search-bar">
        <LocatorSearchBar/>
      </div>
    </div>
  </template>
  
  <script>
  import LocatorSearchBar from '../components/LocatorSearch.vue';

  export default {
    components: {
      LocatorSearchBar
    }
  };
  </script>
  
  <style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');
  
  body {
    text-align: center;
  }
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
  .search-bar {
  margin-top: 40px; /* Adjust the margin-top value to move the search bar down */
  }
  .list-search-results {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center; /* Center text content within the list */
  }
  .list-search-results img {
  display: block;
  margin: 0 auto;
  width: 200px !important;
  height: auto !important;
  }
  /* Center the text content within each result item */
.list-search-results .result-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
}
/* Center the images within each result item */
.list-search-results .result-item img {
  display: block;
  margin: 0 auto;
  width: 200px !important;
  height: auto !important;
}


  </style>