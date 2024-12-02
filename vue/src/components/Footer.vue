<template>
  <footer class="footer">
    <div class="container">
      <span>&copy; {{ year }} {{ companyName }}</span>
      <p class="slogan">Be Brew-tiful. Sip, Savor, Repeat.</p>
      <div class="social-links">
        <a href="https://www.facebook.com/profile.php?id=100094171302714" target="_blank" class="social-link">
          <img src="src\assets\facebook.jpg" alt="Facebook">
        </a>
        <a href="https://www.instagram.com/joltcoffeeshoplocator/" target="_blank" class="social-link">
          <img src="src\assets\instagram.jpg" alt="Instagram">
        </a>
      </div>
    </div>
  </footer>
</template>

  
  <script>
  export default {
    data() {
      return {
        year: new Date().getFullYear(),
        companyName: "Jolt Coffee Shop Locator",
      };
    },
  };
  </script>
  
  <style scoped>
  .footer {
    background-color: transparent;
    padding: 20px 0;
    text-align: center;
  }
  .container {
    max-width: 1200px;
    margin: 0 auto;
  }
  .footer span {
    color: grey;
    font-size: 14px;
    line-height: 1.5;
  }
  .slogan {
    font-size: 15px; 
    margin-top: 10px; 
    font-weight: bold; 
    color: rgb(234,189,99);
  }
  .social-links {
    margin-top: 20px;
  }
  .social-link {
    margin-right: 20px; 
  }
  .social-link img {
    width: 30px; 
    height: 30px; 
    border-radius: 50%; 
  }
  </style>
  