<!-- David.vue Component -->

 <template>
    <article>

        <section class="image-container">
            <a href="https://www.linkedin.com/in/daviddefoshajr/" target="_blank">
                <img src="@/assets/about_view/david.jpg" alt="David DeFosha" title="David Defosha">
            </a>
        </section>

        <section class="about-container">
            <header>
                <h2>David DeFosha</h2>
            </header>

            <details>
                <summary title="Learn More about David">Front-End Developer</summary>
                <p> 
                    &nbsp; Ever since I was young, I became fascinated with coding and how much depth it could bring 
                    with endless possibilities and allow me to use what I am passionate about, to pioneer anything I set my mind to.

                    <br><br> &nbsp; When I was 14 years old I learned how to create my very own macros in a game called World Of Warcraft which allowed 
                    me to assign a single keystroke to move through an array of my numerous abilities without having to click them separately 
                    and gain a big advantage over others. That was the day I found my passion in coding and knew it was what I wanted to do 
                    for the rest of my life.

                    <br><br> &nbsp; With a wide background in sales and customer service experience, there is a lot I can carry over into the 
                    programming world. Working closely with teams, developing strong people skills, and hands on experience with project and 
                    goal deadlines is something I have discovered to be a very important aspect in the development field. I am always eager to 
                    learn and grow, and I thrive on challenges.
                </p>
            </details>
        </section>

    </article>
 </template>

<script>
export default {
    name: 'David',
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
}

h2 {
    font-size: 1.3rem;
}

summary {
    cursor: pointer;
    padding-bottom: 1.5rem;
}

.about-container:hover {
    background-color: #e8bb64;
    color: rgb(53, 37, 19);
}

p {
    text-align: left;
    padding: 1vw;
    line-height: 1.3rem;
}
</style>