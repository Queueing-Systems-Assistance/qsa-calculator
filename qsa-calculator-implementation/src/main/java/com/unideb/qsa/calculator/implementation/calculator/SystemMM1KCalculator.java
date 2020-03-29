package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | 1 | K Service.
 */
@Component
public class SystemMM1KCalculator {

    public double EWW0(Map<SystemFeature, Double> features) {
        return WAvg(features) / (1 - P0(features));
    }

    public double FWt(Map<SystemFeature, Double> features) {
        double result = 0.0;
        for (double i = 0; i < features.get(K) - 1; i++) {
            result += PiN(i + 1, features) * QnMuT(i, features.get(Mu) * features.get(t));
        }
        return 1 - result;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return (1 - PK(features)) * features.get(Lambda);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        double result;
        if (features.get(Lambda).equals(features.get(Mu))) {
            result = features.get(K) / 2;
        } else {
            result = (Ro(features) * (1 - ((features.get(K) + 1) * Math.pow(Ro(features), features.get(K)))
                                      + (features.get(K) * Math.pow(Ro(features), features.get(K) + 1))))
                     / ((1 - Ro(features)) * (1 - Math.pow(Ro(features), features.get(K) + 1)));
        }
        return result;
    }

    public double P0(Map<SystemFeature, Double> features) {
        return ((1 - Ro(features)) * Math.pow(Ro(features), 0)) / (1 - Math.pow(Ro(features), features.get(K) + 1));
    }

    public double PK(Map<SystemFeature, Double> features) {
        return propN(features.get(K), features);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        return propN(features.get(n), features) / (1 - PK(features));
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return propN(features.get(n), features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) - (1 - P0(features));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) / LambdaAvg(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return QAvg(features) / LambdaAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return (1 - PK(features)) * Ro(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        double result = 0.0;
        for (double i = 0; i < features.get(K); i++) {
            result += PiN(i, features) * QnMuT(i, features.get(Mu) * features.get(t));
        }
        return 1 - result;
    }

    private double QnMuT(double nMmc, double MuTMmc) {
        double result = 0.0;
        for (int i = 0; i <= nMmc; i++) {
            result += Math.pow(MuTMmc, i) / CalculatorHelper.factorial(i);
        }
        return result * Math.pow(Math.E, -MuTMmc);
    }

    private double PiN(double nMm1k, Map<SystemFeature, Double> features) {
        return propN(nMm1k, features) / (1 - PK(features));
    }

    private double propN(double nMm1k, Map<SystemFeature, Double> features) {
        double result;
        if (features.get(Mu).equals(features.get(Lambda))) {
            result = 1 / (features.get(K) + 1);
        } else {
            result = ((1 - Ro(features)) * Math.pow(Ro(features), nMm1k))
                     / (1 - Math.pow(Ro(features), features.get(K) + 1));
        }
        return result;
    }

}
