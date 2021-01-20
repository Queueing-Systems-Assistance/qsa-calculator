package com.unideb.qsa.calculator.domain.exception;

/**
 * Exception when output features not found.
 */
public class QSAInvalidOutputException extends QSAClientException {

    private final String message;

    public QSAInvalidOutputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
