<template>
  <div class="brewery-list">
    <h2 class="section-title">Breweries</h2>
    <div v-if="isLoading" class="loading-message">Loading...</div>
    <div v-else-if="breweries.length === 0" class="no-results-message">No breweries found.</div>
    <div v-else class="brewery-cards">
      <div v-for="brewery in breweries" :key="brewery.breweryId" class="brewery-card">
        <img v="brewery.breweryId" v-bind:src="getLogo(brewery.logoFilename)"/>
        <!-- Any of these could be removed, just testing out -->
        <h3 class="brewery-name">{{ brewery.breweryName }}</h3>
        <p class="brewer-name">Brewer: {{ brewery.brewerName }}</p>
        <p class="contact-info">Phone: {{ brewery.contactInformation }}</p>
        <p class="location">Address: {{ brewery.city }}, {{ brewery.stateCode }}, {{ brewery.postalCode }}</p>
        <router-link :to="'/breweries/' + brewery.breweryId" class="see-taps-link">See What's On Tap</router-link>
      </div>
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
    }
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
  font-size: 1.5rem;
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
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.brewery-name {
  font-size: 1.2rem;
  margin-bottom: 5px;
}

.brewer-name,
.contact-info,
.location {
  font-size: 1rem;
  margin-bottom: 5px;
}
</style>
