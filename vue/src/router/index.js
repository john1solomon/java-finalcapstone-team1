import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import BreweryListView from '../views/BreweryListView.vue';
import BreweryDetailView from '../views/BreweryDetailView.vue';
import BeerListView from '../views/BeerListView.vue';
import AddBreweryView from '../views/AddBreweryView.vue';
import EditBreweryView from '../views/EditBreweryView.vue';
import AddBeerView from '../views/AddBeerView.vue'
import EditBeerView from '../views/EditBeerView.vue';
import BeerReviewView from '../views/BeerReviewView.vue';

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
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path:'/beers',
    name:'beer-list',
    component:BeerListView
  },
  {
    path:'/breweries',
    name: 'brewery-list',
    component: BreweryListView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path:'/breweries/create',
    name:'add-brewery',
    component:AddBreweryView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/breweries/:breweryId/update',
    name:'edit-brewery',
    component:EditBreweryView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/breweries/:breweryId',
    name:'brewery-details',
    component: BreweryDetailView
  },
  {
    path:'/breweries/:breweryId/beer/create',
    name:'add-beer',
    component: AddBeerView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/breweries/:breweryId/beer/:beerId/update',
    name:'edit-beer',
    component: EditBeerView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/review/:breweryId/:beerId',
    name:'beer-review',
    component: BeerReviewView,
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
    return {name: 'login'};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;