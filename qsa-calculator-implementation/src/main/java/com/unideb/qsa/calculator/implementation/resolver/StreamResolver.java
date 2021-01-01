package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.StreamOutput;

/**
 * Resolves stream values.
 */
@Component
public class StreamResolver {

    /**
     * Check if stream next value should be calculated based on the current value.
     * @param streamOutput stream input values (from, to, steps)
     * @param currentValue the value of the current stream
     * @return true, if the next value should be calculated, false otherwise
     */
    public boolean shouldCalculateStream(Map<StreamOutput, String> streamOutput, double currentValue) {
        boolean shouldCalculate;
        double from = Double.parseDouble(streamOutput.get(StreamOutput.from));
        double to = Double.parseDouble(streamOutput.get(StreamOutput.to));
        if (from > to) {
            shouldCalculate = currentValue >= to;
        } else {
            shouldCalculate = currentValue <= to;
        }
        return shouldCalculate;
    }

    /**
     * Calculate stream next value based on the current value.
     * @param streamOutput stream input values (from, to, steps)
     * @param currentValue the value of the current stream
     * @return next value of the stream
     */
    public double calculateNextValue(Map<StreamOutput, String> streamOutput, double currentValue) {
        double steps = Double.parseDouble(streamOutput.get(StreamOutput.steps));
        double from = Double.parseDouble(streamOutput.get(StreamOutput.from));
        double to = Double.parseDouble(streamOutput.get(StreamOutput.to));
        if (from > to) {
            steps = Math.abs(steps) * -1;
        } else {
            steps = Math.abs(steps);
        }
        return currentValue + steps;
    }
}
