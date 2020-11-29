package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

@Component
public class SystemMMcmKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        return Lambda / Mu;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        final double Ro = Ro(features);
        final double P0Inverse = P0InverseRecursive(Ro, K, c, m);
        return pow(P0Inverse, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        double result = binomialCoefficientDouble((int)K, (int)n) * pow(Ro, n) * P0;
        if(n >= c) {
            final double factor = factorial(n) / (factorial(c) * pow(c, n - c));
            result *= factor;
        }
        return result;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double PK = PB(features);
        Map<SystemFeature, Double> PinFeatures = copyOf(features);
        PinFeatures.put(SystemFeature.K, K - 1);
        final double Pn = Pn(PinFeatures);
        return Pn / (1 - PK);
    }

    public double PB(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        Map<SystemFeature, Double> PBFeatures = copyOf(features);
        PBFeatures.put(SystemFeature.K, K - 1);
        PBFeatures.put(SystemFeature.n, m);
        return Pn(PBFeatures);
    }

    public double PW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double i = c; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            double Pii = Pin(PiiFeatures);
            sum += Pii;
        }
        return sum;
    }

    public double cAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double i = 0.0; i <= m; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            if(i < c) {
                sum += i * Pi;
            } else {
                sum += c * Pi;
            }
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return K - NAvg;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double cAvg = cAvg(features);
        return cAvg / c;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return (K - NAvg) / K;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double cAvg = cAvg(features);
        return Mu * cAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double i = 0.0; i <= m; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            sum += i * Pi;
        }
        return sum;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double i = c; i <= m; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            sum += (i - c) * Pi;
        }
        return sum;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double QAvg = QAvg(features);
        return QAvg / LambdaAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double NAvg = NAvg(features);
        return NAvg / LambdaAvg;
    }

    public double ETau(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        final double TAvg = TAvg(features);
        final double dividend = (K - NAvg) * TAvg;
        return dividend / NAvg;
    }

    public double ENR(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double ETao = ETau(features);
        return Lambda * ETao;
    }

    private double P0InverseRecursive(double Ro, double K, double c, double m) {
        if(c == m) {
            double sum = 0.0;
            for (double i = 0.0; i <= c; i++) {
                sum += binomialCoefficientDouble((int)K, (int)i) * pow(Ro, i);
            }
            return sum;
        } else {
            final double recursive = P0InverseRecursive(Ro, K, c, m - 1);
            final double dividend = binomialCoefficientDouble((int)K, (int)m) * factorial(m) * pow(Ro, m);
            final double divisor = factorial(c) * pow(c, m - K);
            return recursive + dividend / divisor;
        }
    }

    private double Qnx(double n, double x) {
        double result = 0;
        for (double k = 0; k <= n; k++) {
            result += pow(x, k) / factorial(k);
        }
        return pow(E, -1 * x) * result;
    }
}
