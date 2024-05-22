<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else class="edit-brewery-screen">
    <div class="edit-brewery-form">
      <h1>Update Brewery</h1>
      <brewery-form v-bind:brewery="brewery" />
    </div>
  </div>
</template>

<script>

import breweryService from '../services/BreweryService.js';
import BreweryForm from '../components/BreweryForm.vue';

export default {
  components: {
    BreweryForm
  },
  data() {
    return {
      brewery: {},
      isLoading: true
    }
  },
  methods: {
    getBrewery(breweryId) {
      breweryService.getBrewery(breweryId)
        .then(response => {
          this.brewery = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response) {
            if (error.response.status == 404) {
              this.$router.push({name: 'NotFoundView'});
            } else {
              this.$store.commit('SET_NOTIFICATION',
              `Error getting brewery. Response received was "${error.response.statusText}".`);
            }
          } else if (error.request) {
            this.$store.commit('SET_NOTIFICATION', `Error getting brewery. Server could not be reached.`);
          } else {
            this.$store.commit('SET_NOTIFICATION', `Error getting brewery. Request could not be created.`);
          }
        })
    }
  },
  created() {
    this.getBrewery(this.$route.params.breweryId);
  }
};
</script>

<style scoped>
.edit-brewery-screen {
  background-image: url('../assets/backgroundtest.jpg');
  min-height: 100vh
}
.edit-brewery-form {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

h1 {
  text-align:center;
}
</style>