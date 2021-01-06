package com.unideb.qsa.calculator.domain.error;

import java.util.List;
import java.util.Map;

/**
 * Represents a validation error message.
 */
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
