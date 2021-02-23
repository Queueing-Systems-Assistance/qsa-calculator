package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hypo | 1 | K | K (2 phases) Service.
 */
@Component
public class SystemMHypo1KK2Calculator extends SystemMG1KKAbstractCalculator {

    @Override
    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        return 1 / Mu1 + 1 / Mu2;
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double phase1 = Mu1 / (Mu1 + (index * LambdaFin));
        final double phase2 = Mu2 / (Mu2 + (index * LambdaFin));
        return phase1 * phase2;
    }
}
