package com.enos.model;

public class Station {
    public int sid;
    public int elevation;
    public int latitude;
    public int longitude;
    public String name;
    public int timezone;
    public String triplet;
    public boolean wind;

    public String getTriplet() {
        return triplet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setWind(boolean wind) {
        this.wind = wind;
    }

    public void setTriplet(String triplet) { this.triplet = triplet; }

}
