package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | c | K Reneging Service.
 */
@Component
public class SystemMMcKRenegingCalculator extends SystemMMcKBalkingRenegingAbstractCalculator {

    @Override
    public double bn(Map<SystemFeature, Double> features) {
        return 1.0;
    }

    @Override
    public double rn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        double result = 0.0;
        if (n > c) {
            final double Theta = features.get(SystemFeature.Theta);
            result = (n - c) * Theta;
        }
        return result;
    }
}
