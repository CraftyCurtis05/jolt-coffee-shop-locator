<template>
  <div class="carousel-container">
    <div class="carousel">
      <transition name="slide" mode="out-in">
        <div :key="currentIndex" class="carousel-item">
          <img :src="images[currentIndex]" alt="Slide" class="carousel-image" />
        </div>
      </transition>
    </div>
    <button @click="prevSlide" class="prev-btn">Previous</button>
    <button @click="nextSlide" class="next-btn">Next</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: [
          'https://images.pexels.com/photos/3184286/pexels-photo-3184286.jpeg?auto=compress&cs=tinysrgb&w=800',
          'https://images.pexels.com/photos/2159065/pexels-photo-2159065.jpeg?auto=compress&cs=tinysrgb&w=600',
          'https://images.pexels.com/photos/1516983/pexels-photo-1516983.jpeg?auto=compress&cs=tinysrgb&w=600',
          'https://images.pexels.com/photos/846080/pexels-photo-846080.jpeg?auto=compress&cs=tinysrgb&w=600'
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
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

.carousel-container {
  max-width: 900px; /* Adjust the maximum width of the container as needed */
  margin: auto;
  position: relative;
}
.carousel {
  overflow: hidden;
  position: relative;
}
.carousel-item {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  width: 100%;
  height: auto;
}
.carousel-image {
  width: 900px; /* Set a larger fixed width for the images */
  height: 600px; /* Set a larger fixed height for the images */
  object-fit: cover; /* Maintain aspect ratio and cover the container */
}
.prev-btn,
.next-btn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  padding: 10px;
}
.prev-btn {
  left: 10px;
}
.next-btn {
  right: 10px;
}
.slide-enter-active,
.slide-leave-active {
  transition: opacity 0.5s;
}
.slide-enter, .slide-leave-to {
  opacity: 0;
}
button {
  display: inline-block;
  background-color: #3a2f2e;
  width: 80px;
  color: #ffffff;
  text-align: center;
  border: 2px solid #cccccc;
  border-radius: 10px;
  font-size: 14px;
  margin: 5px;
  -webkit-transition: all 0.5s; /* add this line, chrome, safari, etc */
  -moz-transition: all 0.5s; /* add this line, firefox */
  -o-transition: all 0.5s; /* add this line, opera */
  transition: all 0.5s; /* add this line */
}
button:hover {
  background-color: #e8bb64;
}
</style>
