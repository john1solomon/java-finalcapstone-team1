<template>
  <div class="beer-list">
    <h2 class="section-title">Beers</h2>
    <!-- Search input fields -->
    <div class="search-container">
      <input type="text" v-model="nameQuery" placeholder="Search by Name" class="search-input" />
      <input type="text" v-model="searchQuery" placeholder="Search by Type" class="search-input" />
      <input type="text" v-model="ratingQuery" placeholder="Search by Rating" class="search-input" />
      <input type="text" v-model="abvQuery" placeholder="Search by ABV" class="search-input" />
    </div>
    <div v-if="isLoading" class="loading-message">Loading...<img src='@/assets/beerloading.gif' /></div>
    <div v-else-if="filteredBeers.length === 0" class="no-results-message">No beers found.</div>
    <div v-else class="beer-cards">
      <beer-card v-for="beer in filteredBeers" :beer="beer" :key="beer.beerId" :fromPage="fromPage"/>
    </div>
  </div>
</template>

<script>
import BreweryService from '../services/BreweryService.js';
import BeerCard from '../components/BeerCard.vue';

export default {
  data() {
    return {
      status: false,
      beers: [],
      isLoading: true,
      nameQuery:'',
      searchQuery: '',
      ratingQuery: '',
      abvQuery: '',
      fromPage: 'BeerList'
    };
  },
  computed: {
    filteredBeers() {
      return this.beers.filter(beer => {
        // Filter based on beerType, beerName, averageRating, and abv fields
        return (
          beer.beerType.toLowerCase().includes(this.searchQuery.toLowerCase()) &&
          beer.beerName.toLowerCase().includes(this.nameQuery.toLowerCase()) &&
          (this.ratingQuery === '' || beer.averageRating.toString().includes(this.ratingQuery)) &&
          (this.abvQuery === '' || beer.abv.toString().includes(this.abvQuery))
        );
      });
    }
  },
  components: {
    BeerCard
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
i {
  color: red;
}
.search-input {
  width: 100%;
  border-radius: 8px;
  gap:20px;
  display:flex;
  flex-wrap:wrap;
}

.beer-list {
  padding: 20px;
}

.search-container {
  display: flex;
  flex-direction: column;
  width: 500px;
  gap:5px;
  margin-left: auto;
  margin-right: auto;
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
  margin-top: 20px;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}
</style>