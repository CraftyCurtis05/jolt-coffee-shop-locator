<!-- Steven.vue Component -->

<template>
  <article class="developer">

    <!-- Steven Image -->
    <section class="image-container">
      <a
        href="https://www.linkedin.com/in/steven-riggle/"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Visit Steven Riggle on LinkedIn"
      >
        <img
          src="@/assets/about/steven.webp"
          alt="Steven Riggle"
          title="Click to Go to Steven's LinkedIn"
        />
      </a>
    </section>

    <!-- About Section -->
    <section class="about-container">

      <!-- Name -->
      <header>
        <h3>Steven Riggle</h3>
      </header>

      <!-- Job Description -->
      <p class="developer-role">Back-End Developer</p>

      <!-- Developer Bio -->
      <details>
        <summary title="Learn more about Steven Riggle">
          Meet Steven
        </summary>

        <!-- Bio Content -->
        <p
          class="bio-content"
          tabindex="0"
        >
          I’m an aspiring developer in the Tech Elevator program with a background in
          problem-solving and complex systems. Before tech, I honed my attention to
          detail and critical thinking as a drag racer and mechanic. I thrive on
          tackling challenges, making software development a natural fit.

          <br><br>

          My career has included project management in construction and roles as a
          scrum master and product owner at Kroger Technology. I also served six years
          in the U.S. Navy, earning a humanitarian service medal for search and rescue
          after the 2009 tsunami in American Samoa. I’m excited to bring my diverse
          skills to the tech world and contribute to a team’s success.
        </p>
      </details>

    </section>

  </article>
</template>

<script>
export default {
  name: 'Steven'
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
  outline: none;
}

.image-container a:focus-visible img {
  border-color: rgb(245, 242, 242);
  transform: scale(1.05);
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
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  color: rgb(245, 242, 242);
  font-size: .95rem;
  padding: 0 .75rem;
  transition: color 0.3s ease-in-out;
  z-index: 100;
}

summary:hover {
  font-size: 1rem;
  font-weight: 00;
  color: #e8bb64;
  text-decoration: underline;
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
