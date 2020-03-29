package com.unideb.qsa.calculator.domain.system;

import java.util.List;

/**
 * Represents a system output feature.
 */
public final class SystemOutput {

    private final String id;
    private final String name;
    private final String description;
    private final List<Double> values;

    private SystemOutput(SystemOutput.Builder builder) {
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

    public List<Double> getValues() {
        return values;
    }

    /**
     * Builder for {@link SystemOutput}.
     */
    public static class Builder {

        private String name;
        private String id;
        private String description;
        private List<Double> values;

        public SystemOutput.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public SystemOutput.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public SystemOutput.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SystemOutput.Builder withValue(List<Double> values) {
            this.values = values;
            return this;
        }

        public SystemOutput build() {
            return new SystemOutput(this);
        }
    }
}
