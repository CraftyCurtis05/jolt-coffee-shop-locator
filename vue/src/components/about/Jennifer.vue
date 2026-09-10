<!-- Jennifer.vue Component -->

<template>
  <article>

    <!-- Jennifer Image -->
    <section class="image-container">
      <a
        href="https://www.linkedin.com/in/jcurtisdeveloper/"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/about/jennifer.webp"
          alt="Jennifer Curtis"
          title="Jennifer Curtis"
        />
      </a>
    </section>

    <!-- About Section -->
    <section class="about-container">

      <!-- Name -->
      <header>
        <h3>Jennifer Curtis</h3>
      </header>

      <!-- Job Description -->
      <details>
        <summary title="Learn More about Jennifer">
          Full-Stack Developer
        </summary>

        <!-- Dropdown Quote -->
        <p>
          &nbsp; "I've always been the type to ask a lot of questions and wonder how everything connects—something I only
          realized is a huge advantage in IT! Once I dove into data analysis and machine learning, I was hooked. Fast-forward
          to countless hours of studying (COMPTIA A+, Docker, System Admin, Data Science), which led me to Tech Elevator's Java
          Full Stack Bootcamp.

          <br><br>

          &nbsp; Learning new languages, exploring tech, and collaborating with like-minded people? It's like my
          dream came true! I'm an inquisitive, tenacious extrovert, eager to dive deeper into software development and tech
          opportunities that let me keep learning and exploring."
        </p>
      </details>

    </section>

  </article>
</template>

<script>
export default {
  name: 'Jennifer'
};
</script>

<style scoped>
/* Laptop L - 1440px */

article {
  position: relative;
  width: 20vw;
  background-color: rgb(53, 37, 19);
  border: .2vw #e8bb64 solid;
  padding: 1vw;
}

img {
  width: 13vw;
  height: 13vw;
  border: .6vw #e8bb64 solid;
  border-radius: 50%;
  transition: transform 0.3s ease-in-out;
}

img:hover {
  transform: scale(1.1);
}

.about-container {
  color: rgb(245, 242, 242);
  border: .2vw #e8bb64 solid;
  transition: all 0.5s ease-in-out;
}

.about-container:hover {
  background-color: #e8bb64;
  color: rgb(53, 37, 19);
}

h3 {
  font-size: 1.3rem;
}

details {
  font-size: .9rem;
}

summary {
  cursor: pointer;
  padding-bottom: 3vw;
  z-index: 100;
}

p {
  max-height: 5vw;
  text-align: left;
  padding: 1vw;
  line-height: 1.5;
  overflow-y: auto; /* Adds vertical scroll when content exceeds max height */
}
</style>