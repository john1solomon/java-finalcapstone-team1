<template>
  <div class="login-header">
    <h1>Login</h1>
  </div>

  <div id="login" class="login">
    <div class="beer-clipart">
      <img src="@/assets/beer-glasses1.jpg" class="clip-art">
    </div>
    <form v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.login{
  display: flex;
  text-align: center;
  justify-content: center;
  padding-top: 10%;
  font-size: x-large;
  margin-bottom: 100px;
  margin-right: 10%;
  ;
}
.login-header {
  display: flex;
  height: 200px;
  background-color: black;
  margin-bottom: 1rem;
  color: whitesmoke;
  justify-content: center;
  text-align: center;
  
}
.beer-clipart {
  display: flex;
  max-width: 75%;
  max-height: 20%;
  padding-right: 5%;
  margin-right: 10%;
}
.login-header > h1 {
  margin-top: 50px;
  font-size: 75px;
}
</style>