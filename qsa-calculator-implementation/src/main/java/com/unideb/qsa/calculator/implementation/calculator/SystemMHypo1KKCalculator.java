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
        final double Lambda = features.get(SystemFeature.Lambda);
        return 1 / Lambda;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return a / SAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        double result = 0;
        if (Mu2 == 0.0) {
            result = 1 / Mu1;
        } else {
            if (Mu3 == 0.0) {
                final double divisor = (Mu1 + Mu2) / 2;
                result = 1 / divisor;
            } else if (Mu2 != 0.0 && Mu3 != 0.0) {
                final double divisor = (Mu1 + Mu2 + Mu3) / 3;
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
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu1 = features.get(SystemFeature.Mu1);
        final double Mu2 = features.get(SystemFeature.Mu2);
        final double Mu3 = features.get(SystemFeature.Mu3);
        double result = 1;
        double calculation = Mu1 / (Mu1 + (index * Lambda));
        if (Mu2 == 0.0) {
            result *= calculation;
        } else {
            double calculation2 = Mu2 / (Mu2 + (index * Lambda));
            if (Mu3 == 0.0) {
                result *= calculation * calculation2;
            } else {
                double calculation3 = Mu3 / (Mu3 + (index * Lambda));
                result *= calculation * calculation2 * calculation3;
            }
        }
        return result;
    }
}
