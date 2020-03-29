package com.unideb.qsa.calculator.domain.exception;

/**
 * Exception for internal errors. Custom error message used when this exception throws.
 */
public final class QSAMessageException extends RuntimeException {

    public QSAMessageException(String message) {
        super(message);
    }
}
