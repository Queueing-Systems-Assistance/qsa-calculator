package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;
import static java.lang.Math.round;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | D | 1 Service.
 */
@Component
public class SystemMD1Calculator extends SystemMG1AbstractCalculator {

    @Override
    public double eS(Map<SystemFeature, Double> features) {
        return features.get(SystemFeature.Ds);
    }

    @Override
    public double eSPow2(Map<SystemFeature, Double> features) {
        final double Ds = features.get(SystemFeature.Ds);
        return pow(Ds, 2);
    }

    @Override
    public double eSPow3(Map<SystemFeature, Double> features) {
        final double Ds = features.get(SystemFeature.Ds);
        return pow(Ds, 3);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        double result;
        if (n == 0.0) {
            result = 1 - Ro;
        } else if (n == 1.0) {
            result = (1 - Ro) * (pow(E, Ro) - 1);
        } else {
            double sum = 0.0;
            for (int i = 1; i <= n; i++) {
                double dividend = pow(-1, n - i) * pow(i * Ro, n - i - 1) * (i * Ro + n - i) * pow(E, i * Ro);
                double divisor = factorial(n - i);
                sum += dividend / divisor;
            }
            result = (1 - Ro) * sum;
        }
        return result;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double eS = eS(features);
        final double n = round(t / eS);
        Map<SystemFeature, Double> PnFeatures = copyOf(features);
        PnFeatures.put(SystemFeature.n, n);
        final double Pn = Pn(PnFeatures);
        final double part1 = Pn * (t - n * eS) / eS;
        double part2 = 0.0;
        for (double i = 0.0; i <= n - 1; i++) {
            PnFeatures.put(SystemFeature.n, i);
            part2 += Pn(PnFeatures);
        }
        return part1 + part2;
    }
}
