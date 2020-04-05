package com.enos;

import com.enos.model.Station;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

//https://mkyong.com/webservices/jax-rs/integrate-jackson-with-resteasy/
//https://mkyong.com/webservices/jax-rs/restful-java-client-with-apache-httpclient/

@Path("/station")
public class StationService {

    @GET
    @Path("/test")
    @Produces("application/json")
    public Station getStation(){

        Station station = new Station();
        station.setName("Eldora");
        station.setElevation(9082);
        station.setWind(false);

        return station;
    }

    //We don't actually want to add stations unless importing the static list of station data
    @POST
    @Path("/test")
    @Consumes("application/json")
    public Response createStation(Station station){

        String result = "Station created: " + station;
        return Response.status(201).entity(result).build();
    }


}
