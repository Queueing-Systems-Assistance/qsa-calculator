package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Gamma | 1 | K | K Service.
 */
@Component
public class SystemMGamma1KKCalculator extends SystemMG1KKAbstractCalculator {

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Mu = features.get(SystemFeature.Mu);
        return Alpha / Mu;
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        final double divisor = Mu + index * LambdaFin;
        return pow(Mu / divisor, Alpha);
    }
}
