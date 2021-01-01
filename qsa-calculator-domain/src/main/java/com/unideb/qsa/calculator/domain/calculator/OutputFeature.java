package com.unideb.qsa.calculator.domain.calculator;

import java.util.List;

/**
 * Represents a system output feature.
 */
public final class OutputFeature {

    private String id;
    private String name;
    private String description;
    private List<String> values;

    public OutputFeature() {
    }

    private OutputFeature(OutputFeature.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.values = builder.values;
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

    public List<String> getValues() {
        return values;
    }

    /**
     * Builder for {@link OutputFeature}.
     */
    public static class Builder {

        private String name;
        private String id;
        private String description;
        private List<String> values;

        public OutputFeature.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public OutputFeature.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public OutputFeature.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public OutputFeature.Builder withValue(List<String> values) {
            this.values = values;
            return this;
        }

        public OutputFeature build() {
            return new OutputFeature(this);
        }
    }
}
