package com.unideb.qsa.calculator.server.advice;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codahale.metrics.MetricRegistry;

import com.unideb.qsa.calculator.implementation.resolver.GeoLocationResolver;

/**
 * Advice to report countries metric  to dashboard.
 */
@Aspect
@Component
@ConditionalOnProperty("management.geo.enabled")
public class GeoLocationMetricReporterAdvice {

    public static final String HEADER = "X-Metrics-IP";

    private static final Logger LOG = LoggerFactory.getLogger(GeoLocationMetricReporterAdvice.class);
    private static final String ERROR_PARSING_IP_ADDRESS = "Error parsing IP address";
    private static final String RESOLVED_COUNTRY = "Resolved country [{}]";
    private static final String ISO_CODE_VALUE = "geo.countries.%s";

    @Autowired
    private GeoLocationResolver geoLocationResolver;
    @Autowired
    private MetricRegistry metricRegistry;

    /**
     * Collect metrics from hotel points estimation requests.
     */
    @Before("execution(* *..*.*Controller.*(..))")
    public void reportHttpRequestCalls() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        sendCountryMetrics(request.getHeader(HEADER));
    }

    /**
     * Report country metrics to dashboard.
     *
     * @param ipAddress ip address of the request
     */
    public void sendCountryMetrics(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            Optional<String> optionalCountry = geoLocationResolver.resolveCountryIsoCode(inetAddress);
            optionalCountry.ifPresent(this::reportCountry);
        } catch (UnknownHostException exception) {
            LOG.warn(ERROR_PARSING_IP_ADDRESS, exception);
        }
    }

    private void reportCountry(String isoCode) {
        metricRegistry.meter(String.format(ISO_CODE_VALUE, isoCode)).mark();
        LOG.info(RESOLVED_COUNTRY, isoCode);
    }
}

