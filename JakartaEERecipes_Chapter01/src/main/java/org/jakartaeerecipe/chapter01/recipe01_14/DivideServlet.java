package org.jakartaeerecipe.chapter01.recipe01_14;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DivideServlet", value = "/DivideServlet")
public class DivideServlet extends HttpServlet {
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
        // Store the input parameter values into Strings
        String numA = request.getParameter("numa");
        String numB = request.getParameter("numb");

        if (Integer.valueOf(numB) == 0) throw new ArithmeticException("Division by zero");

        int quotient = Integer.valueOf(numA) + Integer.valueOf(numB);

        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                    <html>
                        <head>
                            <title>The Quotient of the Numbers</title>
                        </head>
                        <body>
                            <h1>Quotient: %s </h1>
                            <br/>
                            <a href="./chapter01/recipe01_14.html">Try Again</a>
                        </body>
                    </html>""", quotient);
        }
    }
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
