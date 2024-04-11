package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryDao implements BreweryDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Brewery> getBreweries() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT * FROM brewery;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Brewery brewery = mapRowToBrewery(results);
                breweries.add(brewery);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweries;
    }
    @Override
    public Brewery getBreweryById(int breweryId) {
        Brewery brewery = null;
        String sql = "SELECT * FROM brewery WHERE brewery_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            if (results.next()) {
                brewery = mapRowToBrewery(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return brewery;
    }
    @Override
    public List<Beer> getBeers() {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM brewery_beer;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Beer beer = mapRowToBeer(results);
                beers.add(beer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beers;
    }

    @Override
    public List<Beer> getBeersForBrewery(int breweryId) {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM brewery_beer WHERE brewery_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            while (results.next()) {
                Beer beer = mapRowToBeer(results);
                beers.add(beer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beers;
    }

    @Override
    public Beer getBeerById(int beerId) {
        Beer beer = null;
        String sql = "SELECT * FROM brewery_beer WHERE brewery_beer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while (results.next()) {
                beer = mapRowToBeer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beer;
    }

    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setBreweryName(rs.getString("brewery_name"));
        brewery.setBrewerName(rs.getString("brewer_name"));
        brewery.setContactInformation(rs.getString("contact_information"));
        brewery.setStreetAddress(rs.getString("street_address"));
        brewery.setCity(rs.getString("city"));
        brewery.setStateCode(rs.getString("state_code"));
        brewery.setPostalCode(rs.getString("postal_code"));
        brewery.setHistory(rs.getString("history"));
        brewery.setActive(rs.getBoolean("active"));
        brewery.setDaysHoursOfOperation(rs.getString("days_hours_of_operation"));
        return brewery;
    }

    private Beer mapRowToBeer(SqlRowSet rs) {
        Beer beer = new Beer();
        beer.setBeerId(rs.getInt("brewery_beer_id"));
        beer.setBreweryId(rs.getInt("brewery_id"));
        beer.setBeerName(rs.getString("beer_name"));
        beer.setBeerDescription(rs.getString("beer_description"));
        beer.setBeerType(rs.getString("beer_type"));
        beer.setAbv(rs.getBigDecimal("abv"));
        beer.setNumRatings(rs.getInt("num_ratings"));
        beer.setAverageRating(rs.getBigDecimal("avg_rating"));
        beer.setLastActive(rs.getDate("last_active").toLocalDate());
        return beer;
    }

}
