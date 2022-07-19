package org.jakartaeerecipe.chapter01.recipe01_18;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ReaderExample", urlPatterns = {"/ReaderExample"})
public class ReaderExample extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String filename = "/WEB-INF/test.txt";
        ServletContext context = getServletContext();
        InputStream in = context.getResourceAsStream(filename);
        try (PrintWriter out = response.getWriter()) {
            String path = "http://"
                    + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    + request.getContextPath()
                    + "/AcmeReaderServlet";
            out.printf("""
                    <html>
                        <head>
                        <title>Intro to Jakarta EE 10 - Servlet Reader Example</title>
                                    </head>
                                    <body>
                                        <h1>Servlet ReaderExample at %s</h1>
                                        Invoking the endpoint: %s
                    <br>
                          """, request.getContextPath(), path);
            out.flush();
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setChunkedStreamingMode(2);
            conn.setDoOutput(true);
            conn.connect();
            if (in != null) {
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(inReader);
                String text = "";
                out.println("Beginning Read");
                try (BufferedWriter output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
                    out.println("got the output...beginning loop");
                    while ((text = reader.readLine()) != null) {
                        out.println("reading text: " + text);
                        out.flush();
                        output.write(text);
                        Thread.sleep(1000);
                        output.write("Ending example now..");
                        out.flush();
                    }
                    output.flush();
                    output.close();
                }
            }
            out.println("Review the Glassfish server log for messages...");
            out.println("</body>");
            out.println("</html>");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(ReaderExample.class.getName()).log(Level.SEVERE, null, ex);
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



