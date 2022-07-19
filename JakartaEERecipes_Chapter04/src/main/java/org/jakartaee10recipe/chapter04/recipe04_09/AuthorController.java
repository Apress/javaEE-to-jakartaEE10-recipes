package org.jakartaee10recipe.chapter04.recipe04_09;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.jakartaee10recipe.chapter04.recipe04_06.Book;
import org.jakartaee10recipe.chapter04.recipe04_06.Chapter;

@SessionScoped
@Named(value = "ch4AuthorController")
public class AuthorController implements Serializable {

    private List<Author> authorBookList = null;
    private List<Author> authorList = null;
    private List<Author> completeAuthorList = null;
    private String storeName = "Acme Bookstore";

    private final String juneauBio =
            "Josh Juneau has been developing software"
                    + " since the mid-1990s. PL/SQL development and database programming"
                    + " was the focus of his career in the beginning, but as his skills developed,"
                    + " he began to use Java and later shifted to it as a primary base for his"
                    + " application development. Josh has worked with Java in the form of graphical"
                    + " user interface, web, and command-line programming for several years. "
                    + "During his tenure as a Java developer, he has worked with many frameworks"
                    + " such as JSF, EJB, and JBoss Seam. At the same time, Josh has extended his"
                    + " knowledge of the Java Virtual Machine (JVM) by learning and developing applications"
                    + " with other JVM languages such as Jython and Groovy. His interest in learning"
                    + " new languages that run on the JVM led to his interest in Jython. Since 2006,"
                    + " Josh has been the editor and publisher for the Jython Monthly newsletter. "
                    + "In late 2008, he began a podcast dedicated to the Jython programming language.";
    private final String telangBio = "This is Tarun Telang's Bio";
    private Author current;
    private String authorLast;

    private Book currentBook;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {

    }

    @PostConstruct
    public void init(){
        authorLast = null;
        populateAuthors();
        populateJakartaFundamentalsAuthorList();
        populateJavaEERecipesAuthorList();
        populateCompleteAuthorList();
    }

    private void populateAuthors(){
        authorBookList = new ArrayList<>();
        Book book1 = new Book("Introducing Jakarta EE Fundamentals", "jakartaeefundamentals.jpeg");

        // Chapters used for Recipe 4-10
        book1 = addChapters1(book1);

        Book book2 = new Book("Java EE to Jakarta EE 10 Recipes", "book.png");

        Author author1 = new Author("Josh", "Juneau", juneauBio);
        author1.addBook(book2);
        authorBookList.add(author1);

        Author author2 = new Author("Tarun", "Telang", telangBio);
        author2.addBook(book1);
        author2.addBook(book2);
        authorBookList.add(author2);
    }

    /**
     * Utility method for adding chapters...not very clean.  This will be
     * replaced with database tables/logic in future chapters
     * @param book
     * @return
     */
    private Book addChapters1(Book book){
        Chapter chapter1 = new Chapter(1, "What is Jakarta EE", null);
        Chapter chapter2 = new Chapter(2, "History", null);
        Chapter chapter3 = new Chapter(3, "The Need for Jakarta EE and its Guiding Principles", null);
        Chapter chapter4 = new Chapter(4, "Specifications", null);
        Chapter chapter5 = new Chapter(5, "Specifications Overview", null);
        Chapter chapter6 = new Chapter(6, "Testing Compatibility Kits (TCKs)", null);
        Chapter chapter7 = new Chapter(7, "Compatible Implementations", null);

        List <Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter1);
        chapterList.add(chapter2);
        chapterList.add(chapter3);
        chapterList.add(chapter4);
        chapterList.add(chapter5);
        chapterList.add(chapter6);
        chapterList.add(chapter7);
        book.setChapters(chapterList);
        return book;
    }

    /**
     * Utility method for adding chapters...not very clean.  This will be
     * replaced with database tables/logic in future chapters
     * @param book
     * @return
     */
    private Book addChapters2(Book book){
        Chapter chapter1 = new Chapter(1, "Jakarta Servlets", null);
        Chapter chapter2 = new Chapter(2, "Jakarta Server Pages", null);
        Chapter chapter3 = new Chapter(3, "Jakarta Server Faces", null);
        Chapter chapter4 = new Chapter(4, "Facelets", null);
        Chapter chapter5 = new Chapter(5, "Jakarta Server Faces Components", null);
        Chapter chapter6 = new Chapter(6, "Advanced Jakarta Server Faces and AJAX", null);
        Chapter chapter7 = new Chapter(7, "Databases (JDBC)", null);
        Chapter chapter8 = new Chapter(8, "Jakarta Persistence", null);
        Chapter chapter9 = new Chapter(9, "Jakarta Enterprise Beans", null);
        Chapter chapter10 = new Chapter(10, "Context and Dependency Injection", null);

        List <Chapter> chapterList = new ArrayList();
        chapterList.add(chapter1);
        chapterList.add(chapter2);
        chapterList.add(chapter3);
        chapterList.add(chapter4);
        chapterList.add(chapter5);
        chapterList.add(chapter6);
        chapterList.add(chapter7);
        chapterList.add(chapter8);
        chapterList.add(chapter9);
        chapterList.add(chapter10);
        book.setChapters(chapterList);
        return book;

    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java 7 Recipes book
     * @return
     */
    private String populateJakartaFundamentalsAuthorList() {
        authorList = new ArrayList();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Introducing Jakarta EE Fundamentals")){
                    // Set the currently selected book
                    setCurrentBook(book);
                    authorList.add(author);
                }
            }
        }

        return "book";
    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java EE 7 Recipes book
     * @return
     */
    private String populateJavaEERecipesAuthorList() {
        authorList = new ArrayList();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java EE to Jakarta EE 10 Recipes")){
                    // Set the currently selected book
                    setCurrentBook(book);
                    authorList.add(author);
                }
            }
        }
        return "book";

    }

    /**
     * Populates completeAuthorList with each existing Author object
     * @return
     */
    private String populateCompleteAuthorList() {
        completeAuthorList = new ArrayList();
        for(Author author:authorBookList){
            completeAuthorList.add(author);
        }
        currentBook = null;
        return "book";
    }

    public String displayAuthor(String last) {
        for (Author author : authorList) {
            if (author.getLast().equals(last)) {
                current = author;
            }
        }
        return "bio";
    }

    /**
     * @return the authorList
     */
    public List getAuthorList() {
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
     * @return the authorLast
     */
    public String getAuthorLast() {
        return authorLast;
    }

    /**
     * @param authorLast the authorLast to set
     */
    public void setAuthorLast(String authorLast) {
        displayAuthor(authorLast);
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

    /**
     * @return the currentBook
     */
    public Book getCurrentBook() {
        return currentBook;
    }

    /**
     * @param currentBook the currentBook to set
     */
    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }
}
