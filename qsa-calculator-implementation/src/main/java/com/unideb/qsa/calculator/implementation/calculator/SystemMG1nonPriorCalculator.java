package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 non Prior Service.
 */
@Component
public class SystemMG1nonPriorCalculator {

    public double Lambda(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        return Lambda1 + Lambda2 + Lambda3;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double Lambda = Lambda(features);
        final double part1 = Lambda1 * eSc1 / Lambda;
        final double part2 = Lambda2 * eSc2 / Lambda;
        final double part3 = Lambda3 * eSc3 / Lambda;
        return part1 + part2 + part3;
    }

    public double eSPow2(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        final double Lambda = Lambda(features);
        final double part1 = Lambda1 * eSPow2c1 / Lambda;
        final double part2 = Lambda2 * eSPow2c2 / Lambda;
        final double part3 = Lambda3 * eSPow2c3 / Lambda;
        return part1 + part2 + part3;
    }

    public double eSPow3(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSPow3c1 = features.get(SystemFeature.eSPow3c1);
        final double eSPow3c2 = features.get(SystemFeature.eSPow3c2);
        final double eSPow3c3 = features.get(SystemFeature.eSPow3c3);
        final double Lambda = Lambda(features);
        final double part1 = Lambda1 * eSPow3c1 / Lambda;
        final double part2 = Lambda2 * eSPow3c2 / Lambda;
        final double part3 = Lambda3 * eSPow3c3 / Lambda;
        return part1 + part2 + part3;
    }

    public double D2S1(Map<SystemFeature, Double> features) {
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        return eSPow2c1 - pow(eSc1, 2);
    }

    public double D2S2(Map<SystemFeature, Double> features) {
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        return eSPow2c2 - pow(eSc2, 2);
    }

    public double D2S3(Map<SystemFeature, Double> features) {
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        return eSPow2c3 - pow(eSc3, 2);
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double Lambda = Lambda(features);
        return Lambda * SAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return 1 - Ro;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double eSPow2 = eSPow2(features);
        final double Ro = Ro(features);
        final double dividend = Lambda * eSPow2;
        final double divisor = 2 * (1 - Ro);
        return dividend / divisor;
    }

    public double EW2(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double D2W = D2W(features);
        return D2W + pow(WAvg, 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double eSPow2 = eSPow2(features);
        final double eSPow3 = eSPow3(features);
        final double Ro = Ro(features);
        final double part1Dividend = Lambda * eSPow3;
        final double part1Divisor = 3 * (1 - Ro);
        final double part2Dividend = pow(Lambda, 2) * pow(eSPow2, 2);
        final double part2Divisor = 4 * pow(1 - Ro, 2);
        return part1Dividend / part1Divisor + part2Dividend / part2Divisor;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double WAvg = WAvg(features);
        return Lambda * WAvg;
    }

    public double TAvg1(Map<SystemFeature, Double> features) {
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double WAvg = WAvg(features);
        return WAvg + eSc1;
    }

    public double TAvg2(Map<SystemFeature, Double> features) {
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double WAvg = WAvg(features);
        return WAvg + eSc2;
    }

    public double TAvg3(Map<SystemFeature, Double> features) {
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double WAvg = WAvg(features);
        return WAvg + eSc3;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double Lambda = Lambda(features);
        final double TAvg1 = TAvg1(features);
        final double TAvg2 = TAvg2(features);
        double TAvg3 = 0.0;
        if (Lambda3 != 0.0) {
            TAvg3 = TAvg3(features);
        }
        final double part1 = Lambda1 * TAvg1 / Lambda;
        final double part2 = Lambda2 * TAvg2 / Lambda;
        final double part3 = Lambda3 * TAvg3 / Lambda;
        return part1 + part2 + part3;
    }

    public double ET2c1(Map<SystemFeature, Double> features) {
        final double TAvg1 = TAvg1(features);
        final double D2T1 = D2T1(features);
        return D2T1 + pow(TAvg1, 2);
    }

    public double ET2c2(Map<SystemFeature, Double> features) {
        final double TAvg2 = TAvg2(features);
        final double D2T2 = D2T2(features);
        return D2T2 + pow(TAvg2, 2);
    }

    public double ET2c3(Map<SystemFeature, Double> features) {
        final double TAvg3 = TAvg3(features);
        final double D2T3 = D2T3(features);
        return D2T3 + pow(TAvg3, 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double Lambda = Lambda(features);
        final double ET2c1 = ET2c1(features);
        final double ET2c2 = ET2c2(features);
        double ET2c3 = 0.0;
        if (Lambda3 != 0.0) {
            ET2c3 = ET2c3(features);
        }
        final double part1 = Lambda1 * ET2c1 / Lambda;
        final double part2 = Lambda2 * ET2c2 / Lambda;
        final double part3 = Lambda3 * ET2c3 / Lambda;
        return part1 + part2 + part3;
    }

    public double D2T1(Map<SystemFeature, Double> features) {
        final double D2S1 = D2S1(features);
        final double D2W = D2W(features);
        return D2W + D2S1;
    }

    public double D2T2(Map<SystemFeature, Double> features) {
        final double D2S2 = D2S2(features);
        final double D2W = D2W(features);
        return D2W + D2S2;
    }

    public double D2T3(Map<SystemFeature, Double> features) {
        final double D2S3 = D2S3(features);
        final double D2W = D2W(features);
        return D2W + D2S3;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double ET2 = ET2(features);
        return ET2 - pow(TAvg, 2);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double TAvg = TAvg(features);
        return Lambda * TAvg;
    }

    public double ECost(Map<SystemFeature, Double> features) {
        final double CS = features.get(SystemFeature.CS);
        final double CWS = features.get(SystemFeature.CWS);
        final double CI = features.get(SystemFeature.CI);
        final double CSR = features.get(SystemFeature.CSR);
        final double R = features.get(SystemFeature.R);
        final double Lambda = Lambda(features);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return CS + NAvg * CWS + P0 * CI + CSR - Lambda * R;
    }
}
