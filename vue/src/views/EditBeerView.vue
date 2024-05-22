<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else class="edit-beer-screen">
    <div class="edit-beer-form">
      <h1>Update Beer</h1>
      <beer-form v-bind:beer="beer" />
    </div>
  </div>
</template>

<script>

import breweryService from '../services/BreweryService.js';
import BeerForm from '../components/BeerForm.vue';

export default {
  components: {
    BeerForm
  },
  data() {
    return {
      beer: {},
      isLoading: true
    }
  },
  methods: {
    getBeer(beerId) {
      breweryService.getBeer(beerId)
        .then(response => {
          this.beer = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          if (error.response) {
            if (error.response.status == 404) {
              this.$router.push({name: 'NotFoundView'});
            } else {
              this.$store.commit('SET_NOTIFICATION',
              `Error getting beer. Response received was "${error.response.statusText}".`);
            }
          } else if (error.request) {
            this.$store.commit('SET_NOTIFICATION', `Error getting beer. Server could not be reached.`);
          } else {
            this.$store.commit('SET_NOTIFICATION', `Error getting beer. Request could not be created.`);
          }
        })
    }
  },
  created() {
    this.getBeer(this.$route.params.beerId);
  }
};
</script>

<style scoped>

.edit-beer-screen {
  background-image: url('../assets/pint.png');
  min-height: 100vh
}
.edit-beer-form {
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