<!-- Jennifer.vue Component -->

<template>
  <article class="developer">

    <!-- Jennifer Image -->
    <section class="image-container">
      <a
        href="https://www.linkedin.com/in/jcurtisdeveloper/"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Visit Jennifer Curtis on LinkedIn"
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
      <p class="developer-role">Full-Stack Developer</p>

      <!-- Developer Bio -->
      <details>
        <summary title="Learn more about Jennifer Curtis">
          Meet Jennifer
        </summary>

        <!-- Bio Content -->
       <p
          class="bio-content"
          tabindex="0"
        >
          I've always been the type to ask questions, look for patterns and figure out
          how different pieces connect. That curiosity naturally led me into technology,
          where I built a foundation across systems administration, infrastructure and
          software development before completing Tech Elevator's Java Full-Stack
          Bootcamp.

          <br><br>

          Since then, I've continued building and refining full-stack applications with
          Java, Spring Boot, PostgreSQL, JavaScript and Vue.js while expanding my work 
          across APIs, responsive design, application security and systems-focused 
          problem-solving. I enjoy understanding both how a system works technically
          and how it can be improved for the people using it.
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

.developer {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20rem;
  max-width: 100%;
  background-color: rgb(53, 37, 19);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem;
  margin: .75rem;
  transition: transform 0.3s ease-in-out;
}

.developer:hover {
  transform: translateY(-.2rem);
}

.image-container a:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
  border-radius: 50%;
}

img {
  display: block;
  width: 11rem;
  height: 11rem;
  object-fit: cover;
  border: .5rem #e8bb64 solid;
  border-radius: 50%;
  transition: transform 0.3s ease-in-out;
}

img:hover {
  transform: scale(1.05);
}

.about-container {
  width: 100%;
  color: rgb(245, 242, 242);
  border: .15rem #e8bb64 solid;
  margin-top: 1rem;
  transition: border-color 0.3s ease-in-out;
}

.about-container:hover {
  border-color: rgb(245, 242, 242);
}

h3 {
  font-size: 1.3rem;
  margin: 1rem auto .5rem;
}

.developer-role {
  font-size: .9rem;
  text-align: center;
  padding: 0 .75rem;
  margin: 0 auto .75rem;
}

details {
  width: 100%;
  font-size: .9rem;
}

summary {
  padding: 0 .75rem 1rem;
  transition: color 0.3s ease-in-out;
  z-index: 100;
}

summary:hover {
  color: #e8bb64;
}

summary:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}

.bio-content {
  max-height: 9rem;
  text-align: left;
  font-size: .85rem;
  line-height: 1.5;
  padding: .75rem;
  margin: 0;
  overflow-y: auto; /* Adds vertical scroll when content exceeds max height */
}

.bio-content:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: -.15rem;
}

.bio-content::-webkit-scrollbar {
  width: 15px;
}

.bio-content::-webkit-scrollbar-thumb {
  min-height: 1rem;
  background-image: url("/assets/scrollbar/scrollbar_thumb_small.webp");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: 15px;
}
</style>
