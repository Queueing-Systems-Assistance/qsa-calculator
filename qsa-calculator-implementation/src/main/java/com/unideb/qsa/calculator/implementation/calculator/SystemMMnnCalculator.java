package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.exp;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | n Service.
 */
@Component
public class SystemMMnnCalculator {

    public double BcRo(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        return ErlangBRecursive(c, Ro);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double SAvg = SAvg(features);
        return 1 - exp(-t / SAvg);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double BcRo = BcRo(features);
        return Lambda * (1 - BcRo);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double SAvg = SAvg(features);
        return LambdaAvg * SAvg;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        double number = 0;
        for (int i = 0; i <= n; i++) {
            number += pow(Ro, i) / factorial(i);
        }
        return pow(Ro, n) / factorial(n) / number;

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

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double LambdaAvg = LambdaAvg(features);
        final double SAvg = SAvg(features);
        return LambdaAvg * SAvg / c;
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if (c == 1.0) {
            result = Ro / (1 + Ro);
        } else {
            final double recursive = ErlangBRecursive(c - 1, Ro);
            result = Ro * recursive / (c + Ro * recursive);
        }
        return result;
    }

}
