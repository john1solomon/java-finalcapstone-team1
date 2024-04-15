<template>
<footer class="footer">
    <div class="container">
        <!-- <div class="footer-links">
            <router-link to="/contact" class="footer-link">Contact</router-link>
        </div> -->
        <p class="copyright">&copy; 2024 NorthCoast BeerFinder. All Rights Reserved.</p>
    </div>
</footer>
</template>

<script>
export default {
    name:'Footer'
};

</script>

<style scoped>
.footer {
    background-color: #333;
    color:#fff;
    padding: 0.5rem 0;
    
    
}

.container {
    max-width:1200px;
    margin: 0 auto;
    display: flex;
    justify-content:space-between;
    align-items: center;
    
    

}

.footer-links {
    display: flex;

}

.footer-link {
    color:#fff;
    text-decoration: none;
    margin-right: 1.5rem;
    font-size: 1rem;
    text-transform: uppercase;

}

.footer-link:hover {
    color:#ffcc00;

}

.copyright {
    font-size: 1rem;
    text-transform: uppercase;
}
</style>