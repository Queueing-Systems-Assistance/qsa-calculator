package com.unideb.qsa.calculator.domain.calculator.response;

import java.util.List;

import com.unideb.qsa.calculator.domain.calculator.OutputFeature;

/**
 * Represents a chart response.
 */
public final class StreamOutputFeatureRepresentation {

    private final List<OutputFeature> outputFeatures;
    private final List<Double> stream;

    private StreamOutputFeatureRepresentation(StreamOutputFeatureRepresentation.Builder builder) {
        this.outputFeatures = builder.outputFeatures;
        this.stream = builder.labels;
    }

    public List<Double> getStream() {
        return stream;
    }

    public List<OutputFeature> getOutputFeatures() {
        return outputFeatures;
    }

    /**
     * Builder for {@link StreamOutputFeatureRepresentation}.
     */
    public static class Builder {

        private List<OutputFeature> outputFeatures;
        private List<Double> labels;

        public StreamOutputFeatureRepresentation.Builder withOutputFeatures(List<OutputFeature> outputFeatures) {
            this.outputFeatures = outputFeatures;
            return this;
        }

        public StreamOutputFeatureRepresentation.Builder withStream(List<Double> labels) {
            this.labels = labels;
            return this;
        }

        public StreamOutputFeatureRepresentation build() {
            return new StreamOutputFeatureRepresentation(this);
        }
    }
}
