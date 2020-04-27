package com.enos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import java.io.IOException;
import java.util.*;

//335:CO:SNTL - Berthoud Pass
//https://mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/?utm_source=mkyong.com&utm_medium=referral&utm_campaign=afterpost-related&utm_content=link1

@Path("/snotel")
public class ObservationService {

    @GET
    @Path("/observations/{stationTriplet}")
    @Produces("application/json")
    public String getObservation(
            @PathParam("stationTriplet")
                    String stationTriplet,
            @QueryParam("from")
                    String from,
            @QueryParam("to")
                    String to
            ) throws IOException {


        SnotelReportsService snotelReportsService = new SnotelReportsService();
//        String dateRange = "2020-04-13,2020-04-14";
        String dateRange = from + "," + to;
        String requestUri = snotelReportsService.buildObservationUrl(stationTriplet, dateRange);

        HttpUtil httpUtil = new HttpUtil();
        String resp = httpUtil.doGet(requestUri);

        String observations = "No observations found";

        try {

            CSVUtils csvUtils = new CSVUtils();
            Map parsedCSV = csvUtils.convertCSVToJSON(resp);
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(parsedCSV);
            observations = csvUtils.arrayListToJSON(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return observations;
    }

}
