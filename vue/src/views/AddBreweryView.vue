<template>
  <div class="add-brewery-screen">
    <div class="add-brewery-form">
      <h1>Add Brewery</h1>
      <brewery-form v-bind:brewery="brewery" />
    </div>
  </div>
</template>

<script>

import BreweryForm from '../components/BreweryForm.vue';

export default {
  components: {
    BreweryForm
  },
  data() {
    return {
      brewery: {
        breweryId: 0,
        breweryName: '',
        brewerUsername: '',
        contactInformation: null,
        streetAddress: null,
        city: null,
        stateCode: null,
        postalCode: null,
        logoFilename: null,
        breweryURL: null,
        menuURL: null,
        ocbaInfoURL: null,
        mapURL: null
      }
    }
  }
};
</script>
<style scoped>
.add-brewery-screen {
  background-image: url('../assets/backgroundtest.jpg');
  min-height: 100vh
}

.add-brewery-form {
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