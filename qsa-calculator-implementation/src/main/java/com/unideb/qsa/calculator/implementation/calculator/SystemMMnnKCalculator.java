package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

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

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        double sum = 0;
        for(int i = 0; i <= c; i++) {
            sum += binomialCoefficientDouble((int)K, i) * pow(Ro, i);
        }
        return 1 / sum;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        return binomialCoefficientDouble((int)K, (int)n) * pow(Ro, n) * P0;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0;
        for(double i = 0; i <= c; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            sum += i * Pi;
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return K - NAvg;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double mAvg = mAvg(features);
        return mAvg / K;
    }

    public double ETau(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ut = Ut(features);
        return Ut / (Mu * (1 - Ut));
    }

    public double ENR(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double ETau = ETau(features);
        return Lambda * ETau;
    }

    public double PBKc(Map<SystemFeature, Double> features) {
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
