package org.jakartaeerecipe.chapter08.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.jakartaeerecipe.entity.BookAuthor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class BookAuthorFacade extends AbstractFacade<BookAuthor> {
    @PersistenceContext(unitName = "JakartaEERecipesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookAuthorFacade() {
        super(BookAuthor.class);
    }


    /**
     * Recipe 9-1
     * @return
     */
    public List<BookAuthor> findAuthor(){
        return em.createQuery("select o from BookAuthor o")
                .getResultList();
    }

    /**
     * Recipe 9-10
     *
     */
    public List<BookAuthor> findAuthorByLast(String authorLast){
        return em.createQuery("select o from BookAuthor o " +
                        "where UPPER(o.last) = UPPER(:authorLast)")
                .setParameter("authorLast", authorLast).getResultList();
    }

    /**
     * Return BookAuthor object given a specified authorId
     * @param authorId
     * @return
     */
    public BookAuthor findByAuthorId(BigDecimal authorId){
        return (BookAuthor) em.createQuery("select object(o) from BookAuthor o " +
                        "where o.id = :id")
                .setParameter("id", authorId)
                .getSingleResult();
    }

    /**
     * Recipe 9-5 Using SqlResultSetMapping
     * @return
     */
    public List findAuthorBooksMapping(){
        Query qry = em.createNativeQuery(
                "select b.id as BOOK_ID, b.title as TITLE, " +
                        "ba.id AS AUTHOR_ID, ba.firstname as FIRSTNAME, ba.lastname as LASTNAME " +
                        "from book_author ba, book b, author_work aw " +
                        "where aw.author_id = ba.id " +
                        "and b.id = aw.book_id", "authorBooks");

        return  qry.getResultList();
    }

    /**
     * 10-5 Without SqlResultSetMapping
     * @return
     */
    public List<Map> findAuthorBooks(){

        Query qry = em.createNativeQuery(
                "select ba.id, ba.lastname, ba.firstname, ba.bio, b.id, b.title, b.image, b.description " +
                        "from book_author ba, book b, author_work aw " +
                        "where aw.author_id = ba.id " +
                        "and b.id = aw.book_id");

        List<Object[]> results = qry.getResultList();
        List data = new ArrayList<HashMap>();

        if (!results.isEmpty()) {
            for (Object[] result : results) {
                HashMap resultMap = new HashMap();
                resultMap.put("authorId", result[0]);
                resultMap.put("authorLast", result[1]);
                resultMap.put("authorFirst", result[2]);
                resultMap.put("authorBio", result[3]);
                resultMap.put("bookId", result[4]);
                resultMap.put("bookTitle", result[5]);
                resultMap.put("bookImage", result[6]);
                resultMap.put("bookDescription", result[7]);


                data.add(resultMap);
            }

        }
        return data;
    }
    /**
     public void createUser(String username, String password){
     StoredProcedureQuery qry = em.createStoredProcedureQuery("createUser")
     .setParameter("USER", username)
     .setParameter("PASS",password);

     try {
     qry.executeUpdate();
     } catch (Exception ex) {
     System.out.println(ex);
     }
     }
     * */
    /**
     * @return the authorBookList
     */
    public List<BookAuthor> getAuthorBookList() {

        List<BookAuthor> authorBookList = em.createNamedQuery("BookAuthor.findAll").getResultList();

        return authorBookList;
    }
}
