import { createStore as _createStore } from 'vuex';
import axios from 'axios';
const NOTIFICATION_TIMEOUT = 3000;

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
      },
      SET_NOTIFICATION(state, notification) {
        // Clear the current notification if one exists
        if (state.notification) {
          this.commit('CLEAR_NOTIFICATION');
        }

        if (typeof notification === 'string') {
          // If only a string was sent, create a notification object with defaults
          notification = {
            message: notification,
            type: 'error',
            timeout: NOTIFICATION_TIMEOUT
          }
        } else {
          // Else add default values if needed
          notification.type = notification.type || 'error';
          notification.timeout = notification.timeout || NOTIFICATION_TIMEOUT;
        }

        // Set the notification in state
        state.notification = notification;

        // Clear the message after timeout (see https://developer.mozilla.org/en-US/docs/Web/API/setTimeout)
        notification.timer = window.setTimeout(() => {
          this.commit('CLEAR_NOTIFICATION');
        }, notification.timeout);
      },
      CLEAR_NOTIFICATION(state) {
        if (state.notification && state.notification.timer) {
          window.clearTimeout(state.notification.timer);
        }
        state.notification = null;
      },
    },

    // Adding this to try to tackle handling roles and permissions I will comment out below to know what garbage to remove if it doesn't work
    getters: {
      // Getter to retrieve the user's role (authorities)
      getUserRole: state => {
        return state.user ? state.user.authorities[0].name : null;
      },
      getUserId: state => {
        return state.user ? state.user.id : null;
      },
      getBrewer: state => {
        return state.user.username;
      },
      // Getters to check if the user has the a specific role
      isUserBrewer: state => {
        return state.user.authorities[0].name === 'ROLE_BREWER';
      },
      isUserAdmin: state => {
        return state.user.authorities[0].name === 'ROLE_ADMIN';
      },
      isUserUser: state => {
        return state.user.authorities[0].name === 'ROLE_USER';
      }
    }
    // End of code I added to 'store'
  });
  return store;
}