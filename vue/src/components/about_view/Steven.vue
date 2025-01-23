<!-- Steven.vue Component -->

<template>
    <article>

        <!-- Steven Image -->
        <section class="image-container">
            <a href="https://www.linkedin.com/in/steven-riggle/" target="_blank">
                <img src="@/assets/about_view/steven.jpg" alt="Steven Riggle" title="Jennifer Curtis">
            </a>
        </section>

        <!-- About Section -->
        <section class="about-container">

            <!-- Name -->
            <header>
                <h3>Steven Riggle</h3>
            </header>

            <!-- Job Description -->
            <details>
                <summary title="Learn More about Steven">Back-End Developer</summary>
                
                <!-- Dropdown Quote -->
                <p>
                    &nbsp; "I’m an aspiring developer in the Tech Elevator program with a background in problem-solving and complex 
                    systems. Before tech, I honed my attention to detail and critical thinking as a drag racer and mechanic. I thrive 
                    on tackling challenges, making software development a natural fit. 
                    
                    <br><br>&nbsp; My career has included project management in construction and roles as a scrum master and product 
                    owner at Kroger Technology. I also served six years in the U.S. Navy, earning a humanitarian service medal for search 
                    and rescue after the 2009 tsunami in American Samoa. I’m excited to bring my diverse skills to the tech world and 
                    contribute to a team’s success."
                </p>
            </details>
        </section>

    </article>
 </template>

<script>
export default {
    name: 'Steven'
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