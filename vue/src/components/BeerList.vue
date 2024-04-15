<template>
  <div class="beer-list">
    <h2 class="section-title">Beers</h2>
    <div v-if="isLoading" class="loading-message">Loading...<img src='@/assets/beerloading.gif' /></div>
    <div v-else-if="beers.length === 0" class="no-results-message">No beers found.</div>
    <div v-else class="beer-cards">
      <div v-for="beer in beers" :key="beer.beerId" class="beer-card">
        <h3 class="beer-name">{{ beer.beerName }}</h3>
        <!-- <p class="beer-description">Description: {{ beer.beerDescription || 'Not available' }}</p> -->
        <p class="beer-type">Type: {{ beer.beerType }}</p>
        <p class="abv">ABV: {{ beer.abv }}</p>
        <p class="num-ratings">Number of Ratings: {{ beer.numRatings }}</p>
        <p class="average-rating">Average Rating: {{ beer.averageRating }}</p>
        <!-- <p class="last-active">Last Active: {{ beer.lastActive }}</p> -->
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
      BreweryService.getBeers()
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
.beer-list {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.section-title {
  font-size: 36px;
  margin-bottom: 20px;
}

.loading-message,
.no-results-message {
  font-size: 24px;
  text-align: center;
  margin-top: 20px;
}

.beer-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.beer-card {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
