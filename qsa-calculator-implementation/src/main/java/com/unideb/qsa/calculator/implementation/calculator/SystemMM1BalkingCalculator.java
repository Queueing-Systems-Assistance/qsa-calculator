package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 | Balking Service.
 */
@Component
public class SystemMM1BalkingCalculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double PJ(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double LambdaAvg = LambdaAvg(features);
        return LambdaAvg / Lambda;
    }

    public double bn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        return 1 / (1 + n);
    }

    public double LambdaN(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double bn = bn(features);
        return bn * Lambda;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double TAvg = TAvg(features);
        return NAvg / TAvg;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double part1 = pow(E, -Ro) + 2 * Ro - 1;
        final double part2 = pow(E, -Ro);
        return Ro - part1 * part2;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro = Ro(features);
        final double dividend = 2 - (Ro + 2) * pow(E, -Ro);
        final double divisor = pow(Mu, 2) * pow(1 - pow(E, -Ro), 2);
        return Ro * dividend / divisor;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double D2T = D2T(features);
        final double SAvg = SAvg(features);
        return D2T - pow(SAvg, 2);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        final double n = features.get(SystemFeature.n);
        final double P0 = P0(features);
        return pow(Ro, n) / factorial(n) * P0;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double Ro = Ro(features);
        return pow(E, -Ro);
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double US = US(features);
        return NAvg - US;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double Ro = Ro(features);
        return Ro / (Mu * (1 - pow(E, -Ro)));
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double TAvg = TAvg(features);
        return TAvg - 1 / Mu;
    }

    public double ECost(Map<SystemFeature, Double> features) {
        final double CS = features.get(SystemFeature.CS);
        final double CWS = features.get(SystemFeature.CWS);
        final double CI = features.get(SystemFeature.CI);
        final double CSR = features.get(SystemFeature.CSR);
        final double R = features.get(SystemFeature.R);
        final double LambdaAvg = LambdaAvg(features);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return CS + NAvg * CWS + P0 * CI + CSR - LambdaAvg * R;
    }
}
