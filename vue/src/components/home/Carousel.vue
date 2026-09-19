<!-- Carousel.vue Component -->

<template>

  <!-- Image Carousel -->
  <div class="carousel-container">

    <!-- Current Carousel Image -->
    <div class="carousel">

      <div class="carousel-item">
        <img
          :src="images[currentIndex]"
          alt=""
          fetchpriority="high"
        />
      </div>

    </div>

    <!-- Previous Image Button -->
    <button
      type="button"
      class="prev-btn"
      @click="prevSlide"
      title="Previous Image"
      aria-label="Previous carousel image"
    >
      <img
        src="@/assets/home/carousel/prev_icon_wht.webp"
        class="default"
        alt=""
      />

      <img
        src="@/assets/home/carousel/prev_icon_bwn.webp"
        class="hover"
        alt=""
      />
    </button>

    <!-- Next Image Button -->
    <button
      type="button"
      class="next-btn"
      @click="nextSlide"
      title="Next Image"
      aria-label="Next carousel image"
    >
      <img
        src="@/assets/home/carousel/next_icon_wht.webp"
        class="default"
        alt=""
      />

      <img
        src="@/assets/home/carousel/next_icon_bwn.webp"
        class="hover"
        alt=""
      />
    </button>

  </div>
</template>

<script>
import carousel01 from '../../assets/home/carousel/carousel_01.webp';
import carousel02 from '../../assets/home/carousel/carousel_02.webp';
import carousel03 from '../../assets/home/carousel/carousel_03.webp';
import carousel04 from '../../assets/home/carousel/carousel_04.webp';

export default {
  name: 'Carousel',

  data() {
    return {

      // Store the images displayed in the carousel
      images: [
        carousel01,
        carousel02,
        carousel03,
        carousel04
        // Add more carousel images as needed
      ],

      // Track the image currently displayed
      currentIndex: 0
    };
  },

  methods: {

    // Display the previous carousel image
    prevSlide() {

      // Move to the last image if the carousel is currently on the first image
      this.currentIndex = (this.currentIndex - 1 + this.images.length) % this.images.length;

    },

    // Display the next carousel image
    nextSlide() {

      // Move back to the first image after reaching the last image
      this.currentIndex = (this.currentIndex + 1) % this.images.length;

    }
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.carousel-container {
  position: relative;
  width: 70vw;
  max-width: 70rem;
  background-color: var(--color-coffee);
  padding: 2%;
}

.carousel-item {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.carousel img {
  display: block;
  width: 85%;
  aspect-ratio: 16 / 9;
  object-fit: cover;
  border: .2rem var(--color-accent) solid;
}

.prev-btn,
.next-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 50%;
  width: 4vw;
  min-width: 2.75rem;
  height: 4vw;
  min-height: 2.75rem;
  background-color: rgba(0, 0, 0, .2);
  border: none;
  border-radius: 50%;
  transform: translateY(-50%);
  padding: .625rem;
  cursor: pointer;
  z-index: 10;
  transition: background-color .5s ease-in-out;
}

.prev-btn {
  left: 1%;
}

.next-btn {
  right: 1%;
}

.prev-btn:hover,
.next-btn:hover,
.prev-btn:active,
.next-btn:active,
.prev-btn:focus-visible,
.next-btn:focus-visible {
  background-color: var(--color-accent);
}

.prev-btn:focus-visible,
.next-btn:focus-visible {
  outline: var(--focus-outline-width) var(--color-accent) solid;
  outline-offset: .15rem;
}

button img {
  display: block;
  position: absolute;
  width: 70%;
  height: auto;
}

.default {
  opacity: 1;
  transition: opacity var(--transition-speed) ease-in-out;
}

.hover {
  opacity: 0;
  transition: opacity var(--transition-speed) ease-in-out;
}

/* Switch navigation images during interaction */
.prev-btn:hover .default,
.next-btn:hover .default,
.prev-btn:active .default,
.next-btn:active .default,
.prev-btn:focus-visible .default,
.next-btn:focus-visible .default {
  opacity: 0;
}

.prev-btn:hover .hover,
.next-btn:hover .hover,
.prev-btn:active .hover,
.next-btn:active .hover,
.prev-btn:focus-visible .hover,
.next-btn:focus-visible .hover {
  opacity: 1;
}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .carousel-container {
    width: 95vw;
  }

  .prev-btn,
  .next-btn {
    width: 3rem;
    min-width: 3rem;
    height: 3rem;
    min-height: 3rem;
    padding: .4rem;
  }

}


/* Mobile - 650px */
@media screen and (max-width: 650px) {

  .prev-btn,
  .next-btn {
    width: 2.5rem;
    min-width: 2.5rem;
    height: 2.5rem;
    min-height: 2.5rem;
    padding: .3rem;
  }

}


/* Mobile - 500px */
@media screen and (max-width: 500px) {

  .prev-btn,
  .next-btn {
    width: 2.25rem;
    min-width: 2.25rem;
    height: 2.25rem;
    min-height: 2.25rem;
    padding: .25rem;
  }

}


/* Mobile L - 470px */
@media screen and (max-width: 470px) {

  .carousel-container {
    width: 90vw;
  }

  .prev-btn,
  .next-btn {
    width: 1.75rem;
    min-width: 1.75rem;
    height: 1.75rem;
    min-height: 1.75rem;
    padding: .1rem;
  }

}
</style>
