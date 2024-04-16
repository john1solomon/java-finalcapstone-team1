import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:9000'
});

export default {

    getBeerReviews() {
        return http.get(`/review`);
    },

    getBeerReviewsForUser(userId) {
        return http.get(`/user/${userId}/review`);
    },

    getBeerReviewsForBeer(beerId) {
        return http.get(`/beer/${beerId}/review`);
    },

    createReview(review) {
        return http.post(`/review/`, review);
    },

    updateReview(review) {
        return http.put(`/review/`, review);
    },

    deleteReview(review) {
        return http.delete(`/review`, { data: review });
    }

}