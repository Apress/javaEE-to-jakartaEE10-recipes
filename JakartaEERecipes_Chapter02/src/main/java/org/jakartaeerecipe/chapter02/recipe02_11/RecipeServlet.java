package org.jakartaeerecipe.chapter02.recipe02_11;

import java.io.*;
import java.sql.*;

import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "RecipeServlet", value = "/RecipeServlet")
public class RecipeServlet extends HttpServlet {

    private final String jdbcUrl = "jdbc:derby://localhost:1527/acme";

//    @Resource(name="jdbc/DerbyConnection")
//    private DataSource ds;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int result = -1;
        try (PrintWriter out = response.getWriter()) {
            /*
             * TODO Perform validation on the request parameters here
             */
            result = insertRow (request.getParameter("recipeNumber"),
                    request.getParameter("name"),
                    request.getParameter("description"),
                    request.getParameter("text"));

            out.println("""
                <html>
                <head>
                <title>Servlet RecipeServlet</title>
                </head>
                <body>
            """);
            if(result > 0){

                out.println("""
                    <font color='green'>Record successfully inserted!</font>
                    <br/><br/><a href='/JakartaEERecipes_Chapter02-1.0-SNAPSHOT/chapter02/recipe02_11.jspx'>Insert another record</a>
                    """);
            } else {
                out.println("""
                    <font color='red'>Record NOT inserted!</font>
                    <br/><br/><a href='/JakartaEERecipes_Chapter02-1.0-SNAPSHOT/chapter02/recipe02_11.jspx'>Try Again</a>
                """);
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
           e.printStackTrace();
        }
    }

    public int insertRow(String recipeNumber,
                         String name,
                         String description,
                         String text) {

        String sql = "INSERT INTO RECIPE VALUES(" +
                "?,?,?,?)";

        int result = -1;
        try (Connection conn = DriverManager.getConnection(jdbcUrl)){
//             Connection conn = DriverManager.getConnection(jdbcUrl);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, recipeNumber);
                stmt.setString(2, name);
                stmt.setString(3, description);
                stmt.setString(4, text);
                // Returns row-count or 0 if not successful
                result = stmt.executeUpdate();
            }


            if (result > 0){
                System.out.println("-- Record created --");
            } else {
                System.out.println("!! Record NOT Created !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
//
//    protected Connection getConnection() {
//        Connection conn = null;
//        try {
//            Context ctx = new InitialContext();
//            DataSource ds =
//                    (DataSource) ctx.lookup("jdbc/DerbyConnection");
//            conn = ds.getConnection();
//        } catch (NamingException ex) {
//            ex.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
