<!-- David.vue Component -->

 <template>
    <article>

        <!-- David Image -->
        <section class="image-container">
            <a href="https://www.linkedin.com/in/daviddefoshajr/" target="_blank">
                <img src="@/assets/about_view/david.jpg" alt="David DeFosha" title="David Defosha">
            </a>
        </section>

        <!-- About Section -->
        <section class="about-container">

            <!-- Name -->
            <header>
                <h3>David DeFosha</h3>
            </header>

            <details>
                <!-- Job Description -->
                <summary title="Learn More about David">Front-End Developer</summary>
                
                <!-- Dropdown Quote -->
                <p> 
                    &nbsp; "I've been fascinated by coding since I was 14 when I learned to create macros in World of Warcraft, 
                    which gave me an edge by allowing me to perform complex actions with a single keystroke. That was the moment 
                    I knew coding was my passion. 
                    
                    <br><br>&nbsp; With a background in sales and customer service, I bring strong people skills, team collaboration, 
                    and experience with deadlines—all essential in the development world. I’m always eager to learn, grow, and take 
                    on new challenges, and I’m excited to continue my journey in programming. My goal is to use my passion for coding 
                    to build innovative solutions and make a real impact."
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
article {
    position: relative;
    background-color: rgb(53, 37, 19);
    width: 20vw;
    border:.2vw #e8bb64 solid;
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
    text-align: left;
    max-height: 5vw;
    padding: 1vw;
    line-height: 1.5;
    overflow-y: auto; /* Adds vertical scroll when content exceeds max height */
}

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

    h3 {
        font-size: 1.7rem;
    }

    details {
        font-size: 1.5rem;
    }
}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

    h3 {
        font-size: 1rem;
    }

    details {
        font-size: .8rem;
    }
}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

    article {
        width: 30vw;
    }

    h3 {
        font-size: 1rem;
    }

    details {
        font-size: .8rem;
    }

    p {
        position: absolute;
        background-color: #e8bb64;
        max-height: 25vw;
        z-index: 1000;
    }
}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

    article {
        width: 45vw;
    }

    h3 {
        font-size: .9rem;
    }

    details {
        font-size: .7rem;
    }

    p {
        max-height: 40vw;
    }
}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

    p {
        max-height: 48vw;
    }
}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

    p {
        max-height: 58vw;
    }
}
</style>