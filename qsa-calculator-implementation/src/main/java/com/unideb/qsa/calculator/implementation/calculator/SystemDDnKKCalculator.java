package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.min;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System D | D | n | K | K Service.
 */
@Component
public class SystemDDnKKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double Da = features.get(SystemFeature.Da);
        return 1 / Da;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Ds = features.get(SystemFeature.Ds);
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        return c * a * Ds;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Ds = features.get(SystemFeature.Ds);
        return 1 / Ds;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double z = z(features);
        final double calculation = K / (c * (1 + z));
        return min(1, calculation);
    }

    public double z(Map<SystemFeature, Double> features) {
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        return E0 / SAvg;
    }
}
