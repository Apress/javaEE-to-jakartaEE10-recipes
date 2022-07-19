package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.AuthorWorkFacade;
import org.jakartaeerecipe.chapter08.session.BookAuthorFacade;
import org.jakartaeerecipe.chapter08.session.BookFacade;
import org.jakartaeerecipe.entity.AuthorWork;
import org.jakartaeerecipe.entity.Book;
import org.jakartaeerecipe.entity.BookAuthor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named(value="authorController")
@SessionScoped
public class AuthorController implements Serializable {

    @EJB
    private BookAuthorFacade ejbFacade;

    @EJB
    private BookFacade bookFacade;

    @EJB
    private AuthorWorkFacade authorWorkFacade;

    private List<BookAuthor> authorBookList = null;
    private List<BookAuthor> authorList = null;
    private List<BookAuthor> completeAuthorList = null;
    // Recipe 10-1
    private List<BookAuthor> allAuthors = null;
    // recipe 10-5
    private List<Map>authorBooks = null;
    private String storeName = "Acme Bookstore";
    private BookAuthor current;
    private String authorLast;
    private Book currentBook;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        System.out.println("Author Controller Created");
        authorLast = null;
        // Query database for all authors
        authorBookList = null;
    }

    /**
     * Searches through all Author objects and populates the authorList with
     * only those authors who were involved with the specified book
     *
     * @return
     */
    public String populateAuthorList(BigDecimal bookId) {
        authorList = new ArrayList();
        if (getAuthorBookList() == null) {
            setAuthorBookList((List<BookAuthor>) ejbFacade.findAll());
        }

        List<AuthorWork> awList = authorWorkFacade.performFind(bookId);
        for (AuthorWork work : awList) {


            BookAuthor foundAuthor = ejbFacade.findByAuthorId(work.getAuthorId().getId());
            // Set the currently selected book

            authorList.add(foundAuthor);
        }

        setCurrentBook(bookFacade.find(bookId));
        return "/chapter08/book";
    }

    /**
     * Populates completeAuthorList with each existing Author object
     *
     * @return
     */
    private String populateCompleteAuthorList() {
        completeAuthorList = new ArrayList();
        for (BookAuthor author : getAuthorBookList()) {
            completeAuthorList.add(author);
        }
        currentBook = null;
        return "/chapter08/book";
    }

    public String displayAuthor(String last) {
        if(authorList == null){
            authorList = ejbFacade.findAll();
        }
        List<AuthorWork> awList = null;

        boolean foundFlag = false;
        for (BookAuthor author : authorList) {

            if (author.getLast().equals(last)) {
                // Find author's books and populate book list
                awList = authorWorkFacade.performFindByAuthor(author);
                List <Book> authorBooks = new ArrayList();
                for(AuthorWork authWork:awList){
                    Book newbook = bookFacade.find(authWork.getBookId());

                    authorBooks.add(newbook);
                }

                setCurrent(author);
            }
        }

        return "/chapter08/bio";
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
    public BookAuthor getCurrent() {
        System.out.println("Current author is:  "+ current.getFirst());
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(BookAuthor current) {
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
        this.authorLast = authorLast;
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
    public List<BookAuthor> getCompleteAuthorList() {
        return completeAuthorList;
    }

    /**
     * @param completeAuthorList the completeAuthorList to set
     */
    public void setCompleteAuthorList(List<BookAuthor> completeAuthorList) {
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

    public void findAuthor() {
        if (this.authorLast != null) {
            for (BookAuthor author : authorList) {
                if (author.getLast().equals(authorLast)) {
                    this.current = author;
                }
            }
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null,
                    new FacesMessage("No last name specified."));

        }
    }

    /**
     * Auto-completes author names from the authorBookList
     *
     * @param text
     * @return
     */
    public List<String> complete(String text) {
        List<String> results = new ArrayList();
        // This should print each time you type a letter in the autocomplete box
        System.out.println("completing: " + text);
        for (BookAuthor author : getAuthorBookList()) {
            if (author.getLast().toUpperCase().contains(text.toUpperCase())) {
                results.add(author.getLast().toUpperCase() + " " + author.getFirst().toUpperCase());
            }
        }

        return results;
    }

    public List<BookAuthor> getAuthorBookList(){
        return ejbFacade.getAuthorBookList();
    }

    /**
     * @param authorBookList the authorBookList to set
     */
    public void setAuthorBookList(List<BookAuthor> authorBookList) {
        this.authorBookList = authorBookList;
    }

    /**
     * Recipe 9-1: Querying all objects of an entity
     * @return
     */

    public String populateAllAuthors(){
        allAuthors = ejbFacade.findAuthor();
        return "/chapter09/recipe9_1.xhtml";
    }


    public List<BookAuthor> getAllAuthors() {
        System.out.println("Invoking all authors");
        if(allAuthors == null){
            populateAllAuthors();
        }
        return allAuthors;
    }

    /**
     * @param allAuthors the allAuthors to set
     */
    public void setAllAuthors(List<BookAuthor> allAuthors) {
        this.allAuthors = allAuthors;
    }

    /**
     * Recipe 9-5a
     */
    public String findAuthorBooks(){
        System.out.println("here....");
        authorBooks = ejbFacade.findAuthorBooksMapping();
        return "/chapter09/recipe9_5a.xhtml";
    }

    /**
     * Recipe 9-5b
     */
    public String findAuthorBooksWithoutMapping(){
        authorBooks = ejbFacade.findAuthorBooks();
        return "/chapter09/recipe9_5b.xhtml";
    }

    /**
     * @return the authorBooks
     */
    public List<Map> getAuthorBooks() {
        return authorBooks;
    }

    /**
     * @param authorBooks the authorBooks to set
     */
    public void setAuthorBooks(List<Map> authorBooks) {
        this.authorBooks = authorBooks;
    }


    /**
     * Recipe 9-10
     */
    public String findAuthorByLast(){
        System.out.println("authorlast: " + authorLast);
        authorList = ejbFacade.findAuthorByLast(authorLast);
        return "/chapter09/recipe9_10b.xhtml";
    }
}
