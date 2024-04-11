package com.techelevator.model;

public class Brewery {
    private int breweryId;
    private String breweryName;
    private String brewerName;
    private String contactInformation;
    private String streetAddress;
    private String city;
    private String stateCode;
    private String postalCode;
    private String history;
    private boolean active;
    private String daysHoursOfOperation;
    public Brewery() {}
    public Brewery(int breweryId, String breweryName, String brewerName, String contactInformation, String streetAddress, String city, String stateCode, String postalCode, String history, boolean active, String daysHoursOfOperation) {
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.brewerName = brewerName;
        this.contactInformation = contactInformation;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.history = history;
        this.active = active;
        this.daysHoursOfOperation = daysHoursOfOperation;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public String getBrewerName() {
        return brewerName;
    }

    public void setBrewerName(String brewerName) {
        this.brewerName = brewerName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDaysHoursOfOperation() {
        return daysHoursOfOperation;
    }

    public void setDaysHoursOfOperation(String daysHoursOfOperation) {
        this.daysHoursOfOperation = daysHoursOfOperation;
    }
}
