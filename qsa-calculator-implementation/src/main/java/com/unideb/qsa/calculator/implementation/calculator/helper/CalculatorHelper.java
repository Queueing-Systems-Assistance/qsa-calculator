package com.unideb.qsa.calculator.implementation.calculator.helper;

import org.apache.commons.math3.special.Gamma;

/**
 * Utility class for mathematical algorithms.
 */
public final class CalculatorHelper {

    public static final double VALUE_1_3 = 1.3;
    public static final int VALUE_2 = 2;
    public static final int VALUE_3 = 3;
    public static final int VALUE_40 = 40;
    public static final int VALUE_4 = 4;
    public static final int VALUE_10 = 10;
    public static final int VALUE_20 = 20;
    public static final int VALUE_100 = 100;
    public static final int VALUE_200 = 200;

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
