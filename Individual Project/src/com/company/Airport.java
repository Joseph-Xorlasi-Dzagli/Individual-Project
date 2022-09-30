package com.company;

public class Airport {
    private int airportID;
    private String airportName;
    private String City;
    private String Country;
    private String IATA;
    private String ICAO;
    private float Latitude;
    private float Longitude;
    private int Altitude;
    private float Timezone;
    private char DST;
    private String DatabaseTimezone;
    private String Type;
    private String Source;

    Airport(String airportEntry){
        String[] attributes = airportEntry.split(",");
        try {
            airportID = Integer.parseInt(attributes[0]);
            airportName = attributes[1];
            City = attributes[2];
            Country = attributes[3];
            IATA = attributes[4];
            ICAO = attributes[5];
            Latitude = Float.parseFloat(attributes[6]);
            Longitude = Float.parseFloat(attributes[7]);
            Altitude = Integer.parseInt(attributes[8]);
            DST = attributes[10].charAt(0);
//            Timezone = Float.parseFloat(attributes[9]);
            DatabaseTimezone = attributes[11];
            Type = attributes[12];
            Source = attributes[13];
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }

    }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public int getAltitude() {
        return Altitude;
    }

    public void setAltitude(int altitude) {
        Altitude = altitude;
    }

    public float getTimezone() {
        return Timezone;
    }

    public void setTimezone(float timezone) {
        Timezone = timezone;
    }
}











//(int airportID, String airportName, String City, String Country, String IATA, String ICAO, int Latitude, int Longitude, int Altitude, char DST, int Timezone, String DatabaseTimezone, String Type, String Source)