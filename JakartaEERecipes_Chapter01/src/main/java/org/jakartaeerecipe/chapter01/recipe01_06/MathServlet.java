package org.jakartaeerecipe.chapter01.recipe01_06;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MathServlet", value = "/MathServlet")
public class MathServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");

        // Store the input parameter values into Strings
        String numA = req.getParameter("numa");
        String numB = req.getParameter("numb");
        int solution = 0;

        try (PrintWriter out = res.getWriter()) {
            try {
                solution = Integer.valueOf(numA) + Integer.valueOf(numB);
            } catch (java.lang.NumberFormatException ex) {
                // Display error if an exception is raised
                out.println("Please use numbers only...try again.");
                return;
            }

            out.printf("""
                <html>
                    <head>
                        <title>Test Math Servlet</title>
                    </head>
                <body>
                    Solution: %s + %s = %s
                    <br/>
                        <a href='recipe01_06.html'>Add Two More Numbers</a>
                </body>
                </html>""", numA, numB, solution);
        }
    }
}
