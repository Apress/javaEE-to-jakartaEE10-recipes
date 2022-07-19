package org.jakartaeerecipe.chapter08.local;

import java.util.List;
import jakarta.ejb.Local;
import org.jakartaeerecipe.entity.BookAuthor;

/**
 * Local business interface for the BookAuthorFacade stateless session bean
 * @author juneau
 */
// Comment this annotation to decorate the EJB itself with the annotation
@Local
public interface BookAuthorType {
    public List<BookAuthor> findAuthor();

    public List<BookAuthor> findAuthorByLast(String last);
}
