package com.unideb.qsa.calculator.domain.chart;

import java.util.List;

import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.domain.system.SystemOutput;

/**
 * Represents a chart response.
 */
public final class ChartRepresentation {

    private SystemElement systemElement;
    private List<SystemOutput> systemOutputs;
    private List<Double> labels;

    private ChartRepresentation(ChartRepresentation.Builder builder) {
        this.systemElement = builder.systemElement;
        this.systemOutputs = builder.systemOutputs;
        this.labels = builder.labels;
    }

    public List<Double> getLabels() {
        return labels;
    }

    public List<SystemOutput> getSystemOutputs() {
        return systemOutputs;
    }

    public SystemElement getSystemElement() {
        return systemElement;
    }

    /**
     * Builder for {@link ChartRepresentation}.
     */
    public static class Builder {

        private SystemElement systemElement;
        private List<SystemOutput> systemOutputs;
        private List<Double> labels;

        public ChartRepresentation.Builder withSystemElement(SystemElement systemElement) {
            this.systemElement = systemElement;
            return this;
        }

        public ChartRepresentation.Builder withSystemOutputs(List<SystemOutput> systemOutputs) {
            this.systemOutputs = systemOutputs;
            return this;
        }

        public ChartRepresentation.Builder withLabels(List<Double> labels) {
            this.labels = labels;
            return this;
        }

        public ChartRepresentation build() {
            return new ChartRepresentation(this);
        }
    }
}
