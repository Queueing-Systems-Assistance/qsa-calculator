package com.unideb.qsa.calculator.server.advice;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * Test for {@link ControllerRequestLoggingAdvice}.
 */
public class ControllerRequestLoggingAdviceTest {

    @Mock
    private Appender appender;
    @Mock
    private JoinPoint joinPoint;
    @Mock
    private Signature signature;
    @InjectMocks
    private ControllerRequestLoggingAdvice requestLoggingAdvice;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void logRequestsShouldLogRequestWhenCalled() {
        //GIVEN
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        given(appender.getName()).willReturn("MOCK");
        given(joinPoint.getSignature()).willReturn(signature);
        root.addAppender(appender);
        //WHEN
        requestLoggingAdvice.logRequests(joinPoint);
        //THEN
        verify(appender).doAppend(argThat((ArgumentMatcher) argument -> ((LoggingEvent) argument).getLevel().levelStr.equals("INFO")));
    }
}

