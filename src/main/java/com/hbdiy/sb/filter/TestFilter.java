package com.hbdiy.sb.filter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by john on 2016/9/7.
 */
@WebFilter(filterName = "testFilter", urlPatterns = "/user/get3")
public class TestFilter implements Filter {
    private final Logger logger = Logger.getLogger(Filter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("test filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
