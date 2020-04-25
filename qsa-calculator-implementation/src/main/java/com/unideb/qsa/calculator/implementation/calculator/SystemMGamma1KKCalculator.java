package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Gamma | 1 | K | K Service.
 */
@Component
public class SystemMGamma1KKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double SAvg = SAvg(features);
        return a / SAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Beta = features.get(SystemFeature.Beta);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        double sum = 0;
        for (double i = 0; i < K; i++) {
            final double part1 = factorial(K - 1);
            final double part2 = factorial(i) * factorial((K - 1) - i);
            final double part3 = functionBn(i, Alpha, Beta);
            sum += part1 / part2 * part3;
        }
        return pow(1 + K * SAvg / E0 * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Beta = features.get(SystemFeature.Beta);
        return 1 / Beta;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
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

    private double functionBn(double index, double alphaMGamma1KK, double betaMGamma1KK) {
        double result = 1;
        if (index != 0) {
            for (double i = 1; i <= index; i++) {
                final double laplace = functionLaplace(betaMGamma1KK, alphaMGamma1KK, i);
                result *= (1 - laplace) / laplace;
            }
        }
        return result;
    }

    private double functionLaplace(double Beta, double Alpha, double index) {
        double divisor = Beta + index * Alpha;
        return pow(Beta / divisor, Alpha);
    }
}
