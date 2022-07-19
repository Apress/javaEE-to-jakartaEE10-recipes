package org.jakartaeerecipe.chapter06.recipe06_06;

import org.jakartaeerecipe.chapter06.entity.Author;
import org.jakartaeerecipe.chapter06.CreateConnection;

import java.sql.*;

public class AuthorDAOStandard {

    public AuthorDAOStandard() {
    }

    public void queryBookAuthor() {
        String qry = "select ID, FIRSTNAME, LASTNAME, BIO from book_author";
        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(qry)) {

            while (rs.next()) {
                int author_id = rs.getInt("ID");
                String first_name = rs.getString("FIRSTNAME");
                String last_name = rs.getString("LASTNAME");
                String bio = rs.getString("BIO");
                System.out.println(author_id + "\t" + first_name
                        + " " + last_name + "\t" + bio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Do not use this method in production, instead make use of
     * PreparedStatements
     *
     * @param first
     * @param last
     * @param bio
     */
    private void performCreate(String first, String last, String bio) {
        String sql = "INSERT INTO BOOK_AUTHOR VALUES("
                + "next value for BOOK_AUTHOR_S, "
                + "'" + last + "', "
                + "'" + first + "', "
                + "'" + bio + "')";
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Returns row-count or 0 if not successful
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("-- Record created --");
            } else {
                System.out.println("!! Record NOT Created !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performUpdate(String first, String last, String bio) {
        String sql = "UPDATE BOOK_AUTHOR "
                + "SET bio = '" + bio + "' "
                + "WHERE LASTNAME = '" + last + "' "
                + "AND FIRSTNAME = '" + first + "'";
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("-- Record Updated --");

            } else {
                System.out.println("!! Record NOT Updated !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performDelete(String first, String last) {
        String sql = "DELETE FROM BOOK_AUTHOR WHERE LASTNAME = '" + last + "' "
                + "AND FIRSTNAME = '" + first + "'";
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("-- Record Deleted --");
            } else {
                System.out.println("!! Record NOT Deleted!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Facade method for inserting Author objects into the BOOK_AUTHOR table
     *
     * @param author
     */
    public void insert(Author author) {
        performCreate(author.getFirst(), author.getLast(), author.getBio());
    }

    /**
     * Facade method for updating Author objects in the BOOK_AUTHOR table
     *
     * @param author
     */
    public void update(Author author) {
        this.performUpdate(author.getFirst(), author.getLast(), author.getBio());
    }

    /**
     * Facade method for deleting Author objects from the BOOK_AUTHOR table
     *
     * @param author
     */
    public void delete(Author author) {
        performDelete(author.getFirst(), author.getLast());
    }

    public static void main(String[] args) {
        AuthorDAOStandard authorDao = new AuthorDAOStandard();
        authorDao.queryBookAuthor();
        authorDao.performCreate("Nikita", "Goswami", "N/A");
        authorDao.performUpdate("Nikita", "Goswami", "Nikita’s Bio");
        authorDao.queryBookAuthor();
        authorDao.performDelete("Nikita", "Goswami");
    }
}
