<!-- Carousel.vue Component -->

<template>
  <article class="carousel-container">

    <section class="carousel">
      <transition name="slide" mode="out-in">
        <div :key="currentIndex" class="carousel-item">
          <img :src="images[currentIndex]" alt="Slide" title="Carousel Image"/>
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
.carousel-container {
  position: relative;
  width: 50%;
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
}

.prev-btn {
  left: 1%;
}

.next-btn {
  right: 1%;
}

button:hover {
  background-color: #e8bb64;
  color: #525459;
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
  position: absolute; /* Overlay */
}

.default:hover {
  opacity: 0; /* Hide default on hover */
}

.hover:hover {
  opacity: 1; /* Show hover image */
}


/* !!!!!! REDO RESPONSIVE STYLING !!!!!! */

/* Media Queries for Responsiveness */
@media (max-width: 768px) {
  .carousel-image {
    height: auto; /* Allow the height to adjust for smaller screens */
  }

  .prev-btn,
  .next-btn {
    width: 30px;
    height: 30px;
    font-size: 12px; /* Adjust button size */
  }
}

@media (max-width: 480px) {
  .prev-btn,
  .next-btn {
    width: 25px;
    height: 25px;
  }
}
</style>
