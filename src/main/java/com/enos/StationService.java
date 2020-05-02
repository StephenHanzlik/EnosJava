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
    public String getStation() throws ClassNotFoundException {

        PsqlService psqlService = new PsqlService();
        String stationsJson = psqlService.executeQuery("SELECT * FROM stations");

        return stationsJson;
    }

    //TODO: lock this down with auth since this list is meant to be static and edited only with permissions
    @POST
    @Path("/stations")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createStation(String reqBody) throws ClassNotFoundException {

        CSVUtils csvUtils = new CSVUtils();
        Station station = csvUtils.jsonToStation(reqBody);

        //TODO: build DAO for this
        PsqlService psqlService = new PsqlService();
        String insertStatement = "INSERT INTO stations (elevation, location, name, timezone, triplet, wind) values (" + station.getElevation() + ", \'" + station.getLocation()
                + "\', \'" + station.getName() + "\', " + station.getTimezone() + ", \'" + station.getTriplet() + "\', " + station.getWind() + ")";

        int updatedRowCount = psqlService.executeUpdate(insertStatement);

        if(updatedRowCount > 0){
            //TODO: could abstract all this to a response service
            return Response.status(200).entity(reqBody).build();
        }else{
            return Response.status(500).entity("There was an internal server error").build();
        }

    }

}
