package com.enos;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//https://www.baeldung.com/resteasy-tutorial

@ApplicationPath("/rest")
public class RestEasyService extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public void RestEasyServices() {
        singletons.add(new StationService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
