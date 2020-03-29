package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | Inf Service.
 */
@Component
public class SystemMMInfCalculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        return 1 - Math.pow(Math.E, -(features.get(Mu) * features.get(t)));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return (Math.pow(Ro(features), features.get(n)) / CalculatorHelper.factorial(features.get(n))) * Math.pow(Math.E, -Ro(features));
    }

    public double P0(Map<SystemFeature, Double> features) {
        return Math.pow(Math.E, -Ro(features));
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return 0;
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

    public double WAvg(Map<SystemFeature, Double> features) {
        return 0;
    }
}
