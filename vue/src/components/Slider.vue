 <!-- going to attempt to implement a slider/carousel feature to main page, similar to GLB website none of this does anything rn -->


<template>
    <div class="slider">
      <carousel>
        <slide v-for="(item, index) in items" :key="index">
          <!-- Content for each slide -->
          <img :src="item.image" alt="Slide {{ index }}">
          <div class="slide-caption">
            <h2>{{ item.title }}</h2>
            <p>{{ item.description }}</p>
          </div>
        </slide>
      </carousel>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Slider',
    data() {
      return {
        items: [
          { image: '@/assets/backgroundtest.jpg', title: 'Slide 1', description: 'Description for Slide 1' },
          { image: 'path/to/image2.jpg', title: 'Slide 2', description: 'Description for Slide 2' },
          //
        ]
      };
    }
  };
  </script>
  
  <style scoped>
 
  .slide-caption {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.5);
    padding: 1rem;
    color: #fff;
  }
  </style>
  
