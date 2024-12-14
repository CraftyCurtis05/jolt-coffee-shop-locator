<!-- Naomi.vue Component -->

<template>
    <article>

        <section class="image-container">
            <a href="https://www.linkedin.com/in/naomi-adams-0804a4106/" target="_blank">
                <img src="@/assets/about_view/naomi.jpg" alt="Naomi Adams" title="Naomi Adams">
            </a>
        </section>

        <section class="about-container">
            <header>
                <h3>Naomi Adams</h3>
            </header>

            <details>
                <summary title="Learn More about Naomi">Front-End Developer</summary>
                <p>
                    &nbsp; With over a decade of entrepreneurial experience under my belt, I embarked on a transformative journey that 
                    has now led me to the world of technology. I successfully completed a rigorous full-stack coding bootcamp, equipping 
                    myself with the technical skills needed to thrive in this dynamic field.

                    <br><br> &nbsp; My transition from entrepreneurship to tech represents a pivotal moment in my career. It signifies my 
                    unwavering commitment to growth, learning, and embracing new challenges. The bootcamp experience not only deepened my 
                    technical knowledge but also sharpened my problem-solving abilities and enhanced my adaptability.
                    
                    <br><br> &nbsp; Having completed the bootcamp, my goal remains the same: to excel in the technology world, where 
                    opportunities for innovation and growth abound. I am now ready to apply my entrepreneurial spirit, combined with my 
                    newfound technical expertise, to create solutions that make a meaningful impact.
                </p>
            </details>
        </section>

    </article>
 </template>

<script>
export default {
    name: 'Naomi',
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
    transition: all 0.5s;
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
</style>