<!-- Footer.vue Component -->

<template>
  <footer v-if="!$route.meta.hideNavBar">

    <div class="footer-container">
      <p class="slogan">Be Brew-tiful. Sip, Savor, Repeat.</p>

      <div class="social-links">
        <a href="https://www.facebook.com/" target="_blank" class="social-link">
          <img src="@/assets/app/social_media/facebook_logo.png" alt="Facebook">
        </a>

        <a href="https://www.instagram.com/" target="_blank" class="social-link">
          <img src="@/assets/app/social_media/instagram_logo.png" alt="Instagram">
        </a>

        <a href="https://www.linkedin.com/" target="_blank" class="social-link">
          <img src="@/assets/app\social_media/linkedIn_logo.png" alt="LinkedIn">
        </a>

        <a href="https://www.twitter.com/" target="_blank" class="social-link">
          <img src="@/assets/app/social_media/twitter_logo.png" alt="Twitter">
        </a>
        
      </div>
      <span>&copy; {{ year }} {{ companyName }}</span>
    </div>
  </footer>
</template>

  
<script>
export default {
  name: 'Footer',
  data() {
    return {
      year: new Date().getFullYear(),
      companyName: "Jolt Coffee Shop Locator"
    };
  }
};
</script>
  
<style scoped>
footer {
  background-color: transparent;
  text-align: center;
  background-color: rgb(160, 153, 145);
  border-top: .1rem #333437 solid;
  padding: 1rem 0;
  margin-top: 1rem;
}

.container-footer {
  max-width: 1200px;
  margin: 0 auto;
}

footer span {
  color: #333437;
  font-size: 14px;
  line-height: 1.5;
}

.slogan {
  font-size: 1rem; 
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
