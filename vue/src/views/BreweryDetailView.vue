<template>
  <!-- <div class="container"> -->
    <Header/>
    <BreweryDetail/>
    <Footer/>
  <!-- </div> -->
</template>

<script>
import Header from '../components/Header.vue';
import BreweryDetail from '../components/BreweryDetail.vue';
import Footer from '../components/Footer.vue';

export default {
  components: {
    Header,
    BreweryDetail,
    Footer
  }
};
</script>

<style scoped>

</style>