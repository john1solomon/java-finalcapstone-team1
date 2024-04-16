import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:9000'
});

export default {

  listBreweries() {
    return http.get(`/brewery`);
  },

  getBrewery(breweryId) {
    return http.get(`/brewery/${breweryId}`)
  },

  
  getBreweryBeers(breweryId) {
    return http.get(`/brewery/${breweryId}/beer`)
  },

  getBeers() {
    return http.get(`/beer`)
  },

  addBeer() {
    return http.post(`/beer`)
  }

}
