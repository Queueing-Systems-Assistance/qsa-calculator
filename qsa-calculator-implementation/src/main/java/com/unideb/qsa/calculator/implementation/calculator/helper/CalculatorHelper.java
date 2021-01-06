package com.unideb.qsa.calculator.implementation.calculator.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Merges two maps, where the value will be a unique list (actually a set).
     * @return merged maps.
     */
    public static Collector<Map.Entry<String, List<String>>, ?, Map<String, List<String>>> mergeMaps() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (errors1, errors2) -> new ArrayList<>(Stream.of(errors1, errors2).flatMap(List::stream).collect(Collectors.toSet())));
    }
}
