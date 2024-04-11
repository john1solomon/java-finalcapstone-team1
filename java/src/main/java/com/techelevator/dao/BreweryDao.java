package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;

import java.util.List;
public interface BreweryDao {
    List<Brewery> getBreweries();
    Brewery getBreweryById(int breweryId);
    List<Beer> getBeers();
    List<Beer> getBeersForBrewery(int breweryId);
    Beer getBeerById(int beerId);
}
