package org.jakartaeerecipe.chapter08.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("groovyController")
@SessionScoped
class GroovyController implements Serializable {

    def getGroovyBanner(){
        "Hello, this is Groovy"
    }

}
