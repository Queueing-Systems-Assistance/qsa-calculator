package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 Service.
 */
@Component
public class SystemMG1Calculator extends SystemMG1AbstractCalculator {

    @Override
    public double eS(Map<SystemFeature, Double> features) {
        return features.get(SystemFeature.eS);
    }

    @Override
    public double eSPow2(Map<SystemFeature, Double> features) {
        return features.get(SystemFeature.eSPow2);
    }

    @Override
    public double eSPow3(Map<SystemFeature, Double> features) {
        return features.get(SystemFeature.eSPow3);
    }
}
