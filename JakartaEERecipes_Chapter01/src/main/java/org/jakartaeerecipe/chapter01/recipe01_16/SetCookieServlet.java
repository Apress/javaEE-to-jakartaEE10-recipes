package org.jakartaeerecipe.chapter01.recipe01_16;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SetCookieServlet", value = "/SetCookieServlet")
public class SetCookieServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie cookie = new Cookie("sessionId","12345");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-30);
        response.addCookie(cookie);
        try {
            out.println("""
                <html>
                    <head>
                        <title>Set Cookie</title>
                    </head>
                    <body>
                        <h1>Servlet SetCookieServlet is setting a cookie into the browser</h1>
                        <br/><br/>
                        <a href='DisplayCookieServlet'>Display the cookie contents.</a>
                    </body>
                </html>""");
        } finally {
           out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
