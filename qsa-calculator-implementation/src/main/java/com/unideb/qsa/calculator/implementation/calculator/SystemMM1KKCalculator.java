package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Alpha;
import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | 1 | K | K Service.
 */
@Component
public class SystemMM1KKCalculator {

    public double BKz(Map<SystemFeature, Double> features) {
        return P0(features);
    }

    public double E0(Map<SystemFeature, Double> features) {
        return 1 / features.get(Alpha);
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        double tmp = 0;
        for (double i = 0; i <= features.get(K) - 1; i++) {
            tmp += (Math.pow(z(features), i)) / CalculatorHelper.factorial(i);
        }
        return WAvg(features) / (1 - ((Math.pow(z(features), features.get(K) - 1))
                                      / CalculatorHelper.factorial(features.get(K) - 1) / tmp));
    }

    public double FTt(Map<SystemFeature, Double> features) {
        return 1 - (QnxFunc(features.get(K) - 1, z(features) + features.get(t) * features.get(Mu))
                    / QnxFunc(features.get(K) - 1, z(features)));
    }

    public double FWt(Map<SystemFeature, Double> features) {
        return 1 - (QnxFunc(features.get(K) - 2, z(features) + features.get(t) * features.get(Mu))
                    / QnxFunc(features.get(K) - 1, z(features)));
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return a(features) / SAvg(features);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * TAvg(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        double tmp = 0;
        for (int i = 0; i <= features.get(K); i++) {
            tmp += CalculatorHelper.factorial(features.get(K))
                   / (CalculatorHelper.factorial(features.get(K) - i))
                   * (Math.pow(SAvg(features) / (E0(features)), i));
        }
        return Math.pow(tmp, -1);
    }

    public double Pi0(Map<SystemFeature, Double> features) {
        double tmp = 0;
        for (double i = 0; i <= features.get(K) - 1; i++) {
            tmp += Math.pow(z(features), i) / CalculatorHelper.factorial(i);
        }

        return ((Math.pow(z(features), features.get(K) - 1)) / CalculatorHelper.factorial(features.get(K) - 1)) / tmp;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        return (features.get(K) - features.get(n))
               * Pn(features) / (features.get(K) - NAvg(features));
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.factorial(features.get(K)))
               / (features.get(K) - features.get(n))
               * (Math.pow(z(features), -(features.get(n)))) * P0(features);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
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

    public double z(Map<SystemFeature, Double> features) {
        return E0(features) / SAvg(features);
    }

    private double QnxFunc(double nParam, double xParam) {
        double result = 0;
        for (int i = 0; i <= nParam; i++) {
            result += Math.pow(xParam, i) / CalculatorHelper.factorial(i);
        }
        return result * Math.pow(Math.E, -xParam);
    }
}
