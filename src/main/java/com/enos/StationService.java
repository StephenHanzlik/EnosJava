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
        String stationsJson = psqlService.execute("SELECT * FROM stations");

        return stationsJson;
    }

    //Used for importing static list of station json
    @POST
    @Path("/stations")
    @Consumes("application/json")
    public String createStation(Station station) throws ClassNotFoundException {

        PsqlService psqlService = new PsqlService();
        String insertStatement = "INSERT INTO stations (elevation, location, name, timezone, triplet, wind) values (123, 'My Test Locvation', 'My Test Name', 123, '123test:CO:SNTL', false)";
        String stationsJson = psqlService.execute(insertStatement);

        return stationsJson;
    }


}
