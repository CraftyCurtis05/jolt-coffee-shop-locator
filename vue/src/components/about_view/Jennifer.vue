<!-- Jennifer.vue Component -->

<template>
    <article>

        <!-- Jennifer Image -->
        <section class="image-container">
            <a href="https://www.linkedin.com/in/jcurtisdeveloper/" target="_blank">
                <img src="@/assets/about_view/jennifer.jpg" alt="Jennifer Curtis" title="Jennifer Curtis">
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
                <summary title="Learn More about Jennifer">Full-Stack Developer</summary>

                <!-- Dropdown Quote -->
                <p>
                    &nbsp; "I've always been the type to ask a lot of questions and wonder how everything connects—something I only 
                    realized is a huge advantage in IT! Once I dove into data analysis and machine learning, I was hooked. Fast-forward 
                    to countless hours of studying (COMPTIA A+, Docker, System Admin, Data Science), which led me to Tech Elevator's Java 
                    Full Stack Bootcamp. 
                    
                    <br><br> &nbsp; Learning new languages, exploring tech, and collaborating with like-minded people? It's like my 
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
        max-height: 41vw;
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