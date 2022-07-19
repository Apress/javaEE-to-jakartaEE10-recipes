package org.jakartaeerecipe.chapter08.local;

import java.math.BigDecimal;
import java.util.List;
import jakarta.ejb.Local;

import org.jakartaeerecipe.entity.AuthorWork;
import org.jakartaeerecipe.entity.AuthorWork;
import org.jakartaeerecipe.entity.Book;
import org.jakartaeerecipe.entity.BookAuthor;

/**
 * Local business interface for the AuthorWorkFacade stateless session bean
 * @author juneau
 */
// Comment this annotation to decorate the EJB itself with the annotation
@Local
public interface AuthorWorkType {
    public List<AuthorWork> performFind(BigDecimal bookId);

    public List<AuthorWork> performFindByAuthor(BookAuthor authorId);

    public Long findAuthorCount(Book book);
}
