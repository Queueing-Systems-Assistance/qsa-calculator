package com.unideb.qsa.calculator.domain.error;

import java.util.List;

/**
 * Represents a validation error message.
 */
public final class ValidationErrorResponse {

    private final String errorMessage;
    private final List<String> inputIds;

    public ValidationErrorResponse(ValidationErrorResponse.Builder builder) {
        this.errorMessage = builder.errorMessage;
        this.inputIds = builder.inputIds;
    }

    public List<String> getInputIds() {
        return inputIds;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Builder for {@link ValidationErrorResponse}.
     */
    public static class Builder {

        private String errorMessage;
        private List<String> inputIds;

        public ValidationErrorResponse.Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ValidationErrorResponse.Builder withInputIds(List<String> inputIds) {
            this.inputIds = inputIds;
            return this;
        }

        public ValidationErrorResponse build() {
            return new ValidationErrorResponse(this);
        }
    }
}
