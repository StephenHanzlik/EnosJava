package com.enos;

import com.enos.model.Station;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;


//No longer using these.  May be helpful for cleaning up unnecessary dependencies
//https://mkyong.com/webservices/jax-rs/integrate-jackson-with-resteasy/
//https://mkyong.com/webservices/jax-rs/restful-java-client-with-apache-httpclient/

//Using These
//https://www.baeldung.com/jackson-object-mapper-tutorial
//http://tutorials.jenkov.com/java-json/jackson-objectmapper.html#write-json-from-objects
@Path("/snotel")
public class StationService {

    @GET
    @Path("/stations")
    @Produces("application/json")
    public String getStation() throws IOException {

        //Test Resp
//        ObjectMapper objectMapper = new ObjectMapper();
////
//        Station station = new Station();
//        station.setName("Eldora");
//        station.setElevation(9082);
//        station.setWind(false);
//        station.setTriplet("test");
////
//        String resp = objectMapper.writeValueAsString(station);

        //We want to use HttpUtils here to make a request to SNOTEL
        //Currently id does a dummy get
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doGet();

        return "resp";
    }

    //We don't actually want to add stations unless importing the static list of station data
    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createStation(Station station){

        String result = "Station created: " + station;
        return Response.status(201).entity(result).build();
    }


}
