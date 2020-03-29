package com.unideb.qsa.calculator.server.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for HTTP calls.
 */
@Aspect
@Component
public class ControllerRequestLoggingAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerRequestLoggingAdvice.class);
    private static final String REQUEST_PARAMETERS = "The method [{}] called with {}";

    /**
     * Log out the Controller requests with parameters.
     *
     * @param joinPoint controllers join point
     */
    @Before("within(*..*Controller)")
    public void logRequests(JoinPoint joinPoint) {
        LOG.info(REQUEST_PARAMETERS, joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }
}
