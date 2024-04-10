<template>
<header class="header">
    <div class="container">
        <div class="header-logo">
    <img src="@/assets/logo.jpg" alt="NorthCoast BeerFinder" class="logo">
        </div>
    <nav class="header-nav">
        <ul class="nav-list">
            <li class="nav-item"><router-link to="/" class="nav-link">Home</router-link></li>
            <li class="nav-item"><router-link to="/breweries" class="nav-link">Breweries</router-link></li>
            <li class="nav-item"><router-link to="/beers" class="nav-link">Beers</router-link></li>
            <li class="nav-item"><router-link to="/contact" class="nav-link">Contact</router-link></li>
            <li class="nav-item"><router-link to="/logout" class="nav-link">Logout</router-link></li>
        </ul>
    </nav>
    </div>
</header>
</template>

<script>
export default {
    name:'Header'
};
</script>

<style scoped>

.header{
    background-color: black;
    padding:1rem 0;
}

.container {
    max-width: 1200px;
    margin:0 auto;
    display:flex;
    justify-content: space-between;
    align-items: center;
}
.logo {
  height: 200px; 
}

.header-nav {
    font-size: 1.2 rem;
}

.nav-list {
    list-style: none;
    padding:0;
    margin:0;
    display:flex;

}

.nav-item {
    margin-right:1.5rem;
}

.nav-item:last-child {
  margin-right: 0;
}



.nav-link {
    color: #fff;
    font-family: Arial, Helvetica, sans-serif;
    text-decoration: none;
    text-transform: uppercase;
    
    transition: color 0.3s ease;
}

.nav-link:hover {
    color:#ffcc00 !important;

}

</style>