package com.unideb.qsa.calculator.server.integration.helper;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Generates requests, and send them to the server.
 */
public class RequestGenerator {

    private static final String LOCALE_EN = "en";
    private static final String LOCALE_HU = "hu";

    /**
     * Send template request for each available locales.
     *
     * @param path   url
     * @param body   request body
     * @param params request parameters
     */
    public final List<Response> sendTemplateRequests(String path, String body, String... params) {
        return List.of(
                generateRequest(path, LOCALE_EN, body, params),
                generateRequest(path, LOCALE_HU, body, params));
    }

    private Response generateRequest(String path, String locale, String body, String... params) {
        RequestSpecification specification = RestAssured
                .given()
                .queryParam("locale", locale)
                .body(body == null ? "" : body)
                .contentType(ContentType.JSON);
        addParams(specification, params);
        return StringUtils.isBlank(body) ? specification.get(path) : specification.post(path);
    }

    private void addParams(RequestSpecification specification, String[] params) {
        if (params != null) {
            IntStream.iterate(0, index -> index < params.length, index -> index + 2)
                     .forEach(value -> specification.queryParam(params[value], params[value + 1]));
        }
    }
}
