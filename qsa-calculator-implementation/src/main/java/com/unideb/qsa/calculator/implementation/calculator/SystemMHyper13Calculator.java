package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hyper | 1 (2 phases) Service.
 */
@Component
public class SystemMHyper12Calculator extends SystemMG1AbstractCalculator {

    @Override
    public double eS(Map<SystemFeature, Double> features) {
        return 0;
    }

    @Override
    public double eSPow2(Map<SystemFeature, Double> features) {
        return 0;
    }

    @Override
    public double eSPow3(Map<SystemFeature, Double> features) {
        return 0;
    }
}
