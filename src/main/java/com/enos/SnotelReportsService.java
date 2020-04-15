package com.enos;

import java.util.*;
import java.util.Scanner;


//a service to build requests to pass to HttpUtil and to format data
public class SnotelReportsService {

    public static final String BASE_URL = "https://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customSingleStationReport/daily";
    public static final String TRIPLET_ID_NAME_PREPEND= "%7Cid%3D%22%22%7Cname";
    public static final String SNOTEL_QUERY_FIELDS = "/WTEQ::value,WTEQ::delta,SNWD::value,SNWD::delta";


//**********************************************************************************
//            Old Call: http://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customSingleStationReport/daily/335:CO:SNTL%7Cid%3D%22%22%7Cname/2013-01-15,2013-01-18/WTEQ%3A%3Avalue%2CWTEQ%3A%3Adelta%2CSNWD%3A%3Avalue%2CSNWD%3A%3Adelta
//
//            WTEQ::value,WTEQ::delta,SNWD::value,SNWD::delta
//
//            Date: 2013-01-18
//            Snow Water Equivalent (in) Start of Day Values: 5.8
//            Change In Snow Water Equivalent (in): 0.0
//            Snow Depth (in) Start of Day Values: 28
//            Change In Snow Depth (in): -1
//
//
//            New Call: https://wcc.sc.egov.usda.gov/reportGenerator/view_csv/customMultiTimeSeriesGroupByStationReport/daily/start_of_period/335:CO:SNTL%7Cid=%22%22%7Cname/0,0/TAVG::value,TMAX::value,TMIN::value,PREC::value,PRCP::value,SNWD::value,TOBS::value?fitToScreen=false
//
//            TAVG::value,TMAX::value,TMIN::value,PREC::value,PRCP::value,SNWD::value,TOBS::value
//
//            Date: 2020-04-08
//            Air Temperature Average (degF): Null
//            Air Temperature Maximum (degF): Null
//            Air Temperature Minimum (degF): Null
//            Precipitation Accumulation (in) Start of Day Values: 23.8
//            Precipitation Increment (in): Null
//            Snow Depth (in) Start of Day Values: 64
//            Air Temperature Observed (degF) Start of Day Values: 29
// **********************************************************************************



    public String buildObservationUrl(String stationTriplet, String dateRange){

        stationTriplet = "/" + stationTriplet;
        dateRange = "/" + dateRange;

        String requestUri = BASE_URL + stationTriplet + TRIPLET_ID_NAME_PREPEND + dateRange + SNOTEL_QUERY_FIELDS;

        return requestUri;
    }

//    public String buildStationUrl(String stationTriplet, String dateRange){
//
//        stationTriplet = "/" + stationTriplet;
//        dateRange = "/" + dateRange;
//
//        String requestUri = BASE_URL + stationTriplet + TRIPLET_ID_NAME_PREPEND + dateRange + SNOTEL_QUERY_FIELDS;
//
//        return requestUri;
//    }





//    https://stackoverflow.com/questions/50651713/convert-a-comma-separated-string-to-json-in-java
//    public Map convertResponseToJSON(String snotelReport) {
        //This doesn't quite do what we want
//        String [] arrayStr = snotelReport.split(",");
//        Map<String,String> map = new HashMap<>();
//
//        String key = null;
//        for (String s: arrayStr){
//            if(key == null) {
//                key = s;
//            } else {
//                map.put(key, s);
//                key = null;
//            }
//        }


//    }

}
