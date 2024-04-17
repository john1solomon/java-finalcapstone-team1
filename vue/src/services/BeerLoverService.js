import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:9000'
});

export default {

    // getBeerReviews() {
    //     return http.get(`/review`);
    // },

    getBeerReviewsForUser(userId) {
        return http.get(`/user/${userId}/review`);
    },

    getBeerReviewsForBeer(beerId) {
        return http.get(`/beer/${beerId}/review`);
    },

    getBeerReviewForUserAndBeer(userId, beerId) {
        return http.get(`/review/?userId=${userId}&beerId=${beerId}`);
    },

    createBeerReview(beerReview) {
        return http.post(`/review/`, beerReview);
    },

    updateBeerReview(beerReview) {
        return http.put(`/review/`, beerReview);
    },

    deleteBeerReview(userId, beerId) {
        return http.delete(`/review/?userId=${userId}&beerId=${beerId}`);
    }

}