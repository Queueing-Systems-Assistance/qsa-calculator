package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.XAxis;

/**
 * Resolves X axis properties.
 */
@Component
public class XAxisResolver {

    /**
     * Check if x axis next value should be calculated based on the current value.
     *
     * @param xAxis        properties
     * @param currentValue the value of the current x axis position
     * @return true, if the next value should be calculated, false otherwise
     */
    public boolean checkXAxisShouldCalculate(Map<XAxis, Double> xAxis, double currentValue) {
        boolean shouldCalculate;
        if (xAxis.get(XAxis.from) > xAxis.get(XAxis.to)) {
            shouldCalculate = currentValue >= xAxis.get(XAxis.to);
        } else {
            shouldCalculate = currentValue <= xAxis.get(XAxis.to);
        }
        return shouldCalculate;
    }

    /**
     * Calculate X axis next value based on the current value.
     *
     * @param xAxis        properties
     * @param currentValue the value of the current axis position
     * @return next value of the x axis
     */
    public double calculateNextValue(Map<XAxis, Double> xAxis, double currentValue) {
        double steps;
        if (xAxis.get(XAxis.from) > xAxis.get(XAxis.to)) {
            steps = Math.abs(xAxis.get(XAxis.steps)) * -1;
        } else {
            steps = Math.abs(xAxis.get(XAxis.steps));
        }
        return currentValue + steps;
    }
}
