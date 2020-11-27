package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Hyper | 1 | K | K Service.
 */
@Component
public class SystemMHyper1KKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        return 1 / Lambda;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return a / SAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double sum = functionCnK(K, features);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        return pow(1 + K * SAvg / E0 * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        double result;
        if (Mu2 != 0.0 && Mu3 != 0.0) {
            final double divisor = (Mu1 + Mu2 + Mu3) / 3;
            result = 1 / divisor;
        } else if (Mu3 == 0.0 && Mu2 != 0.0) {
            final double divisor = (Mu1 + Mu2) / 2;
            result = 1 / divisor;
        } else {
            result = 1 / Mu1;
        }
        return result;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double E0 = E0(features);
        final double LambdaAvg = LambdaAvg(features);
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

    private double functionCnK(double K, Map<SystemFeature, Double> features) {
        double sum = 0;
        for (double i = 0; i < K; i++) {
            final double part1 = factorial(K - 1);
            final double part2 = factorial(i) * factorial(K - 1 - i);
            final double part3 = functionBn(features, i);
            sum += part1 / part2 * part3;
        }
        return sum;
    }

    private double functionLaplace(Map<SystemFeature, Double> features, double index) {
        double result;
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        final double p1 = features.get(SystemFeature.p1);
        final double p2 = features.get(SystemFeature.p2);
        final double p3 = features.get(SystemFeature.p3);
        double calculation1 = p1 * (Mu1 / (Mu1 + (index * Lambda)));
        if (Mu2 == 0.0) {
            result = calculation1;
        } else {
            final double Mu2NotZero = p2 * (Mu2 / (Mu2 + (index * Lambda)));
            double calculation2 = calculation1 + Mu2NotZero;
            if (Mu3 == 0.0) {
                result = calculation2;
            } else {
                final double Mu3NotZero = p3 * (Mu3 / (Mu3 + (index * Lambda)));
                result = calculation2 + Mu3NotZero;
            }
        }
        return result;
    }
}
