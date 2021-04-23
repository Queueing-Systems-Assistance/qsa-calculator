package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Map;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 Service.
 * Abstract class providing calculations for M | G | 1 systems.
 */
public abstract class SystemMG1AbstractCalculator {

    public abstract double eS(Map<SystemFeature, Double> features);

    public abstract double eSPow2(Map<SystemFeature, Double> features);

    public abstract double eSPow3(Map<SystemFeature, Double> features);

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eS = eS(features);
        return Lambda * eS;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return 1 - Ro;
    }

    public double PN1(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return eS(features);
    }

    public double C2S(Map<SystemFeature, Double> features) {
        final double eSPow2 = eSPow2(features);
        final double eS = eS(features);
        return (eSPow2 - pow(eS, 2)) / pow(eS, 2);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        final double dividend = Lambda * eSPow2;
        final double divisor = 2 * (1 - Ro);
        return dividend / divisor;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double eS = eS(features);
        final double Ro = Ro(features);
        final double C2S = C2S(features);
        final double part1 = eS / (1 - Ro);
        final double part2 = (1 + C2S) / 2;
        return part1 * part2;
    }

    public double EW2(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = eSPow3(features);
        final double WAvg = WAvg(features);
        final double Ro = Ro(features);
        double part1 = 2 * pow(WAvg, 2);
        double part2 = Lambda * eSPow3 / (3 * (1 - Ro));
        return part1 + part2;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double EW2 = EW2(features);
        final double WAvg = WAvg(features);
        return EW2 - pow(WAvg, 2);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double eS = eS(features);
        final double WAvg = WAvg(features);
        return WAvg + eS;
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double eSPow2 = eSPow2(features);
        final double EW2 = EW2(features);
        final double Ro = Ro(features);
        return EW2 + eSPow2 / (1 - Ro);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double ET2 = ET2(features);
        final double TAvg = TAvg(features);
        return ET2 - pow(TAvg, 2);
    }

    public double D2TLCFS(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eS = eS(features);
        final double eSPow2 = eSPow2(features);
        final double eSPow3 = eSPow3(features);
        final double Ro = Ro(features);
        final double D2S = eSPow2 - pow(eS, 2);
        final double part1 = Lambda * eSPow3 / (3 * pow(1 - Ro, 2));
        final double part2 = pow(Lambda, 2) * (1 + Ro) * pow(eSPow2, 2) / (4 * pow(1 - Ro, 3));
        return D2S + part1 + part2;
    }

    public double D2TSIRO(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eS = eS(features);
        final double eSPow2 = eSPow2(features);
        final double eSPow3 = eSPow3(features);
        final double Ro = Ro(features);
        final double D2S = eSPow2 - pow(eS, 2);
        final double part1 = 2 * Lambda * eSPow3 / (3 * (1 - Ro) * (2 - Ro));
        final double part2 = pow(Lambda, 2) * (2 + Ro) * pow(eSPow2, 2) / (4 * pow(1 - Ro, 2) * (2 - Ro));
        return D2S + part1 + part2;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double WAvg = WAvg(features);
        return Lambda * WAvg;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = eSPow3(features);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        double part1 = (pow(Lambda, 3) * eSPow3) / (3 * (1 - Ro));
        double part2 = pow(pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro)), 2);
        double part3 = pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro));
        return part1 + part2 + part3;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double Ro = Ro(features);
        return QAvg + Ro;
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow3 = eSPow3(features);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        double part1 = (pow(Lambda, 3) * eSPow3) / (3 * (1 - Ro));
        double part2 = pow(pow(Lambda, 2) * eSPow2 / (2 * (1 - Ro)), 2);
        double part3 = pow(Lambda, 2) * (3 - 2 * Ro) * eSPow2 / (2 * (1 - Ro));
        double part4 = Ro * (1 - Ro);
        return part1 + part2 + part3 + part4;
    }

    public double ENdDelta(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return 1 / (1 - Ro);
    }

    public double D2NdDelta(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        final double dividend = Ro * (1 - Ro) + pow(Lambda, 2) * eSPow2;
        final double divisor = pow(1 - Ro, 3);
        return dividend / divisor;
    }

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double eS = eS(features);
        final double Ro = Ro(features);
        return eS / (1 - Ro);
    }

    public double VarDelta(Map<SystemFeature, Double> features) {
        final double eS = eS(features);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        final double part1 = eSPow2 / pow(1 - Ro, 3);
        final double part2 = pow(eS, 2) / pow(1 - Ro, 2);
        return part1 - part2;
    }

    public double PiT90Appr(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double D2T = D2T(features);
        return TAvg + 1.3 * sqrt(D2T);
    }

    public double PiT95Appr(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double D2T = D2T(features);
        return TAvg + 2.0 * sqrt(D2T);
    }

    public double ECost(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double CS = features.get(SystemFeature.CS);
        final double CWS = features.get(SystemFeature.CWS);
        final double CI = features.get(SystemFeature.CI);
        final double CSR = features.get(SystemFeature.CSR);
        final double R = features.get(SystemFeature.R);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return CS + NAvg * CWS + P0 * CI + CSR - Lambda * R;
    }
}
