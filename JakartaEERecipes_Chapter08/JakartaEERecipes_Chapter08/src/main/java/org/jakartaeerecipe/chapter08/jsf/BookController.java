package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.BookFacade;
import org.jakartaeerecipe.entity.Book;
import org.jakartaeerecipe.entity.BookAuthor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Named(value="bookController")
@SessionScoped
public class BookController implements Serializable {


    @EJB
    BookFacade ejbFacade;

    private List<Book> completeBookList = null;
    private List<Book> completeListNoCache;
    private List<Map> customBookList = null;
    private List<Book> booksByAuthor = null;
    private List<Book> nativeBookList = null;
    private List<Book> namedNativeBookList = null;

    private BookAuthor bookAuthor;

    @Inject
    private AuthorController authorController;

    public BookController(){

    }

    /**
     * @return the completeBookList
     */
    public List<Book> getCompleteBookList() {
        completeBookList = ejbFacade.findAll();
        return completeBookList;
    }

    public void populateCompleteListNoCache(){
        setCompleteListNoCache(ejbFacade.findAllBooks());
    }

    /**
     *
     */
    public List<Map> getCustomBookList(){
        customBookList = ejbFacade.obtainCustomList();
        return customBookList;
    }

    /**
     * @param completeBookList the completeBookList to set
     */
    public void setCompleteBookList(List<Book> completeBookList) {
        this.completeBookList = completeBookList;
    }

    public String populateBookList(BigDecimal bookId){
        String returnValue = authorController.populateAuthorList(bookId);
        return returnValue;
    }

    /**
     * Recipe 9-2
     * @param author
     * @return
     */
    public String findBooksByAuthor(BookAuthor author){
        setBooksByAuthor(ejbFacade.findBooksByAuthor(author));
        System.out.println("books by author: " + author.getLast() + " - " + this.booksByAuthor.size()) ;
        return "/chapter09/recipe9_2b.xhtml";
    }

    /**
     * Returns a book, given a book ID.
     * @param id
     * @return
     */
    public Book findById(BigDecimal id){
        return ejbFacade.findById(id);
    }

    /**
     * @return the booksByAuthor
     */
    public List<Book> getBooksByAuthor() {
        if(booksByAuthor == null){
            booksByAuthor = ejbFacade.findBooksByAuthor(bookAuthor);
        }
        return booksByAuthor;
    }

    /**
     * @param booksByAuthor the booksByAuthor to set
     */
    public void setBooksByAuthor(List<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }

    /**
     * Recipe 10-3a: Using a Native Query
     */

    public List<Book> getNativeBookList() {
        nativeBookList = ejbFacade.obtainNativeList();
        return nativeBookList;
    }

    /**
     * @param nativeBookList the nativeBookList to set
     */
    public void setNativeBookList(List<Book> nativeBookList) {
        this.nativeBookList = nativeBookList;
    }

    /**
     * @return the namedNativeBookList
     */
    public List<Book> getNamedNativeBookList() {
        namedNativeBookList = ejbFacade.obtainNamedNativeList();
        return namedNativeBookList;
    }

    /**
     * @param namedNativeBookList the namedNativeBookList to set
     */
    public void setNamedNativeBookList(List<Book> namedNativeBookList) {
        this.namedNativeBookList = namedNativeBookList;
    }

    /**
     * @return the bookAuthor
     */
    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    /**
     * @param bookAuthor the bookAuthor to set
     */
    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * @return the completeListNoCache
     */
    public List<Book> getCompleteListNoCache() {
        return completeListNoCache;
    }

    /**
     * @param completeListNoCache the completeListNoCache to set
     */
    public void setCompleteListNoCache(List<Book> completeListNoCache) {
        this.completeListNoCache = completeListNoCache;
    }

}
