package com.unideb.qsa.calculator.server.integration.helper;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Builds headers for tests.
 */
public class HeaderBuilder {

    /**
     * Creates headers for test requests, forcing the campaign by setting the test campaign header.
     * @return http header object.
     */
    //TODO: Check for multiple languages
    public HttpHeaders setupHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        return headers;
    }
}
