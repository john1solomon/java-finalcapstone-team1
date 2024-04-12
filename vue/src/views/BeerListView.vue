<template>
    <div class="container">
        <Header/>
        <BeerList/>
        <Footer/>
        
    </div>
</template>

<script>
import Header from '@/components/Header.vue';
import BeerList from '../components/BeerList.vue';
import Footer from '@/components/Footer.vue';



export default {
    components: {
   
    Header,
    BeerList,
    Footer
   
}
};
</script>
<style scoped>

</style> 