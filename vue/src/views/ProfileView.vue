<template>
  <body>
    <h1>Profile</h1>
    <p>Jolt Up Your Profile</p>
    <main>
      <div class="profile-pic">
        <ProfilePic/>
      </div>
      <div class="profile">
        <Profile/>
      </div>
      <div class="favorites">
        <Favorites/>
      </div>
    </main>
  </body>
</template>

<script>
import ProfilePic from '../components/ProfilePic.vue';
import Profile from '../components/Profile.vue';
import Favorites from '../components/Favorites.vue';

export default {
  components: {
    ProfilePic,
    Profile,
    Favorites
  }
};
</script>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: 'Ubuntu', sans-serif;
  color: #333437;
  padding-top: 5rem;
}

h1{
  font-weight: bolder;
  font-size: 1.8rem;
  text-align: center;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: .5vw;
}

main {
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
  padding: 0vw 3vw;
}
</style>