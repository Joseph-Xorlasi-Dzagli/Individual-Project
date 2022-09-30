package com.company;

public class Airline {
    private int AirlineID;
    private String Name;
    private String Alias;
    private String IATA;
    private String ICAO;
    private String Callsign;
    private String Country;
    private char Active;


    Airline(String AirlineEntry){
        String[] attributes = AirlineEntry.split(",");
        AirlineID = Integer.parseInt(attributes[0]);
        Name = attributes[1];
        Alias = attributes[2];
        IATA = attributes[3];
        ICAO = attributes[4];
        Callsign = attributes[5];
        Country = attributes[6];
        Active = attributes[7].charAt(0);

    }

    public int getAirlineID() {
        return AirlineID;
    }

    public void setAirlineID(int airlineID) {
        AirlineID = airlineID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlias() {
        return Alias;
    }
}
