// index.js Router

import { createRouter, createWebHistory } from 'vue-router';

// Import views
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import ShopView from '../views/ShopView.vue';
import ArticlesView from '../views/ArticlesView.vue';
import LocatorView from '../views/LocatorView.vue';
import ProfileView from '../views/ProfileView.vue';
import AboutUsView from '../views/AboutUsView.vue';
import NotFoundView from '../views/NotFoundView.vue';


/*
 * The Vue Router directs the browser to render a specific view
 * inside App.vue based on the current URL.
 *
 * It also checks whether a route requires the user to be authenticated.
 * If authentication is required and the user is not logged in,
 * they are redirected to the login page.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      title: 'Home | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      title: 'Sign In | Jolt',
      hideNavBar: true,
      requiresAuth: false
    }
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView,
    meta: {
      title: 'Logout | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      title: 'Create Account | Jolt',
      hideNavBar: true,
      requiresAuth: false
    }
  },
  {
    path: '/shop',
    name: 'shop',
    component: ShopView,
    meta: {
      title: 'Shop | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/articles',
    name: 'articles',
    component: ArticlesView,
    meta: {
      title: 'Articles | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/locator',
    name: 'locator',
    component: LocatorView,
    meta: {
      title: 'Coffee Shop Locator | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/profile',
    name: 'profile',
    component: ProfileView,
    meta: {
      title: 'Profile | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/aboutUs',
    name: 'aboutUs',
    component: AboutUsView,
    meta: {
      title: 'About Us | Jolt',
      requiresAuth: true
    }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'not-found',
    component: NotFoundView,
    meta: {
      title: 'Page Not Found | Jolt',
      requiresAuth: true
    }
  }
];


// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes,

  // Start each page at the top when navigating
  scrollBehavior() {
    return {
      top: 0
    };
  }
});


// Check authentication before navigating to protected routes
router.beforeEach((to) => {

  // Update the browser tab title for the current page
  document.title = to.meta.title || 'Jolt';

  // Determine if the route requires authentication
  const requiresAuth = to.matched.some(route => route.meta.requiresAuth);

  // Get the saved authentication token
  const token = localStorage.getItem('token');

  // Redirect unauthenticated users to the login page
  if (requiresAuth && !token) {
    return { name: 'login' };
  }
});


export default router;
