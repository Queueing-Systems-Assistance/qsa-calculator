package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.c;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | K Service.
 */
@Component
public class SystemMMnKCalculator {

    public double EWW0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double WAvg = WAvg(features);
        double sum = 0;
        for (double i = 0; i < c; i++) {
            Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, i);
            double Pin = Pin(PinFeatures);
            sum += Pin;
        }
        return WAvg / (1 - sum);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double PK = PK(features);
        return Lambda * (1 - PK);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double QAvg = QAvg(features);
        double sum1 = 0;
        double sum2 = 0;
        for (double i = 0; i <= c - 1; i++) {
            Map<SystemFeature, Double> PnFeatures = copyOf(features);
            PnFeatures.put(SystemFeature.n, i);
            double Pn = Pn(PnFeatures);
            sum1 += i * Pn;
            sum2 += Pn;
        }
        return QAvg + sum1 + c * (1 - sum2);
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i <= c; i++) {
            sum1 += pow(Ro, i) / factorial(i);
        }
        for (double i = 1; i <= K - c; i++) {
            sum2 += pow(Ro / c, i);
        }
        final double factor = pow(Ro, c) / factorial(c);
        return pow(sum1 + factor * sum2, -1);
    }

    public double PK(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        Map<SystemFeature, Double> PKFeatures = copyOf(features);
        PKFeatures.put(SystemFeature.n, K);
        return Pn(PKFeatures);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double Pn = Pn(features);
        final double PK = PK(features);
        return Pn / (1 - PK);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        double result;
        if (n <= c) {
            result = pow(Ro, n) / factorial(n) * P0;
        } else {
            result = pow(Ro, n) / factorial(c) * P0 * pow(Ro / c, n - c);
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double a = a(features);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        final double part1 = pow(Ro, c) * a * P0;
        final double part2 = factorial(c) * pow(1 - a, 2);
        final double part3 = 1 + (K - c) * pow(a, K - c + 1) - (K - c + 1) * pow(a, K - c);
        return part1 / part2 * part3;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        return c - Lambda / Mu;
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
        final double Ro = Ro(features);
        final double c = features.get(SystemFeature.c);
        return Ro / c;
    }
}
