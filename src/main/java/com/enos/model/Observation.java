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

    public  String getChangeInSnowWaterEquivalent(){ return change_in_snow_water_equivalent; }

    public String getSnowDepth() { return snow_depth_in; }

    public String getChangeInSnowDepth() { return change_in_snow_depth_in; }

    public String getAirTemperatureObserved() { return air_temperature_observed_degf; }

//     Date: String,
//     Snow_Water_Equivalent_in: String,
//     Change_In_Snow_Water_Equivalent_in: String,
//     Snow_Depth_in: String,
//     Change_In_Snow_Depth_in: String,
//     Air_Temperature_Observed_degF: String

}
