package org.jakartaeerecipe.chapter01.recipe01_13;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read parameter from form that contains the file name to download
        String fileToDownload = request.getParameter("filename");

        // Call the download method with the given file
        System.err.println("Downloading file now...");

        doDownload(request, response, fileToDownload);

    }

    private void doDownload(HttpServletRequest request, HttpServletResponse response, String originalFile) throws IOException {
        final int BYTES = 1024;
        int length;

        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context = getServletConfig().getServletContext();

        response.setContentType((context.getMimeType( originalFile ) != null) ?
                context.getMimeType( originalFile ) : "text/plain" );
        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFile + "\"" );

        InputStream in = context.getResourceAsStream("/" + originalFile);
        byte[] bbuf = new byte[BYTES];
        while ((in != null) && ((length = in.read(bbuf)) != -1))
        {
            outStream.write(bbuf,0,length);
        }
        outStream.flush();
        outStream.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
