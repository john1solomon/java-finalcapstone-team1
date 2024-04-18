<template>
  <!-- <BButton @click="modal = !modal"> Add Review </BButton>
  <BModal v-model="modal" title="Add Review"> Foobar? <BFormSelect v-model="ex1Selected" :options="ex1Options" /> Dardir
  <BFormSelect v-model="ex1Selected" :options="ex1Options" size="sm" class="mt-3" /></BModal> -->

 
  
  <div class="brewery-details">
    <!-- Brewery details card -->
    <div class="card brewery-card">
      <img v="brewery.breweryId" v-bind:src="getLogo(brewery.logoFilename)" class="card-image"/>
      <h2 class="brewery-name"> <a :href="brewery.breweryURL" target="_blank" rel="noopener noreferrer" class="links">
     {{ brewery.breweryName }}</a></h2>
      <a :href="brewery.ocbaInfoURL" target="_blank" rel="noopener noreferrer"  class="links">
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
      <div>
        <i class="fa-heart" :class="[status ? 'fa-solid' : 'fa-regular']" @click="toggleStatus"></i>
      </div>
    </div>

    <!-- Beers on tap card -->
    <h3 class="beers-header">Beers on Tap</h3>
    <div v-if="beers.length > 0" class="beers-card">
     <div v-for="beer in beers" :key="beer.beerId" class="beer-card">
        <h4 class="beer-name">{{ beer.beerName }}</h4>
        <!-- <p class="beer-description">Description: {{ beer.beerDescription || 'Not available' }}</p> -->
        <p class="beer-type">Type: {{ beer.beerType }}</p>
        <p class="abv">ABV: {{ beer.abv }}</p>
        <p class="num-ratings">Number of Ratings: {{ beer.numRatings }}</p>
        <p class="average-rating">Average Rating: {{ beer.averageRating }}</p>
        <!-- <p class="last-active">Last Active: {{ beer.lastActive }}</p> -->
        <BButton @click="modal = !modal"> Add Review </BButton>
        <BModal v-model="modal" title="Add Review"> Foobar? <BFormSelect v-model="ex1Selected" :options="ex1Options" /> Dardir
        <BFormSelect v-model="ex1Selected" :options="ex1Options" size="sm" class="mt-3" /></BModal>
      </div>
      
    </div>
    <div v-else class="no-beers-message">See Website for Current Selection</div>
  </div>
</template>

  
  <script>
  import BreweryService from '@/services/BreweryService.js';
  
  export default {
    data() {
      return {
        status: false,
        brewery: {},
        beers: [],
        modal: false,
        ex1Options: [
          {value: null, text: 'Please select an option'},
          {value: 'a', text: 'This is First option'},
          {value: 'b', text: 'Selected Option'},
          {value: {C: '3PO'}, text: 'This is an option with object value'},
          {value: 'd', text: 'This one is disabled', disabled: true},
        ]
      };
    },
    methods: {
      toggleStatus() {
        this.status = !this.status;
      },
      fetchBreweryDetails() {
        const breweryId = this.$route.params.breweryId;
  
        // Fetch brewery details
        BreweryService.getBrewery(breweryId)
          .then(response => {
            this.brewery = response.data;
            console.log('Brewery details:', this.brewery);
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
      },
      getLogo(filename) {
      return new URL(`../assets/${filename}`, import.meta.url).href;
    },
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
  display:grid;
  
  gap:20px;
  justify-items:center;
  text-align: center;
  margin-top: 20px;
}

.card {
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 15px;
  color: #fff;
    font-family: Arial, Helvetica, sans-serif;
    text-decoration: none;
    text-transform: uppercase;
  
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
}

.brewery-card {
  width:400px;
  background-color: #333;
  color: #fff;
    font-family: Arial, Helvetica, sans-serif;
    text-decoration: none;
    text-transform: uppercase;
    display:flex;
  align-items: center;
  flex-wrap:wrap;
  text-transform: uppercase;
    word-wrap: break-word;
}

.beers-card {
 
  background-color: #fff;
  display: flex;
  flex-wrap:wrap;
  justify-content: center;
  padding:20px;
  gap:20px;

}

.beers-header {
  font-size: 30px;
  font-weight: bolder;
  text-transform: uppercase;
  font-family: Arial, Helvetica, sans-serif;
 
}




.beer-card {
  display:flex;
  flex-direction:column;
  justify-content: space-between;
  background-color:#333;
  border-bottom: 1px solid #ddd;
  width:300px;
  padding: 15px;
  color: #fff;
    font-family: Arial, Helvetica, sans-serif;
    text-decoration: none;
    text-transform: uppercase;
    word-wrap: break-word;
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

.card-image {
 
  height:200px;
  
}
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