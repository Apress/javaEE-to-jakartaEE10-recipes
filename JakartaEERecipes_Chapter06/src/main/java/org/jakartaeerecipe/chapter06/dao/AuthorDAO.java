package org.jakartaeerecipe.chapter06.dao;

import org.jakartaeerecipe.chapter06.entity.Author;
import org.jakartaeerecipe.chapter06.CreateConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public AuthorDAO() {
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

    public List<Author> obtainCompleteAuthorList() {
        String qry = "select id, FIRSTNAME, LASTNAME, BIO from book_author";
        List<Author> authors = new ArrayList();
//        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(qry)) {
            while (rs.next()) {
                int author_id = rs.getInt("ID");
                String first_name = rs.getString("FIRSTNAME");
                String last_name = rs.getString("LASTNAME");
                String bio = rs.getString("BIO");
                Author author = new Author(author_id, first_name,
                        last_name, bio);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
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

    /**
     * Queries the database for a particular author based upon ID and returns
     * the Author object if found.
     *
     * @param id
     * @return
     */
    public Author performFind(int id) {
        String qry = "SELECT ID, LASTNAME, FIRSTNAME, BIO "
                + "FROM BOOK_AUTHOR "
                + "WHERE ID = ?";

        Author author = null;
        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {

                if (rs.next()) {
                    int author_id = rs.getInt("ID");
                    String first_name = rs.getString("FIRSTNAME");
                    String last_name = rs.getString("LASTNAME");
                    String bio = rs.getString("BIO");
                    author = new Author(author_id,
                            first_name,
                            last_name,
                            bio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    /**
     * Queries the database for a particular author based upon first and last
     * name and returns a list of Author objects if found.
     *
     * @param first firstname
     * @param last lastname
     * @return
     */
    public List<Author> performFind(String first, String last) {
        String qry = "SELECT ID, LASTNAME, FIRSTNAME, BIO "
                + "FROM BOOK_AUTHOR "
                + "WHERE LASTNAME = ? "
                + "AND FIRSTNAME = ?";

        List authorList = new ArrayList();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, last.toUpperCase());
            stmt.setString(2, first.toUpperCase());
            try (ResultSet rs = stmt.executeQuery();) {

                while (rs.next()) {
                    int authorId = rs.getInt("ID");
                    String firstName = rs.getString("FIRST");
                    String lastName = rs.getString("LAST");
                    String bio = rs.getString("BIO");
                    Author author = new Author(authorId,
                            firstName,
                            lastName,
                            bio);
                    authorList.add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
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
        AuthorDAO authorDao = new AuthorDAO();
        authorDao.queryBookAuthor();
        authorDao.performCreate("Nikita", "Goswami", "N/A");

        // Find any author named Nikita Goswami in authList
        List<Author> authorList = authorDao.performFind("Nikita", "Goswami");
        for(Author author: authorList) {
            author.setBio("New Bio");
            authorDao.update(author);
        }

        authorDao.queryBookAuthor();

        // Delete any author named Nikita Goswami
        for(Author author: authorList) {
            authorDao.delete(author);
        }
    }
}
