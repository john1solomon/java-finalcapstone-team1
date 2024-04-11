package com.techelevator.controller;

import com.techelevator.dao.BreweryDao;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BreweryController {
    private BreweryDao breweryDao;

    public BreweryController(BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    @RequestMapping(path = "/brewery", method = RequestMethod.GET)
    public List<Brewery> getBreweries() {
        return breweryDao.getBreweries();
    }

    @RequestMapping(path = "/brewery/{breweryId}", method = RequestMethod.GET)
    public Brewery getBreweryById(@PathVariable int breweryId) {
        return breweryDao.getBreweryById(breweryId);
    }

    @RequestMapping(path = "/beer", method = RequestMethod.GET)
    public List<Beer> getBeers() {
        return breweryDao.getBeers();
    }

    @RequestMapping(path = "/brewery/{breweryId}/beer", method = RequestMethod.GET)
    public List<Beer> getBeersForBrewery(@PathVariable int breweryId) {
        return breweryDao.getBeersForBrewery(breweryId);
    }

    @RequestMapping(path = "/beer/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable int beerId) {
        return breweryDao.getBeerById(beerId);
    }
}
