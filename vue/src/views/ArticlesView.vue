<template>
  <h1>Articles</h1>
  <p>Dive into the Coffee Chronicles: A World of Brew-tiful Stories</p>
    <section class="articles-pics">
      <div class="articles-pic"><a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank"><img src="src\assets\health2.webp"></a></div>
      <div class="articles-pic"><a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank"><img src="src\assets\brainBoosting.jpeg"></a></div>
      <div class="articles-pic"><a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank"><img src="src\assets\tooMuch.jpeg"></a></div>
      <div class="articles-pic"><a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank"><img src="src\assets\bestTime1.jpeg"></a></div>
    </section>
    <section class="articles-texts">
      <div class="articles-text1"><a href="https://riordanclinic.org/2018/03/13-health-benefits-of-coffee-based-on-science/?gclid=Cj0KCQiAsburBhCIARIsAExmsu76rAuS3rnEO2lznJSxaKKwz8erMeb-IEj_v6c1q9rGZAkSqsr1ehYaAqYOEALw_wcB" target="_blank">Health Benefits of Coffee</a></div>
      <div class="articles-text2"><a href="https://www.medscape.com/viewarticle/994049?form=fpf" target="_blank">Coffee Brain Boosting Effect</a></div>
      <div class="articles-text3"><a href="https://www.nytimes.com/2023/09/26/well/eat/coffee-benefits-caffeine-risks.html" target="_blank">How much is too much Coffee?</a></div>
      <div class="articles-text4"><a href="https://www.inc.com/geoffrey-james/scientists-just-discovered-best-time-of-day-to-drink-your-first-cup-of-coffee.html" target="_blank">Best Time to Drink Coffee</a></div>
    </section>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Lobster+Two&family=Ubuntu&display=swap');

h1{
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
  margin-top: 10px; /* Add some spacing */
}
.articles-pics {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
  position: relative;
}
.articles-pic {
  border: 10px solid white;
  resize: both;
}
.articles-text1 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 175px;
  bottom: 800px; 
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.articles-text2 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 775px;
  bottom: 832px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.articles-text3 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 140px;
  bottom: 445px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
.articles-text4 {
  display: flex;
  flex-direction: row;
  position: relative;
  left: 790px;
  bottom: 477px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; /* Adjust the font size as needed */
}
a:link {
  color: white;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; 
}
a:visited {
  color: white;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px;
}
a:hover{
  color:#e8bb64;
  text-decoration: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 790;
  font-size: 24px; 
}
</style>