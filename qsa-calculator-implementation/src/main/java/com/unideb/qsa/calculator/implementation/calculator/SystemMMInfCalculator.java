package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | Inf Service.
 */
@Component
public class SystemMMInfCalculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        return 1 - pow(E, -Mu * t);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        return pow(Ro, n) / factorial(n) * pow(E, -Ro);
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return pow(E, -Ro);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return 0;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return SAvg(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return 0;
    }
}
