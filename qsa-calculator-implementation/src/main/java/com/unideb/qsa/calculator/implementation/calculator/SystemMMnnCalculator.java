package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
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
        return 1 - exp(-1 * t / SAvg);
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

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        double sum = 0;
        for (double k = 0; k <= c; k++) {
            sum += pow(Lambda / Mu, k) * (1 / factorial(k));
        }
        return pow(sum, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        double result;
        if (n == 0) {
            result = P0(features);
        } else {
            final double Ro = Ro(features);
            result = ErlangBRecursive(n, Ro);
        }
        return result;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double SAvg = SAvg(features);
        return Lambda * SAvg;
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

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double eAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double Lambda = features.get(SystemFeature.Lambda);
        Map<SystemFeature, Double> PcFeatures = copyOf(features);
        PcFeatures.put(SystemFeature.n, c);
        final double Pc = Pn(PcFeatures);
        final double divisor = Lambda * (1 - Pc);
        return c / divisor - 1 / Mu;
    }

    public double EDelta(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double Lambda = features.get(SystemFeature.Lambda);
        return Ro / Lambda;
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if (c == 1.0) {
            result = Ro / (1 + Ro);
        } else {
            final double recursive = Ro * ErlangBRecursive(c - 1, Ro);
            result = recursive / (c + recursive);
        }
        return result;
    }

}
