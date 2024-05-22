<template>
  <form class="beer-form" @submit.prevent="submitForm">

    <label for="beerName">Beer Name:</label>
    <input id="beerName" placeholder="Beer Name" v-model="editBeer.beerName" type="text" required>

    <label for="beerType">Beer Type:</label>
    <input id="beerType" placeholder="Beer Type" v-model="editBeer.beerType" type="text" required>

    <label for="abv">ABV:</label>
    <input id="abv" placeholder="ABV (ex:7.5)" v-model="editBeer.abv" type="number" step="0.1" required>

    <div class="actions">
      <button class="btn-submit" type="submit">Save</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>

  </form>
</template>

<script>

import breweryService from '../services/BreweryService.js';

export default {
  props: {
    beer: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      editBeer: {
        beerId: this.beer.beerId,
        breweryId: this.beer.breweryId,
        beerName: this.beer.beerName,
        beerDescription: this.beer.beerDescription,
        beerType: this.beer.beerType,
        abv: this.beer.abv,
        numRatings: this.beer.numRatings,
        averageRating: this.beer.averageRating,
        bayesianRating: this.beer.bayesianRating,
        lastActive: this.beer.lastActive
        // lastActive: new Date().toISOString().split('T')[0]
      }
    };
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }
      if (this.editBeer.beerId === 0) {
        // add
        breweryService
          .createBeer(this.editBeer)
          .then(response => {
            if (response.status === 201) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: 'A new beer was added.',
                  type: 'success'
                }
              );
              this.$router.push({ name: 'brewery-details', params: { breweryId: this.editBeer.breweryId } });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'creating');
          });
      } else {
        breweryService
          .updateBeer(this.editBeer)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: ` Beer ${this.editBeer.beerId} was updated.`,
                  type: 'success'
                }
              );
              this.$router.push({ name: 'brewery-details', params: { breweryId: this.editBeer.breweryId } });
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'updating');
          });
      }
    },
    cancelForm() {
      this.$router.push({ name: 'brewery-details', params: { breweryId: this.editBeer.breweryId } });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " beer. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " beer. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " beer. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';
      if (this.editBeer.beerName.length === 0) {
        msg += 'The beer must have a name. ';
      }
      if (this.editBeer.beerType.length === 0) {
        msg += 'The beer must have a type.';
      }
      if (this.editBeer.abv === 0) {
        msg += 'The beer must have an ABV value.';
      }
      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
.beer-form {
  /* margin-top: 20px; */
  width: 50%;
  display: grid;
  /* gap: 10px; */
}

.beer-form label {
  margin-top: 20px;
}

.beer-form #beerName {
  width: 500px;
}

.beer-form #beerType {
  width: 500px;
}

.beer-form #abv {
  width: 135px;
}

.actions {
  margin-top: 20px;
}

.btn-submit {
  margin-right: 20px;
  width: 100px;
}

.btn-cancel {
  width: 100px;
}

</style>