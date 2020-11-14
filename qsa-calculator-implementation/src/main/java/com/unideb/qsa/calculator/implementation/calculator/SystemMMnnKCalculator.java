package com.unideb.qsa.calculator.implementation.calculator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | n | K Service.
 */
@Component
public class SystemMMnnKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double PBcn(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        return EngsetRecursive(K, c, Ro);
    }

    private double EngsetRecursive(double K, double c, double Ro) {
        double result = 0;
        if (c == 1) {
            final double base = (K - 1) * Ro;
            result = base / (1 + base);
        } else {
            final double recursive = EngsetRecursive(K, c - 1, Ro);
            final double base = (K - c) * Ro * recursive;
            result = base / (c + base);
        }
        return result;
    }
}
