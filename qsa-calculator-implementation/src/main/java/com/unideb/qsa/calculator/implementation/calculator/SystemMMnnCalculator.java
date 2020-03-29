package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.c;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | n | n Service.
 */
@Component
public class SystemMMnnCalculator {

    public double BcRo(Map<SystemFeature, Double> features) {
        return ErlangBRecursive(features.get(c), Ro(features));
    }

    public double FTt(Map<SystemFeature, Double> features) {
        return 1 - Math.exp(-features.get(t) / SAvg(features));
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return features.get(Lambda) * (1 - BcRo(features));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * SAvg(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        double number = 0;
        for (int i = 0; i <= features.get(n); i++) {
            number += Math.pow(Ro(features), i) / CalculatorHelper.factorial(i);
        }
        return (Math.pow(Ro(features), features.get(n)) / CalculatorHelper.factorial(features.get(n))) / number;

    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return SAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return (LambdaAvg(features) * SAvg(features)) / features.get(c);
    }

    private double ErlangBRecursive(double cMmcc, double roMmcc) {
        double result;
        if (cMmcc == 1) {
            result = roMmcc / (1 + roMmcc);
        } else {
            double recursive = ErlangBRecursive(cMmcc - 1, roMmcc);
            result = roMmcc * recursive / (cMmcc + roMmcc * recursive);
        }
        return result;
    }

}
