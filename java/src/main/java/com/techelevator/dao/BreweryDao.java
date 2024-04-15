package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import com.techelevator.model.BreweryEvent;

import java.util.List;
public interface BreweryDao {
    List<Brewery> getBreweries();
    Brewery getBreweryById(int breweryId);
    Brewery createBrewery(Brewery brewery);
    Brewery updateBrewery(Brewery brewery);
    List<Beer> getBeers();
    List<Beer> getBeersForBrewery(int breweryId);
    Beer getBeerById(int beerId);
    Beer createBeer(Beer beer);
    Beer updateBeer(Beer beer);
    int deleteBeerById(int beerId);
    List<BreweryEvent> getBreweryEvents();
    List<BreweryEvent> getBreweryEventsForBrewery(int breweryId);
    BreweryEvent getBreweryEventById(int breweryEventId);
    BreweryEvent createBreweryEvent(BreweryEvent breweryEvent);
    BreweryEvent updateBreweryEvent(BreweryEvent breweryEvent);
    int deleteBreweryEventById(int breweryEventId);
}
