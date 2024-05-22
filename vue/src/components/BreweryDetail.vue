<template>
  <div class="brewery-details">
    <div class="brewery-card">
      <img v="brewery.breweryId" v-bind:src="getLogo(brewery.logoFilename)" class="card-image"/>
      <h2 class="brewery-name"> <a :href="brewery.breweryURL" target="_blank" rel="noopener noreferrer" class="links">
        {{ brewery.breweryName }}</a>
      </h2>
      <a :href="brewery.ocbaInfoURL" target="_blank" rel="noopener noreferrer" class="links">
        Hours and Info
      </a>
      <p class="brewery-location">
        Address: {{ brewery.streetAddress }} {{ brewery.city }}, {{ brewery.stateCode }}, {{ brewery.postalCode }}</p>
      <a :href="brewery.mapURL" target="_blank" rel="noopener noreferrer" class="links">
      Directions
      </a>
      <p class="contact-info">Contact: {{ brewery.contactInformation }}</p>
      <a :href="brewery.menuURL" target="_blank" rel="noopener noreferrer" class="links">
        Menu
      </a>

      <div class="brewery-buttons" v-if="brewerMatch" >
        <button id="updateBrewery" v-on:click="$router.push({ name: 'edit-brewery', params: {breweryId: brewery.breweryId} })">Update Brewery</button>
        <button id="addBeer" v-on:click="$router.push({ name: 'add-beer', params: {breweryId: brewery.breweryId} })">Add Beer</button>
      </div>

    </div>

    <!-- Beers on tap card -->
    <h2 class="beers-header">Beers on Tap</h2>
    <div v-if="beers.length > 0" class="beers-list">
      <beer-card v-for="beer in beers" :brewerUsername="brewery.brewerUsername" :beer="beer" :key="beer.beerId" :fromPage="fromPage"/>
    </div>
    <div v-else class="no-beers-message">See Website for Current Selection</div>
  </div>
</template>

<script>
import breweryService from '../services/BreweryService.js';
import BeerCard from '../components/BeerCard.vue';

export default {
  data() {
    return {
      brewery: {},
      beers: [],
      fromPage: 'BreweryDetail'
    };
  },
  computed: {
    brewerMatch() {
      return this.$store.getters.getBrewer === this.brewery.brewerUsername;
    }
  },
  methods: {
    fetchBreweryDetails() {
      const breweryId = this.$route.params.breweryId;

      // Fetch brewery details
      breweryService.getBrewery(breweryId)
        .then(response => {
          this.brewery = response.data;
        })
        .catch(error => {
          console.error('Error fetching brewery details:', error);
        });

      // Fetch beers on tap for the brewery
      breweryService.getBreweryBeers(breweryId)
        .then(response => {
          this.beers = response.data;
        })
        .catch(error => {
          console.error('Error fetching beers on tap:', error);
        });
    },
    getLogo(filename) {
      return new URL(`../assets/${filename}`, import.meta.url).href;
    }
  },
  created() {
    this.fetchBreweryDetails();
  }
};
</script>

<style scoped>
i {
  color: red;
}

.brewery-details {
  display:block;
  gap:20px;
  justify-items: center;
  text-align: center;
  margin-top: 20px;
}

.brewery-card {
  display: flex;
  width: 500px;
  background-color: #333;
  border-radius: 5px;
  color: #fff;
  flex-direction: column;
  border: 1px solid #ddd;
  font-family: Arial, Helvetica, sans-serif;
  text-decoration: none;
  text-transform: uppercase;
  word-wrap: break-word;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  padding: 10px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 1rem;
}

.add-beer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 1rem;
}

.brewery-buttons button {
  width: 170px;
}

.brewery-buttons #updateBrewery {
  margin-right: 20px;
}

.add-beer #beerName {
  width: 500px;
}

.add-beer #beerType {
  width: 500px;
}

.add-beer #abv {
  width: 135px;
}

.add-beer .form-buttons {
  margin-top: 1rem;
}

.beer-buttons #updateBeer {
  margin-right: 20px;
}

.add-beer input {
  width: 100px;
}

.add-beer input[type=submit] {
  margin-right: 20px;
}

.beers-list {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  padding:20px;
  gap:20px;
  flex-direction: row;
}

.beers-header {
  font-size: 30px;
  font-weight: bolder;
  text-transform: uppercase;
  font-family: Arial, Helvetica, sans-serif;
}

/* Styles for the no beers message */
.no-beers-message {
  margin-top: 20px;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  font-size:large;
  text-shadow: 0 0 10px #ffcc00 ;
  animation:glow 1s ease infinite alternate;
  text-transform: uppercase;
}
@keyframes glow {
  from {
    text-shadow:0 0 10px#ffcc00 ;
  }
  to {
    text-shadow:0 0 30px #ffcc00 ;
  }
}

/* .card-image {
  height:200px;
} */

#checkbox-1 {
  justify-content: left;
}

.links {
  padding: 15px;
  color: #fff;
  font-family: Arial, Helvetica, sans-serif;
  text-decoration: none;
  text-transform: uppercase;
}

.links:hover {
  color:#ffcc00 !important;
}
</style>