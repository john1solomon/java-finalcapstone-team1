<template>
  <div class="brewery-list">
    <h2 class="section-title">Breweries</h2>
    <div v-if="isLoading" class="loading-message">Loading...</div>
    <div v-else-if="breweries.length === 0" class="no-results-message">No breweries found.</div>
    <div v-else class="brewery-cards">
      <router-link v-for="brewery in breweries" :key="brewery.breweryId" :to="'/breweries/' + brewery.breweryId" class="brewery-card">
        <img v="brewery.breweryId" v-bind:src="getLogo(brewery.logoFilename)" class="card-image"/>
        <!-- Any of these could be removed, just testing out -->
        <h3 class="brewery-name">{{ brewery.breweryName }}</h3>
        <!-- <p class="brewer-name">Brewer: {{ brewery.brewerName }}</p>
        <p class="contact-info">Phone: {{ brewery.contactInformation }}</p>
        <p class="location">Address: {{ brewery.city }}, {{ brewery.stateCode }}, {{ brewery.postalCode }}</p> -->
       </router-link>
     
    </div>
  </div>
</template>

<script>
import BreweryService from '../services/BreweryService.js';

export default {
  data() {
    return {
      breweries: [],
      isLoading: true
    };
  },
  methods: {
    fetchBreweries() {
      BreweryService.listBreweries()
        .then(response => {
          this.breweries = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          console.error('Error fetching breweries:', error);
          this.isLoading = false;
        });
    },

    getLogo(filename) {
      return new URL(`../assets/${filename}`, import.meta.url).href;
    },
  },
  created() {
    this.fetchBreweries();
  }
};
</script>

<style scoped>
.brewery-list {
  margin-top: 20px;
  text-align: center;
}

.section-title {
  font-size: 2.5rem;
  text-transform: uppercase;
  font-family: Arial, Helvetica, sans-serif;
}

.loading-message {
  margin-top: 20px;
}

.no-results-message {
  margin-top: 20px;
}

.brewery-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.brewery-card {
  margin: 10px;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 300px;
  background-color:black;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color: #f9f9f9;
  
  font-family: Arial, Helvetica, sans-serif;
  text-decoration: none;
  text-transform: uppercase;
    
  transition: color 0.3s ease;
}

.brewery-card:hover {
    color:#ffcc00 !important;

}

.brewery-name {
  font-size: 1.2rem;
  margin-bottom: 5px;
}

.card-image {
  height:200px;
}
.location {
  font-size: 1rem;
  margin-bottom: 5px;
}
</style>
