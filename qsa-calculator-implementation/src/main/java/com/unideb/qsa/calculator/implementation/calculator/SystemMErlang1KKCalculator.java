package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Erlang | 1 | K | K Service.
 */
@Component
public class SystemMErlang1KKCalculator {

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
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        double sum = 0;
        for (double i = 0; i < K; i++) {
            double dividend = factorial(K - 1);
            double divisor = factorial(i) * factorial((K - 1) - i);
            double Bi = Bn(i, Lambda, Mu);
            sum += dividend / divisor * Bi;
        }
        return pow(1 + K * SAvg / E0 * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        double LambdaAvg = LambdaAvg(features);
        double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double TAvg = TAvg(features);
        return TAvg - SAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    private double Bn(double n, double lambda, double mu) {
        double result = 1;
        if (n != 0) {
            for (double i = 1; i <= n; i++) {
                final double laplace = laplaceStieltjes(mu, lambda, i);
                result *= (1 - laplace) / laplace;
            }

        }
        return result;
    }

    private double laplaceStieltjes(double mu, double lambda, double n) {
        return pow(mu / (mu + n * lambda), n);
    }
}
