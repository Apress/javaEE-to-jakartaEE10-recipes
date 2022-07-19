package org.jakartaeerecipe.chapter01.recipe01_18;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
// @WebFilter(urlPatterns = "/*", asyncSupported = true)
public class AsyncServlet extends HttpServlet implements Filter {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("Starting doGet");
        AsyncContext ac = request.startAsync();
        ac.addListener(new MyListener());
        System.out.println("Do some stuff in doGet ...");

        // Start another service
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        executor.execute(new ServletService(ac));
        System.out.println("Some more stuff in doGet ...");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Going through the servlet filter...");
    }
}
