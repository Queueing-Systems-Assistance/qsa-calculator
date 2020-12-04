package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Gamma | 1 | K | K Service.
 */
@Component
public class SystemMGamma1KKCalculator extends SystemMG1KKCalculator {

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double divisor = Mu + index * Lambda;
        return pow(Mu / divisor, Alpha);
    }
}
