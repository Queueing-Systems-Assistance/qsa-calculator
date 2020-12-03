package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

@Component
public class SystemMHypo1KK2Calculator extends SystemMG1KKCalculator {

    @Override
    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        return 2 / (Mu1 + Mu2);
    }

    @Override
    public double laplaceTransform(Map<SystemFeature, Double> features, double index) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double phase1 = Mu1 / (Mu1 + (index * Lambda));
        final double phase2 = Mu2 / (Mu2 + (index * Lambda));
        return phase1 * phase2;
    }
}
