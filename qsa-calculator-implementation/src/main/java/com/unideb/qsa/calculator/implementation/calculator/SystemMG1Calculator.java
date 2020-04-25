package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 Service.
 */
@Component
public class SystemMG1Calculator {

    public double SAvg(Map<SystemFeature, Double> features) {
        return features.get(SystemFeature.eS);
    }

    public double C2S(Map<SystemFeature, Double> features) {
        final double eSPow2 = features.get(SystemFeature.eSPow2);
        final double eS = features.get(SystemFeature.eS);
        return (eSPow2 - pow(eS, 2)) / pow(eS, 2);
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = features.get(SystemFeature.eSPow3);
        final double eSPow2 = features.get(SystemFeature.eSPow2);
        final double Ro = Ro(features);
        double part1 = (pow(Lambda, 3) * eSPow3) / (3 * (1 - Ro));
        double part2 = pow(pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro)), 2);
        double part3 = pow(Lambda, 2) * (3 - 2 * Ro) * eSPow2 / (2 * (1 - Ro));
        double part4 = Ro * (1 - Ro);
        return part1 + part2 + part3 + part4;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = features.get(SystemFeature.eSPow3);
        final double eSPow2 = features.get(SystemFeature.eSPow2);
        final double Ro = Ro(features);
        double part1 = (pow(Lambda, 3) * eSPow3) / (3 * (1 - Ro));
        double part2 = pow(pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro)), 2);
        double part3 = pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro));
        return part1 + part2 + part3;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double ET2 = ET2(features);
        final double TAvg = TAvg(features);
        return ET2 - pow(TAvg, 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double EW2 = EW2(features);
        final double WAvg = WAvg(features);
        return EW2 - pow(WAvg, 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double eSPow2 = features.get(SystemFeature.eSPow2);
        final double EW2 = EW2(features);
        final double Ro = Ro(features);
        return EW2 + eSPow2 / (1 - Ro);
    }

    public double EW2(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = features.get(SystemFeature.eSPow3);
        final double WAvg = WAvg(features);
        final double Ro = Ro(features);
        double part1 = 2 * pow(WAvg, 2);
        double part2 = Lambda * eSPow3 / (3 * (1 - Ro));
        return part1 + part2;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final Double eS = features.get(SystemFeature.eS);
        final double Ro = Ro(features);
        final double C2S = C2S(features);
        return eS / (1 - Ro) * ((1 + C2S) / 2);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double Ro = Ro(features);
        return QAvg + Ro;
    }

    public double PN1(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double WAvg = WAvg(features);
        return Lambda * WAvg;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eS = features.get(SystemFeature.eS);
        return Lambda * eS;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double eS = features.get(SystemFeature.eS);
        final double WAvg = WAvg(features);
        return WAvg + eS;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow2 = features.get(SystemFeature.eSPow2);
        final double Ro = Ro(features);
        final double dividend = Lambda * eSPow2;
        final double divisor = 2 * (1 - Ro);
        return dividend / divisor;
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

}
