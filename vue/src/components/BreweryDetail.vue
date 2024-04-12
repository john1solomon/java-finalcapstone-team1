<template>
    <div class="brewery-details">
      <!-- Display brewery details -->
      <h2 class="brewery-name">{{ brewery.breweryName }}</h2>
      <p class="brewery-location">Location: {{ brewery.city }}, {{ brewery.stateCode }}, {{ brewery.postalCode }}</p>
      <p class="contact-info">Contact: {{ brewery.contactInformation }}</p>
  
      <!-- Display beers on tap -->
      <h3 class="beers-header">Beers on Tap</h3>
      <div class="beer-container" v-for="beer in beers" :key="beer.beerId">
        <h4 class="beer-name">{{ beer.beerName }}</h4>
        <p class="beer-description">Description: {{ beer.beerDescription || 'Not available' }}</p>
        <p class="beer-type">Type: {{ beer.beerType }}</p>
        <p class="abv">ABV: {{ beer.abv }}</p>
        <p class="num-ratings">Number of Ratings: {{ beer.numRatings }}</p>
        <p class="average-rating">Average Rating: {{ beer.averageRating }}</p>
        <p class="last-active">Last Active: {{ beer.lastActive }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import BreweryService from '@/services/BreweryService.js';
  
  export default {
    data() {
      return {
        brewery: {},
        beers: []
      };
    },
    methods: {
      fetchBreweryDetails() {
        const breweryId = this.$route.params.breweryId;
  
        // Fetch brewery details
        BreweryService.getBrewery(breweryId)
          .then(response => {
            this.brewery = response.data;
          })
          .catch(error => {
            console.error('Error fetching brewery details:', error);
          });
  
        // Fetch beers on tap for the brewery
        BreweryService.getBreweryBeers(breweryId)
          .then(response => {
            this.beers = response.data;
          })
          .catch(error => {
            console.error('Error fetching beers on tap:', error);
          });
      }
    },
    created() {
      this.fetchBreweryDetails();
    }
  };
  </script>
  
  <style scoped>
  /*style this, man*/
  </style>