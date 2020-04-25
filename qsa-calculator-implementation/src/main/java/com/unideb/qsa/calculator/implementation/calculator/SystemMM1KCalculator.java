package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 | K Service.
 */
@Component
public class SystemMM1KCalculator {

    public double EWW0(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        final double WAvg = WAvg(features);
        return WAvg / (1 - P0);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double K = features.get(SystemFeature.K);
        double result = 0.0;
        for (double i = 0; i < K - 1; i++) {
            final double PiN = PiN(i + 1, features);
            final double QnMuT = QnMuT(i, Mu * t);
            result += PiN * QnMuT;
        }
        return 1 - result;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double PK = PK(features);
        return (1 - PK) * Lambda;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double K = features.get(SystemFeature.K);
        final double result;
        if (Lambda == Mu) {
            result = K / 2;
        } else {
            final double Ro = Ro(features);
            final double part1 = (K + 1) * pow(Ro, K);
            final double part2 = K * pow(Ro, K + 1);
            final double dividend = Ro * (1 - part1 + part2);
            final double divisor = (1 - Ro) * (1 - pow(Ro, K + 1));
            result = dividend / divisor;
        }
        return result;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double dividend = (1 - Ro) * pow(Ro, 0);
        final double divisor = 1 - pow(Ro, K + 1);
        return dividend / divisor;
    }

    public double PK(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        return propN(K, features);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double PK = PK(features);
        return propN(n, features) / (1 - PK);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        return propN(n, features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return NAvg - (1 - P0);
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
        final double NAvg = NAvg(features);
        final double LambdaAvg = LambdaAvg(features);
        return NAvg / LambdaAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double LambdaAvg = LambdaAvg(features);
        return QAvg / LambdaAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double PK = PK(features);
        final double Ro = Ro(features);
        return (1 - PK) * Ro;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        double result = 0.0;
        for (double i = 0; i < K; i++) {
            final double PiN = PiN(i, features);
            final double QnMuT = QnMuT(i, Mu * t);
            result += PiN * QnMuT;
        }
        return 1 - result;
    }

    private double QnMuT(double nMmc, double MuTMmc) {
        double result = 0.0;
        for (int i = 0; i <= nMmc; i++) {
            result += pow(MuTMmc, i) / factorial(i);
        }
        return result * pow(E, -MuTMmc);
    }

    private double PiN(double nMm1k, Map<SystemFeature, Double> features) {
        final double PK = PK(features);
        return propN(nMm1k, features) / (1 - PK);
    }

    private double propN(double nMm1k, Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double K = features.get(SystemFeature.K);
        final double Lambda = features.get(SystemFeature.Lambda);
        double result;
        if (Mu == Lambda) {
            result = 1 / (K + 1);
        } else {
            final double Ro = Ro(features);
            final double dividend = (1 - Ro) * pow(Ro, nMm1k);
            final double divisor = 1 - pow(Ro, K + 1);
            result = dividend / divisor;
        }
        return result;
    }

}
