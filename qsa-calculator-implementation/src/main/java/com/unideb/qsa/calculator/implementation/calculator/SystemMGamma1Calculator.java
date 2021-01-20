package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | Gamma | 1 Service.
 */
@Component
public class SystemMGamma1Calculator extends SystemMG1AbstractCalculator {

    @Override
    public double eS(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Mu = features.get(SystemFeature.Mu);
        return Alpha / Mu;
    }

    @Override
    public double eSPow2(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Mu = features.get(SystemFeature.Mu);
        final double dividend = Alpha * (Alpha + 1);
        final double divisor = pow(Mu, 2);
        return dividend / divisor;
    }

    @Override
    public double eSPow3(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        final double Mu = features.get(SystemFeature.Mu);
        final double dividend = Alpha * (Alpha + 1) * (Alpha + 2);
        final double divisor = pow(Mu, 3);
        return dividend / divisor;
    }
}
