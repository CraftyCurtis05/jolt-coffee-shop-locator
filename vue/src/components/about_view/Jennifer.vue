<!-- Jennifer.vue Component -->

<template>
    <article>

        <section class="image-container">
            <a href="https://www.linkedin.com/in/jcurtisdeveloper/" target="_blank">
                <img src="@/assets/about_view/jennifer.jpg" alt="Jennifer Curtis" title="Jennifer Curtis">
            </a>
        </section>

        <section class="about-container">
            <header>
                <h3>Jennifer Curtis</h3>
            </header>

            <details>
                <summary title="Learn More about Jennifer">Full-Stack Developer</summary>
                <p>
                    &nbsp; For as long as I can remember, I've had a persistent curiosity and endless questions about how everything 
                    is connected. I did not realize how useful this need for information was in IT, until I started studying and working 
                    with data analysis and machine learning last year. That was just the start... From the beginning, I was hooked!
                    
                    <br><br> &nbsp; Endless hours of researching and studying, from COMPTIA A+ and Docker to System Administration and 
                    Data Science, which led me to enrolling in Tech Elevator's Java Full Stack Bootcamp. Learning new languages, endless 
                    research, focusing on efficiency and organization, plus being surrounded by like-minded people- It's everything I didn't 
                    even know I wanted!
                    
                    <br><br> &nbsp; Inquisitive, tenacious and approachable extrovert interested in career opportunities where I can further 
                    indulge my love of learning and dive into the endless possibilities of software development and technology.
                </p>
            </details>
        </section>

    </article>
 </template>

 <script>
export default {
    name: 'Jennifer',
    data() {
        return {
            active: false
        }    
    }
};
</script>

<style scoped>
article {
    background-color: rgb(53, 37, 19);
    width: 20vw;
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

summary {
    cursor: pointer;
    padding-bottom: 1.5rem;
}

p {
    text-align: left;
    padding: 1vw;
    line-height: 1.3rem;
}

/* Laptop L - 1440px */

/* 4K - 2560px */
@media screen and (min-width: 2560px) {

}

/* Laptop - 1024px */
@media screen and (max-width: 1024px) {

}

/* Tablet - 768px */
@media screen and (max-width: 768px) {

}

/* Mobile L - 425px */
@media screen and (max-width: 425px) {

}

/* Mobile M - 375px */
@media screen and (max-width: 375px) {

}

/* Mobile S - 320px */
@media screen and (max-width: 320px) {

}
</style>