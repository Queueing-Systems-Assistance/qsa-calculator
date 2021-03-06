package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hyper | 1 | K | K (3 phases) Service.
 */
@Component
public class SystemMHyper1KK3Calculator extends SystemMG1KKAbstractCalculator {

    @Override
    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        final double p1 = features.get(SystemFeature.p1);
        final double p2 = features.get(SystemFeature.p2);
        final double p3 = features.get(SystemFeature.p3);
        return p1 / Mu1 + p2 / Mu2 + p3 / Mu3;
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        final double p1 = features.get(SystemFeature.p1);
        final double p2 = features.get(SystemFeature.p2);
        final double p3 = features.get(SystemFeature.p3);
        final double phase1 = p1 * (Mu1 / (Mu1 + (index * LambdaFin)));
        final double phase2 = p2 * (Mu2 / (Mu2 + (index * LambdaFin)));
        final double phase3 = p3 * (Mu3 / (Mu3 + (index * LambdaFin)));
        return phase1 + phase2 + phase3;
    }
}
