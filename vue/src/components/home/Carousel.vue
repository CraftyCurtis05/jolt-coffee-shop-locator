<!-- Carousel.vue Component -->

<template>

  <!-- Image Carousel -->
  <article class="carousel-container">

    <!-- Current Carousel Image -->
    <section class="carousel">
      <transition name="slide" mode="out-in">

        <div
          class="carousel-item"
          :key="currentIndex"
        >
          <img
            :src="images[currentIndex]"
            :alt="'Coffee shop carousel image ' + (currentIndex + 1)"
            title="Carousel Image Slide"
          />
        </div>

      </transition>
    </section>

    <!-- Previous Image Button -->
    <button
      type="button"
      class="prev-btn"
      @click="prevSlide"
      title="Previous Image"
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

      // *DEBUG* Log the current carousel image for debugging
      // console.log('Previous carousel image:', this.currentIndex);
    },

    // Display the next carousel image
    nextSlide() {

      // Move back to the first image after reaching the last image
      this.currentIndex = (this.currentIndex + 1) % this.images.length;

      // *DEBUG* Log the current carousel image for debugging
      // console.log('Next carousel image:', this.currentIndex);
    }
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.carousel-container {
  position: relative;
  width: 70vw;
  background-color: rgb(53, 37, 19);
  box-sizing: border-box;
  padding: 2%;
}

.carousel-item {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: auto;
}

.carousel img {
  width: 85%;
  height: auto;
  border: .4vw #e8bb64 solid;
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
  cursor: pointer;
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
.next-btn:hover {
  background-color: #e8bb64;
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
.next-btn:hover .default {
  opacity: 0;
}

.prev-btn:hover .hover,
.next-btn:hover .hover {
  opacity: 1;
}


/* 4K - 2560px */
@media screen and (min-width: 2560px) {

}


/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

  .carousel-container {
    width: 80vw;
  }
}


/* Tablet - 768px */
@media screen and (max-width: 768px) {

  .carousel-container {
    width: 85vw;
  }
}


/* Mobile L - 425px */
@media screen and (max-width: 425px) {

  .carousel-container {
    width: 90vw;
  }
}


/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}


/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>