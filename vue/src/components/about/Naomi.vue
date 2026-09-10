<!-- Naomi.vue Component -->

<template>
  <article>

    <!-- Naomi Image -->
    <section class="image-container">
      <a
        href="https://www.linkedin.com/in/naomi-adams-0804a4106/"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
          src="@/assets/about/naomi.webp"
          alt="Naomi Adams"
          title="Naomi Adams"
        />
      </a>
    </section>

    <!-- About Section -->
    <section class="about-container">

      <!-- Name -->
      <header>
        <h3>Naomi Adams</h3>
      </header>

      <!-- Job Description -->
      <details>
        <summary title="Learn More about Naomi">
          Front-End Developer
        </summary>

        <!-- Dropdown Quote -->
        <p>
          &nbsp; "After over a decade of entrepreneurship, I made a bold pivot into the world of tech,
          completing a full-stack coding bootcamp to equip myself with the skills to thrive in this exciting
          field. This shift marks a key moment in my career—one that reflects my commitment to continuous growth,
          learning, and embracing new challenges.

          <br><br>

          &nbsp; Tech Elevator bootcamp not only boosted my technical expertise but also
          sharpened my problem-solving and adaptability. Now that I've wrapped up my training, I’m eager to apply
          my entrepreneurial mindset and fresh tech skills to create innovative solutions that make a real impact."
        </p>
      </details>

    </section>

  </article>
</template>

<script>
export default {
  name: 'Naomi'
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