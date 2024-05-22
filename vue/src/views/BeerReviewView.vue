<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else class="beer-review-screen">
    <div class="beer-review-form">
      <beer-review-form :beer="beer" :brewery="brewery" :beerReview="beerReview" :newReview="newReview" :fromPage="fromPage" />
    </div>
  </div>
</template>

<script>

import breweryService from '../services/BreweryService.js';
import beerLoverService from '../services/BeerLoverService.js';
import BeerReviewForm from '../components/BeerReviewForm.vue';

export default {
  components: {
    BeerReviewForm
  },
  data() {
    return {
      beer: {},
      brewery: {},
      beerReview: {},
      isLoading: true,
      newReview: false,
      fromPage: ''
    }
  },
  methods: {
    getBeer(beerId) {
      breweryService.getBeer(beerId)
        .then(response => {
          this.beer = response.data;
        })
        .catch(error => {
          console.log(error);
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
        });
    },
    getBrewery(breweryId) {
      breweryService.getBrewery(breweryId)
        .then(response => {
          this.brewery = response.data;
        })
        .catch(error => {
          console.log(error);
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
        });
    },
    getBeerReview(userId, beerId) {
      // Get the beer review for the user and beer (if it exists)
      beerLoverService.getBeerReviewForUserAndBeer(userId, beerId)
       .then(response => {
         // If there is no review for the user and beer, then the response will be 204 (No Content)
         // and we can just set the beerReview object to an empty object.
         console.log("respone.status: " + response.status);
         if (response.status == 204) {
           this.beerReview.userId = userId;
           this.beerReview.beerId = beerId;
           this.beerReview.beerRating = 0;
           this.beerReview.review = '';
           this.beerReview.favorite = false;
           this.newReview = true;
         } else {
           this.beerReview = response.data;
         }
         this.isLoading = false;
         console.log(this.beerReview);
       })
       .catch(error => {
         console.log(error);
         if (error.response) {
           this.$store.commit('SET_NOTIFICATION',
           `Error getting beer review. Response received was "${error.response.statusText}".`);
         } else if (error.request) {
           this.$store.commit('SET_NOTIFICATION', `Error getting beer review. Server could not be reached.`);
         } else {
           this.$store.commit('SET_NOTIFICATION', `Error getting beer review. Request could not be created.`);
         }
       });
    }
  },
  created() {
    console.log("Beer Id: " + this.$route.params.beerId);
    this.getBeer(this.$route.params.beerId);
    console.log("Brewery Id: " + this.$route.params.breweryId);
    this.getBrewery(this.$route.params.breweryId);
    this.fromPage = this.$route.params.fromPage;
  },
  mounted() {
    this.getBeerReview(this.$store.getters.getUserId, this.$route.params.beerId);
    console.log(this.beerReview);
  }
};
</script>

<style scoped>

.beer-review-screen {
  background-image: url('../assets/beer-toast.png');
  min-height: 100vh;
}

.beer-review-form {
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