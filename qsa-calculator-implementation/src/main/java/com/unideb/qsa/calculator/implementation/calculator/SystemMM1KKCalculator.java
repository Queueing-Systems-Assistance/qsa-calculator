package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 | K | K Service.
 */
@Component
public class SystemMM1KKCalculator {
    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double P0 = P0(features);
        return WAvg / (1 - P0);
    }
    //?
    public double PTt(Map<SystemFeature, Double> features) {
        return FTt(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = Qnx(K - 1, z + t * Mu);
        final double divisor = Qnx(K - 1, z);
        return 1 - dividend / divisor;
    }

    public double PWt(Map<SystemFeature, Double> features) {
        return FWt(features);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = Qnx(K - 2, z + t * Mu);
        final double divisor = Qnx(K - 1, z);
        return 1 - dividend / divisor;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double SAvg = SAvg(features);
        return a / SAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        return ErlangBRecursive(K, z);
    }

    public double Pi0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        final double dividend = K * P0;
        final double divisor = K - NAvg;
        return dividend / divisor;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        final double Pn = Pn(features);
        final double dividend = (K - n) * Pn;
        final double divisor = K - NAvg;
        return dividend / divisor;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double z = z(features);
        final double P0 = P0(features);
        final double fraction = factorial(K) / factorial(K - n);
        return fraction * pow(z, -1 * n) * P0;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double E0 = E0(features);
        final double LambdaAvg = LambdaAvg(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double z(Map<SystemFeature, Double> features) {
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        return E0 / SAvg;
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if(c == 1) {
            result = Ro / (1 + Ro);
        } else {
            final double recursive = ErlangBRecursive(c - 1, Ro);
            final double dividend = Ro * recursive;
            final double divisor = c + Ro * recursive;
            result = dividend / divisor;
        }
        return result;
    }

    private double Qnx(double n, double x) {
        double result = 0;
        for (double k = 0; k <= n; k++) {
            result += pow(x, k) / factorial(k);
        }
        return pow(E, -1 * x) * result;
    }
}
