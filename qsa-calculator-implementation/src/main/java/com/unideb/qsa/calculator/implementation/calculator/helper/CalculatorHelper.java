package com.unideb.qsa.calculator.implementation.calculator.helper;

import org.apache.commons.math3.special.Gamma;

/**
 * Utility class for mathematical algorithms.
 */
public final class CalculatorHelper {

    private CalculatorHelper() {

    }

    /**
     * Calculate the given number factorial value.
     *
     * @param value given number
     * @return given number factorial value
     */
    public static double factorial(double value) {
        return Gamma.gamma(value + 1);
    }
}
