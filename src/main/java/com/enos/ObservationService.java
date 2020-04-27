package com.enos;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;



@Path("/snotel")
public class ObservationService {

    @GET
    @Path("/observations/{stationTriplet}")
    @Produces("application/json")
    public String getObservation(@PathParam("stationTriplet") String stationTriplet) throws IOException {

        //We want to use HttpUtils here to make a request to SNOTEL
        //Currently id does a dummy get
        SnotelReportsService snotelReportsService = new SnotelReportsService();
        //335:CO:SNTL - Berthoud Pass
//         stationTriplet = "335:CO:SNTL";
        String dateRange = "2020-04-13,2020-04-14";
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

//    @POST
//    @Path("/observations")
//    @Consumes("application/json")
//    public Response createObservation(Observation observation){
//
//        String result = "Station created: " + observation;
//        return Response.status(201).entity(result).build();
//    }


}
