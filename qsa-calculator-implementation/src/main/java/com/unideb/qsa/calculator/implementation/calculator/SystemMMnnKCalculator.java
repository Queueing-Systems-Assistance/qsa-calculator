package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | n | K Service.
 */
@Component
public class SystemMMnnKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        return LambdaFin / Mu;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        double sum = 0;
        for (int i = 0; i <= c; i++) {
            sum += binomialCoefficientDouble((int) KFin, i) * pow(Ro, i);
        }
        return 1 / sum;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        return binomialCoefficientDouble((int) KFin, (int) n) * pow(Ro, n) * P0;
    }

    public double PinFin(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double c = features.get(SystemFeature.c);
        final double Pn = Pn(features);
        final double dividend = (KFin - n) * Pn;
        double divisor = 0.0;
        for (double i = 0.0; i <= c - 1; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            divisor += (KFin - i) * Pi;
        }
        return dividend / divisor;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double NAvg = NAvg(features);
        return NAvg / c;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double NAvg = NAvg(features);
        return Mu * NAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0;
        for (double i = 0; i <= c; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            sum += i * Pi;
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return KFin - NAvg;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double mAvg = mAvg(features);
        return mAvg / KFin;
    }

    public double ETau(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ut = Ut(features);
        return Ut / (Mu * (1 - Ut));
    }

    public double ENR(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double ETau = ETau(features);
        return LambdaFin * ETau;
    }

    public double PBKc(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double KFin = features.get(SystemFeature.KFin);
        final double c = features.get(SystemFeature.c);
        return EngsetRecursive(KFin, c, Ro);
    }

    public double EDeltar(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double KFin = features.get(SystemFeature.KFin);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = KFin * LambdaFin * P0;
        return dividend / divisor;
    }

    private double EngsetRecursive(double K, double c, double Ro) {
        double result;
        if (c == 1) {
            final double base = (K - 1) * Ro;
            result = base / (1 + base);
        } else {
            final double recursive = EngsetRecursive(K, c - 1, Ro);
            final double base = (K - c) * Ro * recursive;
            result = base / (c + base);
        }
        return result;
    }
}
