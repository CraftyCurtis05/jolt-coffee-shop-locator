<!-- Carousel.vue Component -->

<template>
  <article class="carousel-container">

    <section class="carousel">
      <transition name="slide" mode="out-in">
        <div class="carousel-item">
          <img :src="images[currentIndex]" alt="Image Slide" title="Carousel Image Slide" />
        </div>
      </transition>
    </section>

    <button @click="prevSlide" class="prev-btn">
      <img src="@/assets/home_view/carousel/prev_icon_wht.png" class="default">
      <img src="@/assets/home_view/carousel/prev_icon_bwn.png" class="hover" title="Previous Image">
    </button>

    <button @click="nextSlide" class="next-btn">
      <img src="@/assets/home_view/carousel/next_icon_wht.png" class="default">
      <img src="@/assets/home_view/carousel/next_icon_bwn.png" class="hover" title="Next Image">
    </button>
    
  </article>
</template>

<script>
export default {
  name: 'Carousel',
  data() {
    return {
      images: [
        'src/assets/home_view/carousel/carousel_01.png',
        'src/assets/home_view/carousel/carousel_02.png',
        'src/assets/home_view/carousel/carousel_03.png',
        'src/assets/home_view/carousel/carousel_04.png'
        // Add more image URLs as needed
      ],
      currentIndex: 0,
    };
  },
  methods: {
    prevSlide() {
      this.currentIndex = (this.currentIndex - 1 + this.images.length) % this.images.length;
    },
    nextSlide() {
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

button:hover {
  background-color: #e8bb64;
  color: #525459;
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

button img {
  display: block;
  position: absolute;
  width: 70%;
  height: auto;
}

.default {
  opacity: 1;
}

.hover {
  opacity: 0; /* Initially hidden */
}

.hover:hover {
  opacity: 1; /* Show hover image */
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
