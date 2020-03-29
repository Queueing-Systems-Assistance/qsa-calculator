package com.unideb.qsa.calculator.server.advice;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import com.unideb.qsa.calculator.implementation.resolver.GeoLocationResolver;

/**
 * Test for {@link GeoLocationMetricReporterAdvice}.
 */
public class GeoLocationMetricReporterAdviceTest {

    private static final String HEADER = "X-Metrics-IP";
    private static final String CORRECT_IP = "192.168.0.1";
    private static final String MALFORMED_IP = "192.168.0.1222";
    private static final String RESOLVED_COUNTRY = "HU";
    private static final String ISO_CODE_VALUE = "geo.countries.HU";

    @Mock
    private GeoLocationResolver geoLocationResolver;
    @Mock
    private MetricRegistry metricRegistry;
    @Mock
    private Meter meter;
    @InjectMocks
    private GeoLocationMetricReporterAdvice geoLocationMetricReporterAdvice;

    @BeforeMethod
    public void setup() {
        initMocks(this);
    }

    @Test
    public void reportHttpRequestCallsShouldReportCorrectly() throws UnknownHostException {
        // GIVEN
        RequestContextHolder.setRequestAttributes(createTestServletRequestAttributes(CORRECT_IP));
        InetAddress ipAddress = InetAddress.getByName(CORRECT_IP);
        given(geoLocationResolver.resolveCountryIsoCode(ipAddress)).willReturn(Optional.of(RESOLVED_COUNTRY));
        given(metricRegistry.meter(ISO_CODE_VALUE)).willReturn(meter);
        // WHEN
        geoLocationMetricReporterAdvice.reportHttpRequestCalls();
        // THEN
        verify(geoLocationResolver).resolveCountryIsoCode(ipAddress);
        verify(metricRegistry).meter(ISO_CODE_VALUE);
        verify(meter).mark();
    }

    @Test
    public void reportHttpRequestCallsShouldNotReportWhenCountryIsEmpty() throws UnknownHostException {
        // GIVEN
        RequestContextHolder.setRequestAttributes(createTestServletRequestAttributes(CORRECT_IP));
        InetAddress ipAddress = InetAddress.getByName(CORRECT_IP);
        given(geoLocationResolver.resolveCountryIsoCode(ipAddress)).willReturn(Optional.empty());
        // WHEN
        geoLocationMetricReporterAdvice.reportHttpRequestCalls();
        // THEN
        verify(geoLocationResolver).resolveCountryIsoCode(ipAddress);
        verifyNoInteractions(metricRegistry);
        verifyNoInteractions(meter);
    }

    @Test
    public void reportHttpRequestCallsShouldNotReportWhenExceptionThrown() {
        // GIVEN
        RequestContextHolder.setRequestAttributes(createTestServletRequestAttributes(MALFORMED_IP));
        // WHEN
        geoLocationMetricReporterAdvice.reportHttpRequestCalls();
        // THEN
        verifyNoInteractions(geoLocationResolver);
        verifyNoInteractions(metricRegistry);
        verifyNoInteractions(meter);
    }

    private ServletRequestAttributes createTestServletRequestAttributes(String ipAddress) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HEADER, ipAddress);
        return new ServletRequestAttributes(request);
    }
}
