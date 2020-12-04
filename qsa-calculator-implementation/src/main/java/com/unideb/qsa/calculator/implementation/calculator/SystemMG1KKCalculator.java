package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 | K | K Service.
 * Abstract class providing calculations for M | G | 1 | K | K systems.
 */
public abstract  class SystemMG1KKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        return 1 / Lambda;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        double sum = 0.0;
        for (double i = 0.0; i <= K - 1; i++) {
            final double binomialCoefficient = binomialCoefficientDouble((int)K - 1, (int)i);
            final double Bi = functionBn(features, i);
            sum += binomialCoefficient * Bi;
        }
        return pow(1 + K * SAvg / E0 * sum, -1);
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return a / SAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double EDelta(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = Lambda * P0;
        return dividend / divisor;
    }

    public double functionBn(Map<SystemFeature, Double> features, double index) {
        double result = 1.0;
        if (index != 0) {
            for (double i = 1.0; i <= index; i++) {
                final double laplace = laplaceTransform(features, i);
                result *= (1 - laplace) / laplace;
            }
        }
        return result;
    }

    public abstract double SAvg(Map<SystemFeature, Double> features);

    public abstract double laplaceTransform(Map<SystemFeature, Double> features, double index);
}
