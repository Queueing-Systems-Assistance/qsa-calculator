package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 Service.
 */
@Component
public class SystemMM1Calculator {

    public double D2N(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return Ro / pow(1 - Ro, 2);
    }

    public double US(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double dividend = pow(Ro, 2) * (1 + Ro - pow(Ro, 2));
        final double divisor = pow(1 - Ro, 2);
        return dividend / divisor;
    }

    public double D2QQ0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return Ro / pow(1 - Ro, 2);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro = Ro(features);
        final double divisor = Mu * (1 - Ro);
        return pow(1 / divisor, 2);
    }

    public double D2TLCFS(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double eSPow2 = 2 / pow(Mu, 2);
        final double eSPow3 = 6 / pow(Mu, 3);
        final double Ro = Ro(features);
        final double part1 = Lambda * eSPow3 / (3 * (1 - Ro));
        final double part2 = pow(Lambda, 2) * (1 + Ro) * pow(eSPow2, 2) / (4 * pow(1 - Ro, 3));
        return part1 + part2;
    }

    public double D2TSIRO(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double eSPow2 = 2 / pow(Mu, 2);
        final double eSPow3 = 6 / pow(Mu, 3);
        final double Ro = Ro(features);
        final double part1 = 2 * Lambda * eSPow3 / (3 * (1 - Ro) * (2 - Ro));
        final double part2 = pow(Lambda, 2) * (2 + Ro) * pow(eSPow2, 2) / (4 * pow(1 - Ro, 2) * (2 - Ro));
        return part1 + part2;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double SAvg = SAvg(features);
        final double dividend = (2 - Ro) * Ro * pow(SAvg, 2);
        final double divisor = pow(1 - Ro, 2);
        return dividend / divisor;
    }

    public double EQQ0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return 1 / (1 - Ro);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double PTt = PTt(features);
        return 1 - PTt;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double PWt = PWt(features);
        return 1 - PWt;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return Ro / (1 - Ro);
    }

    public double PNn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        return pow(Ro, n);
    }

    public double PTt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double TAvg = TAvg(features);
        return exp(-t / TAvg);
    }

    public double PWt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double Ro = Ro(features);
        final double TAvg = TAvg(features);
        return Ro * exp(-t / TAvg);
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        return TAvg * log(10);
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        return TAvg * log(20);
    }

    public double PiTr(Map<SystemFeature, Double> features) {
        final double r = features.get(SystemFeature.r);
        final double TAvg = TAvg(features);
        return TAvg * log(100 / (100 - r));
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double Ro = Ro(features);
        final double result = TAvg * log(10 * Ro);
        return max(0, result);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double Ro = Ro(features);
        final double result = TAvg * log(20 * Ro);
        return max(0, result);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        final double r = features.get(SystemFeature.r);
        final double TAvg = TAvg(features);
        final double Ro = Ro(features);
        final double result = TAvg * log(100 * Ro / (100 - r));
        return max(0, result);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        return (1 - Ro) * pow(Ro, n);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return pow(Ro, 2) / (1 - Ro);
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
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro = Ro(features);
        return 1 / (Mu * (1 - Ro));
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double NAvg = NAvg(features);
        return NAvg * (1 / Mu);
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro = Ro(features);
        return 1 / (Mu * (1 - Ro));
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        final double EWW0 = EWW0(features);
        return pow(EWW0, 2);
    }

    public double ENdDelta(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return 1 / (1 - Ro);
    }

    public double D2NdDelta(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double eSPow2 = 2 / pow(Mu, 2);
        final double Ro = Ro(features);
        final double dividend = Ro * (1 - Ro) + pow(Lambda, 2) * eSPow2;
        final double divisor = pow(1 - Ro, 3);
        return dividend / divisor;
    }

    public double PNdDeltan(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        double result = 0.0;
        if (n > 0.0) {
            final double Ro = Ro(features);
            final double part1 = 1 / n;
            final double part2 = binomialCoefficientDouble(2 * (int)n - 2, (int)n - 1);
            final double part3 = pow(Ro, n - 1) / pow(1 + Ro, 2 * n - 1);
            result = part1 * part2 * part3;
        }
        return result;
    }

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double eS = 1 / Mu;
        final double Ro = Ro(features);
        return eS / (1 - Ro);
    }

    public double VarDelta(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double eS = 1 / Mu;
        final double eSPow2 = 2 / pow(Mu, 2);
        final double Ro = Ro(features);
        final double part1 = eSPow2 / pow(1 - Ro, 3);
        final double part2 = pow(eS, 2) / pow(1 - Ro, 2);
        return part1 - part2;
    }
}
