<template>
    <div class="feature">
      <!-- Hero section -->
      <section class="hero">
        <div class="container">
          <h1 class="hero-title">Discover Local Breweries</h1>
          <p class="hero-subtitle">Explore the best breweries in Cleveland</p>
          
        </div>
      </section>
  
      <!-- Featured Breweries section -->
      <section class="featured-breweries">
        <div class="container">
          <h2 class="section-title">Featured Breweries</h2>
          <div class="breweries-grid">
            <!-- Example brewery card -->
            <div class="brewery-card">
              <img src="../assets/glb.jpg" alt="Brewery 1" class="brewery-image">
              <h3 class="brewery-name">Great Lakes Brewing</h3>
              <p class="brewery-description">It may not be the brewery you want, but it's the brewery you deserve.</p>
              
            </div>
            <!-- Add more brewery cards as needed -->
          </div>
        </div>
      </section>
  
      <!-- Upcoming Events section -->
      <section class="upcoming-events">
        <div class="container">
          <h2 class="section-title">Upcoming Events</h2>
          <div class="events-list">
            <!-- Example event item -->
            <div class="event-item">
              <h3 class="event-title">Drinking with Devs</h3>
              <img src="../assets/nbb.png" alt="Brewery 2" class="brewery-image">
              <p class="event-date">Date: April 19, 2024</p>
              <p class="event-description">So long & good luck, Tech Elevator Cohort 25</p>
              
            </div>
            <!-- Add more event items as needed -->
          </div>
        </div>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Feature'
  };
  </script>
  
  <style scoped>
  /* Styles for the feature component */
  .feature {
    /* Add feature-specific styles */
  }
  
  /* Styles for hero section */
  .hero {
    background-image: url('../assets/backgroundtest.jpg');
    background-size: cover;
    background-position: center;
    color:ghostwhite;
    text-align: center;
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
  
  /* Styles for featured breweries section */
  .featured-breweries {
    padding: 4rem 0;
    display:flex;
    justify-content: center;
    align-items: center;
  }
  
  .section-title {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 2rem;
  }
  
  .breweries-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 2rem;
  }
  
  .brewery-card {
    background-color: #f8f8f8;
    padding: 2rem;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 300px;
    margin:0 auto;
  }
  
  .brewery-image {
    width: 100%;
    border-radius: 5px;
  }
  
  .brewery-name {
    font-size: 1.5rem;
    margin-top: 1rem;
  }
  
  .brewery-description {
    margin-top: 1rem;
  }
  
  /* Styles for upcoming events section */
  .upcoming-events {
    padding: 4rem 0;
    display:flex;
    justify-content: center;
    align-items: center;
  }
  
  .events-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 2rem;
  }
  
  .event-item {
    background-color: #f8f8f8;
    padding: 2rem;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 300px;
    margin: 0 auto;
  }
  
  .event-title {
    font-size: 1.5rem;
    margin-bottom: 1rem;
  }
  
  .event-date {
    font-style: italic;
    margin-bottom: 1rem;
  }
  
  .event-description {
    margin-bottom: 1rem;
  }
  </style>