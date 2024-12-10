<!-- ProfileForm.vue Component -->

<template>
  <section v-if="isVisible" class="profile-form-container">
    <header>
      <h1>Profile Form</h1>
    </header>
    
    <article class="profile-form">
      <form @submit.prevent="submitForm" ref="profileForm">

        <!-- Personal Information Section -->
        <fieldset class="name">
          <legend>Personal Information</legend>
          <div>
            <label for="firstName">First Name:</label>
            <input
              type="text"
              id="firstName"
              v-model="user.firstName"
              :required="!status"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="lastName">Last Name:</label>
            <input
              type="text"
              id="lastName"
              v-model="user.lastName"
              :required="!status"
              @input="trackChanges"
            />
          </div>
        </fieldset>

        <!-- Birthday Section (only visible if the status is false) -->
        <fieldset class="birthday" v-if="!status">
          <legend>Birthday</legend>
          <div>
            <label for="birthMonth">Birth Month:</label>
            <select
              id="birthMonth"
              v-model="user.birthMonth"
              :disabled="status"
              :required="!status"
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
              :disabled="status"
              :required="!status"
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
              :disabled="status"
              :required="!status"
              min="1900"
              max="2024"
            />
          </div>
        </fieldset>

        <!-- Location Section -->
        <fieldset class="location">
          <legend>Location Details</legend>
          <div>
            <label for="address1">Address 1:</label>
            <input
              type="text"
              id="address1"
              v-model="user.address1"
              :required="!status"
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
              :required="!status"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="state">State:</label>
            <input
              type="text"
              id="state"
              v-model="user.state"
              :required="!status"
              @input="trackChanges"
            />
          </div>
          <div>
            <label for="zipcode">Zipcode:</label>
            <input
              type="text"
              id="zipcode"
              v-model="user.zipcode"
              :required="!status"
              @input="trackChanges"
            />
          </div>
        </fieldset>

        <!-- Save and cancel form buttons -->
        <div>
          <button type="submit" @click="saveProfile">Save Profile</button>
          <button type="button" @click="closeForm">Cancel</button>
        </div>

      </form>
    </article>
  </section>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
  data() {
    return {
      // Array of months used for the birth month dropdown
      months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],

      // Indicates if the form is in 'update' mode (true = updating, false = creating)
      status: false,

      // Toggles the visibility of the profile form
      isVisible: false,

      // Tracks the fields that have been modified
      changedFields: {},

      // User's profile data
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
      }
    };
  },
  methods: {

    /**
     * Opens the profile form and emits visibility change event.
     */
    openForm() {
      this.isVisible = true;
      this.$emit('form-visible', true);
    },

    /**
     * Closes the profile form and emits visibility change event.
     */
    closeForm() {
      this.isVisible = false;
      this.$emit('form-visible', false);
    },

    /**
     * Fetches the profile creation/update status from the backend.
     * Sets `status` to determine if the form is for creating or updating a profile.
     */
    async fetchStatus() {
      try {
        const response = await ProfileService.getStatus();
        this.status = response;   // Set form status (true = updating, false = creating)
      } catch (error) {
        console.error('Error fetching status:', error);
      }
    },

    /**
     * Saves the profile data by either creating a new profile or updating an existing one.
     * Depending on the status, it calls the appropriate service method.
     */
    async saveProfile() {
      try {
        if (!this.status) {
          // Create new profile if status is false
          await ProfileService.createProfile(this.user);
          alert('Profile created successfully!');
          this.closeForm();
        } else {
          // Update existing profile with changed fields
          const updatedProfile = { ...this.changedFields };
          await ProfileService.updateProfile(updatedProfile);

          // Emit updated profile and close form
          this.$emit('profile-updated', this.user);
          alert('Profile updated successfully!');
          this.closeForm();
        }
      } catch (error) {
        console.error('Error saving profile:', error);
        alert("Make sure to fill out all required profile fields!")
      }
    },

    /**
     * Fetches the existing profile data from the backend to populate the form.
     */
    async fetchProfile() {
      try {
        const response = await ProfileService.getProfile();
        this.user = { ...response };  // Populate form with existing data
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
    },

    /**
     * Tracks changes to form fields and updates the `changedFields` object 
     * to store modified values.
     */
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
    }  
  },

  mounted() {
    // Fetch the initial status of the profile (new or updating)
    this.fetchStatus();
    // Load the user's profile data if available
    this.fetchProfile();
  }
};
</script>

<style scoped>
.profile-form-container {
  width: 20vw;
  padding: 0 3vw;
}
</style>