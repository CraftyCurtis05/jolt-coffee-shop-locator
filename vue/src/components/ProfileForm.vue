<!-- ProfileForm.vue Component -->
<template>
  <article class="profile-form-modal" v-if="isVisible">
    <header>
      <h1>Profile Form</h1>
    </header>
    <section class="profile-form">
      <form @submit.prevent="submitForm">
        <!-- Personal Information Section -->
        <fieldset>
          <legend>Personal Information</legend>
          <!-- First Name Input -->
          <div>
            <label for="firstName">First Name:</label>
            <input
              type="text"
              id="firstName"
              v-model="user.firstName"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
          <!-- Last Name Input -->
          <div>
            <label for="lastName">Last Name:</label>
            <input 
              type="text" 
              id="lastName" 
              v-model="user.lastName"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
        </fieldset>

        <!-- Birthday Section -->
        <fieldset class="birthday" v-if="isFormSubmitted === false">
          <legend>Birthday</legend>
          <div>
            <label for="birthMonth">Birth Month:</label>
            <select 
              id="birthMonth" 
              v-model="user.birthMonth" 
              :disabled="isFormSubmitted" 
              :required="!isFormSubmitted"
            >
              <option value="" disabled>Select Month</option>
              <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
            </select>
          </div>
          <div>
            <label for="birthDay">Birth Day:</label>
            <input 
              type="number" 
              id="birthDay" 
              v-model="user.birthDay" 
              :disabled="isFormSubmitted" 
              :required="!isFormSubmitted"
              min="1" 
              max="31" 
            />
          </div>
          <div>
            <label for="birthYear">Birth Year:</label>
            <input 
              type="number" 
              id="birthYear" 
              v-model="user.birthYear" 
              :disabled="isFormSubmitted" 
              :required="!isFormSubmitted"
              min="1900" 
              max="2024" 
            />
          </div>
        </fieldset>

        <!-- Address Section -->
        <fieldset>
          <legend>Location Details</legend>
          <div>
            <label for="address1">Address 1:</label>
            <input 
              type="text" 
              id="address1" 
              v-model="user.address1"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="address2">Address 2:</label>
            <input 
              type="text" 
              id="address2" 
              v-model="user.address2"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="city">City:</label>
            <input 
              type="text" 
              id="city" 
              v-model="user.city"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="state">State:</label>
            <input 
              type="text" 
              id="state" 
              v-model="user.state"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="zipcode">Zipcode:</label>
            <input 
              type="text" 
              id="zipcode" 
              v-model="user.zipcode"
              :required="!isFormSubmitted"
              @input="trackChanges"
            />
          </div>
        </fieldset>

        <div>
          <button type="submit">Save Profile</button>
          <button type="button" @click="closeForm">Cancel</button>
        </div>
      </form>
    </section>
  </article>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      isVisible: false,
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
      ],
      isFormSubmitted: false,  // Flag for form submission state
      changedFields: {}  // Object to track changed fields
    };
  },
  methods: {

    openForm() {
      this.isVisible = true;  // Show modal
    },

    closeForm() {
      this.isVisible = false;  // Hide modal
    },

    async fetchFormSubmittedState() {
      try {
        const response = await ProfileService.getFormSubmitted();
        this.isFormSubmitted = response;  // Get the submission state from the server
      } catch (error) {
        console.error("Error fetching form submission state:", error);
      }
    },

    async fetchProfile() {
      try {
        const response = await ProfileService.getProfile();
        this.user = { ...response };  // Populate form with existing profile data
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
    },
    // Track changes to the form fields
    trackChanges() {
      // Only track fields that have been modified from the original state
      this.changedFields = {
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        birthMonth: this.user.birthMonth,
        birthDay: this.user.birthDay,
        birthYear: this.user.birthYear,
        address1: this.user.address1,
        address2: this.user.address2,
        city: this.user.city,
        state: this.user.state,
        zipcode: this.user.zipcode
      };
    },

    async submitForm() {
      try {
        const updatedProfile = { ...this.changedFields }; // Only the changed fields
        await ProfileService.saveProfile(updatedProfile);

        this.isFormSubmitted = true;
        this.$emit('profile-updated', this.user); // Emit updated profile to parent

        console.log('Form submitted successfully');
        this.closeForm();
      } catch (error) {
        alert("Error saving your profile. Please try again.");
      }
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
  },
  mounted() {
    this.fetchFormSubmittedState();  // Fetch initial form submission state
    this.fetchProfile();  // Fetch the user's profile
  }
};
</script>

<style scoped>
.profile-form {
  max-width: 500px;
  margin: 0 auto;
}

.profile-form label {
  display: block;
  margin-bottom: 5px;
}

.profile-form input,
.profile-form select {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.profile-form button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.profile-form button:hover {
  background-color: #45a049;
}

.profile-form-modal {
  width: 20vw;
  padding: 0 3vw;
}
</style>