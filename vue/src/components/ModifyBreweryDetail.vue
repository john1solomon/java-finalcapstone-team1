<template>
  <form >
    <div class="field">
      <label for="beerID">Beer ID</label>
      <input type="number" id="beerId" name="beerId" v-model="beer.beerId" />
    </div>
    <div class="field">
      <label for="breweryId">Brewery ID</label>
      <input type="number" id="breweryId" name="breweryId" v-model="beer.breweryId" />
    </div>
    <div class="field">
      <label for="beerName">Beer Name</label>
      <input type="text" id="beerName" name="beerName" v-model="beer.beerName" />
    </div>
   
    <div class="field">
      <label for="beerType">Type</label>
      <input type="text" id="beerType" name="beerType" v-model="beer.beerType" />
    </div>
    <div class="field">
      <label for="abv">ABV</label>
      <input type="number" id="abv" name="abv" v-model="beer.abv" />
    </div>

    

    <div class="actions">
      <button class="btn-submit" type="submit" v-on:click="createBeer">Save</button>
      <button class="btn-delete" type="button" v-if="beer.beerId" v-on:click="deleteBeer">Delete</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>
  </form>
</template>

<script>
import BreweryService from '../services/BreweryService';

export default {
  props: {
    beer: {
      type: Object,
      default: () => ({
        beerId: '',
        breweryId: '',
        beerName: '',
        beerDescription: '',
        beerType: '',
        abv: '',
        numRatings: '',
        averageRating: '',
        lastActive: null
      })
    },
  },
  methods: {
    submitForm() {
      if (!this.validateForm()) {
        return;
      }
      
      if (this.beer.beerId) {
        BreweryService.updateBeer(this.beer.beerId, this.beer)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit('SET_NOTIFICATION', {
                message: `Beer ${this.beer.beerName} was updated.`,
                type: 'success'
              });
              // Redirect to brewery details view or appropriate route
            } else {
              this.handleErrorResponse(response, 'updating');
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'updating');
          });
      } else {
        BreweryService.createBeer(this.beer)
          .then(response => {
            if (response.status === 201) {
              this.$store.commit('SET_NOTIFICATION', {
                message: 'New beer was added successfully.',
                type: 'success'
              });
              // Redirect to brewery details view or appropriate route
            } else {
              this.handleErrorResponse(response, 'adding');
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'adding');
          });
      }
    },
    cancelForm() {
      // Redirect to previous page or appropriate route
      this.$router.back();
    },
    deleteBeer() {
      if (confirm("Are you sure you want to delete this beer? This action cannot be undone.")) {
        BreweryService.deleteBeer(this.beer.beerId)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: `Beer ${this.beer.beerName} was successfully deleted.`,
                  type: 'success'
                }
              );
              // Redirect to brewery details view or appropriate route
            }
          })
          .catch(error => {
            if (error.response) {
              this.$store.commit('SET_NOTIFICATION',
                `Error deleting beer. Response received was "${error.response.statusText}".`);
            } else if (error.request) {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting beer. Server could not be reached.');
            } else {
              this.$store.commit('SET_NOTIFICATION', 'Error deleting beer. Request could not be created.');
            }
          });
      }
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        if (error.response.status === 404) {
          this.$router.push({ name: 'NotFoundView' });
        } else {
          this.$store.commit('SET_NOTIFICATION',
            `Error ${verb} beer. Response received was "${error.response.statusText}".`);
        }
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', `Error ${verb} beer. Server could not be reached.`);
      } else {
        this.$store.commit('SET_NOTIFICATION', `Error ${verb} beer. Request could not be created.`);
      }
    },
    validateForm() {
      let msg = '';

      if (this.beer.beerName.trim().length === 0) {
        msg += 'Beer name is required. ';
      }

      if (this.beer.abv === null || isNaN(this.beer.abv)) {
        msg += 'ABV must be a valid number. ';
      }

      if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    }
  },
};
</script>

<style>
.field {
  margin-bottom: 1rem;
}
</style>