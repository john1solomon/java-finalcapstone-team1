package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Beer {
    private int beerId;
    private int breweryId;
    private String beerName;
    private String beerDescription;
    private String beerType;
    private BigDecimal abv;
    private int numRatings;
    private BigDecimal averageRating;
    private LocalDate lastActive;

    public Beer () {}
    public Beer(int beerId, int breweryId, String beerName, String beerDescription, String beerType, BigDecimal abv, int numRatings, BigDecimal averageRating, LocalDate lastActive) {
        this.beerId = beerId;
        this.breweryId = breweryId;
        this.beerName = beerName;
        this.beerDescription = beerDescription;
        this.beerType = beerType;
        this.abv = abv;
        this.numRatings = numRatings;
        this.averageRating = averageRating;
        this.lastActive = lastActive;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public BigDecimal getAbv() {
        return abv;
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public LocalDate getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDate lastActive) {
        this.lastActive = lastActive;
    }
}
