<template>
    <div class="beer-list">
      <h2 class="section-title">Beers</h2>
      <div v-if="isLoading" class="loading-message">Loading...</div>
      <div v-else-if="beers.length === 0" class="no-results-message">No beers found.</div>
      <div v-else class="beer-cards">
        <div v-for="beer in beers" :key="beer.beerId" class="beer-card">
          <h3 class="beer-name">{{ beer.beerName }}</h3>
          <p class="beer-description">Description: {{ beer.beerDescription || 'Not available' }}</p>
          <p class="beer-type">Type: {{ beer.beerType }}</p>
          <p class="abv">ABV: {{ beer.abv }}</p>
          <p class="num-ratings">Number of Ratings: {{ beer.numRatings }}</p>
          <p class="average-rating">Average Rating: {{ beer.averageRating }}</p>
          <p class="last-active">Last Active: {{ beer.lastActive }}</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import BreweryService from '@/services/BreweryService.js';
  
  export default {
    data() {
      return {
        beers: [],
        isLoading: true
      };
    },
    methods: {
      fetchBeers() {
        BreweryService.getBeer()
          .then(response => {
            this.beers = response.data;
            this.isLoading = false;
          })
          .catch(error => {
            console.error('Error fetching beers:', error);
            this.isLoading = false;
          });
      }
    },
    created() {
      this.fetchBeers();
    }
  };
  </script>
  
  <style scoped>
  /* Style this too, man */
  </style>