package com.techelevator.controller;

import com.techelevator.dao.BreweryDao;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import com.techelevator.model.BreweryEvent;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
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
    @RequestMapping(path = "/brewery", method = RequestMethod.POST)
    public Brewery createBeer(@Valid @RequestBody Brewery brewery) {
        return breweryDao.createBrewery(brewery);
    }
    @RequestMapping(path = "/brewery/{breweryId}", method = RequestMethod.PUT)
    public Brewery updateBrewery(@Valid @RequestBody Brewery brewery, @PathVariable int breweryId) {
        return breweryDao.updateBrewery(brewery);
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
    @RequestMapping(path = "/beer", method = RequestMethod.POST)
    public Beer createBeer(@Valid @RequestBody Beer beer) {
        return breweryDao.createBeer(beer);
    }
    @RequestMapping(path = "/beer/{beerId}", method = RequestMethod.PUT)
    public Beer updateBeer(@Valid @RequestBody Beer beer, @PathVariable int beerId) {
        return breweryDao.updateBeer(beer);
    }
    @RequestMapping(path = "/beer/{beerId}", method = RequestMethod.DELETE)
    public int deleteBeerById(@PathVariable int beerId) {
        return breweryDao.deleteBeerById(beerId);
    }
    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public List<BreweryEvent> getBreweryEvents() {
        return breweryDao.getBreweryEvents();
    }
    @RequestMapping(path = "/brewery/{breweryId}/event", method = RequestMethod.GET)
    public List<BreweryEvent> getBreweryEventsForBrewery(@PathVariable int breweryId) {
        return breweryDao.getBreweryEventsForBrewery(breweryId);
    }
    @RequestMapping(path = "/event/{breweryEventId}", method = RequestMethod.GET)
    public BreweryEvent getBreweryEventById(@PathVariable int breweryEventId) {
        return breweryDao.getBreweryEventById(breweryEventId);
    }
    @RequestMapping(path = "/event", method = RequestMethod.POST)
    public BreweryEvent createBreweryEvent(@Valid @RequestBody BreweryEvent breweryEvent) {
        return breweryDao.createBreweryEvent(breweryEvent);
    }
    @RequestMapping(path = "/event/{breweryEventId}", method = RequestMethod.PUT)
    public BreweryEvent updateBreweryEvent(@Valid @RequestBody BreweryEvent breweryEvent, @PathVariable int breweryEventId) {
        return breweryDao.updateBreweryEvent(breweryEvent);
    }
    @RequestMapping(path = "/event/{breweryEventId}", method = RequestMethod.DELETE)
    public int deleteBreweryEventById(@PathVariable int breweryEventId) {
        return breweryDao.deleteBreweryEventById(breweryEventId);
    }
}
