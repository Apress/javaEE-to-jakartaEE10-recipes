package org.jakartaeerecipe.chapter01.recipe01_04;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SimpleServletNoDescriptor",
        value = "/SimpleServletNoDescriptor")
public class SimpleServletNoDescriptor extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException {
        message = "Look ma, no WEB-XML!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                        <html>
            	           <head>
                                <title>Servlet SimpleServlet</title>
                           </head>
                           <body>
                              <h1>Servlet SimpleServlet at %s</h1>
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
