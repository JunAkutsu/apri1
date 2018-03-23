package com.apri.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	logger.info("init!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	logger.info("Before!!");
        chain.doFilter(request, response);
    	logger.info("After!!");
    }

    @Override
    public void destroy() {
    	logger.info("destroy!!");
    }
}
