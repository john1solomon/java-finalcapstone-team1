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
    private String logoFilename;
    private String breweryURL;
    private String menuURL;
    private String ocbaInfoURL;
    private String mapURL;
    public Brewery() {}
    public Brewery(int breweryId, String breweryName, String brewerName, String contactInformation, String streetAddress, String city, String stateCode, String postalCode, String logoFilename, String breweryURL, String menuURL, String ocbaInfoURL, String mapURL) {
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.brewerName = brewerName;
        this.contactInformation = contactInformation;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.logoFilename = logoFilename;
        this.breweryURL = breweryURL;
        this.menuURL = menuURL;
        this.ocbaInfoURL = ocbaInfoURL;
        this.mapURL = mapURL;
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

    public String getLogoFilename() {
        return logoFilename;
    }

    public void setLogoFilename(String logoFilename) {
        this.logoFilename = logoFilename;
    }
    public String getBreweryURL() {
        return breweryURL;
    }

    public void setBreweryURL(String breweryURL) {
        this.breweryURL = breweryURL;
    }
    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }
    public String getOcbaInfoURL() {
        return ocbaInfoURL;
    }

    public void setOcbaInfoURL(String ocbaInfoURL) {
        this.ocbaInfoURL = ocbaInfoURL;
    }
    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
    }
}
