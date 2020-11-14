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
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        return EngsetRecursive(c, n, Ro);
    }

    private double EngsetRecursive(double c, double n, double Ro) {
        double result = 0;
        if (n == 1) {
            final double base = (c - 1) * Ro;
            result = base / (1 + base);
        } else {
            final double recursive = EngsetRecursive(c, n - 1, Ro);
            final double base = (c - n) * Ro * recursive;
            result = base / (n + base);
        }
        return result;
    }
}
