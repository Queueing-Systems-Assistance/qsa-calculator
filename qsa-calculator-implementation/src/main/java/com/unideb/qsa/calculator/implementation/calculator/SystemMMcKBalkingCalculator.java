package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | c | K Balking Service.
 */
@Component
public class SystemMMcKBalkingCalculator extends SystemMMcKBalkingRenegingAbstractCalculator {

    @Override
    public double bn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        return 1.0 / (n + 1.0);
    }

    @Override
    public double rn(Map<SystemFeature, Double> features) {
        return 0;
    }
}
