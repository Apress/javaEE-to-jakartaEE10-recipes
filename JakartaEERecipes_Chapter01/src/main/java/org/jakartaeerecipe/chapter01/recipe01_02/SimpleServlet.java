package org.jakartaeerecipe.chapter01.recipe01_02;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {

    private String message;

    @Override
    public void init() {
        message = "Welcome to Java EE to Jakarta EE 10 Recipes!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset-UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.printf("""
                        <html>
                           <head>
                               <title>Simple Servlet</title>
                           </head>
                           <body>
                             <h1>Simple Servlet at %s</h1>
                             <br/>%s
                            </body>
                        </html>
                    """, request.getContextPath(), message);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
