package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.c;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | n | K Service.
 */
@Component
public class SystemMMnKCalculator {

    public double EWW0(Map<SystemFeature, Double> features) {
        double tmp1 = 0;

        for (int i = 0; i < features.get(c); i++) {
            tmp1 += ((Math.pow(Ro(features), i) / CalculatorHelper.factorial(i)) * P0(features))
                    / (1 - PK(features));
        }

        return WAvg(features) / (1 - tmp1);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return features.get(Lambda) * (1 - PK(features));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        double tmp1 = 0;
        double tmp2 = P0(features);

        for (int i = 1; i < features.get(c); i++) {
            tmp1 += i * ((Math.pow(Ro(features), i)
                          / CalculatorHelper.factorial(i)) * P0(features));
        }

        for (double i = 1; i < features.get(c); i++) {
            tmp2 += (Math.pow(Ro(features), i)
                     / CalculatorHelper.factorial(i)) * P0(features);
        }
        return QAvg(features) + tmp1 + features.get(c) * (1 - tmp2);
    }

    public double P0(Map<SystemFeature, Double> features) {
        double tmp1 = 0;
        double tmp2 = 0;

        for (int i = 0; i < features.get(c); i++) {
            tmp1 += Math.pow(features.get(Lambda), i) / (CalculatorHelper.factorial(i) * Math.pow(features.get(Mu), i));
        }

        for (double i = features.get(c); i <= features.get(K); i++) {
            tmp2 += Math.pow(features.get(Lambda), i) / (Math.pow(features.get(c), i - features.get(c))
                                                         * CalculatorHelper.factorial(features.get(c)) * Math.pow(features.get(Mu), i));
        }

        return Math.pow(tmp1 + tmp2, -1);
    }

    public double PK(Map<SystemFeature, Double> features) {
        return Math.pow(features.get(Lambda), features.get(K))
               / (Math.pow(features.get(c), features.get(K) - features.get(c))
                  * CalculatorHelper.factorial(features.get(c)) * Math.pow(features.get(Mu), features.get(K))) * P0(features);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        return Pn(features) / (1 - PK(features));
    }

    public double Pn(Map<SystemFeature, Double> features) {
        double result;
        if (features.get(n) <= features.get(c)) {
            result = (Math.pow(Ro(features), features.get(n))
                      / CalculatorHelper.factorial(features.get(n))) * P0(features);
        } else {
            result = (Math.pow(Ro(features), features.get(n))
                      / CalculatorHelper.factorial(features.get(c))) * P0(features)
                     * Math.pow(Ro(features) / features.get(c), features.get(n) - features.get(c));
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return ((Math.pow(Ro(features), features.get(c)) * a(features) * P0(features))
                / (CalculatorHelper.factorial(features.get(c)) * Math.pow(1 - a(features), 2)))
               * (1 + (features.get(K) - features.get(c)) * Math.pow(a(features), features.get(K) - features.get(c) + 1) - (
                                                                                                                                   features.get(K)
                                                                                                                                   - features.get(c)
                                                                                                                                   + 1) * Math.pow(
                a(features), features.get(K) - features.get(c)));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return features.get(c) - features.get(Lambda) / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) / LambdaAvg(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return QAvg(features) / LambdaAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return Ro(features) / features.get(c);
    }
}
