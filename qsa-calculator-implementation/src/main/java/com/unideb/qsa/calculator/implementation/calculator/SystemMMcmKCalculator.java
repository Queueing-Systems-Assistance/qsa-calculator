package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | c | m | K Service.
 */
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
        if (n >= c) {
            final double factor = factorial(n) / (factorial(c) * pow(c, n - c));
            result *= factor;
        }
        return result;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double m = features.get(SystemFeature.m);
        final double Pn = Pn(features);
        final double dividend = (K - n) * Pn;
        double divisor = 0.0;
        for (double i = 0.0; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            divisor += (K - i) * Pi;
        }
        return dividend / divisor;
    }

    public double PB(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        double result = 0.0;
        if (m != K) {
            Map<SystemFeature, Double> PBFeatures = copyOf(features);
            PBFeatures.put(SystemFeature.K, K - 1);
            PBFeatures.put(SystemFeature.n, m);
            result = Pn(PBFeatures);
        }
        return result;
    }

    public double PW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double i = c; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            sum += Pin(PiiFeatures);
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
            if (i < c) {
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

    public double FWt(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        final double z = Mu / Lambda;
        Map<SystemFeature, Double> Pi0Features = copyOf(features);
        Pi0Features.put(SystemFeature.n, 0.0);
        final double Pi0 = Pin(Pi0Features);
        final double dividend = pow(c, c) * CumulativePoisson(K - 1 - c, c * (z + Mu * t)) * Pi0;
        final double divisor = factorial(c) * Poisson(m - 1, c * z);
        return 1 - dividend / divisor;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double m = features.get(SystemFeature.m);
        final double t = features.get(SystemFeature.t);
        final double z = Mu / Lambda;
        Map<SystemFeature, Double> Pi0Features = copyOf(features);
        Pi0Features.put(SystemFeature.n, 0.0);
        final double Pi0 = Pin(Pi0Features);
        final double dividend = CumulativePoisson(m - 1, z + Mu * t);
        final double divisor = Poisson(m - 1, z);
        return 1 - dividend / divisor * Pi0;
    }

    private double P0InverseRecursive(double Ro, double K, double c, double m) {
        double result = 0.0;
        if (c == m) {
            for (double i = 0.0; i <= c; i++) {
                result += binomialCoefficientDouble((int)K, (int)i) * pow(Ro, i);
            }
        } else {
            final double recursive = P0InverseRecursive(Ro, K, c, m - 1);
            final double dividend = binomialCoefficientDouble((int)K, (int)m) * factorial(m) * pow(Ro, m);
            final double divisor = factorial(c) * pow(c, m - c);
            result = recursive + dividend / divisor;
        }
        return result;
    }

    private double CumulativePoisson(double k, double lambda) {
        double sum = 0.0;
        for(double i = 0.0; i <= k; i++) {
            sum += Poisson(i, lambda);
        }
        return sum;
    }

    private double Poisson(double k, double lambda) {
        final double part1 = pow(lambda, k);
        final double part2 = factorial(k);
        final double part3 = pow(E, -lambda);
        return part1 / part2 * part3;
    }
}
