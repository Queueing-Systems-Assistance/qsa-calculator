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
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        return LambdaFin / Mu;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        final double P0Inverse = P0InverseRecursive(Ro, KFin, c, m);
        return pow(P0Inverse, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        double result = binomialCoefficientDouble((int) KFin, (int) n) * pow(Ro, n) * P0;
        if (n >= c) {
            final double factor = factorial(n) / (factorial(c) * pow(c, n - c));
            result *= factor;
        }
        return result;
    }

    public double PinFin(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double m = features.get(SystemFeature.m);
        final double Pn = Pn(features);
        final double dividend = (KFin - n) * Pn;
        double divisor = 0.0;
        for (double i = 0.0; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            divisor += (KFin - i) * Pi;
        }
        return dividend / divisor;
    }

    public double PB(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final double KFin = features.get(SystemFeature.KFin);
        double result = 0.0;
        if (m != KFin) {
            Map<SystemFeature, Double> PBFeatures = copyOf(features);
            PBFeatures.put(SystemFeature.KFin, KFin - 1);
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
            sum += PinFin(PiiFeatures);
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
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return KFin - NAvg;
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
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return (KFin - NAvg) / KFin;
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
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        final double TAvg = TAvg(features);
        final double dividend = (KFin - NAvg) * TAvg;
        return dividend / NAvg;
    }

    public double ENR(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double ETau = ETau(features);
        return LambdaFin * ETau;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double t = features.get(SystemFeature.t);
        double sum = 0.0;
        for (double i = c; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            double Pii = PinFin(PiiFeatures);
            double innerSum = 0.0;
            for (double j = 0.0; j <= i - c; j++) {
                double part1 = pow(c * Mu * t, j) / factorial(j);
                double part2 = pow(E, -1 * c * Mu * t);
                innerSum += part1 * part2;
            }
            sum += Pii * innerSum;
        }
        return 1 - sum;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double m = features.get(SystemFeature.m);
        final double t = features.get(SystemFeature.t);
        double sum = 0.0;
        for (double i = 0; i <= m - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            double Pii = PinFin(PiiFeatures);
            double innerSum = 0.0;
            for (double j = 0.0; j <= i; j++) {
                double part1 = pow(Mu * t, j) / factorial(j);
                double part2 = pow(E, -1 * Mu * t);
                innerSum += part1 * part2;
            }
            sum += Pii * innerSum;
        }
        return 1 - sum;
    }

    public double eAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0.0;
        for (double i = 0.0; i <= c - 1; i++) {
            final double ei = ej(features, i);
            final double ai = aj(features, i);
            sum += ei * ai;
        }
        return sum;
    }

    public double EDelta(Map<SystemFeature, Double> features) {
        final double a = a(features);
        final double eAvg = eAvg(features);
        final double dividend = a * eAvg;
        final double divisor = 1 - a;
        return dividend / divisor;
    }

    private double ej(Map<SystemFeature, Double> features, double j) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double dividend = c - j;
        final double divisor = (KFin - j) * LambdaFin;
        return dividend / divisor;
    }

    public double aj(Map<SystemFeature, Double> features, double j) {
        final double c = features.get(SystemFeature.c);
        Map<SystemFeature, Double> PijFeatures = copyOf(features);
        PijFeatures.put(SystemFeature.n, j);
        final double Pij = PinFin(PijFeatures);
        double sum = 0.0;
        for (double i = 0.0; i <= c - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            sum += PinFin(PiiFeatures);
        }
        return Pij / sum;
    }

    private double P0InverseRecursive(double Ro, double K, double c, double m) {
        double result = 0.0;
        if (c == m) {
            for (double i = 0.0; i <= c; i++) {
                result += binomialCoefficientDouble((int) K, (int) i) * pow(Ro, i);
            }
        } else {
            final double recursive = P0InverseRecursive(Ro, K, c, m - 1);
            final double dividend = binomialCoefficientDouble((int) K, (int) m) * factorial(m) * pow(Ro, m);
            final double divisor = factorial(c) * pow(c, m - c);
            result = recursive + dividend / divisor;
        }
        return result;
    }
}
