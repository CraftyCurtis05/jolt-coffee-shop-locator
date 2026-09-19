<!-- AppNotification.vue Component -->

<template>
  <transition name="notification">
    <aside
      v-if="isVisible"
      class="app-notification"
      :class="`notification-${type}`"
      :role="type === 'error' || type === 'warning' ? 'alert' : 'status'"
      :aria-live="type === 'error' || type === 'warning' ? 'assertive' : 'polite'"
    >
      <div class="notification-content">

        <!-- Notification Message -->
        <p>{{ message }}</p>

        <!-- Close Notification -->
        <button
          type="button"
          @click="hideNotification"
          aria-label="Close notification"
        >
          &times;
        </button>

      </div>
    </aside>
  </transition>
</template>

<script>
export default {
  name: 'AppNotification',

  data() {
    return {
      // Store the notification message
      message: '',

      // Store the notification type
      type: 'info',

      // Control notification visibility
      isVisible: false,

      // Store the notification timeout
      notificationTimeout: null
    };
  },

  methods: {

    // Display an application notification
    showNotification(event) {
      const {
        message,
        type = 'info',
        duration = 4000
      } = event.detail;

      // Clear an existing notification timeout
      if (this.notificationTimeout) {
        clearTimeout(this.notificationTimeout);
      }

      this.message = message;
      this.type = type;
      this.isVisible = true;

      // Automatically hide the notification
      this.notificationTimeout = setTimeout(() => {
        this.hideNotification();
      }, duration);
    },

    // Hide the current notification
    hideNotification() {
      this.isVisible = false;

      if (this.notificationTimeout) {
        clearTimeout(this.notificationTimeout);
        this.notificationTimeout = null;
      }
    }
  },

  mounted() {
    // Listen for application notifications
    window.addEventListener('app-notification', this.showNotification);
  },

  beforeUnmount() {
    // Remove the application notification listener
    window.removeEventListener('app-notification', this.showNotification);

    if (this.notificationTimeout) {
      clearTimeout(this.notificationTimeout);
    }
  }
};
</script>

<style scoped>
/* Application Notification */

.app-notification {
  position: fixed;
  top: 5.5rem;
  right: 1.5rem;
  z-index: 9999;
  width: min(24rem, calc(100% - 3rem));
  color: rgb(53, 37, 19);
  background-color: #f8f3e8;
  border: .15rem #e8bb64 solid;
  border-radius: .4rem;
  box-shadow: 0 .35rem 1rem rgba(53, 37, 19, .25);
}

.notification-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
  min-height: 3.5rem;
  padding: .75rem 1rem;
}

.notification-content p {
  margin: 0;
  line-height: 1.4;
}

.notification-content button {
  flex-shrink: 0;
  color: rgb(53, 37, 19);
  background: transparent;
  border: none;
  font-size: 1.5rem;
  line-height: 1;
  padding: 0;
  cursor: pointer;
}

.notification-content button:hover {
  color: #9b611f;
}

.notification-content button:focus-visible {
  outline: .15rem #e8bb64 solid;
  outline-offset: .2rem;
}


/* Notification Types */

.notification-success {
  border-left: .4rem #5f8f55 solid;
}

.notification-error {
  border-left: .4rem #a94442 solid;
}

.notification-warning {
  border-left: .4rem #d59a32 solid;
}

.notification-info {
  border-left: .4rem #5f7f8f solid;
}


/* Notification Animation */

.notification-enter-active,
.notification-leave-active {
  transition:
    opacity .25s ease-in-out,
    transform .25s ease-in-out;
}

.notification-enter-from,
.notification-leave-to {
  opacity: 0;
  transform: translateY(-.75rem);
}


/* Mobile - 600px */

@media (max-width: 600px) {

  .app-notification {
    top: 4.75rem;
    right: 1rem;
    width: calc(100% - 2rem);
  }
}
</style>
