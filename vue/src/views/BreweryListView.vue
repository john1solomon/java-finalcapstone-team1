<template>
    <div class="container">
        <Header/>
        <BreweryList/>
        <Footer/>
        
    </div>
</template>

<script>
import Header from '@/components/Header.vue';
import BreweryList from '@/components/BreweryList.vue';
import Footer from '@/components/Footer.vue';



export default {
    components: {
        BreweryList,
        Header,
        Footer
    }
};
</script>
<style scoped>

</style>