package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 | K | K Service.
 */
@Component
public class SystemMM1KKCalculator {

    public double BKz(Map<SystemFeature, Double> features) {
        return P0(features);
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        final double WAvg = WAvg(features);
        double sum = 0;
        for (double i = 0; i <= K - 1; i++) {
            sum += (pow(z, i)) / factorial(i);
        }
        final double dividend = 1 - ((pow(z, K - 1)) / factorial(K - 1) / sum);
        return WAvg / dividend;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = QnxFunc(K - 1, z + t * Mu);
        final double divisor = QnxFunc(K - 1, z);
        return 1 - dividend / divisor;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = QnxFunc(K - 2, z + t * Mu);
        final double divisor = QnxFunc(K - 1, z);
        return 1 - dividend / divisor;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double SAvg = SAvg(features);
        return a / SAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        double sum = 0;
        for (int i = 0; i <= K; i++) {
            final double part1 = factorial(K);
            final double part2 = factorial(K - i);
            final double part3 = pow(SAvg / E0, i);
            sum += part1 / part2 * part3;
        }
        return pow(sum, -1);
    }

    public double Pi0(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        double sum = 0;
        for (double i = 0; i <= K - 1; i++) {
            sum += pow(z(features), i) / factorial(i);
        }
        final double dividend = (pow(z(features), K - 1)) / factorial(K - 1);
        return dividend / sum;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        final double Pn = Pn(features);
        final double dividend = (K - n) * Pn;
        final double divisor = K - NAvg;
        return dividend / divisor;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double P0 = P0(features);
        final double part1 = factorial(K) / (K - n);
        final double part2 = pow(z(features), -n);
        return part1 * part2 * P0;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double E0 = E0(features);
        final double LambdaAvg = LambdaAvg(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double z(Map<SystemFeature, Double> features) {
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        return E0 / SAvg;
    }

    private double QnxFunc(double nParam, double xParam) {
        double result = 0;
        for (int i = 0; i <= nParam; i++) {
            result += pow(xParam, i) / factorial(i);
        }
        return result * pow(Math.E, -xParam);
    }
}
