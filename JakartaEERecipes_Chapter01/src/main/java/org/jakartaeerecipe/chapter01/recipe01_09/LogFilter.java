package org.jakartaeerecipe.chapter01.recipe01_09;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "LogFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String userIP = request.getRemoteHost();
        System.out.println(filterConfig.getServletContext().getContextPath() + " - Visitor User IP: " + userIP);
        chain.doFilter(request, response);
    }
}
