package com.unideb.qsa.calculator.domain.exception;

import java.util.List;

import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;

/**
 * Exception for internal errors. Validation uses, with different response.
 */
public final class QSAValidationException extends RuntimeException {

    private final List<ValidationErrorResponse> validationErrorResponses;

    public QSAValidationException(List<ValidationErrorResponse> validationErrorResponses) {
        this.validationErrorResponses = validationErrorResponses;
    }

    public List<ValidationErrorResponse> getValidationErrorResponses() {
        return validationErrorResponses;
    }
}
