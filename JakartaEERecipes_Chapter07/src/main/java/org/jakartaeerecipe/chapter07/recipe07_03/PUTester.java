package org.jakartaeerecipe.chapter07.recipe07_03;

import jakarta.persistence.*;
import org.jakartaeerecipe.chapter07.entity.AuthorWork;
import org.jakartaeerecipe.chapter07.entity.AuthorWorkLegacy;
import org.jakartaeerecipe.chapter07.entity.BookAuthor;

import java.util.Iterator;
import java.util.List;

public class PUTester {

    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JakartaEERecipes_LOCAL");
        em = emf.createEntityManager();
        queryAllBookAuthors();
    }

    private static void queryAllBookAuthors() {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createNamedQuery("BookAuthor.findAll");
            List<BookAuthor> authorList = (List<BookAuthor>) query.getResultList();
            for (BookAuthor author : authorList) {
                System.out.print("Name:" + author.getFirstname() + " " + author.getLastname());
                System.out.println();
            }
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void queryAllAuthorWorks() {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
 //           Query query = em.createNamedQuery("AuthorWork.findAll");
            Query query = em.createQuery("select a from AuthorWork a");
            List<AuthorWork> workList = (List<AuthorWork>) query.getResultList();
            for (AuthorWork authorWork : workList) {
                System.out.print("Name:" + authorWork.getAuthorId() + " " + authorWork.getBookId());
                System.out.println();
            }
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBookAuthor() {
        try {
            EntityTransaction entr = em.getTransaction();
            entr.begin();
            BookAuthor author = new BookAuthor();
            author.setFirstname("Joe");
            author.setLastname("Tester");
            author.setBio("An author test account.");
            boolean successful = false;
            try {
                em.persist(author);
                successful = true;
            } finally {
                if (successful){
                    entr.commit();
                } else {
                    entr.rollback();
                }
            }
            Query query = em.createNamedQuery("BookAuthor.findAll");
            List authorList = query.getResultList();
            Iterator authorIterator = authorList.iterator();
            while (authorIterator.hasNext()) {
                author = (BookAuthor) authorIterator.next();
                System.out.print("Name:" + author.getFirstname() + " " + author.getLastname());
                System.out.println();
            }
        } catch (Exception ex){
            ex.printStackTrace();;
        } finally {
            em.close();
        }
    }

    private static void queryAllAuthorWorksLegacy() {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createNamedQuery("AuthorWorkLegacy.findAll");
//            Query query = em.createQuery("select a from AuthorWorkEmbedded a");
            List<AuthorWorkLegacy> workList = (List<AuthorWorkLegacy>) query.getResultList();
            for (AuthorWorkLegacy authorWorkLegacy : workList) {
                System.out.print("Name:" + authorWorkLegacy.getAuthorId() + " " + authorWorkLegacy.getBookId());
                System.out.println();
            }
            transaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
