package org.jakartaeerecipe.chapter06.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jakartaeerecipe.chapter06.entity.Chapter;
import org.jakartaeerecipe.chapter06.CreateConnection;

/**
 *
 * @author juneau
 */
public class ChapterDAO implements java.io.Serializable {

    public ChapterDAO() {
    }

    public List<Chapter> queryChapters() {
        String qry = "select id, book_id, chapter_number, title, description from chapter";

        List<Chapter> chapters = new ArrayList();
//        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(qry)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                int bookId = rs.getInt("BOOK_ID");
                int chapterNo = rs.getInt("CHAPTER_NUMBER");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                Chapter chapter = new Chapter(id,
                        bookId,
                        chapterNo,
                        title,
                        description);
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    /**
     * Utility method to demonstrate a scrollable ResultSet
     */
    private void queryBookChapters() {
        String sql = "SELECT ID, CHAPTER_NUMBER, TITLE, DESCRIPTION "
                + "FROM CHAPTER";

        int id = 0;
        int chapterNumber = 0;
        String title;

        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery()) {

            rs.first();
            id = rs.getInt("ID");
            chapterNumber = rs.getInt("CHAPTER_NUMBER");
            title = rs.getString("TITLE");
            System.out.println(id + " - " + chapterNumber + ": " + title);

            rs.next();
            id = rs.getInt("ID");
            chapterNumber = rs.getInt("CHAPTER_NUMBER");
            title = rs.getString("TITLE");
            System.out.println(id + " - " + chapterNumber + ": " + title);

            rs.last();
            id = rs.getInt("ID");
            chapterNumber = rs.getInt("CHAPTER_NUMBER");
            title = rs.getString("TITLE");
            System.out.println(id + " - " + chapterNumber + ": " + title);

            rs.previous();
            id = rs.getInt("ID");
            chapterNumber = rs.getInt("CHAPTER_NUMBER");
            title = rs.getString("TITLE");
            System.out.println(id + " - " + chapterNumber + ": " + title);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Queries the database for a particular chapter based upon ID and returns
     * the Author object if found.
     *
     * @param id
     * @return
     */
    public Chapter performFind(int id) {
        String qry = "SELECT ID,BOOK_ID, CHAPTER_NUMBER, TITLE, DESCRIPTION "
                + "FROM CHAPTER "
                + "WHERE ID = ?";

        Chapter chapter = null;
        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {

                if (rs.next()) {
                    int chapterId = rs.getInt("ID");
                    int bookId = rs.getInt("BOOK_ID");
                    int chapterNo = rs.getInt("CHAPTER_NUMBER");
                    String title = rs.getString("TITLE");
                    String description = rs.getString("DESCRIPTION");
                    chapter = new Chapter(chapterId,
                            bookId,
                            chapterNo,
                            title,
                            description);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;


    }

    /**
     * Queries the database for a particular chapter based upon title and number
     * and returns a list of Chapter objects if found.
     *
     * @param title chapter title
     * @param chapterNo chapter number
     * @return
     */
    public List<Chapter> performFind(String title, int chapterNo) {
        String qry = "SELECT ID, BOOK_ID, CHAPTER_NUMBER, TITLE, DESCRIPTION "
                + "FROM CHAPTER "
                + "WHERE TITLE = ? "
                + "AND CHAPTER_NUMBER = ?";

        List chapterList = new ArrayList();
        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, title.toUpperCase());
            stmt.setInt(2, chapterNo);
            try (ResultSet rs = stmt.executeQuery();) {

                while (rs.next()) {
                    int chapterId = rs.getInt("ID");
                    int bookId = rs.getInt("BOOK_ID");
                    int chapterNumber = rs.getInt("CHAPTER_NUMBER");
                    String chapterTitle = rs.getString("TITLE");
                    String description = rs.getString("DESCRIPTION");
                    Chapter chapter = new Chapter(chapterId,
                            bookId,
                            chapterNumber,
                            title,
                            description);
                    chapterList.add(chapter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapterList;


    }

    public void readClob() {
        String qry = "select chapter_number, title, description from chapter";
        Clob theClob = null;
        // CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(qry)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int chapterNumber = rs.getInt(1);
                    String title = rs.getString(2);
                    theClob = rs.getClob(3);
                    System.out.println("Clob length: " + theClob.length());
                    System.out.println(chapterNumber + " - " + title + ": ");
                    java.io.InputStream in =
                            theClob.getAsciiStream();
                    int i;
                    while ((i = in.read()) > -1) {
                        System.out.print((char) i);
                    }
                    System.out.println();
                }
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param bookId
     * @param title
     * @param description
     */
    private void performCreate(int chapterNumber, int bookId, String title, String description) {
        String sql = "INSERT INTO CHAPTER VALUES("
                + "next value for CHAPTER_S, ?, ?, ?, ?)";

        Clob textClob = null;
//        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            textClob = conn.createClob();
            textClob.setString(1, description);

            stmt.setInt(1, chapterNumber);
            stmt.setString(2, title.toUpperCase());
            stmt.setInt(3, bookId);
            stmt.setClob(4, textClob);

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
     * Update record in the CHAPTER table
     *
     * @param id
     * @param bookId
     * @param chapterNumber
     * @param title
     * @param description
     */
    private void performUpdate(int id, int bookId, int chapterNumber, String title, String description) {
        String sql = "UPDATE CHAPTER "
                + "SET chapter_number = ?,"
                + "   book_id = ?,"
                + "   title = ?,"
                + "   description = ? "
                + "WHERE ID = ?";
        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, chapterNumber);
            stmt.setInt(2, bookId);
            stmt.setString(3, title.toUpperCase());
            stmt.setString(4, description.toUpperCase());
            stmt.setInt(5, id);
            int result = stmt.executeUpdate(sql);
            if (result > 0) {
                System.out.println("-- Record Updated --");

            } else {
                System.out.println("!! Record NOT Updated !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete record from the CHAPTER table
     *
     * @param id
     */
    private void performDelete(int id) {
        String sql = "DELETE FROM CHAPTER WHERE ID = ?";

        try (Connection conn = CreateConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate(sql);
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
     * Facade method for inserting Chapter objects into the CHAPTER table
     *
     * @param chapter
     */
    public void insert(Chapter chapter) {
        performCreate(chapter.getChapterNumber(),
                chapter.getBookId(),
                chapter.getTitle(),
                chapter.getDescription());
    }

    /**
     * Facade method for updating Chapter objects in the CHAPTER table
     *
     * @param chapter
     */
    public void update(Chapter chapter) {
        this.performUpdate(chapter.getId(), chapter.getBookId(), chapter.getChapterNumber(), chapter.getTitle(), chapter.getDescription());
    }

    /**
     * Facade method for deleting Chapter objects from the CHAPTER table
     *
     * @param chapter chapter to be deleted
     */
    public void delete(Chapter chapter) {
        performDelete(chapter.getId());
    }

    public static void main(String[] args) {
        ChapterDAO chapterDao = new ChapterDAO();
        // chapterDao.queryBookChapters();
        // chapterDao.performCreate(11, 1, "Fake Title", "Fake Description");
        chapterDao.readClob();
    }
}
