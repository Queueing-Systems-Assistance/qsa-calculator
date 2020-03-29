package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Alpha;
import static com.unideb.qsa.calculator.domain.SystemFeature.Beta;
import static com.unideb.qsa.calculator.domain.SystemFeature.K;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | Gamma | 1 | K | K Service.
 */
@Component
public class SystemMGamma1KKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        return 1 / features.get(Alpha);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return a(features) / SAvg(features);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * TAvg(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        double sum = 0;
        for (double i = 0; i < features.get(K); i++) {
            sum += (CalculatorHelper.factorial(features.get(K) - 1))
                   / (CalculatorHelper.factorial(i) * CalculatorHelper.factorial((features.get(K) - 1) - i))
                   * functionBn(i, features.get(Alpha), features.get(Beta));
        }
        return Math.pow(1 + features.get(K) * SAvg(features) / E0(features) * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * WAvg(features);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Beta);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return features.get(K) / LambdaAvg(features) - E0(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return TAvg(features) - SAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return 1 - P0(features);
    }

    private double functionBn(double index, double alphaMGamma1KK, double betaMGamma1KK) {
        double result = 1;
        if (index != 0) {
            for (double i = 1; i <= index; i++) {
                result *= (1 - functionLaplace(betaMGamma1KK, alphaMGamma1KK, i))
                          / functionLaplace(betaMGamma1KK, alphaMGamma1KK, i);
            }
        }
        return result;
    }

    private double functionLaplace(double betaMGamma1KK, double alphaMGamma1KK, double index) {
        return Math.pow(betaMGamma1KK / (betaMGamma1KK + index * alphaMGamma1KK), alphaMGamma1KK);
    }
}
