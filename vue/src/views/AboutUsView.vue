<template>
    <div class="about-us">
      <h1>Who We Are</h1>
      <p>Meet Our Software Engineers</p>
      <div class="team-members">
        <div class="team-member">
            <a href="https://www.linkedin.com/in/jcurtisdeveloper/" target="_blank"><img src="src\assets\Jennifer.jpg" alt="Jennifer" class="team-member-image"></a>
          <div class="bio-tooltip"  :style="{border: '2px solid rgb(223,204,145)'}">
            <p class="name">Jennifer</p>
            <span class="tooltip-text">
              Hello, I'm Jennifer! For as long as I can remember, I've had a persistent curiosity and endless questions about how everything is connected. I did not realize how useful this need for information was in IT, until I started studying and working with data analysis and machine learning last year. That was just the start... 
  
              From the beginning, I was hooked! Endless hours of researching and studying, from COMPTIA A+ and Docker to System Administration and Data Science, which led me to enrolling in Tech Elevator's Java Full Stack Bootcamp. Learning new languages, endless research, focusing on efficiency and organization, plus being surrounded by like-minded people- It's everything I didn't even know I wanted!
  
              Inquisitive, tenacious and approachable extrovert interested in career opportunities where I can further indulge my love of learning and dive into the endless possibilities of software development and technology.
            </span>
          </div>
        </div>
        <div class="team-member">
            <a href="https://www.linkedin.com/in/steven-riggle/" target="_blank"><img src="src\assets\Steven.jpg" alt="Steven" class="team-member-image"></a>

          <div class="bio-tooltip" :style="{border: '2px solid rgb(223,204,145)'}">
            <p>Steven</p>
            <span class="tooltip-text">
              Hello! My name is Steven Riggle, and I am an aspiring developer currently enrolled in the Tech Elevator program. Although I am relatively new to the technology field, I have a strong background in problem-solving and complex systems. As a passionate drag racer and mechanic, attention to detail and critical thinking are skills I have practiced and honed. I find immense enjoyment in tackling challenges and finding creative solutions. This is why I am drawn to software development, as it offers constant opportunities to overcome obstacles. While most of my professional experience lies in project management within the construction industry, I have also gained valuable insights as a scrum master and product owner in an agile environment at Kroger technology. Additionally, I proudly served in the United States Navy for six years, achieving the rank of second class petty officer. During my time in the Navy, I completed two overseas deployments and received several awards, including a humanitarian service medal for search and rescue operations immediately following the 2009 tsunami in American Samoa. I am now eager to embark on the next phase of my professional journey. I am seeking an organization that will challenge and support me, allowing me to grow and improve every day. I am excited to contribute meaningfully to the success of the team.
            </span>
          </div>
        </div>
        <div class="team-member">
          <a href="https://www.linkedin.com/in/naomi-adams-0804a4106/" target="_blank"><img src="src\assets\Naomi.jpg" alt="Naomi" class="team-member-image"></a>
          <div class="bio-tooltip" :style="{border: '2px solid rgb(223,204,145)'}">
            <p>Naomi</p>
            <span class="tooltip-text">
              Hi! I'm Naomi. With over a decade of entrepreneurial experience under my belt, I embarked on a transformative journey that has now led me to the world of technology. I successfully completed a rigorous full-stack coding bootcamp, equipping myself with the technical skills needed to thrive in this dynamic field.
  
              My transition from entrepreneurship to tech represents a pivotal moment in my career. It signifies my unwavering commitment to growth, learning, and embracing new challenges. The bootcamp experience not only deepened my technical knowledge but also sharpened my problem-solving abilities and enhanced my adaptability.
  
              Having completed the bootcamp, my goal remains the same: to excel in the technology world, where opportunities for innovation and growth abound. I am now ready to apply my entrepreneurial spirit, combined with my newfound technical expertise, to create solutions that make a meaningful impact.
  
              Let's continue to explore the limitless possibilities that technology offers, and together, we'll shape a future where innovation knows no bounds.
            </span>
          </div>
        </div>
        <div class="team-member">
            <a href="https://www.linkedin.com/in/daviddefoshajr/" target="_blank"><img src="src\assets\David.jpg" alt="David" class="team-member-image"></a>
          <div class="bio-tooltip" :style="{border: '2px solid rgb(223,204,145)'}">
            <p>David</p>
            <span class="tooltip-text">
              Hello there! I'm David and ever since I was young, I became fascinated with coding and how much depth it could bring with endless possibilities and allow me to use what I am passionate about, to pioneer anything I set my mind to. When I was 14 years old I learned how to create my very own macros in a game called World Of Warcraft which allowed me to assign a single keystroke to move through an array of my numerous abilities without having to click them separately and gain a big advantage over others. That was the day I found my passion in coding and knew it was what I wanted to do for the rest of my life.
  
              With a wide background in sales and customer service experience, there is a lot I can carry over into the programming world. Working closely with teams, developing strong people skills, and hands on experience with project and goal deadlines is something I have discovered to be a very important aspect in the development field. I am always eager to learn and grow, and I thrive on challenges. I take pride in whatever it is I enjoy doing and my continuous growth in knowledge in the Full-Stack cohort I am currently attending has become something I strive to increase and become the best developer that I can be.
            </span>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');
body {
  text-align: center;
}
.about-us {
  text-align: center;
  padding: 20px;
}
h1 {
    text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  font-size: 35px; /* Adjust the font size as needed */
  margin: 0;
}
p {
  text-align: center;
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Nunito Sans', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  margin-top: 10px;
}
.team-members {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 20px;
}
.team-member {
  text-align: center;
  position: relative;
}
.team-member-image {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  transition: transform 0.3s ease-in-out;
}
.team-member:hover .team-member-image {
  transform: scale(1.1);
}
.team-member-name {
  font-family: 'Lobster Two', sans-serif;
  font-family: 'Ubuntu', sans-serif;
  font-weight: bolder;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(247, 18, 18, 0.8);
  padding: 50px;
  border: 50px;
}

.team-member:hover .team-member-bio {
  display: block;
}

.bio-tooltip{
    position: relative;
    top: 30px;
}
</style>

