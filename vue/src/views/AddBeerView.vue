<template>
  <div class="add-beer-screen">
    <div class="add-beer-form">
      <h1>Add Beer</h1>
      <beer-form v-bind:beer="beer" />
    </div>
  </div>
</template>

<script>

import BeerForm from '../components/BeerForm.vue';

export default {
  components: {
    BeerForm
  },
  data() {
    return {
      beer: {
        beerId: 0,
        breweryId: Number(this.$route.params.breweryId),
        beerName: '',
        beerDescription: null,
        beerType: '',
        abv: '',
        numRatings: 0,
        averageRating: null,
        bayesianRating: null,
        lastActive: new Date().toISOString().split('T')[0]
      }
    }
  }
};
</script>

<style scoped>
.add-beer-screen {
  background-image: url('../assets/pint.png');
  min-height: 100vh
}

.add-beer-form {
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