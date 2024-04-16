package com.techelevator.controller;

import com.techelevator.dao.BeerLoverDao;
import com.techelevator.model.BeerReview;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BeerLoverController {
    private BeerLoverDao beerLoverDao;

    public BeerLoverController(BeerLoverDao beerLoverDao) {
        this.beerLoverDao = beerLoverDao;
    }
    @RequestMapping(path = "/review", method = RequestMethod.GET)
    public List<BeerReview> getBeerReviews() {
        return beerLoverDao.getBeerReviews();
    }
    @RequestMapping(path = "/user/{userId}/review", method = RequestMethod.GET)
    public List<BeerReview> getBeerReviewsForUser(@PathVariable int userId) {
        return beerLoverDao.getBeerReviewsForUser(userId);
    }
    @RequestMapping(path = "/beer/{beerId}/review", method = RequestMethod.GET)
    public List<BeerReview> getBeerReviewsForBeer(@PathVariable int beerId) {
        return beerLoverDao.getBeerReviewsForBeer(beerId);
    }
//    @RequestMapping(path = "/review", method = RequestMethod.GET)
//    public List<BeerReview> getBeerReviewsForUserAndBeer(@RequestParam(required = false, defaultValue="0") int userId,
//                                           @RequestParam(required = false, defaultValue="0") int beerId) {
//        return beerLoverDao.getBeerReviewsForUserAndBeer(userId, beerId);
//    }
    @RequestMapping(path = "/review", method = RequestMethod.POST)
    public int createBeerReview(@Valid @RequestBody BeerReview beerReview) {
        return beerLoverDao.createBeerReview(beerReview);
    }
    @RequestMapping(path = "/review", method = RequestMethod.PUT)
    public int updateBeerReview(@Valid @RequestBody BeerReview beerReview) {
        return beerLoverDao.updateBeerReview(beerReview);
    }
    @RequestMapping(path = "/review", method = RequestMethod.DELETE)
    public int deleteBeerReview(@Valid @RequestBody BeerReview beerReview) {
        return beerLoverDao.deleteBeerReview(beerReview);
    }
}