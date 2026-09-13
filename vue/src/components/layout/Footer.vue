<!-- Footer.vue Component -->

<template>
  <footer v-if="!$route.meta.hideNavBar">

    <!-- Footer Content -->
    <article class="footer-container">

      <!-- Jolt Slogan -->
      <p class="slogan">
        Be Brew-tiful. Sip, Savor, Repeat.
      </p>

      <p class="footer-links-heading">
        Connect with Jennifer
      </p>

      <!-- Project Links -->
      <nav
        class="footer-links"
        aria-label="Jennifer's professional links"
      >
        <a
          href="https://github.com/CraftyCurtis05/jolt-coffee-shop-locator"
          target="_blank"
          rel="noopener noreferrer"
          title="Click to Go to Jennifer's Github"
        >
          GitHub
        </a>

        <a
          href="https://jennifercurtis.me/"
          target="_blank"
          rel="noopener noreferrer"
          title="Click to Go to Jennifer's Portfolio"
        >
          Portfolio
        </a>

        <a
          href="https://www.linkedin.com/in/jcurtisdeveloper/"
          target="_blank"
          rel="noopener noreferrer"
          title="Click to Go to Jennifer's LinkedIn"
        >
          LinkedIn
        </a>
      </nav>

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
  width: 100%;
  text-align: center;
  background-color: rgb(160, 153, 145);
  border-top: .1rem #333437 solid;
  padding: 1rem 0;
}

.footer-container {
  width: 100%;
  padding: 0 1rem;
}

.slogan {
  font-size: 1rem;
  font-weight: bold;
  color: rgb(234, 189, 99);
  margin: .5rem auto;
}

.footer-links-heading {
  font-size: .75rem;
  font-weight: 600;
  color: #525459;
  margin: 1.25rem 0 0;
}

.footer-links {
  display: flex;
  justify-content: center;
  align-items: center;
}

.footer-links a {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  font-size: .8rem;
  font-weight: 600;
  color: #333437;
  text-decoration: none;
  padding: 0 .25rem;
  transition:
    transform 0.3s ease-in-out,
    color 0.3s ease-in-out;
}

.footer-links a:hover {
  font-weight: 700;
  color: rgb(53, 37, 19);
  text-decoration: underline;
  transform: scale(1.02);
}

.footer-links a:focus-visible {
  outline: .15rem rgb(234, 189, 99) solid;
  outline-offset: .2rem;
}

footer span {
  display: block;
  color: #333437;
  font-size: .7rem;
  margin-top: .75rem;
}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  footer {
    padding: .8rem 0;
  }

  .footer-container {
    padding: 0 .75rem;
  }

  .slogan {
    font-size: .9rem;
    margin-bottom: 1rem;
  }

  .footer-links {
    gap: 1rem;
  }

  .footer-links a {
    font-size: .75rem;
  }

}
</style>
