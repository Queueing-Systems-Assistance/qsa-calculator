package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

@Component
public class SystemMMcmKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double P0Inverse = P0InverseRecursive(Ro, K, c, m);
        return pow(P0Inverse, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return 0.0;
    }

    private double P0InverseRecursive(double Ro, double K, double c, double m) {
        if(c == m) {
            double sum = 0.0;
            for(double i = 0.0; i <= c; i++) {
                sum += binomialCoefficientDouble((int)K, (int)i) * pow(Ro, i);
            }
            return sum;
        } else {
            final double recursive = P0InverseRecursive(Ro, K, c, m - 1);
            final double dividend = binomialCoefficientDouble((int)K, (int)m) * factorial(m) * pow(Ro, m);
            final double divisor = factorial(c) * pow(c, m - K);
            return recursive + dividend / divisor;
        }
    }
}
