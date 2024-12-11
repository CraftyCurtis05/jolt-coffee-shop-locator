<!-- Naomi.vue Component -->

<template>
    <section>

        <article class="image-container">
            <a href="https://www.linkedin.com/in/naomi-adams-0804a4106/" target="_blank"><img src="@/assets/about/naomi.jpg" alt="Naomi"></a>
        </article>

        <div class="border">
            <header>
                <h1>Naomi Adams</h1>
            </header>

            <article class="about-container">
                <div v-on:mouseover="active = !active">Hello, I'm Naomi!</div>
                <div v-if="active" class="about">
                    With over a decade of entrepreneurial experience under my belt, I embarked on a transformative journey that 
                    has now led me to the world of technology. I successfully completed a rigorous full-stack coding bootcamp, equipping myself 
                    with the technical skills needed to thrive in this dynamic field. My transition from entrepreneurship to tech represents a 
                    pivotal moment in my career. It signifies my unwavering commitment to growth, learning, and embracing new challenges. The 
                    bootcamp experience not only deepened my technical knowledge but also sharpened my problem-solving abilities and enhanced my 
                    adaptability. Having completed the bootcamp, my goal remains the same: to excel in the technology world, where opportunities 
                    for innovation and growth abound. I am now ready to apply my entrepreneurial spirit, combined with my newfound technical expertise, 
                    to create solutions that make a meaningful impact. Let's continue to explore the limitless possibilities that technology offers, 
                    and together, we'll shape a future where innovation knows no bounds.
                </div>
            </article>
        </div>
    </section>
 </template>

<script>
export default {
    data() {
        return {
            active: false
        }    
    }
};
</script>

<style scoped>
section {
    width: 20vw;
    padding: 2vw;
}

img {
    width: 200px;
    height: 200px;
    border-radius: 50%;
    transition: transform 0.3s ease-in-out;
}

img:hover {
    transform: scale(1.1);
}

.border {
    border: .2vw #e8bb64 solid;
}

.about-container {
    cursor: pointer;
}

.about {
    padding: 1vw;
    line-height: 1.3rem;
}
</style>