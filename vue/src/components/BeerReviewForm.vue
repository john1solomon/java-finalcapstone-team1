<template>
  <form class="review-form" @submit.prevent="submitForm">

    <h1>Beer Review</h1>

    <h2> {{ brewery.breweryName }} </h2>

    <h3> {{ beer.beerName }} </h3>

    <label for="reviewText">Review:</label>
    <textarea id="reviewText" name="reviewText"  rows="6" cols="70" v-model="editBeerReview.review" />

    <div class="beerRating">
      <label for="beerRating">Rating:</label>
      <!-- NOTE: Use .number modifier here so editBeerReview.rating is a number, not a string -->
      <select id="beerRating" v-model.number="editBeerReview.beerRating">
        <option value="1">1 Star</option>
        <option value="2">2 Stars</option>
        <option value="3">3 Stars</option>
        <option value="4">4 Stars</option>
        <option value="5">5 Stars</option>
      </select>
    </div>

    <p class="favorite">Favorite? <input v-bind:id="'favorite_' + editBeerReview.userId + '_' + editBeerReview.beerId" type="checkbox" v-model="editBeerReview.favorite"></p>

    <div class="actions">
      <button class="btn-submit" type="submit">Save</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>

  </form>
</template>

<script>

import beerLoverService from '../services/BeerLoverService.js';

export default {
  props: {
    beerReview: {
      type: Object,
      required: true
    },
    beer: {
      type: Object,
      required: true
    },
    brewery: {
      type: Object,
      required: true
    },
    newReview: Boolean,
    fromPage: String
  },
  data() {
    return {
      editBeerReview: {
        userId: this.beerReview.userId,
        beerId: this.beerReview.beerId,
        beerRating: this.beerReview.beerRating,
        review: this.beerReview.review,
        favorite: this.beerReview.favorite
      }
    };
  },
  methods: {
    submitForm() {
      console.log('submitForm');
      console.log(this.newReview);
      console.log(this.fromPage);
      console.log(this.editBeerReview);
      if (!this.validateForm()) {
        return;
      }
      if (this.newReview) {
        beerLoverService
          .createBeerReview(this.editBeerReview)
          .then(response => {
            if (response.status === 201) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: ` Review for User ${this.editBeerReview.userId} and Beer ${this.editBeerReview.beerId} was created.`,
                  type: 'success'
                }
              );
              if (this.fromPage === 'BeerList') {
                this.$router.push({ name: 'beer-list'});
              } else if (this.fromPage === 'BreweryDetails') {
                this.$router.push({ name: 'brewery-details', params: { breweryId: this.brewery.breweryId } });
              } else {
                this.$router.back();
              }
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'creating');
          });
      } else {
        beerLoverService
          .updateBeerReview(this.editBeerReview)
          .then(response => {
            if (response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: ` Review for User ${this.editBeerReview.userId} and Beer ${this.editBeerReview.beerId} was updated.`,
                  type: 'success'
                }
              );
              if (this.fromPage === 'BeerList') {
                this.$router.push({ name: 'beer-list'});
              } else if (this.fromPage === 'BreweryDetails') {
                this.$router.push({ name: 'brewery-details', params: { breweryId: this.brewery.breweryId } });
              } else {
                this.$router.back();
              }
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'updating');
          });
      }
    },
    cancelForm() {
      this.$router.back();
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.$store.commit('SET_NOTIFICATION',
          "Error " + verb + " beer review. Response received was '" + error.response.statusText + "'.");
      } else if (error.request) {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " beer review. Server could not be reached.");
      } else {
        this.$store.commit('SET_NOTIFICATION', "Error " + verb + " beer review. Request could not be created.");
      }
    },
    validateForm() {
      let msg = '';
      if (this.editBeerReview.review.length === 0) {
        msg += 'The review must not be blank. ';
      }
      if (this.editBeerReview.beerRating === 0) {
        msg += 'The beer must have a rating.';
      }
      if (msg.length > 0) {
        console.log('Validation failed: ' + msg);
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
.review-form {
  /* margin-top: 20px; */
  width: 100%;
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  /* gap: 10px; */
}

.review-form label {
  margin-top: 10px;
}

.beerRating label {
  margin-top: 20px;
  margin-right: 10px;
}

.favorite {
  margin-top: 10px;
  margin-bottom: 10px;
}

.favorite input{
  margin-left: 10px;
}

.actions {
  margin-top: 10px;
}

.btn-submit {
  margin-right: 20px;
  width: 100px;
}

.btn-cancel {
  width: 100px;
}

h2 {
  margin-top: 20px;
  margin-bottom: 30px;
  text-align:center;
}

h3 {
  text-align:center;
}

</style>