package org.jakartaeerecipe.chapter08.session;

import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jakartaeerecipe.entity.Book;
import org.jakartaeerecipe.entity.Chapter;

import java.math.BigDecimal;
import java.util.List;

@WebService
@Stateless
public class ChapterFacade extends AbstractFacade<Chapter> {
    @PersistenceContext(unitName = "JakartaEERecipesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChapterFacade() {
        super(Chapter.class);
    }

    @WebMethod
    public List<Book> findBookByChapterTitle(Chapter chapter){
        return em.createQuery("select b from Book b INNER JOIN b.chapters c " +
                        "where c.title = :title")
                .setParameter("title", chapter.getTitle())
                .getResultList();
    }

    public List<Book> findAllBooksByChapterNumber(BigDecimal chapterNumber){
        return em.createQuery("select b from Book b LEFT OUTER JOIN b.chapters c " +
                        "where c.chapterNumber = :num")
                .setParameter("num", chapterNumber)
                .getResultList();
    }

}
