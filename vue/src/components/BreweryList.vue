<!-- This is where the API will go, array just filled in for Demo -->
<template>
    <div class="brewery-list ">
      <h2 class="section-title">On Tap</h2>
      <div class="brewery-cards">
        <div v-for="brewery in breweries" :key="brewery.id" class="brewery-card">
          <img :src="brewery.imageUrl" :alt="brewery.name" class="brewery-image">
          <div class="brewery-info">
            <h3 class="brewery-name">{{ brewery.name }}</h3>
            <p class="brewery-location">{{ brewery.location }}</p>
            <router-link :to="'/breweries/' + brewery.id" class="see-details-link">See Details</router-link>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import nb from '@/assets/nb.jpg'
  import mh from '@/assets/masthead.jpg'
  import tr from '@/assets/terr.jpg'
  
  export default {
    data() {
      return {
        breweries: [
          { id: 1, name: 'Noble Beast Brewing', location: '1470 Lakeside Ave E, Cleveland OH 44114', imageUrl: nb },
          { id: 2, name: 'Masthead Brewing Co.', location: '1261 Superior Ave, Cleveland, OH 44114', imageUrl: mh },
          { id: 3, name: 'Terrestial Brewing Company', location: '7524 Father Frascati, Cleveland, OH 44102', imageUrl: tr },
          
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  .brewery-list {
    margin-top: 50px;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    text-transform: uppercase;
  
   
  }

  .div {
    border:none;
  }
  
 
  .section-title {
    text-align: center;
    font-size: 2rem;
  }
  
  .brewery-cards {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    margin-top: 30px;
  }
  
  .brewery-card {
    width: calc(33.33% - 20px);
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    overflow: hidden;
    
  }
  
  .brewery-image {
    width: 100%;
    height: 200px;
    object-fit:scale-down;
  }
  
  .brewery-info {
    padding: 15px;
    text-align: center;
    
  }
  
  .brewery-name {
    font-size: 1.2rem;
    margin-bottom: 10px;
    text-align: center;
    
  }
  
  .brewery-location {
    font-size: 1rem;
    color: #888;
    text-align: center;
  }
  
  .see-details-link {
    display: block;
    margin-top: 15px;
    text-align: center;
    color: #333;
    text-decoration: none;
    font-weight: bold;
  }

  .see-details-link:hover {
    color:#ffcc00;

  }
  </style>

