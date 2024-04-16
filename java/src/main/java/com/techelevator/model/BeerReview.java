package com.techelevator.model;

public class BeerReview {
    private int userId;
    private int beerId;
    private int beerRating;
    private String review;
    private boolean favorite;

    public BeerReview () {}
    public BeerReview(int userId, int beerId, int beerRating, String review, boolean favorite) {
        this.userId = userId;
        this.beerId = beerId;
        this.beerRating = beerRating;
        this.review = review;
        this.favorite = favorite;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getBeerRating() {
        return beerRating;
    }

    public void setBeerRating(int beerRating) {
        this.beerRating = beerRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
