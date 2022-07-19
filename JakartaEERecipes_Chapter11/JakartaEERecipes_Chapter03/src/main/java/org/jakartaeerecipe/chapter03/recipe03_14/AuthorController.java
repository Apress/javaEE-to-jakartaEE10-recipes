package org.jakartaeerecipe.chapter03.recipe03_14;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named(value = "faceletsAuthorController")
@SessionScoped
public class AuthorController implements Serializable {
    private List<Author> authorBookList;
    private List<Author> completeAuthorList;
    private List<Author> authorList;
    private String storeName = "Acme Bookstore";
    private Author current;

    /**
     * Methods that are annotated with @PostConstruct are invoked when the
     * controller class is created.
     */
    @PostConstruct
    public void init(){
        authorList = new ArrayList<>();
        populateAuthors();
        populateJakartaFundamentalsAuthorList();
    }

    private void populateAuthors(){
        authorBookList = new ArrayList<>();

        Book book1 = addChapters1();
        Book book2 = addChapters2();

        final String juneauBio = "This is Josh Juneau's Bio";
        Author author1 = new Author("Josh", "Juneau", juneauBio);

        author1.addBook(book2);
        author1.addBook(book1);
        authorBookList.add(author1);

        final String telangBio = "This is Tarun Telang's Bio";
        Author author2 = new Author("Tarun", "Telang", telangBio);
        author2.addBook(book1);
        author2.addBook(book2);
        authorBookList.add(author2);
    }

    /**
     * Utility method for adding chapters. This will be
     * replaced with database tables/logic in future chapters
     *
     * @return Book object with chapters populated
     */
    private Book addChapters1(){

        ArrayList<Chapter> chapterList = new ArrayList<>();
//
//        chapterList.add(new Chapter(1, "Getting Started with Java 7", null));
//        chapterList.add(new Chapter(2, "Strings", null));
//        chapterList.add(new Chapter(3, "Numbers and Dates", null));
//        chapterList.add(new Chapter(4, "Data Structures, Conditionals, and Iteration", null));
//        chapterList.add(new Chapter(5, "Input and Output", null));
//        chapterList.add(new Chapter(6, "Exceptions, Logging, and Debugging", null));
//        chapterList.add(new Chapter(7, "Object Oriented Java", null));
//        chapterList.add(new Chapter(8, "Concurrency", null));
//        chapterList.add(new Chapter(9, "Debugging and Unit Testing", null));
//        chapterList.add(new Chapter(10, "Unicode, Internationalization, and Currency Codes", null));
//        chapterList.add(new Chapter(11, "Working with Databases (JDBC)", null));
//        chapterList.add(new Chapter(12, "Java 2D Graphics and Media", null));
//        chapterList.add(new Chapter(13, "Java 3D", null));
//        chapterList.add(new Chapter(14, "Swing API", null));
//        chapterList.add(new Chapter(15, "JavaFX Fundamentals", null));
//        chapterList.add(new Chapter(16, "Graphics with JavaFX", null));
//        chapterList.add(new Chapter(17, "Media with JavaFX", null));
//        chapterList.add(new Chapter(18, "Working with Servlets", null));
//        chapterList.add(new Chapter(19, "Applets", null));
//        chapterList.add(new Chapter(20, "JavaFX on the Web", null));
//        chapterList.add(new Chapter(21, "Email", null));
//        chapterList.add(new Chapter(22, "XML and Web Services", null));
//        chapterList.add(new Chapter(23, "Networking", null));

        return new Book("Introducing Jakarta EE Fundamentals", "jakartaeefundamentals.jpeg", chapterList);
    }

    /**
     * Utility method for adding chapters...not very clean.  This will be
     * replaced with database tables/logic in future chapters
     * @return Book object with chapters populated
     */
    private Book addChapters2(){
        ArrayList<Chapter> chapterList = new ArrayList<>();

//        chapterList.add(new Chapter(1, "Working with Servlets", null));
//        chapterList.add(new Chapter(2, "JavaServer Pages", null));
//        chapterList.add(new Chapter(3, "The Basics of JavaServer Faces", null));
//        chapterList.add(new Chapter(4, "Facelets", null));
//        chapterList.add(new Chapter(5, "JavaServer Faces Components", null));
//        chapterList.add(new Chapter(6, "Advanced JSF and AJAX", null));
//        chapterList.add(new Chapter(7, "Databases (JDBC)", null));
//        chapterList.add(new Chapter(8, "Object Relational Mapping with Java EE", null));
//        chapterList.add(new Chapter(9, "Java EE and Enterprise Java Beans", null));
//        chapterList.add(new Chapter(10, "EJB Query Language", null));
//        chapterList.add(new Chapter(11, "Oracle's Glassfish", null));
//        chapterList.add(new Chapter(12, "Context and Dependency Injection", null));
//        chapterList.add(new Chapter(13, "Java Message Service", null));
//        chapterList.add(new Chapter(14, "Authentication and Security", null));
//        chapterList.add(new Chapter(15, "Java Web Services", null));
//        chapterList.add(new Chapter(16, "Dynamic Language Web Solutions", null));
//        chapterList.add(new Chapter(17, "Deploying Applications to the Cloud", null));
//        chapterList.add(new Chapter(18, "JavaFX in the Enterprise", null));
//        chapterList.add(new Chapter(19, "Writing JavaFX with a Different Language", null));

        return new Book("Jakarta EE 10 Recipes", "jakartaeerecipes.png", chapterList);
    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java 9 Recipes book
     * @return the filename of page containing the details
     */
    public String populateJakartaFundamentalsAuthorList() {
        authorList = new ArrayList<>();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Introducing Jakarta EE Fundamentals")){
                    authorList.add(author);
                }}
        }
        return "recipe03_14a";
    }
    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Jakarta EE 10 Recipes book
     * @return the filename of page containing the details
     */
    public String populateJakartaEERecipesAuthorList() {
        authorList = new ArrayList<>();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java EE to Jakarta EE 10 Recipes")){
                    authorList.add(author);
                }
            }
        }
        return "recipe03_14b";
    }
    /**
     * Populates completeAuthorList with each existing Author object
     */
    private void populateCompleteAuthorList() {
        completeAuthorList = new ArrayList<>();
        completeAuthorList.addAll(authorBookList);
    }

    public String displayAuthor(String last) {
        for (Author author : authorList) {
            if (author.getLast().equals(last)) current = author;
        }
        return "recipe03_14c";
    }
    /**
     * @return the authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }
    /**
     * @return the current
     */
    public Author getCurrent() {
        return current;
    }
    /**
     * @param current the current to set
     */
    public void setCurrent(Author current) {
        this.current = current;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }
    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    /**
     * @return the completeAuthorList
     */
    public List<Author> getCompleteAuthorList() {
        return completeAuthorList;
    }
    /**
     * @param completeAuthorList the completeAuthorList to set
     */
    public void setCompleteAuthorList(List<Author> completeAuthorList) {
        this.completeAuthorList = completeAuthorList;
    }
}




