package com.unideb.qsa.calculator.domain.calculator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a system input feature.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class InputFeature {

    private final String id;
    private final String name;
    private final String description;
    private final boolean typeFraction;
    private final boolean required;

    private InputFeature(InputFeature.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.typeFraction = builder.typeFraction;
        this.required = builder.required;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTypeFraction() {
        return typeFraction;
    }

    public boolean isRequired() {
        return required;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Builder for {@link InputFeature}.
     */
    public static class Builder {

        private String name;
        private String id;
        private String description;
        private boolean required;
        private boolean typeFraction;

        public InputFeature.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public InputFeature.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public InputFeature.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public InputFeature.Builder withRequired(boolean required) {
            this.required = required;
            return this;
        }

        public InputFeature.Builder withTypeFraction(boolean fractionType) {
            this.typeFraction = fractionType;
            return this;
        }

        public InputFeature build() {
            return new InputFeature(this);
        }
    }
}
