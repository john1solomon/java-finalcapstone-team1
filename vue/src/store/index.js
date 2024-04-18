import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {}
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
        // adding logger to log the user object
        console.log('User object', user);
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
    // Adding this to try to tackle handling roles and permissions I will comment out below to know what garbage to remove if it doesn't work
    getters: {
      // Getter to retrieve the user's role (authorities)
      getUserRole: state => {
        return state.user ? state.user.authorities[0].name : null;
      },


      // Getter to check if the user has the ROLE_BREWER role
      isUserBrewer: state => {
      return state.user.authorities[0].name === 'ROLE_BREWER';
      },
      isUserAdmin: state => {
        return state.user.authorities[0].name === 'ROLE_ADMIN';
        }
    }
    // End of code I added to 'store'
  });
  return store;
}
