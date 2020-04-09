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


@Path("/snotel")
public class ObservationService {

    @GET
    @Path("/observations")
    @Produces("application/json")
    public String getObservation() throws IOException {

        //Test Resp
        ObjectMapper objectMapper = new ObjectMapper();
//
        Observation observation = new Observation();
        observation.setDate("11/22/2020");
        observation.setSnowWaterEquivalent("1 millions snows");
//
        String resp = objectMapper.writeValueAsString(observation);

        //We want to use HttpUtils here to make a request to SNOTEL
        //Currently id does a dummy get
//        HttpUtil httpUtil = new HttpUtil();
//        httpUtil.doGet();

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
