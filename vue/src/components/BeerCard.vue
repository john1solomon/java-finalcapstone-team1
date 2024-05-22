<template>
  <div class="beer-card">
    <h3 class="beer-name">{{ beer.beerName }}</h3>
    <p class="beer-type">Type: {{ beer.beerType }}</p>
    <p class="abv">ABV: {{ beer.abv }}</p>
    <p class="num-ratings">Number of Ratings: {{ beer.numRatings }}</p>
    <p class="average-rating">Average Rating: {{ beer.averageRating }}</p>
    <!-- <p class="last-active">Last Active: {{ beer.lastActive }}</p> -->

    <!-- <div class="beer-buttons" v-if="brewerMatch" > -->
    <div class="beer-buttons" v-if="brewerMatch">
      <button id="updateBeer" @click="$router.push({ name: 'edit-beer', params: {beerId: beer.beerId} })">Update Beer</button>
      <button id="deleteBeer" @click="deleteBeer(beer.beerId)">Delete Beer</button>
    </div>

    <button id="reviewBeer" v-if="this.$store.getters.isUserUser" @click="$router.push({ name: 'beer-review', params: {beerId: beer.beerId, breweryId: beer.breweryId, fromPage: fromPage}})">Review Beer</button>

    <!-- <div> -->
      <!-- <i class="fa-heart"  v-if="this.$store.getters.isUserUser"></i> -->
      <!-- <i class="fa-heart" :class="[status ? 'fa-solid' : 'fa-regular']" @click="toggleStatus"></i> -->
    <!-- </div> -->
  </div>
</template>

<script>
import BreweryService from '../services/BreweryService.js';

export default {
  props: {
    beer: Object,
    brewerUsername: String,
    fromPage: String
  },
  computed: {
    brewerMatch() {
      return this.$store.getters.getBrewer === this.brewerUsername;
    }
  },
  methods: {
    deleteBeer(beerId) {
      if (confirm("Are you sure you want to delete this beer? This action cannot be undone.")) {
        BreweryService
          .deleteBeer(beerId)
          .then(() => {
            this.$router.push({ name: 'brewery-details', params: { breweryId: this.beer.breweryId } });
            location.reload();
          })
          .catch(error => {
            console.error('Error deleting beer: ', error);
          });
      }
    }
  }
};
</script>

<style scoped>
i {
  color: red;
}

.beer-card {
  background-color: black;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color:#f9f9f9;
  text-transform: uppercase;
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
  max-width: 350px;
}

.beer-name {
  font-size: 24px;
  margin-bottom: 10px;
}

.beer-description,
.beer-type,
.abv,
.num-ratings,
.average-rating,
.last-active {
  font-size: 16px;
  margin-bottom: 5px;
}
</style>