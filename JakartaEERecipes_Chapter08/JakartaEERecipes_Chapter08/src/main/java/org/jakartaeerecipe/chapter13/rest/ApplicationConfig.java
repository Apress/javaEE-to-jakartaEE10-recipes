package org.jakartaeerecipe.chapter13.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;
@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.jakartaeerecipe.chapter13.rest.SimpleRest.class);
        resources.add(org.jakartaeerecipe.resources.JakartaEEResource.class);
        resources.add(org.jakartaeerecipe.chapter13.rest.service.EmployeeFacadeREST.class);
    }
}