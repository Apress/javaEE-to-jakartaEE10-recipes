package org.jakartaeerecipe.chapter14.recipe14_03;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.json.*;


import org.jakartaeerecipe.chapter08.session.BookAuthorFacade;
import org.jakartaeerecipe.entity.BookAuthor;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named("jsonController")
@RequestScoped
public class JsonController {

    @EJB
    BookAuthorFacade bookAuthorFacade;

    @PostConstruct
    public void init(){
        buildAuthors();
    }

    public JsonController() {
    }



    private String authorJson;

    public String getAuthorJson() {
        return authorJson;
    }

    public void setAuthorJson(String authorJson) {
        this.authorJson = authorJson;
    }


    public void buildAuthors() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        StringBuilder json = new StringBuilder();
        try (StringWriter sw = new StringWriter();) {
            for (BookAuthor author : authors) {
                System.out.println("author" + author.getLast());
                builder.add("author", Json.createObjectBuilder()
                        .add("authorId", author.getId())
                        .add("first", author.getFirst())
                        .add("last", author.getLast())
                        .add("bio", author.getBio()));
            }
            JsonObject result = builder.build();
            try (JsonWriter writer = Json.createWriter(sw)) {
                writer.writeObject(result);
            }
            json.append(sw.toString());
            authorJson = json.toString();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public JsonObject buildAuthorsJson() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject result = null;
        StringBuilder json = new StringBuilder();
        try (StringWriter sw = new StringWriter();) {
            for (BookAuthor author : authors) {
                System.out.println("author" + author.getLast());
                builder.add("author", Json.createObjectBuilder()
                        .add("authorId", author.getId())
                        .add("first", author.getFirst())
                        .add("last", author.getLast())
                        .add("bio", author.getBio()));

            }
            result = builder.build();


        } catch (IOException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public void writeJson() {
        try {
            JsonObject jsonObject = buildAuthorsJson();

            jakarta.json.JsonWriter jsonWriter = Json.createWriter(new FileWriter("Authors.json"));

            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();


            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "JSON Built",
                    "JSON Built"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public String buildAndReturnAuthors() {
        List<BookAuthor> authors = bookAuthorFacade.findAll();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        StringBuilder json = new StringBuilder();
        JsonObjectBuilder object = null;
        for (BookAuthor author : authors) {
            object = builder.add("authorId", author.getId())
                    .add("first", author.getFirst())
                    .add("last", author.getLast())
                    .add("bio", author.getBio());

        }
        builder.add("author", object);
        JsonObject result = builder.build();
        StringWriter sw = new StringWriter();
        try (JsonWriter writer = Json.createWriter(sw)) {
            writer.writeObject(result);
        }
        json.append(sw.toString());
        return json.toString();
    }

    public String readObject() {
        InputStream in = new ByteArrayInputStream(buildAndReturnAuthors().getBytes());
        // or
        //Reader fileReader = new InputStreamReader(getClass().getResourceAsStream("AuthorObject.json"));
        //JsonReader reader = Json.createReader(fileReader);
        JsonReader reader = Json.createReader(in);
        JsonObject obj = reader.readObject();
        return obj.toString();

    }


//        public void buildAuthors() {
//        List<BookAuthor> authors = new ArrayList<>();
//
//        BookAuthor author1 = new BookAuthor();
//        author1.setId(new BigDecimal(1));
//        author1.setFirst("Josh");
//        author1.setLast("Juneau");
//        author1.setBio("Josh Juneau's Bio");
//        authors.add(author1);
//
//        BookAuthor author2 = new BookAuthor();
//        author2.setId(new BigDecimal(2));
//        author2.setFirst("Tarun");
//        author2.setLast("Telang");
//        author2.setBio("Tarun Telang's Bio");
//        authors.add(author2);
//
//        JsonObjectBuilder builder = Json.createObjectBuilder();
//        StringBuilder json = new StringBuilder();
//        try (StringWriter sw = new StringWriter()) {
//            for (BookAuthor author : authors) {
//                System.out.println("id" + author.getId());
//                builder.add("book", Json.createObjectBuilder()
//                        .add("bookId", author.getId())
//                        .add("first", author.getFirst())
//                        .add("last", author.getLast())
//                        .add("image", author.getBio()));
//            }
//            JsonObject result = builder.build();
//            try (JsonWriter writer = Json.createWriter(sw)) {
//                writer.writeObject(result);
//            }
//            json.append(sw);
//            authorJson = json.toString();
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//
//    }
}
