<!-- David.vue Component -->

<template>
  <article class="developer">

    <!-- David Image -->
    <div class="image-container">
      <a
        href="https://www.linkedin.com/in/daviddefoshajr/"
        target="_blank"
        rel="noopener noreferrer"
        aria-label="Visit David DeFosha on LinkedIn - opens in a new tab"
      >
        <img
          src="@/assets/about/david.webp"
          alt="David DeFosha"
          title="Visit David DeFosha on LinkedIn"
          loading="lazy"
        />
      </a>
    </div>

    <!-- About Section -->
    <section class="about-container">

      <!-- Name -->
      <header>
        <h3>David DeFosha</h3>
      </header>

      <!-- Job Description -->
      <p class="developer-role">Front-End Developer</p>

      <!-- Developer Bio -->
      <details>
        <summary title="Learn more about David DeFosha">
          Meet David
        </summary>

        <!-- Bio Content -->
        <p
          class="bio-content"
          tabindex="0"
        >
          I've been fascinated by coding since I was 14 when I learned to create macros
          in World of Warcraft, which gave me an edge by allowing me to perform complex
          actions with a single keystroke. That was the moment I knew coding was my
          passion.

          <br><br>

          With a background in sales and customer service, I bring strong people skills,
          team collaboration, and experience with deadlines—all essential in the
          development world. I’m always eager to learn, grow, and take on new challenges,
          and I’m excited to continue my journey in programming. My goal is to use my
          passion for coding to build innovative solutions and make a real impact.
        </p>
      </details>

    </section>

  </article>
</template>

<script>
export default {
  name: 'David'
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
  background-color: var(--color-coffee);
  border: .15rem var(--color-accent) solid;
  border-radius: var(--border-radius);
  padding: 1rem;
  margin: .75rem;
  transition: transform var(--transition-speed) ease-in-out;
}

.developer:hover,
.developer:focus-within {
  transform: translateY(-.2rem);
}

.image-container a:focus-visible {
  outline: none;
}

.image-container a:focus-visible img {
  border-color: var(--color-surface);
  transform: scale(1.02);
}

img {
  display: block;
  width: 11rem;
  height: 11rem;
  object-fit: cover;
  border: .5rem var(--color-accent) solid;
  border-radius: 50%;
  transition:
    transform var(--transition-speed) ease-in-out,
    border-color var(--transition-speed) ease-in-out;
}

img:hover {
  border-color: var(--color-surface);
  transform: scale(1.02);
}

.about-container {
  width: 100%;
  color: var(--color-surface);
  border: .15rem var(--color-accent) solid;
  margin-top: 1rem;
  transition: border-color var(--transition-speed) ease-in-out;
}

.about-container:hover,
.about-container:focus-within {
  border-color: var(--color-surface);
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
}

summary {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 2.75rem;
  color: var(--color-surface);
  font-size: .95rem;
  padding: 0 .75rem;
  cursor: pointer;
  transition: color var(--transition-speed) ease-in-out;
}

summary:hover {
  font-weight: 600;
  color: var(--color-accent);
  text-decoration: underline;
}

summary:focus-visible {
  outline: var(--focus-outline-width) var(--color-accent) solid;
  outline-offset:var(--focus-outline-offset);
}

.bio-content {
  max-height: 9rem;
  text-align: left;
  font-size: .85rem;
  line-height: 1.5;
  padding: .75rem;
  margin: 0;
  overflow-y: auto;
}

.bio-content:focus-visible {
  outline: var(--focus-outline-width) var(--color-accent) solid;
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
