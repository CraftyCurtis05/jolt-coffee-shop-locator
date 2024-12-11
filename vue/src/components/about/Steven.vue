<!-- Steven.vue Component -->

<template>
    <section>

        <article class="image-container">
            <a href="https://www.linkedin.com/in/steven-riggle/" target="_blank"><img src="@/assets/about/steven.jpg" alt="Steven"></a>
        </article>

        <div class="border">
            <header>
                <h1>Steven Riggle</h1>
            </header>

            <article class="about-container">
                <div v-on:mouseover="active = !active">Hello, I'm Steven!</div>
                <div v-if="active" class="about">
                    I am an aspiring developer currently enrolled in the Tech Elevator program.
                    Although I am relatively new to the technology field, I have a strong background in problem-solving and complex systems. 
                    As a passionate drag racer and mechanic, attention to detail and critical thinking are skills I have practiced and honed. 
                    I find immense enjoyment in tackling challenges and finding creative solutions. This is why I am drawn to software development, 
                    as it offers constant opportunities to overcome obstacles. While most of my professional experience lies in project management 
                    within the construction industry, I have also gained valuable insights as a scrum master and product owner in an agile environment 
                    at Kroger technology. Additionally, I proudly served in the United States Navy for six years, achieving the rank of second class 
                    petty officer. During my time in the Navy, I completed two overseas deployments and received several awards, including a humanitarian 
                    service medal for search and rescue operations immediately following the 2009 tsunami in American Samoa. I am now eager to embark on 
                    the next phase of my professional journey. I am seeking an organization that will challenge and support me, allowing me to grow and 
                    improve every day. I am excited to contribute meaningfully to the success of the team.
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