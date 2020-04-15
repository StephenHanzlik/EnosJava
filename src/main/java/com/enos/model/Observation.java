package com.enos.model;

public class Observation {

    private String date;
    private String snow_water_equivalent;
    private String change_in_snow_water_equivalent;
    private String snow_depth_in;
    private String change_in_snow_depth_in;
    private String air_temperature_observed_degf;

    public void setDate(String date) {
        this.date = date;
    }

    public void setSnowWaterEquivalent(String snow_water_equivalent) {
        this.snow_water_equivalent = snow_water_equivalent;
    }

    public void setChangeInSnowWaterEquivalent(String change_in_snow_water_equivalent) {
        this.change_in_snow_water_equivalent = change_in_snow_water_equivalent;
    }

    public void setSnowDepth(String snow_depth_in) {
        this.snow_depth_in = snow_depth_in;
    }

    public void setChangeInSnowDepth(String change_in_snow_depth_in) {
        this.change_in_snow_depth_in = change_in_snow_depth_in;
    }

    public void setAirTemperatureObserved(String air_temperature_observed_degf){
        this.air_temperature_observed_degf = air_temperature_observed_degf;
    }

    public String getDate() { return date; }

    public String getSnowWaterEquivalent() { return snow_water_equivalent; }

    public String getChangeInSnowWaterEquivalent(){ return change_in_snow_water_equivalent; }

    public String getSnowDepth() { return snow_depth_in; }

    public String getChangeInSnowDepth() { return change_in_snow_depth_in; }

    public String getAirTemperatureObserved() { return air_temperature_observed_degf; }


//DB Set up Local
//$ psqlenos
    //create databasse enos;
    //create user enos;
    //grant all privileges on database enos to enos;
    //

//    https://www.postgresql.org/docs/9.1/sql-createtable.html
//    https://www.postgresqltutorial.com/postgresql-data-types/
//CREATE TABLE stations (
//            elevation   smallint,
//            location    varchar(100),
//    name        varchar(60),
//    timezone    smallint,
//    triplet     varchar(20),
//    wind        boolean
//);

//Stations JSON - https://github.com/StephenHanzlik/Enos_Node_Impl/blob/master/stations.json
//    {
//        "elevation": 8777,
//        "location": {
//           "lat": 40.8852,
//           "lng": -110.8277
//        },
//        "name": "BEAR RIVER RS",
//        "timezone": -7,
//        "triplet": "992:UT:SNTL",
//        "wind": false
//    }

//    insert into stations('elevation', 'location', 'name', 'timezone', 'triplet', 'wind') values(8777, '{"lat":40.8852,"lng":-110.8277}', 'BEAR RIVER RS', -7, '992:UT:SNTL', false);

}
