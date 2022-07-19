package org.jakartaeerecipe.chapter02.recipe02_12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorBean {
    public static Connection conn = null;
    private List<Author> authorList = null;

    public List<Author> queryAuthors() {
        String sql = "SELECT * FROM BOOK_AUTHOR";
        authorList = new ArrayList<>();
        ResultSet rs;

        String jdbcUrl = "jdbc:derby://localhost:1527/acme";
        try (Connection conn = DriverManager.getConnection(jdbcUrl)) {
            System.out.println("Successfully connected");
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Returns row-count or 0 if not successful
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getInt("ID"));
                    author.setFirst(rs.getString("FIRSTNAME"));
                    author.setLast(rs.getString("LASTNAME"));
                    authorList.add(author);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public List<Author> getAuthorList(){
        authorList = queryAuthors();
        return authorList;
    }
}
