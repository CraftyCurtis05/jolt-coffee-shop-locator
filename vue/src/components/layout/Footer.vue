<!-- Footer.vue Component -->

<template>
  <footer v-if="!$route.meta.hideNavBar">

    <!-- Footer Content -->
    <article class="footer-container">

      <!-- Jolt Slogan -->
      <p class="slogan">
        Be Brew-tiful. Sip, Savor, Repeat.
      </p>

      <!-- Social Media Links -->
      <section class="social-links">

        <!-- Facebook -->
        <a
          href="https://www.facebook.com/"
          target="_blank"
          rel="noopener noreferrer"
          class="social-link"
        >
          <img
            src="@/assets/app/social/facebook_logo.webp"
            alt="Facebook"
            title="Facebook"
          >
        </a>

        <!-- Instagram -->
        <a
          href="https://www.instagram.com/"
          target="_blank"
          rel="noopener noreferrer"
          class="social-link"
        >
          <img
            src="@/assets/app/social/instagram_logo.webp"
            alt="Instagram"
            title="Instagram"
          >
        </a>

        <!-- LinkedIn -->
        <a
          href="https://www.linkedin.com/"
          target="_blank"
          rel="noopener noreferrer"
          class="social-link"
        >
          <img
            src="@/assets/app/social/linkedIn_logo.webp"
            alt="LinkedIn"
            title="LinkedIn"
          >
        </a>

        <!-- Twitter -->
        <a
          href="https://www.twitter.com/"
          target="_blank"
          rel="noopener noreferrer"
          class="social-link"
        >
          <img
            src="@/assets/app/social/twitter_logo.webp"
            alt="Twitter"
            title="Twitter"
          >
        </a>

      </section>

      <!-- Copyright -->
      <span>
        &copy; {{ year }} {{ companyName }}
      </span>

    </article>
  </footer>
</template>

<script>
export default {
  name: 'Footer',

  data() {
    return {
      // Footer copyright information
      year: new Date().getFullYear(),
      companyName: 'Jolt Coffee Shop Locator'
    };
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

footer {
  width: 100vw;
  text-align: center;
  background-color: rgb(160, 153, 145);
  border-top: .1rem #333437 solid;
  padding: 1rem 0;
}

.footer-container {
  margin: 0 auto;
}

.slogan {
  font-size: 1rem;
  font-weight: bold;
  color: rgb(234, 189, 99);
  margin-top: .7rem;
}

.social-links {
  margin: 1rem 0;
}

.social-link {
  margin-inline: .5rem;
}

.social-link img {
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
}

footer span {
  color: #333437;
  font-size: .7rem;
}
</style>