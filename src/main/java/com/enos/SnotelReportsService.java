package com.enos;


//a service to build requests to pass to HttpUtil and to format data
public class SnotelReportsService {

    public static final String BASE_URL = "https://wcc.sc.egov.usda.gov/reportGenerator/view_csv";
    public static final String CUSTOM_SINGLE_STATION_REPORT= "/customSingleStationReport";
    public static final String CUSTOM_MULTI_TIME_SERIES_GROUP_BY_STATION_REPORT = "/customMultiTimeSeriesGroupByStationReport";
    public static final String DAILY = "/daily";
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



    public String buildSnotelReportUrl(String stationTriplet, String dateRange){

        stationTriplet = "/" + stationTriplet;
        dateRange = "/" + dateRange;

        String requestUri = BASE_URL + CUSTOM_SINGLE_STATION_REPORT + DAILY + stationTriplet + TRIPLET_ID_NAME_PREPEND + dateRange + SNOTEL_QUERY_FIELDS;

        return requestUri;
    }

}
