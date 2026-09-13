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
                maxlength="50"
                pattern="[A-Za-z][A-Za-z' \-]*"
                autocomplete="given-name"
                required
                @input="trackChanges"
                title="Enter a valid first name using letters, spaces, apostrophes, or hyphens"
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
                maxlength="50"
                pattern="[A-Za-z][A-Za-z' \-]*"
                autocomplete="family-name"
                required
                @input="trackChanges"
                title="Enter a valid last name using letters, spaces, apostrophes, or hyphens"
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
                required
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
                required
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
                required
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
              <label for="address1">Street Address:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="address1"
                v-model="user.address1"
                minlength="3"
                maxlength="100"
                autocomplete="address-line1"
                required
                @input="trackChanges"
                title="Enter a street address between 3 and 100 characters"
              />
            </div>
          </div>

          <!-- Address 2 -->
          <div class="form">
            <div class="col-30">
              <label for="address2">Apt / Unit:</label>
            </div>

            <div class="col-70">
              <input
                type="text"
                id="address2"
                v-model="user.address2"
                maxlength="50"
                autocomplete="address-line2"
                @input="trackChanges"
                title="Address line 2 can contain up to 50 characters"
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
                minlength="2"
                maxlength="100"
                pattern="[A-Za-z][A-Za-z .'\-]*"
                placeholder="Columbus"
                autocomplete="address-level2"
                required
                @input="trackChanges"
                title="Enter a valid city using letters, spaces, periods, apostrophes, or hyphens"
              />
            </div>
          </div>

          <!-- State -->
          <div class="form">
            <div class="col-30">
              <label for="state">State:</label>
            </div>

            <div class="col-70">
              <select
                id="state"
                v-model="user.state"
                autocomplete="address-level1"
                required
                @change="trackChanges"
                title="Select State"
              >
                <option
                  value=""
                  disabled
                >
                  Select State
                </option>

                <option
                  v-for="state in states"
                  :key="state.abbreviation"
                  :value="state.abbreviation"
                >
                  {{ state.name }}
                </option>
              </select>
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
                maxlength="5"
                pattern="\d{5}"
                inputmode="numeric"
                placeholder="43215"
                autocomplete="postal-code"
                required
                @input="trackChanges"
                title="Enter a valid 5-digit ZIP code"
              />
            </div>
          </div>
        </fieldset>

        <!-- Profile Form Buttons -->
        <div class="button-container">
          <button
            type="submit"
            :disabled="
              isSaving ||
              (status && Object.keys(changedFields).length === 0)
            "
            :title="
              isSaving
                ? 'Saving Profile'
                : status && Object.keys(changedFields).length === 0
                  ? 'No Changes to Save'
                  : status
                    ? 'Click to Save Changes'
                    : 'Click to Create Profile'
            "
          >
            {{ isSaving ? 'Saving...' : 'Save Profile' }}
          </button>

          <button
            type="button"
            @click="cancelChanges"
            :disabled="isSaving"
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

      // States used for the state dropdown
      states: [
        { name: 'Alabama', abbreviation: 'AL' },
        { name: 'Alaska', abbreviation: 'AK' },
        { name: 'Arizona', abbreviation: 'AZ' },
        { name: 'Arkansas', abbreviation: 'AR' },
        { name: 'California', abbreviation: 'CA' },
        { name: 'Colorado', abbreviation: 'CO' },
        { name: 'Connecticut', abbreviation: 'CT' },
        { name: 'Delaware', abbreviation: 'DE' },
        { name: 'Florida', abbreviation: 'FL' },
        { name: 'Georgia', abbreviation: 'GA' },
        { name: 'Hawaii', abbreviation: 'HI' },
        { name: 'Idaho', abbreviation: 'ID' },
        { name: 'Illinois', abbreviation: 'IL' },
        { name: 'Indiana', abbreviation: 'IN' },
        { name: 'Iowa', abbreviation: 'IA' },
        { name: 'Kansas', abbreviation: 'KS' },
        { name: 'Kentucky', abbreviation: 'KY' },
        { name: 'Louisiana', abbreviation: 'LA' },
        { name: 'Maine', abbreviation: 'ME' },
        { name: 'Maryland', abbreviation: 'MD' },
        { name: 'Massachusetts', abbreviation: 'MA' },
        { name: 'Michigan', abbreviation: 'MI' },
        { name: 'Minnesota', abbreviation: 'MN' },
        { name: 'Mississippi', abbreviation: 'MS' },
        { name: 'Missouri', abbreviation: 'MO' },
        { name: 'Montana', abbreviation: 'MT' },
        { name: 'Nebraska', abbreviation: 'NE' },
        { name: 'Nevada', abbreviation: 'NV' },
        { name: 'New Hampshire', abbreviation: 'NH' },
        { name: 'New Jersey', abbreviation: 'NJ' },
        { name: 'New Mexico', abbreviation: 'NM' },
        { name: 'New York', abbreviation: 'NY' },
        { name: 'North Carolina', abbreviation: 'NC' },
        { name: 'North Dakota', abbreviation: 'ND' },
        { name: 'Ohio', abbreviation: 'OH' },
        { name: 'Oklahoma', abbreviation: 'OK' },
        { name: 'Oregon', abbreviation: 'OR' },
        { name: 'Pennsylvania', abbreviation: 'PA' },
        { name: 'Rhode Island', abbreviation: 'RI' },
        { name: 'South Carolina', abbreviation: 'SC' },
        { name: 'South Dakota', abbreviation: 'SD' },
        { name: 'Tennessee', abbreviation: 'TN' },
        { name: 'Texas', abbreviation: 'TX' },
        { name: 'Utah', abbreviation: 'UT' },
        { name: 'Vermont', abbreviation: 'VT' },
        { name: 'Virginia', abbreviation: 'VA' },
        { name: 'Washington', abbreviation: 'WA' },
        { name: 'West Virginia', abbreviation: 'WV' },
        { name: 'Wisconsin', abbreviation: 'WI' },
        { name: 'Wyoming', abbreviation: 'WY' }
      ],

      // Current year used for birth year validation
      currentYear: new Date().getFullYear(),

      // Profile status: false = creating, true = updating
      status: false,

      // Control the visibility of the profile form
      isVisible: false,

      // Store profile fields after the user makes changes
      changedFields: {},

      // Track whether the profile is currently being saved
      isSaving: false,

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
      },
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

    // Discard unsaved changes and close the profile form
    cancelChanges() {
      if (ProfileService.originalProfile) {
        this.user = { ...ProfileService.originalProfile };
      }

      this.changedFields = {};
      this.closeForm();
    },

    // Check whether the user already has a profile
    async fetchStatus() {
      try {
        const response = await ProfileService.getStatus();
        this.status = response;

      } catch (error) {
        console.error('Error fetching profile status:', error);
      }
    },

    // Create or update the user's profile
    async saveProfile() {
      this.isSaving = true;

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

          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'Profile created successfully!',
              type: 'success'
            }
          }));
          this.closeForm();

        } else {

          // Send the updated profile fields to the server
          const updatedProfile = { ...this.changedFields };

          // Close the form when no profile fields were changed
          if (Object.keys(updatedProfile).length === 0) {
            this.isSaving = false;
            this.closeForm();
            return;
          }
          
          const savedProfile = await ProfileService.updateProfile(updatedProfile);

          // Store the profile returned by the server
          this.user = { ...savedProfile };
          this.changedFields = {};

          // Update the profile displayed on the page
          this.$emit('profile-updated', savedProfile);

          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'Profile updated successfully!',
              type: 'success'
            }
          }));
          this.closeForm();
        }

      } catch (error) {
        console.error('Error saving profile:', error);

        // Display an error based on the server response
        if (error.response && error.response.status === 400) {
          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'Please check your profile information and correct any invalid fields.',
              type: 'warning'
            }
          }));
        } else {
          window.dispatchEvent(new CustomEvent('app-notification', {
            detail: {
              message: 'There was a problem saving your profile. Please try again.',
              type: 'error'
            }
          }));
        }
      } finally {
        // Allow the profile form to be saved again
        this.isSaving = false;
      }
    },

    // Get the user's existing profile information
    async fetchProfile() {
      try {
        const response = await ProfileService.getProfile();

        if (response) {
          this.user = { ...response };
        }

      } catch (error) {
        console.error('Error fetching profile:', error);
      }
    },

    // Track only profile fields that are different from the original profile
    trackChanges() {
      const originalProfile = ProfileService.originalProfile;

      if (!originalProfile) {
        return;
      }

      const updatedFields = {
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

      this.changedFields = {};

      Object.keys(updatedFields).forEach(key => {
        if (updatedFields[key] !== originalProfile[key]) {
          this.changedFields[key] = updatedFields[key];
        }
      });
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
  width: 100%;
  margin: 1rem auto 0;
}

.profile-form {
  width: 100%;
  color: rgb(53, 37, 19);
  background-color: rgb(160, 153, 145);
  border: .15rem #e8bb64 solid;
  border-radius: .25rem;
  padding: 1rem;
}

.profile-form form {
  width: 100%;
}


/* Profile Form Sections */

.name,
.birthday,
.location {
  display: flex;
  flex-direction: column;
  width: 100%;
  border: .1rem rgb(245, 242, 242) solid;
  padding: .75rem;
  margin: 0 auto .75rem;
}

legend {
  font-size: .9rem;
  font-weight: 500;
  padding: 0 .25rem;
}


/* Profile Form Fields */

.form {
  display: flex;
  align-items: center;
  width: 100%;
  margin-bottom: .5rem;
}

.form:last-child {
  margin-bottom: 0;
}

.form label {
  display: flex;
  align-items: center;
  width: 100%;
  font-size: .85rem;
  font-weight: 500;
  text-align: left;
  padding-right: .5rem;
}

.form input,
.form select {
  width: 100%;
  min-width: 0;
  min-height: 2.75rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: rgb(245, 242, 242);
  border: .1rem #ccc solid;
  border-radius: .2rem;
  padding: .5rem;
}

.form input:focus-visible,
.form select:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .1rem;
}


/* Profile Form Columns */

.col-30 {
  display: flex;
  width: 35%;
}

.col-70 {
  width: 65%;
}


/* Profile Form Buttons */

.button-container {
  display: flex;
  justify-content: center;
  gap: .75rem;
  width: 100%;
  margin-top: .25rem;
}

.button-container button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10rem;
  min-height: 2.75rem;
  font-size: .8rem;
  color: rgb(53, 37, 19);
  background-color: #e8bb64;
  border: .1rem rgb(53, 37, 19) solid;
  border-radius: .2rem;
  padding: .4rem .6rem;
  margin: 0;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out,
    border-color 0.3s ease-in-out,
    box-shadow 0.2s ease-in-out,
    transform 0.15s ease-in-out;
}

.button-container button:hover:not(:disabled) {
  color: #e8bb64;
  background-color: rgb(53, 37, 19);
  border-color: #e8bb64;
  box-shadow:
    inset 0 .15rem .3rem rgba(0, 0, 0, .35),
    0 0 .35rem rgba(232, 187, 100, .35);
  transform: translateY(.05rem);
}

.button-container button:active:not(:disabled) {
  box-shadow:
    inset 0 .25rem .4rem rgba(0, 0, 0, .45),
    0 0 .25rem rgba(232, 187, 100, .3);
  transform: translateY(.1rem);
}

.button-container button:disabled {
  opacity: .6;
  transform: none;
}

.button-container button:disabled:not(.saving) {
  cursor: not-allowed;
}

.button-container button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}
</style>
