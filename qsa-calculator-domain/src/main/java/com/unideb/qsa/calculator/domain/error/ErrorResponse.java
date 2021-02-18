package com.unideb.qsa.calculator.domain.error;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a validation error message.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorResponse {

    private final String message;
    private final Map<String, List<String>> extensions;

    public ErrorResponse(String message) {
        this.message = message;
        this.extensions = Map.of();
    }

    public ErrorResponse(String message, Map<String, List<String>> extensions) {
        this.message = message;
        this.extensions = extensions;
    }

    public Map<String, List<String>> getExtensions() {
        return extensions;
    }

    public String getMessage() {
        return message;
    }
}
