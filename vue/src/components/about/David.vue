<!-- David.vue Component -->

 <template>
    <section>

        <article class="image-container">
            <a href="https://www.linkedin.com/in/daviddefoshajr/" target="_blank"><img src="@/assets/about/david.jpg" alt="David"></a>
        </article>

        <div class="border">
            <header>
                <h1>David DeFosha</h1>
            </header>

            <article class="about-container">
                <div v-on:mouseover="active = !active">Hello, I'm David!</div>
                <div v-if="active" class="about">
                    Ever since I was young, I became fascinated with coding and how much depth it could bring 
                    with endless possibilities and allow me to use what I am passionate about, to pioneer anything I set my mind to. When I 
                    was 14 years old I learned how to create my very own macros in a game called World Of Warcraft which allowed me to assign 
                    a single keystroke to move through an array of my numerous abilities without having to click them separately and gain a 
                    big advantage over others. That was the day I found my passion in coding and knew it was what I wanted to do for the rest 
                    of my life. With a wide background in sales and customer service experience, there is a lot I can carry over into the 
                    programming world. Working closely with teams, developing strong people skills, and hands on experience with project and 
                    goal deadlines is something I have discovered to be a very important aspect in the development field. I am always eager to 
                    learn and grow, and I thrive on challenges. I take pride in whatever it is I enjoy doing and my continuous growth in knowledge 
                    in the Full-Stack cohort I am currently attending has become something I strive to increase and become the best developer that 
                    I can be.
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