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

  getBeer(id) {
    return http.get(`/beer/${id}`)
  },

}
