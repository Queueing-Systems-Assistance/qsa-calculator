package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.exp;
import static java.lang.Math.log10;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n Service.
 */
@Component
public class SystemMMnCalculator {

    public double C1(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double a = a(features);
        final double divisor = 1 - c * (1 - a);
        return PNc / divisor - 1;
    }

    public double C2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double a = a(features);
        final double divisor = c * (1 - a) - 1;
        return PNc / divisor;
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double D2Q = D2Q(features);
        final double Ro = Ro(features);
        final double PNc = PNc(features);
        return D2Q + Ro * (1 + PNc);
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double PNc = PNc(features);
        final double dividend = 1 + a - a * PNc;
        final double divisor = pow(1 - a, 2);
        return a * PNc * dividend / divisor;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double ET2 = ET2(features);
        final double TAvg = TAvg(features);
        return ET2 - pow(TAvg, 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double dividendPart1 = 2 - PNc;
        final double dividendPart2 = PNc * pow(SAvg, 2);
        final double divisor = pow(c, 2) * pow(1 - a, 2);
        return dividendPart1 * dividendPart2 / divisor;
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double SAvg = SAvg(features);
        final double a = a(features);
        return pow(SAvg / (c * (1 - a)), 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double Ro = Ro(features);
        final double SAvg = SAvg(features);
        final double result;
        if (Ro == c - 1) {
            result = 2 * (2 * PNc + 1) * pow(SAvg, 2);
        } else {
            final double a = a(features);
            final double part1 = 2 * PNc * (1 - pow(c, 2) * pow(1 - a, 2)) * pow(SAvg, 2);
            final double part2 = (Ro + 1 - c) * pow(c, 2) * pow(1 - a, 2);
            final double part3 = 2 * pow(SAvg, 2);
            result = part1 / part2 + part3;
        }
        return result;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double SAvg = SAvg(features);
        final double a = a(features);
        return SAvg / (c * (1 - a));
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double Ro = Ro(features);
        final double result;
        if (Ro == c - 1.0) {
            final double PNc = PNc(features);
            final double part1 = 1 + PNc * Mu * t;
            final double part2 = pow(E, -Mu * t);
            result = 1 - part1 * part2;
        } else {
            final double C1 = C1(features);
            final double C2 = C2(features);
            final double a = a(features);
            final double part1 = C1 * pow(E, -Mu * t);
            final double part2 = C2 * pow(E, -Mu * t * c * (1 - a));
            result = 1 + part1 + part2;
        }
        return result;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double PNc = PNc(features);
        final double a = a(features);
        final double calculation = exp(-c * Mu * t * (1 - a));
        return 1 - PNc * calculation;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double Ro = Ro(features);
        return QAvg + Ro;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double Ro = Ro(features);
        final double PNc = PNc(features);
        final double dividend = factorial(c) * (1 - a) * PNc;
        return dividend / pow(Ro, c);
    }

    public double PNc(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double recursive = ErlangBRecursive(c, Ro);
        final double divisor = c - Ro + Ro * recursive;
        return c * recursive / divisor;
    }

    public double PNn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double Ro = Ro(features);
        double result;
        if (n < c) {
            double sum = 0;
            for (double k = n; k <= c - 1; k++) {
                sum += pow(Ro, k) / factorial(k);
            }
            final double P0 = P0(features);
            final double calculation = pow(Ro, c) / (factorial(c) * (1 - a));
            result = P0 * (sum + calculation);
        } else {
            final double PNc = PNc(features);
            result = PNc * pow(a, n - c);
        }
        return result;
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double D2T = D2T(features);
        return TAvg + 1.3 * sqrt(D2T);
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double D2T = D2T(features);
        return TAvg + 2 * sqrt(D2T);
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double PNc = PNc(features);
        final double part1 = c * (1 - a);
        final double part2 = log10(10 * PNc);
        final double result = SAvg / part1 * part2;
        return max(0, result);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double num = 20 * PNc;
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double part1 = c * (1 - a);
        final double result = SAvg / part1 * log10(num);
        return max(0, result);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double r = features.get(SystemFeature.r);
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double PNc = PNc(features);
        final double part1 = c * (1 - a);
        final double part2 = log10((100 * PNc) / (100 - r));
        final double result = SAvg / part1 * part2;
        return max(0, result);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        final double result;
        if (c >= n) {
            result = P0 * (pow(Ro, n) / factorial(n));
        } else {
            result = P0 * (pow(Ro, n) / (factorial(c) * pow(c, n - c)));
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double Ro = Ro(features);
        final double a = a(features);
        return Ro * PNc / (c * (1 - a));
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
        final double SAvg = SAvg(features);
        final double WAvg = WAvg(features);
        return WAvg + SAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double PNc = PNc(features);
        final double dividend = PNc * SAvg;
        final double divisor = c * (1 - a);
        return dividend / divisor;
    }

    public double WAvg0(Map<SystemFeature, Double> features) {
        return 1 - PNc(features);
    }

    public double WAvgW(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double SAvg = SAvg(features);
        final double calculation = exp((-c * t * (1 - a)) / SAvg);
        return 1 - calculation;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        return Ro / c;
    }

    public double US(Map<SystemFeature, Double> features) {
        return a(features);
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if (c == 1) {
            result = Ro / (1 + Ro);
        } else {
            final double recursive = ErlangBRecursive(c - 1, Ro);
            result = Ro * recursive / (c + Ro * recursive);
        }
        return result;
    }
}
