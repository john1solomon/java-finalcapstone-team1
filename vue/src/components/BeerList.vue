<template>
  <div class="beer-list">
    <h2 class="section-title">Beers</h2>
    
    <!-- Search input fields -->
    <div class="search-container">
      <input type="text" v-model="searchQuery" placeholder="Search by Type" class="search-input" />
      <input type="text" v-model="ratingQuery" placeholder="Search by Rating" class="search-input" />
      <input type="text" v-model="abvQuery" placeholder="Search by ABV" class="search-input" />
    </div>
    
    <div v-if="isLoading" class="loading-message">Loading...<img src='@/assets/beerloading.gif' /></div>
    <div v-else-if="filteredBeers.length === 0" class="no-results-message">No beers found.</div>
    <div v-else class="beer-cards">
      <div v-for="beer in filteredBeers" :key="beer.beerId" class="beer-card">
        <h3 class="beer-name">{{ beer.beerName }}</h3>
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
import BreweryService from '../services/BreweryService.js';

export default {
  data() {
    return {
      beers: [],
      isLoading: true,
      searchQuery: '',
      ratingQuery: '',
      abvQuery: ''
    };
  },
  computed: {
    filteredBeers() {
      return this.beers.filter(beer => {
        // Filter based on beerType, averageRating, and abv fields
        return (
          beer.beerType.toLowerCase().includes(this.searchQuery.toLowerCase()) &&
          (this.ratingQuery === '' || beer.averageRating.toString().includes(this.ratingQuery)) &&
          (this.abvQuery === '' || beer.abv.toString().includes(this.abvQuery))
        );
      });
    }
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

.search-input {
  width: 100%;
  border-radius: 8px;
  gap:20px;
  
  display:flex;
  flex-wrap:wrap;

}
.beer-list {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.section-title {
  font-size: 36px;
  margin-bottom: 20px;
  text-transform: uppercase;
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
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
  background-color: black;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color:#f9f9f9;
  text-transform: uppercase;
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
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

