<template>
  <div class="home-view">
    <Header/>
     
    <Feature/>

    <Footer/>
     
    </div>
   </template>

<script>
import Header from '../components/Header.vue';
import Feature from '../components/Feature.vue';
import Footer from '../components/Footer.vue';



export default {
  name: 'Homeview',
  components: {
    Header,
    Feature,
    Footer
    
  }

};
</script>
<style scoped>

</style>