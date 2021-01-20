package com.unideb.qsa.calculator.domain.calculator;

import java.util.List;

/**
 * Represents a system.
 */
public final class SystemElement {

    private final String name;
    private final String id;
    private final String status;
    private final String description;
    private final List<InputFeature> inputs;

    private SystemElement(SystemElement.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.status = builder.status;
        this.inputs = builder.inputs;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<InputFeature> getInputs() {
        return inputs;
    }

    /**
     * Builder for {@link SystemElement}.
     */
    public static class Builder {

        private String name;
        private String id;
        private String status;
        private String description;
        private List<InputFeature> inputs;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withInputs(List<InputFeature> inputs) {
            this.inputs = inputs;
            return this;
        }

        public SystemElement build() {
            return new SystemElement(this);
        }
    }
}
