package com.unideb.qsa.calculator.implementation.calculator.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.special.Gamma;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Utility class for mathematical algorithms.
 */
public final class CalculatorHelper {

    private CalculatorHelper() {
    }

    /**
     * Calculate the given number factorial value.
     * @param value given number
     * @return given number factorial value
     */
    public static double factorial(double value) {
        return Gamma.gamma(value + 1);
    }

    /**
     * Creates a copy of the system features.
     * @param features system features
     * @return copy of the system features
     */
    public static Map<SystemFeature, Double> copyOf(Map<SystemFeature, Double> features) {
        final Map<SystemFeature, Double> copyFeatures = new HashMap<>();
        features.keySet().forEach(systemFeature -> copyFeatures.put(systemFeature, features.get(systemFeature)));
        return copyFeatures;
    }
}
