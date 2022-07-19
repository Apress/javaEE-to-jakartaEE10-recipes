package org.jakartaeerecipe.chapter01.recipe01_08;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SimpleServletCtx1", value = "/SimpleServletCtx1",
        initParams = {@WebInitParam(name="name", value="Duke")})
public class SimpleServletCtx1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                    <html>
                        <head>
                            <title> Simple Servlet Context Example</title>
                        </head>
                        <body>
                            <p>This is a simple servlet to demonstrate context! 
                            Hello %s </p>
                        </body>
                    </html>                            
                    """, getServletConfig().getInitParameter("name"));
        }
    }
}
