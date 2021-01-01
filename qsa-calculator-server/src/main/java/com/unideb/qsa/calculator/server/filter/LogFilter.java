package com.unideb.qsa.calculator.server.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for putting an id to each request, so we can follow them in the logs.
 */
@Component
public class LogFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID = "X-Request-Id";
    private static final String EMPTY_REQUEST_ID = "EMPTY_REQUEST_ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = getRequestId(request);
        MDC.put(REQUEST_ID, requestId);
        try {
            super.doFilter(request, response, filterChain);
        } finally {
            MDC.remove(REQUEST_ID);
        }
    }

    private String getRequestId(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(REQUEST_ID))
                       .orElse(EMPTY_REQUEST_ID);
    }
}
