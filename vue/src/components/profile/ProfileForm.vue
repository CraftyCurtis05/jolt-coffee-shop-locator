<!-- ProfileForm.vue Component -->

<template>
  <article
    v-if="isVisible"
    class="profile-form-container"
  >

    <!-- Profile Form -->
    <section class="profile-form">
      <form @submit.prevent="saveProfile">

        <!-- Personal Information -->
        <fieldset class="name">
          <legend>Personal Information</legend>

          <!-- First Name -->
          <div class="form">
            <div class="col-30">
              <label for="firstName">First Name:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="firstName"
                v-model="user.firstName"
                :required="!status"
                @input="trackChanges"
                title="Enter First Name"
              />
            </div>
          </div>

          <!-- Last Name -->
          <div class="form">
            <div class="col-30">
              <label for="lastName">Last Name:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="lastName"
                v-model="user.lastName"
                :required="!status"
                @input="trackChanges"
                title="Enter Last Name"
              />
            </div>
          </div>
        </fieldset>

        <!-- Birthday -->
        <fieldset
          class="birthday"
          v-if="!status"
        >
          <legend>Birthday</legend>

          <!-- Birth Month -->
          <div class="form">
            <div class="col-30">
              <label for="birthMonth">Birth Month:</label>
            </div>

            <div class="col-70">
              <select
                id="birthMonth"
                v-model="user.birthMonth"
                :disabled="status"
                :required="!status"
                title="Enter Birth Month"
              >
                <option
                  value=""
                  disabled
                >
                  Select Month
                </option>

                <option
                  v-for="month in months"
                  :key="month"
                  :value="month"
                >
                  {{ month }}
                </option>
              </select>
            </div>
          </div>

          <!-- Birth Day -->
          <div class="form">
            <div class="col-30">
              <label for="birthDay">Birth Day:</label>
            </div>

            <div class="col-70">
              <input
                type="number"
                id="birthDay"
                v-model="user.birthDay"
                :disabled="status"
                :required="!status"
                min="1"
                max="31"
                title="Enter Birth Day"
              />
            </div>
          </div>

          <!-- Birth Year -->
          <div class="form">
            <div class="col-30">
              <label for="birthYear">Birth Year:</label>
            </div>

            <div class="col-70">
              <input
                type="number"
                id="birthYear"
                v-model="user.birthYear"
                :disabled="status"
                :required="!status"
                min="1900"
                :max="currentYear"
                title="Enter Birth Year"
              />
            </div>
          </div>
        </fieldset>

        <!-- Location Details -->
        <fieldset class="location">
          <legend>Location Details</legend>

          <!-- Address 1 -->
          <div class="form">
            <div class="col-30">
              <label for="address1">Address 1:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="address1"
                v-model="user.address1"
                :required="!status"
                @input="trackChanges"
                title="Enter Address"
              />
            </div>
          </div>

          <!-- Address 2 -->
          <div class="form">
            <div class="col-30">
              <label for="address2">Address 2:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="address2"
                v-model="user.address2"
                @input="trackChanges"
                title="Enter Address 2"
              />
            </div>
          </div>

          <!-- City -->
          <div class="form">
            <div class="col-30">
              <label for="city">City:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="city"
                v-model="user.city"
                :required="!status"
                @input="trackChanges"
                title="Enter City"
              />
            </div>
          </div>

          <!-- State -->
          <div class="form">
            <div class="col-30">
              <label for="state">State:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="state"
                v-model="user.state"
                maxlength="2"
                :required="!status"
                @input="trackChanges"
                title="Enter State Abbreviation"
              />
            </div>
          </div>

          <!-- Zip Code -->
          <div class="form">
            <div class="col-30">
              <label for="zipcode">Zip Code:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="zipcode"
                v-model="user.zipcode"
                :required="!status"
                @input="trackChanges"
                title="Enter Zip Code"
              />
            </div>
          </div>
        </fieldset>

        <!-- Profile Form Buttons -->
        <div class="button-container">
          <button
            type="submit"
            title="Click to Save Updated Profile"
          >
            Save Profile
          </button>

          <button
            type="button"
            @click="closeForm"
            title="Click to Close Update Profile"
          >
            Cancel
          </button>
        </div>

      </form>
    </section>

  </article>
</template>

<script>
import ProfileService from '../../services/ProfileService.js';

export default {
  name: 'ProfileForm',

  data() {
    return {
      // Months used for the birth month dropdown
      months: [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
      ],

      // Current year used for birth year validation
      currentYear: new Date().getFullYear(),

      // Profile status: false = creating, true = updating
      status: false,

      // Control the visibility of the profile form
      isVisible: false,

      // Store profile fields after the user makes changes
      changedFields: {},

      // User profile information
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

    // Open the profile form
    openForm() {
      this.isVisible = true;
      this.$emit('form-visible', true);
    },

    // Close the profile form
    closeForm() {
      this.isVisible = false;
      this.$emit('form-visible', false);
    },

    // Check whether the user already has a profile
    async fetchStatus() {
      try {
        const response = await ProfileService.getStatus();
        this.status = response;

        // *DEBUG* Log the profile status for debugging
        // console.log('Profile status:', response);

      } catch (error) {
        console.error('Error fetching profile status:', error);
      }
    },

    // Create or update the user's profile
    async saveProfile() {
      try {

        // Create a new profile
        if (!this.status) {
          const savedProfile = await ProfileService.createProfile(this.user);

          // Store the profile returned by the server
          this.user = { ...savedProfile };

          // Switch the form to update mode
          this.status = true;

          // Update the profile displayed on the page
          this.$emit('profile-updated', savedProfile);

          // *DEBUG* Log the created profile for debugging
          // console.log('Profile created:', savedProfile);

          alert('Profile created successfully!');
          this.closeForm();

        } else {

          // Send the updated profile fields to the server
          const updatedProfile = { ...this.changedFields };
          const savedProfile = await ProfileService.updateProfile(updatedProfile);

          // Store the profile returned by the server
          this.user = { ...savedProfile };
          this.changedFields = {};

          // Update the profile displayed on the page
          this.$emit('profile-updated', savedProfile);

          // *DEBUG* Log the updated profile for debugging
          // console.log('Profile updated:', savedProfile);

          alert('Profile updated successfully!');
          this.closeForm();
        }

      } catch (error) {
        console.error('Error saving profile:', error);

        // Display an error based on the server response
        if (error.response && error.response.status === 400) {
          alert('Make sure to fill out all required profile fields!');
        } else {
          alert('There was a problem saving your profile. Please try again.');
        }
      }
    },

    // Get the user's existing profile information
    async fetchProfile() {
      try {
        const response = await ProfileService.getProfile();
        this.user = { ...response };

        // *DEBUG* Log the profile for debugging
        // console.log('Existing profile:', response);

      } catch (error) {
        console.error('Error fetching profile:', error);
      }
    },

    // Store the profile information after a field is changed
    trackChanges() {
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
    // Check whether the user already has a profile
    this.fetchStatus();

    // Get the user's existing profile information
    this.fetchProfile();
  }
};
</script>

<style scoped>
/* Laptop L - 1440px */

.profile-form-container {
  margin: 1rem auto;
}

.profile-form {
  width: 20vw;
  background-color: rgb(160, 153, 145);
  border: 1vw rgb(160, 153, 145) solid;
}

.name,
.birthday,
.location {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}

#birthMonth {
  width: 12vw;
  height: 2.5vw;
}

fieldset {
  width: 18vw;
}

fieldset::after {
  content: "";
  display: table;
  clear: both;
}

legend {
  font-size: .9rem;
  font-weight: 500;
}

.form {
  display: flex;
}

.form label {
  display: flex;
  justify-content: center;
  font-size: .9rem;
  font-weight: 500;
  margin-top: .8rem;
}

.form input {
  width: 100%;
  font-size: .8rem;
  border: .1rem solid #ccc;
  border-radius: .1rem;
  box-sizing: border-box;
  padding: .6rem;
}

.col-30 {
  width: 35%;
}

.col-70,
option {
  width: 70%;
  margin-top: .3rem;
}

form .button-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: .9rem;
}

form button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 6rem;
  height: 1.5rem;
  font-size: .7rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border-radius: .1rem;
  margin-inline: .5rem;
  transition: all 0.5s ease-in-out;
}

form button:hover {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  cursor: pointer;
}
</style>