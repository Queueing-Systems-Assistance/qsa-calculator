package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
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

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        return 1 / Lambda;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double P0 = P0(features);
        return WAvg / (1 - P0);
    }

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
        final double US = US(features);
        final double SAvg = SAvg(features);
        return US / SAvg;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return K - NAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double US = US(features);
        return K - US / Ro;
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double US = US(features);
        final double part1 = US / Ro;
        final double part2 = (1 - US) / Ro;
        final double part3 = K - part1;
        return part1 - part2 * part3;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        return ErlangBRecursive(K, 1 / Ro);
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

    public double D2W(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double K = features.get(SystemFeature.K);
        Map<SystemFeature, Double> Kmin1Features = copyOf(features);
        Kmin1Features.put(SystemFeature.K, K - 1);
        final double NAvgKmin1 = NAvg(Kmin1Features);
        final double D2NKmin1 = D2N(Kmin1Features);
        final double part1 = 1 / pow(Mu, 2);
        final double part2 = NAvgKmin1 + D2NKmin1;
        return part1 * part2;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double D2W = D2W(features);
        final double fraction = 1 / pow(Mu, 2);
        return D2W + fraction;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double a(Map<SystemFeature, Double> features) {
        return US(features);
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return 1 - NAvg / K;
    }

    public double z(Map<SystemFeature, Double> features) {
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        return E0 / SAvg;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double US = US(features);
        return NAvg - US;
    }

    public double EQpow2(Map<SystemFeature, Double> features) {
        final double US = US(features);
        final double NAvg = NAvg(features);
        final double D2N = D2N(features);
        return D2N + pow(NAvg, 2) - 2 * NAvg + US;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double EQpow2 = EQpow2(features);
        return EQpow2 - pow(QAvg, 2);
    }

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double K = features.get(SystemFeature.K);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = K * Lambda * P0;
        return dividend / divisor;
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if (c == 1) {
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
