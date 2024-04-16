<template>
    <div class="container">
        <Header/>
        <ModifyBreweryDetail/>
        <Footer/>
        
    </div>
</template>

<script>
import Header from '../components/Header.vue';
import ModifyBreweryDetail from '../components/ModifyBreweryDetail.vue';
import Footer from '../components/Footer.vue';



export default {
    components: {
        Header,
        ModifyBreweryDetail,
        Footer
    }
};
</script>
<style scoped>

</style>