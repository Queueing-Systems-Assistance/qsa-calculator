package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.exp;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | K | K Service.
 */
@Component
public class SystemMMnKKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double PW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0;
        for (double n = 0; n <= c - 1; n++) {
            Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, n);
            double Pin = Pin(PinFeatures);
            sum += Pin;
        }
        return 1 - sum;
    }

    public double cAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        double sum = 0.0;
        for(double i = 1.0; i <= K; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            if (i <= c) {
                sum += Pi * i;
            } else {
                sum += Pi * c;
            }
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return K - NAvg;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        return 1 / Lambda;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double cAvg = cAvg(features);
        return cAvg / c;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double mAvg = mAvg(features);
        return mAvg / K;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double D = PW(features);
        return WAvg / D;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double C1 = C1(features);
        final double SAvg = SAvg(features);
        final double C2 = C2(features);
        final double z = Mu / Lambda;
        final double dividend = QkLambda(K - c - 1, c * (z + t * Mu));
        final double divisor = QkLambda(K - c - 1, c * z);
        double part1 = C1 * exp(-t / SAvg);
        double part2 = C2 * (dividend / divisor);
        return 1 - part1 + part2;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double t = features.get(SystemFeature.t);
        final double z = Mu / Lambda;
        double P0KMinus1 = P0KMinus1(features);
        final double dividend = pow(c, c) * QkLambda(K - c - 1, c * (z + Mu * t)) * P0KMinus1;
        final double divisor = factorial(c) * pkLambda(K - 1, c * z);
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
        final double Ro = Ro(features);
        double sum = 0.0;
        for (double i = 1.0; i <= K; i++) {
            sum += anRecursive(c, K, i, Ro);
        }
        return 1 / (1 + sum);
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
        final double K = features.get(SystemFeature.K);
        Map<SystemFeature, Double> PinKFeatures = copyOf(features);
        PinKFeatures.put(SystemFeature.K, K - 1);
        return Pn(PinKFeatures);
    }

    public double PnKMin1(Map<SystemFeature, Double> features) {
        return PinK(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        final double an = anRecursive(c, K, n, Ro);
        return an * P0;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        double result = 0;
        for (double n = c + 1; n <= K; n++) {
            Map<SystemFeature, Double> PnFeatures = copyOf(features);
            PnFeatures.put(SystemFeature.n, n);
            final double Pn = Pn(PnFeatures);
            result += (n - c) * Pn;
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

    private double anRecursive(double c, double K, double n, double Ro) {
        double result;
        if (n == 0.0) {
            result = 1.0;
        } else {
            final double recursive = anRecursive(c, K, n - 1, Ro);
            result = (K - n + 1) * recursive * Ro;
            if (n < c) {
                result /= n;
            } else {
                result /= c;
            }
        }
        return result;
    }

    private double C1(Map<SystemFeature, Double> features) {
        final double C2 = C2(features);
        return 1 + C2;
    }

    private double C2(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = Mu / Lambda;
        final double P0KMinus1 = P0KMinus1(features);
        final double dividend = pow(c, c) * QkLambda(K - c - 1, c * z);
        final double divisorPart1 = factorial(c) * (c - 1) * factorial(K - c - 1);
        final double divisorPart2 = pkLambda(K - 1, c * z);
        return dividend / (divisorPart1 * divisorPart2) * P0KMinus1;
    }

    private double P0KMinus1(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> P0K1Features = copyOf(features);
        P0K1Features.put(SystemFeature.K, K - 1);
        return P0(P0K1Features);
    }

    private double QkLambda(double k, double lambda) {
        double sum = 0;
        for (double n = 0; n <= k; n++) {
            sum += pow(lambda, n) / factorial(n);
        }
        return pow(E, -lambda) * sum;
    }

    private double pkLambda(double k, double lambda) {
        final double part1 = pow(lambda, k);
        final double part2 = factorial(k);
        final double part3 = pow(E, -lambda);
        return part1 / part2 * part3;
    }
}
