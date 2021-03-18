package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.exp;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | n | K | K Service.
 */
@Component
public class SystemMMnKKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        return LambdaFin / Mu;
    }

    public double PW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0;
        for (double n = 0; n <= c - 1; n++) {
            Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, n);
            double Pin = PinFin(PinFeatures);
            sum += Pin;
        }
        return 1 - sum;
    }

    public double cAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        double sum = 0.0;
        for (double i = 1.0; i <= KFin; i++) {
            Map<SystemFeature, Double> PiFeatures = copyOf(features);
            PiFeatures.put(SystemFeature.n, i);
            double Pi = Pn(PiFeatures);
            if (i <= c) {
                sum += Pi * i;
            } else {
                sum += Pi * c;
            }
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return KFin - NAvg;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        return 1 / LambdaFin;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double cAvg = cAvg(features);
        return cAvg / c;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double mAvg = mAvg(features);
        return mAvg / KFin;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double D = PW(features);
        return WAvg / D;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double KFin = features.get(SystemFeature.KFin);
        final double c = features.get(SystemFeature.c);
        final double C1 = C1(features);
        final double SAvg = SAvg(features);
        final double C2 = C2(features);
        final double z = Mu / LambdaFin;
        final double dividend = QkLambda(KFin - c - 1, c * (z + t * Mu));
        final double divisor = QkLambda(KFin - c - 1, c * z);
        double part1 = C1 * exp(-t / SAvg);
        double part2 = C2 * (dividend / divisor);
        return 1 - part1 + part2;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double t = features.get(SystemFeature.t);
        double sum = 0.0;
        for (double i = c; i <= KFin - 1; i++) {
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

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        final double WAvg = WAvg(features);
        return KFin / (E0 + WAvg + SAvg);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        double sum = 0.0;
        for (double i = 1.0; i <= KFin; i++) {
            sum += anRecursive(c, KFin, i, Ro);
        }
        return 1 / (1 + sum);
    }

    public double PinFin(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double Pn = Pn(features);
        final double NAvg = NAvg(features);
        final double dividend = (KFin - n) * Pn;
        final double divisor = KFin - NAvg;
        return dividend / divisor;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        final double P0 = P0(features);
        final double an = anRecursive(c, KFin, n, Ro);
        return an * P0;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        double result = 0;
        for (double n = c + 1; n <= KFin; n++) {
            Map<SystemFeature, Double> PnFeatures = copyOf(features);
            PnFeatures.put(SystemFeature.n, n);
            final double Pn = Pn(PnFeatures);
            result += (n - c) * Pn;
        }
        return result;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return KFin / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double SAvg = SAvg(features);
        final double QAvg = QAvg(features);
        final double E0 = E0(features);
        return QAvg * (E0 + SAvg) / (KFin - QAvg);
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

    private double aj(Map<SystemFeature, Double> features, double j) {
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

    private double anRecursive(double c, double K, double n, double Ro) {
        double result;
        if (n == 0.0) {
            result = 1.0;
        } else {
            final double recursive = anRecursive(c, K, n - 1, Ro);
            result = (K - n + 1) * recursive * Ro;
            if (n < c) {
                result /= n;
            } else {
                result /= c;
            }
        }
        return result;
    }

    public double ECost(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double CS = features.get(SystemFeature.CS);
        final double CWS = features.get(SystemFeature.CWS);
        final double CI = features.get(SystemFeature.CI);
        final double CSR = features.get(SystemFeature.CSR);
        final double R = features.get(SystemFeature.R);
        final double LambdaAvg = LambdaAvg(features);
        final double NAvg = NAvg(features);
        final double cAvg = cAvg(features);
        return c * CS + NAvg * CWS + (c - cAvg) * CI + c * CSR - LambdaAvg * R;
    }

    private double C1(Map<SystemFeature, Double> features) {
        final double C2 = C2(features);
        return 1 + C2;
    }

    private double C2(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double KFin = features.get(SystemFeature.KFin);
        final double z = Mu / LambdaFin;
        final double P0KMinus1 = P0KMinus1(features);
        final double dividend = pow(c, c) * QkLambda(KFin - c - 1, c * z);
        final double divisorPart1 = factorial(c) * (c - 1) * factorial(KFin - c - 1);
        final double divisorPart2 = pkLambda(KFin - 1, c * z);
        return dividend / (divisorPart1 * divisorPart2) * P0KMinus1;
    }

    private double P0KMinus1(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final Map<SystemFeature, Double> P0K1Features = copyOf(features);
        P0K1Features.put(SystemFeature.K, KFin - 1);
        return P0(P0K1Features);
    }

    private double QkLambda(double k, double lambda) {
        double sum = 0;
        for (double n = 0; n <= k; n++) {
            sum += pow(lambda, n) / factorial(n);
        }
        return pow(E, -lambda) * sum;
    }

    private double pkLambda(double k, double lambda) {
        final double part1 = pow(lambda, k);
        final double part2 = factorial(k);
        final double part3 = pow(E, -lambda);
        return part1 / part2 * part3;
    }
}
