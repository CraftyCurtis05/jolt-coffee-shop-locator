import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import LinksView from '../views/LinksView.vue';
import ArticlesView from '../views/ArticlesView.vue';
import LocatorView from '../views/LocatorView.vue';
import ProfileView from '../views/ProfileView.vue';
import FavoritesView from '../views/FavoritesView.vue';
import AboutUsView from '../views/AboutUsView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      hideNavBar: true,
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      hideNavBar: true,
      requiresAuth: false
    }
  },
  {
    path: "/links",
    name: "links",
    component: LinksView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/articles",
    name: "articles",
    component: ArticlesView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/locator",
    name: "locator",
    component: LocatorView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/profile",
    name: "profile",
    component: ProfileView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/favorites",
    name: "favorites",
    component: FavoritesView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/aboutUs",
    name: "aboutUs",
    component: AboutUsView,
    meta: {
      requiresAuth: true
    }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;