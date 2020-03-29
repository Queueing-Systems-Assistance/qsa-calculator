package com.unideb.qsa.calculator.domain.exception;

/**
 * Exception for internal errors. The default error message used when this exception throws.
 */
public final class QSAInternalException extends RuntimeException {

    public QSAInternalException(String message) {
        super(message);
    }

}
