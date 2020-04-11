package com.enos;

import com.enos.model.Observation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;



@Path("/snotel")
public class ObservationService {

    @GET
    @Path("/observations")
    @Produces("application/json")
    public String getObservation() throws IOException {



        //We want to use HttpUtils here to make a request to SNOTEL
        //Currently id does a dummy get
        SnotelReportsService snotelReportsService = new SnotelReportsService();
        //335:CO:SNTL - Berthoud Pass
        String stationTriplet = "335:CO:SNTL";
        String dateRange = "2013-01-15,2013-01-18";
        String requestUri = snotelReportsService.buildSnotelReportUrl(stationTriplet, dateRange);

        HttpUtil httpUtil = new HttpUtil();
        String resp = httpUtil.doGet(requestUri);

//        Map convertedResponse = snotelReportsService.convertResponseToJSON(resp);

        CSVUtils csvUtils = new CSVUtils();
        try {
            csvUtils.main(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @POST
    @Path("/observations")
    @Consumes("application/json")
    public Response createStation(Observation observation){

        String result = "Station created: " + observation;
        return Response.status(201).entity(result).build();
    }


}
