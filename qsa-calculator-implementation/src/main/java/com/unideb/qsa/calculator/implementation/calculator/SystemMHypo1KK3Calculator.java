package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hypo | 1 | K | K (3 phases) Service.
 */
@Component
public class SystemMHypo1KK3Calculator extends SystemMG1KKAbstractCalculator {

    @Override
    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        return 3 / (Mu1 + Mu2 + Mu3);
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        final double phase1 = Mu1 / (Mu1 + (index * Lambda));
        final double phase2 = Mu2 / (Mu2 + (index * Lambda));
        final double phase3 = Mu3 / (Mu3 + (index * Lambda));
        return phase1 * phase2 * phase3;
    }
}
