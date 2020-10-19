package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
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
        for (double n = 0; n <= K - 2; n++) {
            final Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, n);
            final double Pin = Pin(PinFeatures);
            final double QnMuT = QnMuT(n, Mu * t);
            result += Pin * QnMuT;
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
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        double result = 0;
        if (Lambda == Mu) {
            result = 1 / (K + 1);
        } else {
            final double Ro = Ro(features);
            result = (1 - Ro) / (1 - pow(Ro, K + 1));
        }
        return result;
    }

    public double PK(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> PnKFeatures = copyOf(features);
        PnKFeatures.put(SystemFeature.n, K);
        return Pn(PnKFeatures);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double PK = PK(features);
        final double Pn = Pn(features);
        return Pn / (1 - PK);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        double result = 0;
        if (Lambda == Mu) {
            result = 1 / (K + 1);
        } else {
            final double n = features.get(SystemFeature.n);
            final double Ro = Ro(features);
            final double dividend = (1 - Ro) * pow(Ro, n);
            final double divisor = 1 - pow(Ro, K + 1);
            result = dividend / divisor;
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return NAvg - (1 - P0);
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
        final double NAvg = NAvg(features);
        final double LambdaAvg = LambdaAvg(features);
        return NAvg / LambdaAvg;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
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
        for (double n = 0; n <= K - 1; n++) {
            final Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, n);
            final double Pin = Pin(PinFeatures);
            final double QnMuT = QnMuT(n, Mu * t);
            result += Pin * QnMuT;
        }
        return 1 - result;
    }

    private double QnMuT(double n, double Mut) {
        double result = 0.0;
        for (int k = 0; k <= n; k++) {
            result += pow(Mut, k) / factorial(k);
        }
        return pow(E, -Mut) * result;
    }
}
