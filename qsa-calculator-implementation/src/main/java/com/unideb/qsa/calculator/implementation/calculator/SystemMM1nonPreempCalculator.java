package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 nonPreemp Service.
 */
@Component
public class SystemMM1nonPreempCalculator {

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

    public double NAvg1(Map<SystemFeature, Double> features) {
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        return Ro1 * (1 + Ro2) / (1 - Ro1);
    }

    public double NAvg2(Map<SystemFeature, Double> features) {
        final double Ro2 = Ro2(features);
        final double Ro1 = Ro1(features);
        final double part1 = 1 - Ro1 * (1 - Ro1 - Ro2);
        final double part2 = (1 - Ro1) * (1 - Ro1 - Ro2);
        return Ro2 * part1 / part2;
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
        final double TAvg = TAvg(features);
        final double Ro = Ro(features);
        final double r = features.get(SystemFeature.r);
        final double result = TAvg * log(100 * Ro / (100 - r));
        return max(0, result);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double n = features.get(SystemFeature.n);
        return (1 - Ro) * pow(Ro, n);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return pow(Ro, 2) / (1 - Ro);
    }

    public double QAvg1(Map<SystemFeature, Double> features) {
        final double Ro1 = Ro1(features);
        final double NAvg1 = NAvg1(features);
        return NAvg1 - Ro1;
    }

    public double QAvg2(Map<SystemFeature, Double> features) {
        final double Ro2 = Ro2(features);
        final double NAvg2 = NAvg2(features);
        return NAvg2 - Ro2;
    }

    public double Ro1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda1 / Mu;
    }

    public double Ro2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda2 / Mu;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Mu = features.get(SystemFeature.Mu);
        return (Lambda1 + Lambda2) / Mu;
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

    public double TAvg1(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double dividend = (1 + Ro2) / Mu;
        final double divisor = 1 - Ro1;
        return dividend / divisor;
    }

    public double TAvg2(Map<SystemFeature, Double> features) {
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double Mu = features.get(SystemFeature.Mu);
        final double dividend = 1 - Ro1 * (1 - Ro1 - Ro2);
        final double divisor = Mu * (1 - Ro1) * (1 - Ro1 - Ro2);
        return dividend / divisor;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double NAvg = NAvg(features);
        return NAvg * (1 / Mu);
    }

    public double WAvg1(Map<SystemFeature, Double> features) {
        final double TAvg1 = TAvg1(features);
        final double SAvg = SAvg(features);
        return TAvg1 - SAvg;
    }

    public double WAvg2(Map<SystemFeature, Double> features) {
        final double TAvg2 = TAvg2(features);
        final double SAvg = SAvg(features);
        return TAvg2 - SAvg;
    }
}
