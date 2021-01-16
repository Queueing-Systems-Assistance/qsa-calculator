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
 * System M | M | n Prior Service.
 */
@Component
public class SystemMMnPriorCalculator {

    public double Lambda(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        return Lambda1 + Lambda2;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda = Lambda(features);
        return Lambda1 / Lambda + Lambda2 / Lambda;
    }

    public double C1(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double a = a(features);
        final double divisor = 1 - c * (1 - a);
        return PNc / divisor - 1;
    }

    public double C2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double PNc = PNc(features);
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
        final double a = a(features);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double dividend = PNc * pow(SAvg, 2);
        final double divisor = pow(c, 2) * pow(1 - a, 2);
        return (2 - PNc) * dividend / divisor;
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double divisor = c * (1 - a);
        return pow(SAvg / divisor, 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double result;
        final double SAvg = SAvg(features);
        final double PNc = PNc(features);
        final double Ro = Ro(features);
        if (Ro == c - 1) {
            result = 2 * (2 * PNc + 1) * SAvg;
        } else {
            final double a = a(features);
            final double dividend = (1 - pow(c, 2) * pow(1 - a, 2)) * pow(SAvg, 2);
            final double divisor = (Ro + 1 - c) * pow(c, 2) * pow(1 - a, 2);
            result = 2 * PNc * dividend / divisor + 2 * pow(SAvg, 2);
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
        final double result;
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        if (Ro(features) == (c - 1)) {
            final double PNc = PNc(features);
            final double part1 = 1 + PNc * Mu * t;
            result = 1 - part1 * pow(E, -Mu * t);
        } else {
            final double a = a(features);
            final double C2 = C2(features);
            final double C1 = C1(features);
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
        final double a = a(features);
        final double PNc = PNc(features);
        return 1 - PNc * exp(-c * Mu * t * (1 - a));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double Ro = Ro(features);
        return QAvg + Ro;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double a = a(features);
        final double PNc = PNc(features);
        return factorial(c) * (1 - a) * PNc / (pow(Ro, c));
    }

    public double PNc(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double recursive = ErlangBRecursive(c, Ro);
        return c * recursive / (c - Ro + Ro * recursive);
    }

    public double PNn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double Ro = Ro(features);
        final double result;
        double sum = 0;
        for (double i = n; i < c; i++) {
            sum += pow(Ro, i) / factorial(i);
        }
        if (c > n) {
            final double P0 = P0(features);
            result = P0 * (sum + pow(Ro, c) / (factorial(c) * (1 - a)));
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
        final double part1 = SAvg / (c * (1 - a));
        final double result = part1 * log10(10 * PNc);
        return max(0, result);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double part1 = SAvg / (c * (1 - a));
        final double result = part1 * log10(20 * PNc);
        return max(0, result);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double r = features.get(SystemFeature.r);
        final double a = a(features);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double part1 = SAvg / (c * (1 - a));
        final double part2 = log10((100 * PNc) / (100 - r));
        final double result = part1 * part2;
        return max(0, result);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        final double result;
        if (c > n) {
            result = P0 * (pow(Ro, n) / factorial(n));
        } else {
            final double divisor = factorial(c) * pow(c, n - c);
            result = pow(Ro, n) / divisor * P0;
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double PNc = PNc(features);
        final double a = a(features);
        return Ro * PNc / (c * (1 - a));
    }

    public double QAvg1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda = Lambda(features);
        final double WAvg1 = WAvg1(features);
        return Lambda1 / Lambda * WAvg1;
    }

    public double QAvg2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda = Lambda(features);
        final double WAvg2 = WAvg2(features);
        return Lambda2 / Lambda * WAvg2;
    }

    public double NAvg1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda = Lambda(features);
        final double TAvg1 = TAvg1(features);
        return Lambda1 / Lambda * TAvg1;
    }

    public double NAvg2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda = Lambda(features);
        final double TAvg2 = TAvg2(features);
        return Lambda2 / Lambda * TAvg2;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Lambda = Lambda(features);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double Lambda = Lambda(features);
        return c - Lambda / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double SAvg = SAvg(features);
        return WAvg + SAvg;
    }

    public double TAvg1(Map<SystemFeature, Double> features) {
        final double WAvg1 = WAvg1(features);
        final double SAvg = SAvg(features);
        return WAvg1 + SAvg;
    }

    public double TAvg2(Map<SystemFeature, Double> features) {
        final double WAvg2 = WAvg2(features);
        final double SAvg = SAvg(features);
        return WAvg2 + SAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double a = a(features);
        return PNc * SAvg / (c * (1 - a));
    }

    public double WAvg2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double SAvg = SAvg(features);
        final double Lambda = Lambda(features);
        final double PNc = PNc(features);
        final double divisorPart1 = 1 - SAvg * Lambda1 / c;
        final double divisorPart2 = 1 - Lambda * SAvg / c;
        final double divisor = c * divisorPart1 * divisorPart2;
        return PNc * SAvg / divisor;
    }

    public double WAvg0(Map<SystemFeature, Double> features) {
        final double PNc = PNc(features);
        return 1 - PNc;
    }

    public double WAvgW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double t = features.get(SystemFeature.t);
        double result = 0;
        if (t > 0) {
            final double a = a(features);
            final double SAvg = SAvg(features);
            result = 1 - exp((-c * t * (1 - a)) / SAvg);
        }
        return result;
    }

    public double WAvg1(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double PNc = PNc(features);
        final double SAvg = SAvg(features);
        final double divisor = c * (1 - (Lambda1 * SAvg / c));
        return PNc * SAvg / divisor;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        return Ro / c;
    }

    public double EDeltar(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = (Lambda1 + Lambda2) * P0;
        return dividend / divisor;
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

