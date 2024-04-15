package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.BeerReview;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeerLoverDao implements BeerLoverDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcBeerLoverDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<BeerReview> getBeerReviews() {
        List<BeerReview> beerReviews = new ArrayList<>();
        String sql = "SELECT * FROM beer_review;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                BeerReview beerReview = mapRowToBeerReview(results);
                beerReviews.add(beerReview);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beerReviews;
    }
    @Override
    public List<BeerReview> getBeerReviewsForUser(int userId) {
        List<BeerReview> beerReviews = new ArrayList<>();
        String sql = "SELECT * FROM beer_review WHERE user_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                BeerReview beerReview = mapRowToBeerReview(results);
                beerReviews.add(beerReview);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beerReviews;
    }
    @Override
    public List<BeerReview> getBeerReviewsForBeer(int beerId) {
        List<BeerReview> beerReviews = new ArrayList<>();
        String sql = "SELECT * FROM beer_review WHERE brewery_beer_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while (results.next()) {
                BeerReview beerReview = mapRowToBeerReview(results);
                beerReviews.add(beerReview);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beerReviews;
    }
//    @Override
//    public BeerReview getBeerReviewForUserAndBeer(int userId, int beerId) {
//        BeerReview beerReview = null;
//
//        // Check the parameters.  If
//        String sql = "SELECT * FROM beer_review WHERE user_id = ? and brewery_beer_id = ?;";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, beerId);
//            if (results.next()) {
//                beerReview = mapRowToBeerReview(results);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//        return beerReview;
//    }
    @Override
    public int createBeerReview(BeerReview beerReview) {
        int numRowsCreated;
        String sql = "INSERT INTO beer_review (user_id, brewery_beer_id, beer_rating, review, favorite) " +
                "VALUES(?, ?, ?, ?, ?);";
        try {
            int rowsAffected = jdbcTemplate.update(sql, beerReview.getUserId(), beerReview.getBeerId(),
                    beerReview.getBeerRating(), beerReview.getReview(), beerReview.isFavorite());

            if (rowsAffected == 0) {
                numRowsCreated = 0;
                throw new DaoException("Zero beer review rows affected, expected at least one");
            } else {
                numRowsCreated = rowsAffected;
            }
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return numRowsCreated;
    }
    @Override
    public int updateBeerReview(BeerReview beerReview) {
        int numRowsUpdated;
        String sql = "UPDATE beer_review " +
                "SET beer_rating = ?, review = ?, favorite = ? " +
                "WHERE user_id = ? AND brewery_beer_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, beerReview.getBeerRating(), beerReview.getReview(),
                    beerReview.isFavorite(), beerReview.getUserId(), beerReview.getBeerId());

            if (rowsAffected == 0) {
                numRowsUpdated = 0;
                throw new DaoException("Zero beer review rows affected, expected at least one");
            } else {
                numRowsUpdated = rowsAffected;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return numRowsUpdated;
    }

    @Override
    public int deleteBeerReview(BeerReview beerReview) {
        int numRowsDeleted;

        String sql = "DELETE FROM beer_review WHERE user_id = ? AND brewery_beer_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, beerReview.getUserId(), beerReview.getBeerId());

            if (rowsAffected == 0) {
                numRowsDeleted = 0;
                throw new DaoException("Zero beer review rows affected, expected at least one");
            } else {
                numRowsDeleted = rowsAffected;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return numRowsDeleted;
    }
    private BeerReview mapRowToBeerReview(SqlRowSet rs) {
        BeerReview beerReview = new BeerReview();
        beerReview.setUserId(rs.getInt("user_id"));
        beerReview.setBeerId(rs.getInt("brewery_beer_id"));
        beerReview.setBeerRating(rs.getInt("beer_rating"));
        beerReview.setReview(rs.getString("review"));
        beerReview.setFavorite(rs.getBoolean("favorite"));
        return beerReview;
    }
}
