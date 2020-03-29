package com.unideb.qsa.calculator.domain.chart;

import java.util.Map;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.XAxis;

/**
 * Represents a chart request.
 */
public final class ChartRequest {

    private Map<SystemFeature, Double> features;
    private Map<XAxis, Double> xAxis;

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

    @Override
    public String toString() {
        return "ChartRequest{"
               + "features=" + features
               + ", xAxis=" + xAxis
               + '}';
    }
}
