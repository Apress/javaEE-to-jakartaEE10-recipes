package org.jakartaeerecipe.chapter01.recipe01_19;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PushServlet", urlPatterns = {"/PushServlet"})
public class PushServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)  throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                <html>
                    <head>
                        <title>Servlet PushServlet</title>
                    </head>
                    <body>
                        <p>Servlet PushServlet at %s!</p>
                        <img src="./resources/images/jakartaee10recipes.png"/>
                    </body>
                </html>""", request.getContextPath());
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In the servlet");

        if (request.getRequestURI().equals("/JakartaEERecipes_Chapter01-1.0-SNAPSHOT/PushServlet") &&
            request.newPushBuilder() != null) {
            System.out.println("Pushing resources");
            PushBuilder builder =
                    request.newPushBuilder().path("/resources/images/jakartaee10recipes.png");
            builder.path("/resources/images/jakartaee10recipes.png");
            builder.push();
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
