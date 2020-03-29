package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Alpha;
import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.mu1;
import static com.unideb.qsa.calculator.domain.SystemFeature.mu2;
import static com.unideb.qsa.calculator.domain.SystemFeature.mu3;
import static com.unideb.qsa.calculator.domain.SystemFeature.p1;
import static com.unideb.qsa.calculator.domain.SystemFeature.p2;
import static com.unideb.qsa.calculator.domain.SystemFeature.p3;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | Hyper | 1 | K | K Service.
 */
@Component
public class SystemMHyper1KKCalculator {

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
        double sum = functionCnK(features.get(K), features);
        return Math.pow(1 + features.get(K) * SAvg(features) / E0(features) * sum, -1);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * WAvg(features);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        double result = 1 / features.get(mu1);
        if (features.get(mu2) != 0.0 && features.get(mu3) != 0.0) {
            result = 1 / ((features.get(mu1) + features.get(mu2) + features.get(mu3)) / CalculatorHelper.VALUE_3);
        } else if (features.get(mu3) == 0.0 && features.get(mu2) != 0.0) {
            result = 1 / ((features.get(mu1) + features.get(mu2)) / CalculatorHelper.VALUE_2);
        }
        return result;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return features.get(K) / LambdaAvg(features) + E0(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return TAvg(features) - SAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return 1 - P0(features);
    }

    private double functionBn(Map<SystemFeature, Double> features, double index) {
        double result = 1;
        if (index != 0) {
            for (double i = 1; i <= index; i++) {
                result *= (1 - functionLaplace(features, i)) / functionLaplace(features, i);
            }
        }
        return result;
    }

    private double functionCnK(double K, Map<SystemFeature, Double> features) {
        double sum = 0;
        for (double i = 0; i < K; i++) {
            sum += (CalculatorHelper.factorial(K - 1) / (CalculatorHelper.factorial(i) * CalculatorHelper.factorial(K - 1 - i)))
                   * functionBn(features, i);
        }
        return sum;
    }

    private double functionLaplace(Map<SystemFeature, Double> features, double index) {
        double result = 1;
        if (features.get(mu2) == 0.0) {
            result *= features.get(p1) * (features.get(mu1) / (features.get(mu1) + (index * features.get(Alpha))));
        } else if (features.get(mu3) == 0.0) {
            result *= features.get(p1) * (features.get(mu1) / (features.get(mu1) + (index * features.get(Alpha))))
                      + (features.get(p2) * (features.get(mu2) / (features.get(mu2) + (index * features.get(Alpha)))));
        } else {
            result *= (features.get(p1) * (features.get(mu1) / (features.get(mu1) + (index * features.get(Alpha))))
                       + (features.get(p2) * (features.get(mu2) / (features.get(mu2) + (index * features.get(Alpha))))))
                      + (features.get(p3) * (features.get(mu3) / (features.get(mu3) + (index * features.get(Alpha)))));
        }
        return result;
    }
}
