package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.pow;

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

}
