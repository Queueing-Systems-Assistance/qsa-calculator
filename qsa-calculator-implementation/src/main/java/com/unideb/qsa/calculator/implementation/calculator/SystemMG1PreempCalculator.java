package com.unideb.qsa.calculator.implementation.calculator;

import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 Preemp Service.
 */
@Component
public class SystemMG1PreempCalculator {

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

    public double Ro1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double eSc1 = features.get(SystemFeature.eSc1);
        return Lambda1 * eSc1;
    }

    public double Ro2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double eSc2 = features.get(SystemFeature.eSc2);
        return Lambda2 * eSc2;
    }

    public double Ro3(Map<SystemFeature, Double> features) {
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc3 = features.get(SystemFeature.eSc3);
        return Lambda3 * eSc3;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double Lambda = Lambda(features);
        return Lambda * SAvg;
    }

    public double WAvg1(Map<SystemFeature, Double> features) {
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double TAvg1 = TAvg1(features);
        return TAvg1 - eSc1;
    }

    public double WAvg2(Map<SystemFeature, Double> features) {
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double TAvg2 = TAvg2(features);
        return TAvg2 - eSc2;
    }

    public double WAvg3(Map<SystemFeature, Double> features) {
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double TAvg3 = TAvg3(features);
        return TAvg3 - eSc3;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double Lambda = Lambda(features);
        final double WAvg1 = WAvg1(features);
        final double WAvg2 = WAvg2(features);
        double WAvg3 = 0.0;
        if (Lambda3 != 0.0) {
            WAvg3 = WAvg3(features);
        }
        final double part1 = Lambda1 * WAvg1 / Lambda;
        final double part2 = Lambda2 * WAvg2 / Lambda;
        final double part3 = Lambda3 * WAvg3 / Lambda;
        return part1 + part2 + part3;
    }

    public double D2W1(Map<SystemFeature, Double> features) {
        final double D2S1 = D2S1(features);
        final double D2T1 = D2T1(features);
        return D2T1 - D2S1;
    }

    public double D2W2(Map<SystemFeature, Double> features) {
        final double D2S2 = D2S2(features);
        final double D2T2 = D2T2(features);
        return D2T2 - D2S2;
    }

    public double D2W3(Map<SystemFeature, Double> features) {
        final double D2S3 = D2S3(features);
        final double D2T3 = D2T3(features);
        return D2T3 - D2S3;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double EW2 = EW2(features);
        final double WAvg = WAvg(features);
        return EW2 - pow(WAvg, 2);
    }

    public double EW2c1(Map<SystemFeature, Double> features) {
        final double D2W1 = D2W1(features);
        final double WAvg1 = WAvg1(features);
        return D2W1 + pow(WAvg1, 2);
    }

    public double EW2c2(Map<SystemFeature, Double> features) {
        final double D2W2 = D2W2(features);
        final double WAvg2 = WAvg2(features);
        return D2W2 + pow(WAvg2, 2);
    }

    public double EW2c3(Map<SystemFeature, Double> features) {
        final double D2W3 = D2W3(features);
        final double WAvg3 = WAvg3(features);
        return D2W3 + pow(WAvg3, 2);
    }

    public double EW2(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double Lambda = Lambda(features);
        final double EW2c1 = EW2c1(features);
        final double EW2c2 = EW2c2(features);
        double EW2c3 = 0.0;
        if (Lambda3 != 0.0) {
            EW2c3 = EW2c3(features);
        }
        final double part1 = Lambda1 * EW2c1 / Lambda;
        final double part2 = Lambda2 * EW2c2 / Lambda;
        final double part3 = Lambda3 * EW2c3 / Lambda;
        return part1 + part2 + part3;
    }

    public double QAvg1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double WAvg1 = WAvg1(features);
        return Lambda1 * WAvg1;
    }

    public double QAvg2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double WAvg2 = WAvg2(features);
        return Lambda2 * WAvg2;
    }

    public double QAvg3(Map<SystemFeature, Double> features) {
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double WAvg3 = WAvg3(features);
        return Lambda3 * WAvg3;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double WAvg = WAvg(features);
        return Lambda * WAvg;
    }

    public double TAvg1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double Ro1 = Ro1(features);
        final double fractionPart = Lambda1 * eSPow2c1 / (2 * (1 - Ro1));
        return eSc1 + fractionPart;
    }

    public double TAvg2(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double RoSum2 = Ro1 + Ro2;
        final double part1 = 1 / (1 - Ro1);
        final double part2Dividend = Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2;
        final double part2Divisor = 2 * (1 - RoSum2);
        return part1 * (eSc2 + part2Dividend / part2Divisor);
    }

    public double TAvg3(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double Ro3 = Ro3(features);
        final double RoSum2 = Ro1 + Ro2;
        final double RoSum3 = RoSum2 + Ro3;
        final double part1 = 1 / (1 - RoSum2);
        final double part2Dividend = Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2 + Lambda3 * eSPow2c3;
        final double part2Divisor = 2 * (1 - RoSum3);
        return part1 * (eSc3 + part2Dividend / part2Divisor);
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

    public double D2T1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow3c1 = features.get(SystemFeature.eSPow3c1);
        final double D2S1 = D2S1(features);
        final double Ro1 = Ro1(features);
        final double part1Dividend = Lambda1 * eSPow3c1;
        final double part1Divisor = 3 * (1 - Ro1);
        final double part2Dividend = pow(Lambda1 * eSPow2c1, 2);
        final double part2Divisor = 4 * pow(1 - Ro1, 2);
        return D2S1 + eSc1 + part1Dividend / part1Divisor + part2Dividend / part2Divisor;
    }

    public double D2T2(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        final double eSPow3c1 = features.get(SystemFeature.eSPow3c1);
        final double eSPow3c2 = features.get(SystemFeature.eSPow3c2);
        final double D2S2 = D2S2(features);
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double RoSum2 = Ro1 + Ro2;
        final double part1 = D2S2 / pow(1 - Ro1, 2);
        final double part2 = eSc2 * Lambda1 * eSPow2c1 / pow(1 - Ro1, 3);
        final double part3Dividend = Lambda1 * eSPow3c1 + Lambda2 * eSPow3c2;
        final double part3Divisor = 3 * pow(1 - Ro1, 2) * (1 - RoSum2);
        final double part4Dividend = pow(Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2, 2);
        final double part4Divisor = 4 * pow(1 - Ro1, 2) * pow(1 - RoSum2, 2);
        final double part5Dividend = (Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2) * Lambda1 * eSPow2c1;
        final double part5Divisor = 2 * pow(1 - Ro1, 3) * (1 - RoSum2);
        return part1 + part2 + part3Dividend / part3Divisor + part4Dividend / part4Divisor + part5Dividend / part5Divisor;
    }

    public double D2T3(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double eSPow2c1 = features.get(SystemFeature.eSPow2c1);
        final double eSPow2c2 = features.get(SystemFeature.eSPow2c2);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        final double eSPow3c1 = features.get(SystemFeature.eSPow3c1);
        final double eSPow3c2 = features.get(SystemFeature.eSPow3c2);
        final double eSPow3c3 = features.get(SystemFeature.eSPow3c3);
        final double D2S3 = D2S3(features);
        final double Ro1 = Ro1(features);
        final double Ro2 = Ro2(features);
        final double Ro3 = Ro3(features);
        final double RoSum2 = Ro1 + Ro2;
        final double RoSum3 = RoSum2 + Ro3;
        final double part1 = D2S3 / pow(1 - RoSum2, 2);
        final double part2 = eSc3 * (Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2) / pow(1 - RoSum2, 3);
        final double part3Dividend = Lambda1 * eSPow3c1 + Lambda2 * eSPow3c2 + Lambda3 * eSPow3c3;
        final double part3Divisor = 3 * pow(1 - RoSum2, 2) * (1 - RoSum3);
        final double part4Dividend = pow(Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2 + Lambda3 * eSPow2c3, 2);
        final double part4Divisor = 4 * pow(1 - RoSum2, 2) * pow(1 - RoSum3, 2);
        final double part5Dividend = (Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2 + Lambda3 * eSPow2c3) * (Lambda1 * eSPow2c1 + Lambda2 * eSPow2c2);
        final double part5Divisor = 2 * pow(1 - RoSum2, 3) * (1 - RoSum3);
        return part1 + part2 + part3Dividend / part3Divisor + part4Dividend / part4Divisor + part5Dividend / part5Divisor;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double Lambda = Lambda(features);
        final double TAvg = TAvg(features);
        final double TAvg1 = TAvg1(features);
        final double TAvg2 = TAvg2(features);
        double TAvg3 = 0.0;
        final double D2T1 = D2T1(features);
        final double D2T2 = D2T2(features);
        double D2T3 = 0.0;
        if (Lambda3 != 0.0) {
            TAvg3 = TAvg3(features);
            D2T3 = D2T3(features);
        }
        final double part1 = Lambda1 * (D2T1 + pow(TAvg1, 2)) / Lambda;
        final double part2 = Lambda2 * (D2T2 + pow(TAvg2, 2)) / Lambda;
        final double part3 = Lambda3 * (D2T3 + pow(TAvg3, 2)) / Lambda;
        return part1 + part2 + part3 - pow(TAvg, 2);
    }

    public double NAvg1(Map<SystemFeature, Double> features) {
        final double Lambda1 = features.get(SystemFeature.Lambda1);
        final double TAvg1 = TAvg1(features);
        return Lambda1 * TAvg1;
    }

    public double NAvg2(Map<SystemFeature, Double> features) {
        final double Lambda2 = features.get(SystemFeature.Lambda2);
        final double TAvg2 = TAvg2(features);
        return Lambda2 * TAvg2;
    }

    public double NAvg3(Map<SystemFeature, Double> features) {
        final double Lambda3 = features.get(SystemFeature.Lambda3);
        final double TAvg3 = TAvg3(features);
        return Lambda3 * TAvg3;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double Lambda = Lambda(features);
        final double TAvg = TAvg(features);
        return Lambda * TAvg;
    }
}
