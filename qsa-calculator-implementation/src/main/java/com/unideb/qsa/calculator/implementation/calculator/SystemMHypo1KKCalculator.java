package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hypo | 1 | K | K Service.
 */
@Component
public class SystemMHypo1KKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return a / SAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double mu1 = features.get(SystemFeature.mu1);
        final double mu2 = features.get(SystemFeature.mu2);
        final double mu3 = features.get(SystemFeature.mu3);
        double result = 0;
        if (mu2 == 0.0) {
            result = 1 / mu1;
        } else {
            if (mu3 == 0.0) {
                final double divisor = (mu1 + mu2) / 2;
                result = 1 / divisor;
            } else if (mu2 != 0.0 && mu3 != 0.0) {
                final double divisor = (mu1 + mu2 + mu3) / 3;
                result = 1 / divisor;
            }
        }
        return result;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * TAvg(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        double sum = 0;
        for (double i = 0; i < K; i++) {
            final double part1 = factorial(K - 1);
            final double part2 = factorial(i) * factorial(K - 1 - i);
            final double part3 = functionBn(features, i);
            sum += part1 / part2 * part3;
        }
        return pow(1 + K * SAvg / E0 * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg + E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    private double functionBn(Map<SystemFeature, Double> features, double index) {
        double result = 1;
        if (index != 0) {
            for (double i = 1; i <= index; i++) {
                final double laplace = functionLaplace(features, i);
                result *= (1 - laplace) / laplace;
            }
        }
        return result;
    }

    private double functionLaplace(Map<SystemFeature, Double> features, double index) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double mu1 = features.get(SystemFeature.mu1);
        final double mu2 = features.get(SystemFeature.mu2);
        final double mu3 = features.get(SystemFeature.mu3);
        double result = 1;
        double calculation = mu1 / (mu1 + (index * Alpha));
        if (mu2 == 0.0) {
            result *= calculation;
        } else {
            double calculation2 = mu2 / (mu2 + (index * Alpha));
            if (mu3 == 0.0) {
                result *= calculation * calculation2;
            } else {
                double calculation3 = mu3 / (mu3 + (index * Alpha));
                result *= calculation * calculation2 * calculation3;
            }
        }
        return result;
    }
}
