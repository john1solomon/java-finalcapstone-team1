<template>
    <div class="feature">
      <!-- Hero section -->
      <section class="hero">
        <div class="container">
          <h1 class="hero-title">Discover Local Breweries</h1>
          <p class="hero-subtitle">Explore the best breweries in Cleveland</p>
          
        </div>
      </section>
  
      
      <section class="carousel-section">
      <div class="brewery-carousel">
        <BCarousel id="breweryCarousel" controls indicators ride="carousel">
          
          <BCarouselSlide>
            <template #img>
              <img src="@/assets/nbbrewery.png"   width="100%"
               alt="Test 1"> </template>
            <h1>NOBLE BEAST BREWING</h1>
            <h2>1470 LAKESIDE AVE CLE OH 44114</h2>
            <h3>WING NIGHT EVERY WEDNESDAY</h3>
            
            
          </BCarouselSlide>

          <BCarouselSlide>
            <template #img>
              <img src="@/assets/glbrewery.jpg"  width="100%" alt="Test 2">
            </template>
            <h1>GREAT LAKES BREWERY</h1>
            <h2>2516 MARKET AVE CLE OH 44113</h2>
            <h3>DRAFT DEALS EVERY MONDAY</h3>
          </BCarouselSlide>

          <BCarouselSlide>
            <template #img>
              <img src="@/assets/isb.png"   width="100%" alt="Test 2">
            </template>
            <h1>IMMIGRANT SON BREWERY</h1>
            <h2>18120 SLOANE AVE CLE OH 44107</h2>
            <h3>TRIVIA EVERY TUESDAY AT 7:00PM</h3>
          </BCarouselSlide>
        </BCarousel>
          </div>

      </section>
  
      <!-- Upcoming Events Carousel -->
      <section class="carousel-section">
      <div class="events-carousel">
        <BCarousel id="EventsCarousel" controls indicators ride="carousel">
          
         

          <BCarouselSlide>
            <template #img>
              <img src="@/assets/5.jpg" width="100%" alt="Test 1">
            </template>
            <h1>TECH ELEVATOR COHORT 25 GRADUATION</h1>
            <h2>FRIDAY APRIL 19, 2024</h2>
            <h3>7100 EUCLID AVE #140 CLE OH 44103</h3>
            <p>smell ya later, nerds</p>
          </BCarouselSlide>
        
        <BCarouselSlide>
        <template #img>
              <img src="@/assets/2prawnevent.png"    width="100%"
               alt="Test 2">
            </template>
            <h1>2 PRAWN APPROACH ALL BEER POTLUCK</h1>
            <h2>SATURDAY APRIL 20, 2024</h2>
            <h3>7100 EUCLID AVE #140 CLE OH 44103</h3>
            <h4>(REAR PARKING LOT)</h4>
          </BCarouselSlide>

          <BCarouselSlide>
            <template #img>
              <img src="@/assets/1.jpg"    width="100%"
               alt="Test 3">
            </template>
            <h1>DRINKING WITH DEVS</h1>
            <h2>FRIDAY APRIL 26, 2024</h2>
            <h3>7100 EUCLID AVE #140 CLE OH 44103</h3>
            <p>BYOB</p>
          </BCarouselSlide>

        </BCarousel>
          </div>
      </section>
    </div>
  </template>
  
  <script>
  import { BCarousel,BCarouselSlide } from 'bootstrap-vue-next';
  
  export default {
    name: 'Feature',
    components: {
      BCarousel,
      BCarouselSlide,
    },
  };
  </script>
  
  <style scoped>
  /* Styles for the feature component */
  .feature {
    /* Add feature-specific styles */
  }
  
  /* Styles for hero section */
  .hero {
    background-image: url('../assets/clevelandsign.png');
    background-size: cover;
    background-position: center;
    background-color: rgba(0,0,0,0.5);
    color:ghostwhite;
    filter:grayscale(.5);
    text-align: center;
    text-shadow:2px 2px 4px rgba(0,0,0,0.5);
    padding: 4rem 0;
  }
  
  .hero-title {
    font-size: 3rem;
    margin-bottom: 1rem;
    
  }
  
  .hero-subtitle {
    font-size: 1.5rem;
    margin-bottom: 2rem;
  }
  
  .events-carousel {
    height:50px;
    width:100%;
    color: #fff;
    font-family: Arial, Helvetica, sans-serif;
    text-decoration: none;
    text-transform: uppercase;
  }
 



  </style>