package com.unideb.qsa.calculator.domain.exception;

import java.util.List;
import java.util.Map;

/**
 * Exception for internal errors. Validation uses, with different response.
 */
public final class QSAValidationException extends QSAClientException {

    private final Map<String, List<String>> validationErrors;

    public QSAValidationException(Map<String, List<String>> validationErrors) {
        this.validationErrors = validationErrors;
    }


    public Map<String, List<String>> getValidationErrors() {
        return validationErrors;
    }
}
