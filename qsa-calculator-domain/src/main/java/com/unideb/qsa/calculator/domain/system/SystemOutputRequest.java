package com.unideb.qsa.calculator.domain.system;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Cass for system output request.
 */
public class SystemOutputRequest {

    private Map<SystemFeature, Double> features;
    private List<String> outputFeatureIds;

    public List<String> getOutputFeatureIds() {
        return Optional.ofNullable(outputFeatureIds).orElse(Collections.emptyList());
    }

    public void setOutputFeatureIds(final List<String> outputFeatureIds) {
        this.outputFeatureIds = outputFeatureIds;
    }

    public Map<SystemFeature, Double> getFeatures() {
        return features;
    }

    public void setFeatures(final Map<SystemFeature, Double> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "SystemOutputRequest{"
               + "features=" + features
               + ", outputFeatureIds=" + outputFeatureIds
               + "}";
    }
}
