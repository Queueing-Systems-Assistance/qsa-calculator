package com.unideb.qsa.calculator.domain.system;

/**
 * Represents a system input feature.
 */
public final class SystemInput {

    private final String id;
    private final String name;
    private final String description;
    private final boolean typeFraction;
    private final boolean required;

    private SystemInput(SystemInput.Builder builder) {
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
     * Builder for {@link SystemInput}.
     */
    public static class Builder {

        private String name;
        private String id;
        private String description;
        private boolean required;
        private boolean typeFraction;

        public SystemInput.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public SystemInput.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public SystemInput.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SystemInput.Builder withRequired(boolean required) {
            this.required = required;
            return this;
        }

        public SystemInput.Builder withTypeFraction(boolean fractionType) {
            this.typeFraction = fractionType;
            return this;
        }

        public SystemInput build() {
            return new SystemInput(this);
        }
    }
}
