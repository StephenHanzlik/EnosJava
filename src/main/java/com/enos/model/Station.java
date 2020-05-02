package com.enos.model;

public class Station {

//    codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices
    private int sid;
    private int elevation;
    private String location;
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

    public String getLocation() { return location; }

    public int getElevation() {
        return elevation;
    }

    public boolean getWind() {
        return wind;
    }

    public String getTriplet() {
        return triplet;
    }

    public int getTimezone() { return timezone; }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setLocation(String location) { this.location = location; }

    public void setWind(boolean wind) {
        this.wind = wind;
    }

    public void setTriplet(String triplet) { this.triplet = triplet; }


}
