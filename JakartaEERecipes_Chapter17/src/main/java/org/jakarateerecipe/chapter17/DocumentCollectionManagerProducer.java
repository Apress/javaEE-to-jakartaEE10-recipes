package org.jakarateerecipe.chapter17;


import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;
import jakarta.nosql.document.DocumentDeleteQuery;
import jakarta.nosql.document.DocumentEntity;
import org.eclipse.jnosql.communication.mongodb.document.MongoDBDocumentConfiguration;

import java.util.Collections;
import java.util.Map;

@RequestScoped
public class DocumentCollectionManagerProducer {

    // setting the name of the database
    private static final String COLLECTION = "acmepools";

    private DocumentConfiguration configuration;

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new MongoDBDocumentConfiguration();
        Map<String, Object> settings = Collections.singletonMap("mongodb-server-host-1", "localhost:27017");
        managerFactory = configuration.get(Settings.of(settings));
    }

    @Produces
    public DocumentCollectionManagerProducer getManager() {
        return managerFactory.get(COLLECTION);
    }

    public DocumentEntity insert(DocumentEntity documentEntity) {
        return null;
    }

    public DocumentEntity update(DocumentEntity saved) {
        return null;
    }

    public void delete(DocumentDeleteQuery delete) {

    }
}
