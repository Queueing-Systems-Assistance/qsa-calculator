package com.unideb.qsa.calculator.domain.chart;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.XAxis;

/**
 * Represents a chart request.
 */
public final class ChartRequest {

    private Map<SystemFeature, Double> features;
    private Map<XAxis, Double> xAxis;
    private List<String> outputFeatureIds;

    public Map<SystemFeature, Double> getFeatures() {
        return features;
    }

    public void setFeatures(Map<SystemFeature, Double> features) {
        this.features = features;
    }

    public Map<XAxis, Double> getxAxis() {
        return xAxis;
    }

    public void setxAxis(Map<XAxis, Double> xAxis) {
        this.xAxis = xAxis;
    }

    public List<String> getOutputFeatureIds() {
        return Optional.ofNullable(outputFeatureIds).orElse(Collections.emptyList());
    }

    public void setOutputFeatureIds(List<String> outputFeatureIds) {
        this.outputFeatureIds = outputFeatureIds;
    }

    @Override
    public String toString() {
        return "ChartRequest{"
               + "features=" + features
               + ", xAxis=" + xAxis
               + ", outputFeatureIds=" + outputFeatureIds
               + "}";
    }
}
