package org.jakartaeerecipe.chapter15;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("""
            <html>
                <body> Login to continue
                    <form method=\"POST\" action=\"j_security_check\">
                        <p><strong>Username </strong>
                        <input type=\"text\" name=\"j_username\">
                        <p><strong>Password </strong>
                        <input type=\"password\" name=\"j_password\">
                        <p>
                        <input type=\"submit\" value=\"Submit\">
                    </form>
                </body>
            </html>""");
    }

}
