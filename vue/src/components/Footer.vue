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
            src="@/assets/app/social_media/facebook_logo.webp"
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
            src="@/assets/app/social_media/instagram_logo.webp"
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
            src="@/assets/app/social_media/linkedIn_logo.webp"
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
            src="@/assets/app/social_media/twitter_logo.webp"
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


/* 4K - 2560px */
@media screen and (min-width: 2560px) {

  footer {
    padding: 2rem 0;
  }

  .slogan {
    font-size: 1.6rem;
  }

  .social-links {
    margin: 2rem 0;
  }

  .social-link img {
    width: 3rem;
    height: 3rem;
  }

  footer span {
    font-size: 1.2rem;
  }
}


/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  footer {
    padding: .75rem 0;
  }

  .slogan {
    font-size: .9rem;
  }

  .social-links {
    margin: .9rem 0;
  }

  .social-link {
    margin-inline: .4rem;
  }

  .social-link img {
    width: 1.8rem;
    height: 1.8rem;
  }

  footer span {
    font-size: .7rem;
  }
}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  footer {
    padding: .5rem 0;
  }

  .slogan {
    font-size: .85rem;
  }

  .social-links {
    margin: .7rem 0;
  }

  .social-link {
    margin-inline: .35rem;
  }

  .social-link img {
    width: 1.6rem;
    height: 1.6rem;
  }

  footer span {
    font-size: .65rem;
  }
}


/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  footer {
    padding: .2rem 0;
  }

  .slogan {
    font-size: .8rem;
  }

  .social-links {
    margin: .5rem 0;
  }

  .social-link {
    margin-inline: .25rem;
  }

  .social-link img {
    width: 1.5rem;
    height: 1.5rem;
  }

  footer span {
    font-size: .6rem;
  }
}


/* Mobile M - 375px */
@media screen and (max-width: 375px) {

  footer {
    padding: .1rem 0;
  }

  .social-links {
    margin: .25rem 0;
  }
}


/* Mobile S - 320px */
@media screen and (max-width: 320px) {

  footer {
    padding: 0;
  }

  .slogan {
    font-size: .75rem;
  }

  .social-links {
    margin: 0;
  }

  .social-link {
    margin-inline: .25rem;
  }

  .social-link img {
    width: 1.35rem;
    height: 1.35rem;
  }

  footer span {
    font-size: .55rem;
  }
}
</style>