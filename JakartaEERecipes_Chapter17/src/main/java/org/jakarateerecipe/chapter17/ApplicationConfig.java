package org.jakarateerecipe.chapter17;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.jakarateerecipe.chapter17.service.AcmePoolsService;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AcmePoolsService.class);
    }
}
