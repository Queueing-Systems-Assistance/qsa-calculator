package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hypo | 1 (2 phases) Service.
 */
@Component
public class SystemMHypo12Calculator extends SystemMG1AbstractCalculator {

    @Override
    public double eS(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        return 1 / Mu1 + 1 / Mu2;
    }

    @Override
    public double eSPow2(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double part1 = 2 / pow(Mu1, 2);
        final double part2 = 2 / pow(Mu2, 2);
        final double part3 = 2 / (Mu1 * Mu2);
        return part1 + part2 + part3;
    }

    @Override
    public double eSPow3(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double part1 = 6 / pow(Mu1, 3);
        final double part2 = 6 / (pow(Mu1, 2) * Mu2);
        final double part3 = 6 / (Mu1 * pow(Mu2, 2));
        final double part4 = 6 / pow(Mu2, 3);
        return part1 + part2 + part3 + part4;
    }
}
