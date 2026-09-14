<template>
  <button
    v-if="isVisible"
    class="back-to-top"
    type="button"
    aria-label="Back to top"
    title="Back to Top"
    @click="scrollToTop"
  ></button>
</template>

<script>
export default {
  name: 'BackToTop',

  data() {
    return {
      isVisible: false
    };
  },

  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },

  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  },

  methods: {

    // Show the button after the user scrolls down the page
    handleScroll() {
      this.isVisible = window.scrollY > 400;
    },

    // Return the user to the top of the page
    scrollToTop() {
      const prefersReducedMotion =
        window.matchMedia('(prefers-reduced-motion: reduce)').matches;

      window.scrollTo({
        top: 0,
        behavior: prefersReducedMotion ? 'auto' : 'smooth'
      });
    }
  }
};
</script>

<style scoped>
.back-to-top {
  position: fixed;
  right: 1.25rem;
  bottom: 2rem;
  z-index: 1000;

  width: 3rem;
  height: 3rem;

  background-color: rgba(245, 242, 242, .15);
  color: rgb(53, 37, 19);

  border: none;
  border-radius: 50%;

  cursor: pointer;

  box-shadow: 0 .2rem .65rem rgba(53, 37, 19, .22);
  backdrop-filter: blur(5px);

  transition:
    transform .2s ease,
    background-color .2s ease,
    box-shadow .2s ease;
}

.back-to-top:hover {
  transform: translateY(-2px);
  box-shadow: 0 0.45rem 1rem rgba(0, 0, 0, 0.25);
}

.back-to-top:focus-visible {
  outline: 3px solid #ffc107;
  outline-offset: 3px;
}


/* Create the back-to-top chevron without an image */
.back-to-top::before,
.back-to-top::after {
  content: '';
  position: absolute;
  top: 50%;

  width: .9rem;
  height: .22rem;

  background-color: currentColor;
  border-radius: 1rem;
}

.back-to-top::before {
  left: 50%;
  transform: translate(-82%, -50%) rotate(-45deg);
}

.back-to-top::after {
  right: 50%;
  transform: translate(82%, -50%) rotate(45deg);
}


@media (max-width: 768px) {
  .back-to-top {
    right: 1rem;
    bottom: 1.25rem;
    width: 2.75rem;
    height: 2.75rem;
  }
}


@media (prefers-reduced-motion: reduce) {
  .back-to-top {
    transition: none;
  }
}
</style>
