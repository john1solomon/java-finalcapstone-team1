package com.techelevator.dao;

import com.techelevator.model.BeerReview;

import java.util.List;

public interface BeerLoverDao {
    List<BeerReview> getBeerReviews();
    List<BeerReview> getBeerReviewsForUser(int userId);
    List<BeerReview> getBeerReviewsForBeer(int beerId);
//    BeerReview getBeerReviewForUserAndBeer(int userId, int beerId);
    int createBeerReview(BeerReview beerReview);
    int updateBeerReview(BeerReview beerReview);
    int deleteBeerReview(BeerReview beerReview);
}
