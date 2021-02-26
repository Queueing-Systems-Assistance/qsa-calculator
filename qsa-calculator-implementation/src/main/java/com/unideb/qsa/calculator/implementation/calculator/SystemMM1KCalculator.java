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
        Map<SystemFeature, Double> Pi0Features = copyOf(features);
        Pi0Features.put(SystemFeature.n, 0.0);
        final double Pi0 = Pin(Pi0Features);
        final double WAvg = WAvg(features);
        return WAvg / (1 - Pi0);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Mu = features.get(SystemFeature.Mu);
        final double t = features.get(SystemFeature.t);
        final double P0 = P0(features);
        final double PK = PK(features);
        double sum = 0.0;
        for (double k = 1; k <= K - 1; k++) {
            double innerSum = 0.0;
            for (int i = 0; i <= k - 1; i++) {
                double Mut = Mu * t;
                innerSum += pow(Mut, i) / factorial(i) * pow(E, -1 * Mut);
            }
            Map<SystemFeature, Double> PkFeatures = copyOf(features);
            PkFeatures.put(SystemFeature.n, k);
            double Pk = Pn(PkFeatures);
            sum += (1 - innerSum) * Pk / (1 - PK);
        }
        return P0 / (1 - PK) + sum;
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

    public double EN2(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= K; i++) {
            PiFeatures.put(SystemFeature.n, i);
            sum += pow(i, 2) * Pn(PiFeatures);
        }
        return sum;
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double EN2 = EN2(features);
        return EN2 - pow(NAvg, 2);
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
        double result;
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

    public double EQ2(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= K; i++) {
            PiFeatures.put(SystemFeature.n, i);
            sum += pow(i - 1, 2) * Pn(PiFeatures);
        }
        return sum;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double EQ2 = EQ2(features);
        return EQ2 - pow(QAvg, 2);
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

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double D2W = D2W(features);
        return D2W + 1 / pow(Mu, 2);
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

    public double EW2(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double K = features.get(SystemFeature.K);
        final Map<SystemFeature, Double> PiiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= K - 1.0; i++) {
            PiiFeatures.put(SystemFeature.n, i);
            final double fraction = (i + pow(i, 2)) / pow(Mu, 2);
            sum += fraction * Pin(PiiFeatures);
        }
        return sum;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double EW2 = EW2(features);
        return EW2 - pow(WAvg, 2);
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

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = Lambda * P0;
        return dividend / divisor;
    }

    private double QnMuT(double n, double Mut) {
        double result = 0.0;
        for (int k = 0; k <= n; k++) {
            result += pow(Mut, k) / factorial(k);
        }
        return pow(E, -Mut) * result;
    }
}
