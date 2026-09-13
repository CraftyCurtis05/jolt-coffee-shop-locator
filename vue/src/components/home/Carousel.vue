<!-- Carousel.vue Component -->

<template>

  <!-- Image Carousel -->
  <article class="carousel-container">

    <!-- Current Carousel Image -->
    <section class="carousel">

      <div
        class="carousel-item"
        :key="currentIndex"
      >
        <img
          :src="images[currentIndex]"
          :alt="'Coffee shop carousel image ' + (currentIndex + 1)"
          title="Carousel Image Slide"
          fetchpriority="high"
        />
      </div>

    </section>

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

  </article>
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
      currentIndex: 0,
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
  },

  mounted() {

    // Preload the carousel images
    this.images.forEach(image => {
      const preloadImage = new Image();
      preloadImage.src = image;
    });
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.carousel-container {
  position: relative;
  width: 70vw;
  max-width: 70rem;
  background-color: rgb(53, 37, 19);
  box-sizing: border-box;
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
  border: .2rem #e8bb64 solid;
}

.prev-btn,
.next-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 50%;
  width: 4vw;
  min-width: 3vw;
  height: 4vw;
  min-height: 3vw;
  background-color: rgba(0, 0, 0, 0.2); /* Semi-transparent buttons */
  border: none;
  border-radius: 50%;
  transform: translateY(-50%);
  padding: 10px;
  z-index: 10;
  transition: all 0.5s ease-in-out;
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
.next-btn:active {
  background-color: #e8bb64;
}

.prev-btn:focus-visible,
.next-btn:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .15rem;
}

button img {
  display: block;
  position: absolute;
  width: 70%;
  height: auto;
}

.default {
  opacity: 1; /* Show the default button image */
}

.hover {
  opacity: 0; /* Hide the hover button image */
}

/* Switch navigation images when the button is hovered */
.prev-btn:hover .default,
.next-btn:hover .default,
.prev-btn:active .default,
.next-btn:active .default {
  opacity: 0;
}

.prev-btn:hover .hover,
.next-btn:hover .hover,
.prev-btn:active .hover,
.next-btn:active .hover {
  opacity: 1;
}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .carousel-container {
    width: 95vw;
  }

  .prev-btn,
  .next-btn {
    width: 2.75rem;
    min-width: 2.75rem;
    height: 2.75rem;
    min-height: 2.75rem;
    padding: .4rem;
  }

}


/* Mobile L - 426px */
@media screen and (max-width: 426px) {

  .carousel-container {
    width: 90vw;
  }

  .prev-btn,
  .next-btn {
    width: 2.75rem;
    min-width: 2.75rem;
    height: 2.75rem;
    min-height: 2.75rem;
    padding: .35rem;
  }

}
</style>
