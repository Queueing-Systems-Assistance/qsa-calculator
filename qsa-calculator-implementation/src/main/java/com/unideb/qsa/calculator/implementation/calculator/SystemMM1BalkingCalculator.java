package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | 1 | Balking Service.
 */
@Component
public class SystemMM1BalkingCalculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double bN(Map<SystemFeature, Double> features) {
        return 1 / (1 + features.get(n));
    }

    public double LambdaN(Map<SystemFeature, Double> features) {
        return bN(features) * features.get(Lambda);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) / TAvg(features);
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        return Ro(features) - Math.pow(Math.E, -Ro(features)) * (Math.pow(Math.E, -Ro(features)) + 2 * Ro(features) - 1);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        return Ro(features) * (2 - (Ro(features) + 2) * Math.pow(Math.E, -Ro(features)))
               / (Math.pow(features.get(Mu), 2) * Math.pow(1 - Math.pow(Math.E, -Ro(features)), 2));
    }

    public double D2W(Map<SystemFeature, Double> features) {
        return D2T(features) - Math.pow(SAvg(features), 2);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return Math.pow(Ro(features), features.get(n)) / CalculatorHelper.factorial(features.get(n)) * P0(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        return Math.pow(Math.E, -Ro(features));
    }

    public double US(Map<SystemFeature, Double> features) {
        return 1 - P0(features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) - US(features);
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return Ro(features) / (features.get(Mu) * (1 - Math.pow(Math.E, -Ro(features))));
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return TAvg(features) - 1 / features.get(Mu);
    }

}
