package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | K | K Service.
 */
@Component
public class SystemMMnKKCalculator {

    public double C1(Map<SystemFeature, Double> features) {
        final double C2 = C2(features);
        return 1 + C2;
    }

    public double C2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        final double P0KMinus1 = P0KMinus1(features);
        final double dividend = pow(c, c) * QkAlpha(K - c - 1, c * z);
        final double divisorPart1 = factorial(c) * (c - 1) * factorial(K - c - 1);
        final double divisorPart2 = pkAlpha(K - 1, c * z);
        return dividend / (divisorPart1 * divisorPart2) * P0KMinus1;
    }

    public double D(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0;
        for (double n = 0; n <= c - 1; n++) {
            sum += PinForD(features, n);
        }
        return 1 - sum;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double D = D(features);
        return WAvg / D;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double C1 = C1(features);
        final double SAvg = SAvg(features);
        final double C2 = C2(features);
        final double z = z(features);
        final double dividend = QkAlpha(K - c - 1, c * (z + t * Mu));
        final double divisor = QkAlpha(K - c - 1, c * z);
        double part1 = C1 * exp(-t / SAvg);
        double part2 = C2 * (dividend / divisor);
        return 1 - part1 + part2;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double z = z(features);
        double P0KMinus1 = P0KMinus1(features);
        final double dividend = pow(c, c) * QkAlpha(K - c - 1, c * z) * P0KMinus1;
        final double divisor = factorial(c) * pkAlpha(K - 1, c * z);
        return 1 - dividend / divisor;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        final double WAvg = WAvg(features);
        return K / (E0 + WAvg + SAvg);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        double sumPart1 = 0;
        for (double k = 0; k <= c; k++) {
            final double combinatoricsOfKk = binomialCoefficientDouble((int) K, (int) k);
            sumPart1 += combinatoricsOfKk * pow(z, -k);
        }
        double sumPart2 = 0;
        for (double k = c + 1; k <= K; k++) {
            final double part1 = factorial(k) / (factorial(c) * pow(c, k - c));
            final double combinatoricsOfKk = binomialCoefficientDouble((int) K, (int) k);
            sumPart2 += part1 * combinatoricsOfKk * pow(z, -k);
        }
        return pow(sumPart1 + sumPart2, -1);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double Pn = Pn(features);
        final double NAvg = NAvg(features);
        final double dividend = (K - n) * Pn;
        final double divisor = K - NAvg;
        return dividend / divisor;
    }

    public double PinK(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double z = z(features);
        final double P0KMinus1 = P0KMinus1(features);
        final double part1 = pow(c, c) / factorial(c);
        final double part2Dividend = pkAlpha(K - n - 1, c * z);
        final double part3Divisor = pkAlpha(K - 1, c * z);
        return part1 * (part2Dividend / part3Divisor) * P0KMinus1;
    }

    public double PnKMin1(Map<SystemFeature, Double> features) {
        return PinK(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        final double P0 = P0(features);
        final double z = z(features);
        final double combinatoricsOfKn = binomialCoefficientDouble((int) K, (int) n);
        final double calculation = pow(z, -n) * P0;
        double result;
        if (n > c) {
            final double part1 = factorial(n) / (factorial(c) * pow(c, n - c));
            result = part1 * combinatoricsOfKn * calculation;
        } else {
            result = combinatoricsOfKn * calculation;
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double P0 = P0(features);
        final double z = z(features);
        double result = 0;
        for (double n = c + 1; n <= K; n++) {
            final double part1 = factorial(n) / (factorial(c) * pow(c, n - c));
            final double part2 = binomialCoefficientDouble((int) K, (int) n) * pow(z, -n) * P0;
            final double currentPn = part1 * part2;
            result += (n - c) * currentPn;
        }
        return result;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double SAvg = SAvg(features);
        final double QAvg = QAvg(features);
        final double E0 = E0(features);
        return QAvg * (E0 + SAvg) / (K - QAvg);
    }

    public double z(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        return E0 / SAvg;
    }

    private double PinForD(Map<SystemFeature, Double> features, double n) {
        final Map<SystemFeature, Double> PinFeatures = new HashMap<>();
        features.keySet().forEach(systemFeature -> PinFeatures.put(systemFeature, features.get(systemFeature)));
        PinFeatures.put(SystemFeature.n, n);
        return Pin(PinFeatures);
    }

    private double P0KMinus1(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> P0K1Features = new HashMap<>();
        features.keySet().forEach(systemFeature -> P0K1Features.put(systemFeature, features.get(systemFeature)));
        P0K1Features.put(SystemFeature.K, K - 1);
        return P0(P0K1Features);
    }

    private double QkAlpha(double k, double Alpha) {
        double sum = 0;
        for (double n = 0; n <= k; n++) {
            sum += pow(Alpha, n) / factorial(n);
        }
        return pow(E, -Alpha) * sum;
    }

    private double pkAlpha(double k, double Alpha) {
        final double part1 = pow(Alpha, k);
        final double part2 = factorial(k);
        final double part3 = pow(E, -Alpha);
        return part1 / part2 * part3;
    }
}
