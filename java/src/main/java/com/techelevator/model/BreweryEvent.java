package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BreweryEvent {
    private int breweryEventId;
    private int breweryId;
    private LocalDate eventDate;
    private String eventName;
    private String eventDescription;
    public BreweryEvent () {}
    public BreweryEvent(int breweryEventId, int breweryId, LocalDate eventDate, String eventName, String eventDescription) {
        this.breweryEventId = breweryEventId;
        this.breweryId = breweryId;
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public int getBreweryEventId() {
        return breweryEventId;
    }
    public void setBreweryEventId(int breweryEventId) {
        this.breweryEventId = breweryEventId;
    }
    public int getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }
    public LocalDate getEventDate() {
        return eventDate;
    }
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
