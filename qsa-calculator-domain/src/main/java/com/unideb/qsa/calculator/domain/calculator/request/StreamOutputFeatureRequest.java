package com.unideb.qsa.calculator.domain.calculator.request;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.StreamOutput;

/**
 * Represents a chart request.
 */
public final class StreamOutputFeatureRequest {

    private Map<SystemFeature, Double> featureConditions;
    private Map<StreamOutput, String> streamOutput;
    private List<String> outputFeatureIds;

    public Map<SystemFeature, Double> getFeatureConditions() {
        return featureConditions;
    }

    public void setFeatureConditions(Map<SystemFeature, Double> featureConditions) {
        this.featureConditions = featureConditions;
    }

    public Map<StreamOutput, String> getStreamOutput() {
        return streamOutput;
    }

    public void setStreamOutput(Map<StreamOutput, String> streamOutput) {
        this.streamOutput = streamOutput;
    }

    public List<String> getOutputFeatureIds() {
        return Optional.ofNullable(outputFeatureIds).orElse(Collections.emptyList());
    }

    public void setOutputFeatureIds(List<String> outputFeatureIds) {
        this.outputFeatureIds = outputFeatureIds;
    }

}
