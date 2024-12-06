<template>
  <main>
    <div class="profile-container">
      <div class="profile-form">
        <h2>Create or Edit Profile</h2>
        <form @submit.prevent="submitForm">
          <!-- First Name and Last Name -->
          <div>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" v-model="user.firstName" required />
          </div>

          <div>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" v-model="user.lastName" required />
          </div>

          <!-- Birth Date -->
          <div>
            <label for="birthMonth">Birth Month:</label>
            <select id="birthMonth" v-model="user.birthMonth" required>
              <option value="" disabled>Select Month</option>
              <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
            </select>
          </div>

          <div>
            <label for="birthDay">Birth Day:</label>
            <input type="number" id="birthDay" v-model="user.birthDay" min="1" max="31" required />
          </div>

          <div>
            <label for="birthYear">Birth Year:</label>
            <input type="number" id="birthYear" v-model="user.birthYear" min="1900" max="2024" required />
          </div>

          <!-- Address -->
          <div>
            <label for="address1">Address 1:</label>
            <input type="text" id="address1" v-model="user.address1" required />
          </div>

          <div>
            <label for="address2">Address 2:</label>
            <input type="text" id="address2" v-model="user.address2" />
          </div>

          <!-- City, State, and Zipcode -->
          <div>
            <label for="city">City:</label>
            <input type="text" id="city" v-model="user.city" required />
          </div>

          <div>
            <label for="state">State:</label>
            <input type="text" id="state" v-model="user.state" required />
          </div>

          <div>
            <label for="zipcode">Zipcode:</label>
            <input type="text" id="zipcode" v-model="user.zipcode" required />
          </div>

          <!-- Submit Button -->
          <button type="submit">Save Profile</button>
        </form>
      </div>
    </div>  
  </main>
</template>
  
<script>
export default {
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        birthMonth: '',
        birthDay: '',
        birthYear: '',
        address1: '',
        address2: '',
        city: '',
        state: '',
        zipcode: ''
      },
      months: [
        'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 
        'September', 'October', 'November', 'December'
      ]
    };
  },
  methods: {
    submitForm() {
      // Here, you would typically send the form data to the server using an API.
      console.log("Form Submitted:", this.user);
      alert("Profile saved successfully!");
      
      // Reset the form after submission
      this.resetForm();
    },
    resetForm() {
      this.user = {
        firstName: '',
        lastName: '',
        birthMonth: '',
        birthDay: '',
        birthYear: '',
        address1: '',
        address2: '',
        city: '',
        state: '',
        zipcode: ''
      };
    }
  }
};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: column;
}

img {
  width: 5vw;
}
</style>