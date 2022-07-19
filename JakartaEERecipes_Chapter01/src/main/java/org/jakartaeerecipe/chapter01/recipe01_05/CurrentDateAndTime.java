package org.jakartaeerecipe.chapter01.recipe01_05;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "CurrentDateAndTime", value = "/CurrentDateAndTime")
public class CurrentDateAndTime extends HttpServlet {

    Date currDateAndTime = new Date();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        synchronized(currDateAndTime){
            currDateAndTime = new Date();
        }

        try (PrintWriter out = response.getWriter()) {
            out.printf("""
                <html>
                   <head>
                       <title>Servlet Current Date And Time</title> 
                   </head>
                       <body>
                           <h1>Servlet CurrentDateAndTime at %s</h1><br/>
                           The current date and time is: %s 
                       </body>
                </html>""", request.getContextPath(), currDateAndTime);
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
