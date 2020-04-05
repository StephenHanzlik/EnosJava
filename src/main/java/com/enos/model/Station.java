package com.enos.model;

public class Station {
    int sid;
    int elevation;
    int latitude;
    int longitude;
    String name;
    int timezone;
    String triplet;
    boolean wind;

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

}
