package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.c;
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
        Double c = features.get(SystemFeature.c);
        double WAvg = WAvg(features);
        double sum = 0;
        for (int i = 0; i < c; i++) {
            double Ro = Ro(features);
            double P0 = P0(features);
            double PK = PK(features);
            sum += pow(Ro, i) / factorial(i) * P0 / (1 - PK);
        }
        return WAvg / (1 - sum);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        Double Lambda = features.get(SystemFeature.Lambda);
        double PK = PK(features);
        return Lambda * (1 - PK);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        Double c = features.get(SystemFeature.c);
        double P0 = P0(features);
        double QAvg = QAvg(features);
        double Ro = Ro(features);
        double sum1 = 0;
        double sum2 = P0;
        for (int i = 1; i < c; i++) {
            sum1 += i * (pow(Ro, i) / factorial(i) * P0);
        }
        for (double i = 1; i < c; i++) {
            sum2 += pow(Ro, i) / factorial(i) * P0;
        }
        return QAvg + sum1 + c * (1 - sum2);
    }

    public double P0(Map<SystemFeature, Double> features) {
        Double Lambda = features.get(SystemFeature.Lambda);
        Double c = features.get(SystemFeature.c);
        Double Mu = features.get(SystemFeature.Mu);
        Double K = features.get(SystemFeature.K);
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < c; i++) {
            double divisor = factorial(i) * pow(Mu, i);
            sum1 += pow(Lambda, i) / divisor;
        }
        for (double i = c; i <= K; i++) {
            double divisor = pow(c, i - c) * factorial(c) * pow(Mu, i);
            sum2 += pow(Lambda, i) / divisor;
        }
        return pow(sum1 + sum2, -1);
    }

    public double PK(Map<SystemFeature, Double> features) {
        Double Lambda = features.get(SystemFeature.Lambda);
        Double c = features.get(SystemFeature.c);
        Double K = features.get(SystemFeature.K);
        Double Mu = features.get(SystemFeature.Mu);
        double P0 = P0(features);
        double divisor = pow(c, K - c) * factorial(c) * pow(Mu, K);
        return pow(Lambda, K) / divisor * P0;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        double Pn = Pn(features);
        double PK = PK(features);
        return Pn / (1 - PK);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        Double n = features.get(SystemFeature.n);
        Double c = features.get(SystemFeature.c);
        double Ro = Ro(features);
        double P0 = P0(features);
        double result;
        if (n <= c) {
            result = pow(Ro, n) / factorial(n) * P0;
        } else {
            result = pow(Ro, n) / factorial(c) * P0 * pow(Ro / c, n - c);
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        Double K = features.get(SystemFeature.K);
        Double c = features.get(SystemFeature.c);
        double a = a(features);
        double Ro = Ro(features);
        double P0 = P0(features);
        double part1 = pow(Ro, c) * a * P0;
        double part2 = factorial(c) * pow(1 - a, 2);
        double part3 = 1 + (K - c) * pow(a, K - c + 1) - (K - c + 1) * pow(a, K - c);
        return part1 / part2 * part3;
    }

    public double Ro(Map<SystemFeature, Double> features) {
        Double Lambda = features.get(SystemFeature.Lambda);
        Double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        Double Lambda = features.get(SystemFeature.Lambda);
        Double Mu = features.get(SystemFeature.Mu);
        return features.get(c) - Lambda / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        double NAvg = NAvg(features);
        double LambdaAvg = LambdaAvg(features);
        return NAvg / LambdaAvg;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        double QAvg = QAvg(features);
        double LambdaAvg = LambdaAvg(features);
        return QAvg / LambdaAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        double Ro = Ro(features);
        Double c = features.get(SystemFeature.c);
        return Ro / c;
    }
}
