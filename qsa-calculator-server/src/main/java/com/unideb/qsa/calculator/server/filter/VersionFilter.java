package com.unideb.qsa.calculator.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for putting the application version into each request.
 */
@Component
public class VersionFilter extends OncePerRequestFilter {

    private static final String VERSION = "version";

    @Autowired
    private BuildProperties buildProperties;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        MDC.put(VERSION, buildProperties.getVersion());
        try {
            super.doFilter(request, response, filterChain);
        } finally {
            MDC.remove(VERSION);
        }
    }
}
