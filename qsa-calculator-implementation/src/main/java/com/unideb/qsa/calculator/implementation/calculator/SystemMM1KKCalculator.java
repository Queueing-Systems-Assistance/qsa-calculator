package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | 1 | K | K Service.
 */
@Component
public class SystemMM1KKCalculator {

    public double Ro(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double Mu = features.get(SystemFeature.Mu);
        return LambdaFin / Mu;
    }

    public double E0(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        return 1 / LambdaFin;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double Pi0 = Pi0(features);
        return WAvg / (1 - Pi0);
    }

    public double PTt(Map<SystemFeature, Double> features) {
        return FTt(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = Qnx(KFin - 1, z + t * Mu);
        final double divisor = Qnx(KFin - 1, z);
        return 1 - dividend / divisor;
    }

    public double PWt(Map<SystemFeature, Double> features) {
        return FWt(features);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double t = features.get(SystemFeature.t);
        final double Mu = features.get(SystemFeature.Mu);
        final double z = z(features);
        final double dividend = Qnx(KFin - 2, z + t * Mu);
        final double divisor = Qnx(KFin - 1, z);
        return 1 - dividend / divisor;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double US = US(features);
        final double SAvg = SAvg(features);
        return US / SAvg;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return KFin - NAvg;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        final double US = US(features);
        return KFin - US / Ro;
    }

    public double D2N(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        final double US = US(features);
        final double part1 = US / Ro;
        final double part2 = (1 - US) / Ro;
        final double part3 = KFin - part1;
        return part1 - part2 * part3;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double Ro = Ro(features);
        return ErlangBRecursive(KFin, 1 / Ro);
    }

    public double Pi0(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        final double dividend = KFin * P0;
        final double divisor = KFin - NAvg;
        return dividend / divisor;
    }

    public double PinFin(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        final double Pn = Pn(features);
        final double dividend = (KFin - n) * Pn;
        final double divisor = KFin - NAvg;
        return dividend / divisor;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double z = z(features);
        final double P0 = P0(features);
        final double fraction = factorial(KFin) / factorial(KFin - n);
        return fraction * pow(z, -1 * n) * P0;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        return 1 / Mu;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double E0 = E0(features);
        final double LambdaAvg = LambdaAvg(features);
        return KFin / LambdaAvg - E0;
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double KFin = features.get(SystemFeature.KFin);
        Map<SystemFeature, Double> Kmin1Features = copyOf(features);
        Kmin1Features.put(SystemFeature.KFin, KFin - 1);
        final double NAvgKmin1 = NAvg(Kmin1Features);
        final double D2NKmin1 = D2N(Kmin1Features);
        final double part1 = 1 / pow(Mu, 2);
        final double part2 = NAvgKmin1 + D2NKmin1;
        return part1 * part2;
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double D2W = D2W(features);
        final double fraction = 1 / pow(Mu, 2);
        return D2W + fraction;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double a(Map<SystemFeature, Double> features) {
        return US(features);
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return 1 - NAvg / KFin;
    }

    public double z(Map<SystemFeature, Double> features) {
        final double E0 = E0(features);
        final double SAvg = SAvg(features);
        return E0 / SAvg;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double US = US(features);
        return NAvg - US;
    }

    public double EQpow2(Map<SystemFeature, Double> features) {
        final double US = US(features);
        final double NAvg = NAvg(features);
        final double D2N = D2N(features);
        return D2N + pow(NAvg, 2) - 2 * NAvg + US;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double EQpow2 = EQpow2(features);
        return EQpow2 - pow(QAvg, 2);
    }

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double KFin = features.get(SystemFeature.KFin);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = KFin * LambdaFin * P0;
        return dividend / divisor;
    }

    public double ECost(Map<SystemFeature, Double> features) {
        final double CS = features.get(SystemFeature.CS);
        final double CWS = features.get(SystemFeature.CWS);
        final double CI = features.get(SystemFeature.CI);
        final double CSR = features.get(SystemFeature.CSR);
        final double R = features.get(SystemFeature.R);
        final double LambdaAvg = LambdaAvg(features);
        final double NAvg = NAvg(features);
        final double P0 = P0(features);
        return CS + NAvg * CWS + P0 * CI + CSR - LambdaAvg * R;
    }

    private double ErlangBRecursive(double c, double Ro) {
        final double result;
        if (c == 1) {
            result = Ro / (1 + Ro);
        } else {
            final double recursive = ErlangBRecursive(c - 1, Ro);
            final double dividend = Ro * recursive;
            final double divisor = c + Ro * recursive;
            result = dividend / divisor;
        }
        return result;
    }

    private double Qnx(double n, double x) {
        double result = 0;
        for (double k = 0; k <= n; k++) {
            result += pow(x, k) / factorial(k);
        }
        return pow(E, -1 * x) * result;
    }
}
