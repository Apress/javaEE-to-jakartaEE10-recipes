package org.jakartaeerecipe.chapter08.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.jakartaeerecipe.entity.AuthorWork;
import org.jakartaeerecipe.entity.BookAuthor;
import org.jakartaeerecipe.entity.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class AuthorWorkFacade extends AbstractFacade<AuthorWork>  {
    @PersistenceContext(unitName = "JakartaEERecipesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorWorkFacade() {
        super(AuthorWork.class);
    }

    /**
     * Return list of AuthorWork objects given a specified book id
     * @param bookId
     * @return
     */

    public List<AuthorWork> performFind(BigDecimal bookId){
        Query qry = em.createQuery("select object(o) from AuthorWork o " +
                        "where o.bookId = :bookId")
                .setParameter("bookId", bookId);

        return qry.getResultList();

    }

    /**
     * Return list of AuthorWork objects given a specified author id
     * @param authorId
     * @return
     */

    public List<AuthorWork> performFindByAuthor(BookAuthor authorId){
        Query qry = em.createQuery("select object(o) from AuthorWork o " +
                        "where o.authorId = :authorId")
                .setParameter("authorId", authorId);

        return qry.getResultList();

    }

    public List<AuthorWork> performFindByAuthorStream(BookAuthor authorId){
        Stream<AuthorWork> awStream = em.createQuery("select object(o) from AuthorWork o")
                .getResultStream();

        return awStream
                .filter(ba -> authorId.equals(ba.getAuthorId()))
                .collect(Collectors.toList());

    }

    /*
     *
     */

    public Long findAuthorCount(Book book){
        Query qry = em.createQuery("select COUNT(o.authorId) from AuthorWork o " +
                        "where o.bookId = :book")
                .setParameter("book", book.getId());
        return (Long) qry.getSingleResult();
    }
}
