package com.unideb.qsa.calculator.domain.error;

/**
 * Represents an error message.
 */
public final class ErrorResponse {

    private final String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
