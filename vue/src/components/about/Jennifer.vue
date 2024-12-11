<!-- Jennifer.vue Component -->

<template>
    <section>

        <article class="image-container">
            <a href="https://www.linkedin.com/in/jcurtisdeveloper/" target="_blank"><img src="@/assets/about/jennifer.jpg" alt="Jennifer"></a>
        </article>

        <div class="border">
            <header>
                <h1>Jennifer Curtis</h1>
            </header>

            <article class="about-container">
                <div v-on:mouseover="active = !active">Hello, I'm Jennifer!</div>
                <div v-if="active" class="about">
                    For as long as I can remember, I've had a persistent curiosity and endless questions about how everything 
                    is connected. I did not realize how useful this need for information was in IT, until I started studying and working with data 
                    analysis and machine learning last year. That was just the start... From the beginning, I was hooked! Endless hours of researching 
                    and studying, from COMPTIA A+ and Docker to System Administration and Data Science, which led me to enrolling in Tech Elevator's 
                    Java Full Stack Bootcamp. Learning new languages, endless research, focusing on efficiency and organization, plus being surrounded 
                    by like-minded people- It's everything I didn't even know I wanted! Inquisitive, tenacious and approachable extrovert interested in 
                    career opportunities where I can further indulge my love of learning and dive into the endless possibilities of software development 
                    and technology.
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