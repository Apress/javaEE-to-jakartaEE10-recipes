package org.jakartaeerecipe.chapter01.recipe01_16;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "DisplayCookieServlet", urlPatterns = {"/DisplayCookieServlet"})
public class DisplayCookieServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        try (PrintWriter out = response.getWriter()) {
            out.print("""
				<html>
					<head>
						<title>Display Cookies</title>
					</head>
					<body>
				""");

            for(Cookie cookie:cookies){
                out.printf("""
                    <p>Cookie Name: %s <br/>
                      Value: %s
                    </p>
                    """, cookie.getName(), cookie.getValue());
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

