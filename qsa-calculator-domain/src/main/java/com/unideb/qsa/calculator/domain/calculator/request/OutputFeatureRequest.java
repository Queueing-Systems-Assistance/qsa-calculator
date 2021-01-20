package com.unideb.qsa.calculator.domain.calculator.request;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Cass for system output request.
 */
public final class OutputFeatureRequest {

    private Map<SystemFeature, Double> featureConditions;
    private List<String> outputFeatureIds;

    public List<String> getOutputFeatureIds() {
        return Optional.ofNullable(outputFeatureIds).orElse(Collections.emptyList());
    }

    public void setOutputFeatureIds(List<String> outputFeatureIds) {
        this.outputFeatureIds = outputFeatureIds;
    }

    public Map<SystemFeature, Double> getFeatureConditions() {
        return featureConditions;
    }

    public void setFeatureConditions(Map<SystemFeature, Double> featureConditions) {
        this.featureConditions = featureConditions;
    }

}
