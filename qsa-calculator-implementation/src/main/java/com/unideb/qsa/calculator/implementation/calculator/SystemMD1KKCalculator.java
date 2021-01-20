package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | D | 1 | K | K Service.
 */
@Component
public class SystemMD1KKCalculator extends SystemMG1KKAbstractCalculator {
    @Override
    public double SAvg(Map<SystemFeature, Double> features) {
        final double Ds = features.get(SystemFeature.Ds);
        return Ds;
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double Ds = features.get(SystemFeature.Ds);
        return pow(E, -1.0 * index * Ds);
    }
}
