package com.enos;

import com.enos.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StationService {

    public List getStationMetadata(Station station){

        List stationMetadata = new ArrayList();

        if(station.equals(Station.COLORADO)){
            stationMetadata.add("Colorado");
            stationMetadata.add("11,000");
            stationMetadata.add("12.3892394,-21.901288908");
        }else if(station.equals(Station.UTAH)){
            stationMetadata.add("UTAH");
            stationMetadata.add("10,200");
            stationMetadata.add("15.6792394,-11.423288908");
        }else if(station.equals(Station.WASHINGTON)){
            stationMetadata.add("Washington");
            stationMetadata.add("6,000");
            stationMetadata.add("29.3892394,-31.901288908");
        }else {
            stationMetadata.add("No Station Found");
        }

        return stationMetadata;

    }

    //Just build the object to return here here

//    public Map getDailyStationReport(Station station){
//
//        Map stationReport;
//
//
//    }

}
