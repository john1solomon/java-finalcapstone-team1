
import Vue from 'vue';
import VueCarousel from 'vue-carousel';

Vue.use(VueCarousel);

/*added Vue and Vue Carousel after running npm install vue-carousel*/
I added the hidden-link class to router and used css to hide the nav-bar for now

<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link class="hidden-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="hidden-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
.hidden-link {
  display: none;
}

</style>