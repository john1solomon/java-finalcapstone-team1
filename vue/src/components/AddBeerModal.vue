<template>
  <div>
    <button @click="showModal = true">Add Beer</button>
    
    <!-- Modal -->
    <div v-if="showModal" class="modal">
      <h2 class="header">Create a New Beer</h2>
      <div class="modal-content">
       
        <span @click="showModal = false" class="close">&times;</span>

        <!-- using @submit.prevent binds the form to the submit event -->
       
        <form @submit.prevent="addBeer">
          <label for="beerName">Beer Name:</label>
          <input id="beerName" placeholder="Beer Name" v-model="beer.beerName" type="text" required>
          
          <label for="beerType">Beer Type:</label>
          <input id="beerType" placeholder="Beer Type" v-model="beer.beerType" type="text" required>
          
          <label for="abv">ABV:</label>
          <input id="abv" placeholder="ABV (ex:7.5)" v-model="beer.abv" type="number" step="0.1" required>

          <!-- <label for="lastActive">Last Active:</label>
          <input id="lastActive" placeholder="YYYY-MM-DD" v-model="beer.lastActive" type="text" required> -->
          
          <button type="submit">Create Beer</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BreweryService from '../services/BreweryService';


export default {
  data() {
    return {
      showModal: false,
      beer: {
        beerId:'',
        breweryId: null,
        beerName: '',
        beerDescription:null,
        beerType: '',
        abv: '',
        numRatings: '',
        averageRating: '',
        bayesianRating: '',
        lastActive: new Date().toISOString().split('T')[0],
        
      }
    };
  },
  mounted() {
    // Trying to access route parameter and assign it to breweryId, confirmed works.
    this.beer.breweryId = this.$route.params.breweryId;
  },
  methods: {
    addBeer() {
      BreweryService.createBeer(this.beer)
      // THIS STUFF IS NONSENSE RIGHT NOW
      .then(response => {
            if (response.status === 200) {
              this.$store.commit('SET_NOTIFICATION', {
                message: 'New beer was added successfully.',
                type: 'success'
              });
              // Redirect to brewery details view or appropriate route
            } else {
              this.handleErrorResponse(response, 'adding');
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, 'adding');
          });
      }
    }
    };
    // THIS STUFF IS NONSENSE RIGHT NOW
</script>

<style scoped>
/* MOSTLY STOLEN STYLEZ */
.modal {
  display: block; /* Ensure the modal is visible */
  position: fixed; /* Position the modal relative to the viewport */
  z-index: 1; /* Make sure the modal appears on top of other content */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scrolling if the content exceeds the viewport */
  /* background-color: rgba(0,0,0,10); Black background with opacity */
  background: url('../assets/prawnmoney.png')
}

.modal-content {
  background-color: #fefefe; /* White background */
  margin: 10% auto; /* Center the modal vertically and horizontally */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Set the width */
  
}

/* Close button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.header{
  animation: rainbow 5s infinite;
}

@keyframes rainbow {
  0% { color: red; }
  10% { color: orange; }
  20% { color: yellow; }
  30% { color: green; }
  40% { color: blue; }
  50% { color: indigo; }
  60% { color: violet; }
  70% { color: red; }
  80% { color: orange; }
  90% { color: yellow; }
  100% { color: green; }
}
</style>