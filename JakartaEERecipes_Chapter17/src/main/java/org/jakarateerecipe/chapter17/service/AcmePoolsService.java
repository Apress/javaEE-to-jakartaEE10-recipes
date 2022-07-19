package org.jakarateerecipe.chapter17.service;

import com.mongodb.MongoWriteException;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import jakarta.inject.Inject;
import jakarta.nosql.document.Document;
import jakarta.nosql.document.DocumentDeleteQuery;
import jakarta.nosql.document.DocumentEntity;
import jakarta.nosql.document.DocumentQuery;

import jakarta.nosql.mapping.document.DocumentTemplate;

import jakarta.ws.rs.*;

import org.jakarateerecipe.chapter17.DocumentCollectionManagerProducer;
import org.jakarateerecipe.chapter17.entity.Customer;
import org.jakarateerecipe.chapter17.entity.Pool;

import java.util.Random;
import java.util.stream.Stream;

import static jakarta.nosql.document.DocumentDeleteQuery.delete;
import static jakarta.nosql.document.DocumentQuery.select;

@Path("acmepools")
public class AcmePoolsService {

    @Inject
    DocumentCollectionManagerProducer manager;

    @Inject
    DocumentTemplate documentTemplate;

    @GET
    @Path("testDocumentDb")
    public String testDocumentDb() {

        Random random = new Random();
        Long id = random.nextLong();
        StringBuilder builder = new StringBuilder("");
        Customer customer = new Customer("Josh", "Juneau", "123 AcmeWay", "JavaLand", "JJ", "12345");

        Pool pool = new Pool(id, 32.0, 16.0, customer);

        Pool savedPool = documentTemplate.insert(pool);

        String returnValue = "Pool length: " + savedPool.getLength() + " - Customer: " + savedPool.getCustomer().getLastName();

        DocumentQuery query = select().from("Pool").where("length").eq(32.0).build();

        Stream<Pool> pools = documentTemplate.select(query);
        pools.forEach(p -> builder.append(p + " "));
        System.out.println(builder.toString());

        return returnValue;
    }

    /**
     * This method is being shown merely for example, as it will not run within
     * an enterprise environment.  This code is to be run within a Java SE environment.
     */
//    @GET
//    @Path("seContainerExample")
//    public static void seContainerExample(){
//
//        try (SeContainer se = SeContainerInitializer.newInstance().initialize()){
//            DocumentTemplate documentTemplate = se.select(DocumentTemplate.class).get();
//            DocumentQuery query = select().from("Pool").build();
//
//            Stream<Pool> pools = documentTemplate.select(query);
//            pools.forEach(p -> System.out.print("Pool: " + p));
//        }
//    }


//    @GET
//    @Path("retrieveAllRecords")
//    public List testDocumentDbAll() {
//        DocumentQuery query = select().from("Pool").build();
//        Stream<Pool> entities = documentTemplate.select(query);
//        e.forEach(p -> System.out.print("Pool: " + p));
//        return entities;
//    }

    @POST
    @Path("createNewDocument/{id}")
    public void createNewDocument(@PathParam("id") int id) {
        System.out.println("Insert new document with ID: " + id);
        if (id > 0) {
            // Utilize DocumentEntity to create a document
            try {
                DocumentEntity documentEntity = DocumentEntity.of("Pool");
                documentEntity.add(Document.of("_id", id));
                documentEntity.add(Document.of("length", 30.0));
                documentEntity.add(Document.of("width", 15.0));
                DocumentEntity saved = manager.getManager().insert(documentEntity);
                //Update Document
                saved.add(Document.of("Customer", "Juneau"));
                DocumentEntity updated = manager.getManager().update(documentEntity);
            } catch (MongoWriteException e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.println("You cannot insert a NULL entity, please provide an id");
        }
    }

    @DELETE
    @Path("testDeteleDocument")
    public void deleteDocument(){
        DocumentDeleteQuery delete = delete().from("Pools").where("length").gte(36.0).build();
        manager.getManager().delete(delete);
    }
}
