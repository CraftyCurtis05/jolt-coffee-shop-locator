<!-- Steven.vue Component -->

<template>
    <article>

        <section class="image-container">
            <a href="https://www.linkedin.com/in/steven-riggle/" target="_blank">
                <img src="@/assets/about_view/steven.jpg" alt="Steven Riggle" title="Jennifer Curtis">
            </a>
        </section>

        <section class="about-container">
            <header>
                <h3>Steven Riggle</h3>
            </header>

            <details>
                <summary title="Learn More about Steven">Back-End Developer</summary>
                <p>
                    &nbsp; I am an aspiring developer currently enrolled in the Tech Elevator program.
                    Although I am relatively new to the technology field, I have a strong background in problem-solving and complex systems. 
                    As a passionate drag racer and mechanic, attention to detail and critical thinking are skills I have practiced and honed.
                    
                    <br><br> &nbsp; I find immense enjoyment in tackling challenges and finding creative solutions. This is why I am drawn to 
                    software development, as it offers constant opportunities to overcome obstacles. While most of my professional experience 
                    lies in project management within the construction industry, I have also gained valuable insights as a scrum master and 
                    product owner in an agile environment at Kroger technology.
                    
                    <br><br> &nbsp; Additionally, I proudly served in the United States Navy for six years, achieving the rank of second class 
                    petty officer. During my time in the Navy, I completed two overseas deployments and received several awards, including a 
                    humanitarian service medal for search and rescue operations immediately following the 2009 tsunami in American Samoa. I am 
                    now eager to embark on the next phase of my professional journey and I am excited to contribute meaningfully to the success 
                    of the team.
                </p>
            </details>
        </section>

    </article>
 </template>

<script>
export default {
    name: 'Steven',
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

h3 {
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