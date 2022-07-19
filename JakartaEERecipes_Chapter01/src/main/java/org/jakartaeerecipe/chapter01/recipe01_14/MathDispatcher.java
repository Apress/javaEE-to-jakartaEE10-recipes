package org.jakartaeerecipe.chapter01.recipe01_14;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MathDispatcher", value = "/MathDispatcher" )
public class MathDispatcher extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In the servlet...");

        // Store the input parameter values into Strings
        String eval = request.getParameter("eval");

        ServletContext sc = getServletConfig().getServletContext();
        RequestDispatcher rd = null;

        switch(eval){
            case "add" -> rd =  sc.getRequestDispatcher("/AddServlet");
            case "subtract" -> rd =  sc.getRequestDispatcher("/SubtractServlet");
            case "multiply" -> rd = sc.getRequestDispatcher("/MultiplyServlet");
            case "divide" -> rd = sc.getRequestDispatcher("/DivideServlet");
        }
        rd.forward(request, response);
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
