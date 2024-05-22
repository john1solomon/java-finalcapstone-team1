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

  createBrewery(brewery) {
    return http.post(`/brewery/`, brewery);
  },

  updateBrewery(brewery) {
    return http.put(`/brewery/${brewery.breweryId}`, brewery);
  },

  getBeers() {
    return http.get(`/beer`)
  },

  getBeer(beerId) {
    return http.get(`/beer/${beerId}`)
  },

  createBeer(beer) {
    return http.post(`/beer/`, beer);
  },

  updateBeer(beer) {
    return http.put(`/beer/${beer.beerId}`, beer);
  },

  deleteBeer(beerId) {
    return http.delete(`/beer/${beerId}`);
  },

  getBreweryEvents() {
    return http.get(`/event`);
  },

  getBreweryEventsForBrewery(breweryId) {
    return http.get(`/brewery/${breweryId}/event`);
  },

  getBreweryEventById(breweryEventId) {
    return http.get(`/event/${breweryEventId}`);
  },

  createEvent(event) {
    return http.post(`/event/`, event);
  },

  updateEvent(event) {
    return http.put(`/event/${event.breweryEventId}`, event);
  },

  deleteEvent(breweryEventId) {
    return http.delete(`/event/${breweryEventId}`);
  }

}