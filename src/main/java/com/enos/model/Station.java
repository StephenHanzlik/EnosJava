package com.enos.model;

public class Station {

//    codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices
    private int sid;
    private int elevation;
    private int latitude;
    private int longitude;
    private String name;
    private int timezone;
    private String triplet;
    private boolean wind;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getElevation() {
        return elevation;
    }

    public void setWind(boolean wind) {
        this.wind = wind;
    }

    public boolean getWind() {
        return wind;
    }

    public void setTriplet(String triplet) { this.triplet = triplet; }

    public String getTriplet() {
        return triplet;
    }

}
