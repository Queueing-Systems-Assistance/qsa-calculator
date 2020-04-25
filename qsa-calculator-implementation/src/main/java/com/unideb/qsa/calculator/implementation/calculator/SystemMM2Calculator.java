package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
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
 * System M | M | 2 Service.
 */
@Component
public class SystemMM2Calculator {

    public double D2N(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double D2Q = D2Q(features);
        final double dividend = 1 + a + 2 * pow(a, 2);
        return D2Q + 2 * a * dividend / (1 + a);
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double dividend = 2 * pow(a, 3) * (pow(1 + a, 2) - 2 * pow(a, 3));
        final double divisor = pow(1 - pow(a, 2), 2);
        return dividend / divisor;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double ET2 = ET2(features);
        return ET2 - pow(TAvg, 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double dividend = pow(a, 2) * (1 + a - pow(a, 2)) * pow(SAvg, 2);
        final double divisor = pow(1 - pow(a, 2), 2);
        return dividend / divisor;
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return pow(SAvg / (2 * (1 - a)), 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double SAvg = SAvg(features);
        final double result;
        if (Ro == 1) {
            result = 10.0 / 3.0 * pow(SAvg, 2);
        } else {
            final double a = a(features);
            final double dividend = pow(a, 2) * (1 - 4 * pow(1 - a, 2)) * pow(SAvg, 2);
            final double divisor = (2 * a - 1) * (1 - a) * (1 - pow(a, 2));
            result = dividend / divisor + 2 * pow(SAvg, 2);
        }
        return result;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return SAvg / (2 * (1 - a));
    }


    public double FTt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double Ro = Ro(features);
        final double a = a(features);
        final double result;
        if (Ro == 1.0) {
            result = 1 - (1 + Mu * t / 3) * pow(E, -Mu * t);
        } else {
            final double aPow2 = pow(a, 2);
            final double part1 = (1 - a) / (1 - aPow2 - aPow2 * 2) * pow(E, -Mu * t);
            final double part2 = aPow2 * 2 / (1 - a - aPow2 * 2) * pow(E, (1 - a) * -Mu * t * 2);
            result = 1 + part1 + part2;
        }
        return result;
    }


    public double FWt(Map<SystemFeature, Double> features) {
        final double mu = features.get(Mu);
        final double t = features.get(SystemFeature.t);
        final double a = a(features);
        final double part1 = (2 * pow(a, 2)) / (1 + a);
        final double part2 = exp(-2 * mu * t * (1 - a));
        return 1 - part1 * part2;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        return (2 * a) / (1 - pow(a, 2));
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double a = a(features);
        return (1 - a) / (1 + a);
    }

    public double PN2(Map<SystemFeature, Double> features) {
        final double a = a(features);
        return (2 * pow(a, 2)) / (1 + a);
    }

    public double PNn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double a = a(features);
        return (2 * pow(a, n)) / (1 + a);
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        final double D2T = D2T(features);
        final double TAvg = TAvg(features);
        return sqrt(D2T) * 1.3 + TAvg;
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        final double D2T = D2T(features);
        final double TAvg = TAvg(features);
        return sqrt(D2T) * 2 + TAvg;
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double SAvg = SAvg(features);
        final double part1 = 2 * (1 - a);
        final double part2 = log10((20 * pow(a, 2)) / (1 + a));
        final double tmp = SAvg / part1 * part2;
        return max(0, tmp);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        final double part1 = 2 * (1 - a);
        final double part2 = log10((40 * pow(a, 2)) / (1 + a));
        final double tmp = SAvg / part1 * part2;
        return max(0, tmp);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        final double r = features.get(SystemFeature.r);
        final double a = a(features);
        final double SAvg = SAvg(features);
        final double part1 = SAvg / (2 * (1 - a));
        final double part2 = log10(200 * pow(a, 2) / ((100 - r) * (1 + a)));
        final double tmp = part1 * part2;
        return max(0, tmp);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double P0 = P0(features);
        final double a = a(features);
        return 2 * P0 * pow(a, n);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        return 2 * pow(a, 3) / (1 - pow(a, 2));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return 2 - (Lambda / Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double WAvg = WAvg(features);
        return 1 / Mu + WAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double SAvg = SAvg(features);
        final double dividend = pow(a, 2) * SAvg;
        final double divisor = 1 - pow(a, 2);
        return dividend / divisor;
    }

    public double WAvg0(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double dividend = 1 + a - 2 * pow(a, 2);
        return dividend / (1 + a);
    }

    public double WAvgW(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        double result = 0;
        if (t > 0) {
            final double SAvg = SAvg(features);
            final double a = a(features);
            result = 1 - exp(-2 * t * (1 - a) / SAvg);
        }
        return result;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return Ro / 2;
    }

    public double US(Map<SystemFeature, Double> features) {
        return a(features);
    }
}
