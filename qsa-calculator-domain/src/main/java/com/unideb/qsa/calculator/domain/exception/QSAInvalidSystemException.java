package com.unideb.qsa.calculator.domain.exception;

/**
 * Exception for system not found.
 */
public class QSAInvalidSystemException extends QSAClientException {

    private final String message;

    public QSAInvalidSystemException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
