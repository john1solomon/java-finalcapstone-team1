package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import com.techelevator.model.BreweryEvent;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Brewery createBrewery(Brewery brewery) {
        Brewery newBrewery = null;
        String sql = "INSERT INTO brewery (brewery_name, brewer_username, contact_information, street_address, city, " +
                "state_code, postal_code, logo_filename, brewery_url, menu_url, ocba_info_url, map_url) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING brewery_id;";
        try {
            int newBreweryId = jdbcTemplate.queryForObject(sql, int.class, brewery.getBreweryName(), brewery.getBrewerUsername(),
                    brewery.getContactInformation(),brewery.getStreetAddress(), brewery.getCity(), brewery.getStateCode(),brewery.getPostalCode(),
                    brewery.getLogoFilename(),brewery.getBreweryURL(), brewery.getMenuURL(), brewery.getOcbaInfoURL(), brewery.getMapURL());
            newBrewery = getBreweryById(newBreweryId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newBrewery;
    }
    @Override
    public Brewery updateBrewery(Brewery brewery) {
        Brewery updatedBrewery;

        String sql = "UPDATE brewery " +
                "SET brewery_name = ?, brewer_username = ?, contact_information = ?, street_address = ?, city = ?, state_code = ?, " +
                "postal_code = ?, logo_filename = ?, brewery_url = ?, menu_url = ?, ocba_info_url = ?, map_url = ? " +
                "WHERE brewery_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, brewery.getBreweryName(), brewery.getBrewerUsername(), brewery.getContactInformation(),
                brewery.getStreetAddress(), brewery.getCity(), brewery.getStateCode(), brewery.getPostalCode(), brewery.getLogoFilename(),
                    brewery.getBreweryURL(), brewery.getMenuURL(), brewery.getOcbaInfoURL(), brewery.getMapURL(), brewery.getBreweryId());

            if (rowsAffected == 0) {
                updatedBrewery = null;
//                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedBrewery = getBreweryById(brewery.getBreweryId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedBrewery;
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
    @Override
    public Beer createBeer(Beer beer) {
        Beer newBeer = null;
        String sql = "INSERT INTO brewery_beer (brewery_id, beer_name, beer_description, beer_type, abv, num_ratings, avg_rating, bayesian_rating, last_active) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING brewery_beer_id;";
        try {
            int newBeerId = jdbcTemplate.queryForObject(sql, int.class, beer.getBreweryId(), beer.getBeerName(), beer.getBeerDescription(),
                    beer.getBeerType(), beer.getAbv(), beer.getNumRatings(), beer.getAverageRating(), beer.getBayesianRating(), beer.getLastActive());
            newBeer = getBeerById(newBeerId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newBeer;
    }
    @Override
    public Beer updateBeer(Beer beer) {
        Beer updatedBeer;

        String sql = "UPDATE brewery_beer " +
                "SET brewery_id = ?, beer_name = ?, beer_description = ?, beer_type = ?, abv = ?, num_ratings = ?, avg_rating = ?, bayesian_rating = ?, last_active = ? " +
                "WHERE brewery_beer_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, beer.getBreweryId(), beer.getBeerName(), beer.getBeerDescription(), beer.getBeerType(),
                    beer.getAbv(), beer.getNumRatings(), beer.getAverageRating(), beer.getBayesianRating(), beer.getLastActive(), beer.getBeerId());

            if (rowsAffected == 0) {
                updatedBeer = null;
//                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedBeer = getBeerById(beer.getBeerId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedBeer;
    }
    @Override
    public int deleteBeerById(int beerId) {
        int numRowsDeleted;

        String sql = "DELETE FROM brewery_beer WHERE brewery_beer_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, beerId);

            if (rowsAffected == 0) {
                numRowsDeleted = 0;
                throw new DaoException("Zero brewery beer rows affected, expected at least one");
            } else {
                numRowsDeleted = rowsAffected;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return numRowsDeleted;
    }
    @Override
    public List<BreweryEvent> getBreweryEvents() {
        List<BreweryEvent> breweryEvents = new ArrayList<>();
        String sql = "SELECT * FROM brewery_event;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                BreweryEvent breweryEvent = mapRowToBreweryEvent(results);
                breweryEvents.add(breweryEvent);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweryEvents;
    }
    @Override
    public List<BreweryEvent> getBreweryEventsForBrewery(int breweryId) {
        List<BreweryEvent> breweryEvents = new ArrayList<>();
        String sql = "SELECT * FROM brewery_event WHERE brewery_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            while (results.next()) {
                BreweryEvent breweryEvent = mapRowToBreweryEvent(results);
                breweryEvents.add(breweryEvent);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweryEvents;
    }
    @Override
    public BreweryEvent getBreweryEventById(int breweryEventId) {
        BreweryEvent breweryEvent = null;
        String sql = "SELECT * FROM brewery_event WHERE brewery_event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryEventId);
            while (results.next()) {
                breweryEvent = mapRowToBreweryEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweryEvent;
    }
    @Override
    public BreweryEvent createBreweryEvent(BreweryEvent breweryEvent) {
        BreweryEvent newBreweryEvent = null;
        String sql = "INSERT INTO brewery_event (brewery_id, event_date, event_name, event_description) " +
                "VALUES(?, ?, ?, ?) RETURNING brewery_event_id;";
        try {
            int newBreweryEventId = jdbcTemplate.queryForObject(sql, int.class, breweryEvent.getBreweryId(), breweryEvent.getEventDate(),
                    breweryEvent.getEventName(), breweryEvent.getEventDescription());
            newBreweryEvent = getBreweryEventById(newBreweryEventId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newBreweryEvent;
    }
    @Override
    public BreweryEvent updateBreweryEvent(BreweryEvent breweryEvent) {
        BreweryEvent updatedBreweryEvent;

        String sql = "UPDATE brewery_event " +
                "SET brewery_id = ?, event_date = ?, event_name = ?, event_description = ? " +
                "WHERE brewery_event_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, breweryEvent.getBreweryId(), breweryEvent.getEventDate(),
                    breweryEvent.getEventName(), breweryEvent.getEventDescription(), breweryEvent.getBreweryEventId());

            if (rowsAffected == 0) {
                updatedBreweryEvent = null;
//                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedBreweryEvent = getBreweryEventById(breweryEvent.getBreweryEventId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedBreweryEvent;
    }
    @Override
    public int deleteBreweryEventById(int breweryEventId) {
        int numRowsDeleted;

        String sql = "DELETE FROM brewery_event WHERE brewery_event_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, breweryEventId);

            if (rowsAffected == 0) {
                numRowsDeleted = 0;
                throw new DaoException("Zero brewery event rows affected, expected at least one");
            } else {
                numRowsDeleted = rowsAffected;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return numRowsDeleted;
    }

    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setBreweryName(rs.getString("brewery_name"));
        brewery.setBrewerUsername(rs.getString("brewer_username"));
        brewery.setContactInformation(rs.getString("contact_information"));
        brewery.setStreetAddress(rs.getString("street_address"));
        brewery.setCity(rs.getString("city"));
        brewery.setStateCode(rs.getString("state_code"));
        brewery.setPostalCode(rs.getString("postal_code"));
        brewery.setLogoFilename(rs.getString("logo_filename"));
        brewery.setBreweryURL(rs.getString("brewery_url"));
        brewery.setMenuURL(rs.getString("menu_url"));
        brewery.setOcbaInfoURL(rs.getString("ocba_info_url"));
        brewery.setMapURL(rs.getString("map_url"));
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
        beer.setBayesianRating(rs.getBigDecimal("bayesian_rating"));
        beer.setLastActive(rs.getDate("last_active").toLocalDate());
        return beer;
    }
    private BreweryEvent mapRowToBreweryEvent(SqlRowSet rs) {
        BreweryEvent breweryEvent = new BreweryEvent();
        breweryEvent.setBreweryEventId(rs.getInt("brewery_event_id"));
        breweryEvent.setBreweryId(rs.getInt("brewery_id"));
        breweryEvent.setEventDate(rs.getDate("event_date").toLocalDate());
        breweryEvent.setEventName(rs.getString("event_name"));
        breweryEvent.setEventDescription(rs.getString("event_description"));
        return breweryEvent;
    }
}
