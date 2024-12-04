<template>
  <div class="carousel-container">
    <div class="carousel">
      <transition name="slide" mode="out-in">
        <div :key="currentIndex" class="carousel-item">
          <img :src="images[currentIndex]" alt="Slide" class="carousel-image" />
        </div>
      </transition>
    </div>
    <button @click="prevSlide" class="prev-btn">
      <img src="@/assets/prev_icon_wht.png" class="default">
      <img src="@/assets/prev_icon_bwn.png" class="hover">
    </button>
    <button @click="nextSlide" class="next-btn">
      <img src="@/assets/next_icon_wht.png" class="default">
      <img src="@/assets/next_icon_bwn.png" class="hover">
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: [
        'src/assets/carousel_01.png',
        'src/assets/carousel_02.png',
        'src/assets/carousel_03.png',
        'src/assets/carousel_04.png'
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
  width: 70%; /* Make the container width flexible */
  padding: 2rem; /* Add some padding for smaller screens */
  box-sizing: border-box;
  background-color: #3c2d30;
}

.carousel {
  overflow: hidden;
  position: relative;
}

.carousel-item {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: auto;

  
}

.carousel-image {
  width: 85%; /* Set the image width to adapt to container */
  height: auto; /* Maintain the aspect ratio */
  object-fit: cover;
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
  left: 10px;
}

.next-btn {
  right: 10px;
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
