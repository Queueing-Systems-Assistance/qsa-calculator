package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.exp;
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

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double cAvg = cAvg(features);
        return cAvg / c;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        double sum = 0.0;
        for (double i = 0.0; i <= K; i++) {
            double dividend = pow(Lambda, i);
            double divisor;
            if (i < c) {
                divisor = factorial(i) * pow(Mu, i);
            } else {
                divisor = pow(c, i - c) * factorial(c) * pow(Mu, i);
            }
            sum += dividend / divisor;
        }
        return pow(sum, -1);
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
        final double Lambda = features.get(SystemFeature.Lambda);
        final double Mu = features.get(SystemFeature.Mu);
        final double n = features.get(SystemFeature.n);
        final double c = features.get(SystemFeature.c);
        final double P0 = P0(features);
        final double dividend = pow(Lambda, n) * P0;
        double divisor;
        if (n < c) {
            divisor = factorial(n) * pow(Mu, n);
        } else {
            divisor = pow(c, n - c) * factorial(c) * pow(Mu, n);
        }
        return dividend / divisor;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double Ro = Ro(features);
        final double r = Ro / c;
        double result = 0.0;
        if (r == 1) {
            for (double i = c + 1; i <= K; i++) {
                Map<SystemFeature, Double> PiFeatures = copyOf(features);
                PiFeatures.put(SystemFeature.n, i);
                double Pi = Pn(PiFeatures);
                result += (i - c) * Pi;
            }
        } else {
            final double P0 = P0(features);
            final double part1 = pow(Ro, c) * r * P0;
            final double part2 = factorial(c) * pow(1 - r, 2);
            final double part3 = 1 + (K - c) * pow(r, K - c + 1) - (K - c + 1) * pow(r, K - c);
            result = part1 / part2 * part3;
        }
        return result;
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

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double cAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double LambdaAvg = LambdaAvg(features);
        return LambdaAvg / Mu;
    }

    public double Pe(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        double sum = 0.0;
        for (double i = 0.0; i <= c - 1; i++) {
            Map<SystemFeature, Double> PiiFeatures = copyOf(features);
            PiiFeatures.put(SystemFeature.n, i);
            sum += Pin(PiiFeatures);
        }
        return sum;
    }

    public double EDelta(Map<SystemFeature, Double> features) {
        final double US = US(features);
        final double eAvg = eAvg(features);
        return US * eAvg / (1 - US);
    }

    public double EDeltar(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double US = US(features);
        final double divisor = Lambda * (1 - US);
        return US / divisor;
    }

    public double eAvg(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double c = features.get(SystemFeature.c);
        final double Pe = Pe(features);
        final Map<SystemFeature, Double> PiiFeatures = copyOf(features);
        double part1 = 0.0;
        for (double i = 0.0; i <= c - 1; i++) {
            PiiFeatures.put(SystemFeature.n, i);
            final double Pii = Pin(PiiFeatures);
            part1 += (c - i) * Pii;
        }
        final double part2 = 1 / (Lambda * Pe);
        return part1 * part2;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        double sum = 0.0;
        for (double n = c; n <= K - 1; n++) {
            Map<SystemFeature, Double> PinFeatures = copyOf(features);
            PinFeatures.put(SystemFeature.n, n);
            double Pin = Pin(PinFeatures);
            double innerSum = 0.0;
            for (double i = 0.0; i <= n - c; i++) {
                double dividend = pow(c * Mu * t, i) * exp(-1 * c * Mu * t);
                innerSum += dividend / factorial(i);
            }
            sum += innerSum * Pin;
        }
        return 1 - sum;
    }
}
