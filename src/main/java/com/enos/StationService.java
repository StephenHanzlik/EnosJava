package com.enos;

import com.enos.model.Station;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


//https://www.baeldung.com/jackson-object-mapper-tutorial
//http://tutorials.jenkov.com/java-json/jackson-objectmapper.html#write-json-from-objects
@Path("/snotel")
public class StationService {

    @GET
    @Path("/stations")
    @Produces("application/json")
    public Response getStation() throws ClassNotFoundException {

        PsqlService psqlService = new PsqlService();
        String reqBody = psqlService.executeQuery("SELECT * FROM stations");

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(reqBody).build();
    }

    @GET
    @Path("/stations/{stationTriplet}")
    @Produces("application/json")
    public Response getStation(
            @PathParam("stationTriplet")
                    String stationTriplet
            ) throws ClassNotFoundException {

        PsqlService psqlService = new PsqlService();
        String reqBody = psqlService.executeQuery("SELECT * FROM stations where triplet="+ "'"+ stationTriplet + "'");

        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(reqBody).build();
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
            //https://stackoverflow.com/questions/23450494/how-to-enable-cross-domain-requests-on-jax-rs-web-services
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                    .header("Access-Control-Max-Age", "1209600")

        }else{
            return Response.status(500).entity("There was an internal server error").build();
        }

    }

    //********************************************************************
    //Testing with cors and headers
    //********************************************************************
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response getMemberList() {
//        List<Member> memberList = memberDao.listMembers();
//        members.addAll(memberList);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity("a string")
                .build();
    }

}
