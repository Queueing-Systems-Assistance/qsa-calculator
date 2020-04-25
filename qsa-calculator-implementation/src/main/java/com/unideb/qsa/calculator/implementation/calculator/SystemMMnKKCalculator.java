package com.unideb.qsa.calculator.implementation.calculator;

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

    public double C1(Map<SystemFeature, Double> features) {
        final double C2 = C2(features);
        return C2 + 1;
    }

    public double C2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        final double pkAlpha = pkAlpha(K - 1, c * z);
        final double p0Process = P0Process(c, K - 1, z);
        final double qkAlpha = QkAlpha(K - c - 1, c * z);
        return pow(c, c) * qkAlpha / (factorial(c) * (c - 1) * (K - c - 1) * pkAlpha) * p0Process;
    }

    public double D(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double NAvg = NAvg(features);
        double sum = 0;
        for (double i = 0; i < c; i++) {
            final double PnProcess = PnProcess(features, i, K);
            sum += (K - i) * PnProcess / (K - NAvg);
        }
        return 1 - sum;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double Alpha = features.get(SystemFeature.Alpha);
        return 1 / Alpha;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double D = D(features);
        return WAvg / D;
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double t = features.get(SystemFeature.t);
        final double K = features.get(SystemFeature.K);
        final double c = features.get(SystemFeature.c);
        final double Mu = features.get(SystemFeature.Mu);
        final double C1 = C1(features);
        final double SAvg = SAvg(features);
        final double C2 = C2(features);
        final double z = z(features);
        final double QkAlpha1 = QkAlpha(K - c - 1, c * (z + t * Mu));
        final double QkAlpha2 = QkAlpha(K - c - 1, c * z);
        return 1 - C1 * exp(-t / SAvg) + C2 * (QkAlpha1 / QkAlpha2);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        final double P0Process = P0Process(c, K - 1, z);
        final double QkAlpha = QkAlpha(K - c - 1, c * z);
        final double pkAlpha = pkAlpha(K - 1, c * z);
        return 1 - (pow(c, c) * QkAlpha * P0Process) / (factorial(c) * pkAlpha);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Mu = features.get(SystemFeature.Mu);
        final double E0 = E0(features);
        final double WAvg = WAvg(features);
        return K / (E0 + WAvg + (1 / Mu));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        return P0Process(c, K, z);
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double z = z(features);
        final double P0Process = P0Process(c, K - 1, z);
        final double pkAlpha1 = pkAlpha(K - n - 1, c * z);
        final double pkAlpha2 = pkAlpha(K - 1, c * z);
        return (pow(c, c) / factorial(c)) * (pkAlpha1 / pkAlpha2) * P0Process;
    }

    public double PinK(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        return PnProcess(features, n, K - 1);
    }

    public double PnKMin1(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double z = z(features);
        final double pkAlpha1 = pkAlpha(K - n - 1, c * z);
        final double pkAlpha2 = pkAlpha(K - 1, c * z);
        final double P0Process = P0Process(c, K - 1, z);
        return pow(c, c) / factorial(c) * pkAlpha1 / pkAlpha2 * P0Process;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double K = features.get(SystemFeature.K);
        return PnProcess(features, n, K);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        double result = 0;
        for (double i = c + 1; i <= K; i++) {
            result += (i - c) * PiBiggerc(features, i);
        }
        return result;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return K / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double Mu = features.get(SystemFeature.Mu);
        final double QAvg = QAvg(features);
        final double E0 = E0(features);
        return QAvg * (E0 + 1 / Mu) / (K - QAvg);
    }

    public double z(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double E0 = E0(features);
        return E0 / (1 / Mu);
    }

    private double P0Process(double numOfServer, double maxNumofCustomers, double zValue) {
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i <= numOfServer; i++) {
            sum1 += functionNCK(maxNumofCustomers, i) * pow(zValue, -i);
        }
        for (double j = numOfServer + 1; j <= maxNumofCustomers; j++) {
            final double part1 = factorial(j) / (factorial(numOfServer) * pow(numOfServer, j - numOfServer));
            final double part2 = functionNCK(maxNumofCustomers, j) * pow(zValue, -j);
            sum2 += part1 * part2;
        }
        return pow(sum1 + sum2, -1);
    }

    private double PiBiggerc(Map<SystemFeature, Double> features, double index) {
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double z = z(features);
        final double P0 = P0(features);
        final double NCK = functionNCK(K, index);
        final double part1 = factorial(index) / (factorial(c) * pow(c, index - c));
        return part1 * NCK * pow(z, -index) * P0;
    }


    private double PnProcess(Map<SystemFeature, Double> features, double index, double Kvalue) {
        final double c = features.get(SystemFeature.c);
        final double z = z(features);
        final double P0Process = P0Process(c, Kvalue, z);
        final double NCK = functionNCK(Kvalue, index);
        final double calculation = NCK * pow(z, -index) * P0Process;
        double result;
        if (index <= c) {
            result = calculation;
        } else {
            final double part1 = factorial(index) / (factorial(c) * pow(c, index - c));
            result = part1 * calculation;
        }
        return result;
    }


    private double QkAlpha(double kValue, double alpha) {
        double sum = 0;
        for (double i = 0; i <= kValue; i++) {
            sum += pow(alpha, i) / factorial(i);
        }
        return pow(E, -alpha) * sum;
    }

    private double pkAlpha(double kValue, double alpha) {
        final double part1 = pow(alpha, kValue);
        final double part2 = factorial(kValue);
        final double part3 = pow(E, -alpha);
        return part1 / part2 * part3;
    }

    private double functionNCK(double n, double k) {
        final double divisor = factorial(k) * factorial(n - k);
        return factorial(n) / divisor;
    }
}
