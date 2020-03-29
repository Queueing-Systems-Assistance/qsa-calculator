package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.c;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.r;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | n Service.
 */
@Component
public class SystemMMnCalculator {

    public double C1(Map<SystemFeature, Double> features) {
        return PNc(features) / (1 - features.get(c) * (1 - a(features))) - 1;
    }

    public double C2(Map<SystemFeature, Double> features) {
        return PNc(features) / (features.get(c) * (1 - a(features)) - 1);
    }

    public double D2N(Map<SystemFeature, Double> features) {
        return D2Q(features) + Ro(features) * (1 + PNc(features));
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        return ((a(features) * PNc(features)) * (1 + a(features) - a(features) * PNc(features))) / (Math.pow(1 - a(features), 2));
    }

    public double D2T(Map<SystemFeature, Double> features) {
        return ET2(features) - Math.pow(TAvg(features), 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        return ((2 - PNc(features)) * (PNc(features) * Math.pow(SAvg(features), 2))) / (Math.pow(features.get(c), 2) * Math.pow(1 - a(features), 2));
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        return Math.pow(SAvg(features) / (features.get(c) * (1 - a(features))), 2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        double result;
        if (Ro(features) == features.get(c) - 1) {
            result = 2 * (2 * PNc(features) + 1) * SAvg(features);
        } else {
            result = (2 * PNc(features) * (1 - Math.pow(features.get(c), 2) * Math.pow(1 - a(features), 2)) * Math.pow(SAvg(features), 2))
                     / ((Ro(features) + 1 - features.get(c)) * Math.pow(features.get(c), 2) * Math.pow(1 - a(features), 2)) + 2 * Math.pow(SAvg(features), 2);
        }
        return result;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        return SAvg(features) / (features.get(c) * (1 - a(features)));
    }

    public double FTt(Map<SystemFeature, Double> features) {
        double result;
        if (Ro(features) == features.get(c) - 1) {
            result = 1 - (1 + PNc(features) * features.get(Mu) * features.get(t)) * Math.pow(Math.E, -features.get(Mu) * features.get(t));
        } else {
            result = 1 + (C1(features) * Math.pow(Math.E, -features.get(Mu) * features.get(t)))
                         * (C2(features) * Math.pow(Math.E, -features.get(Mu) * features.get(t) * features.get(c)) * (1 - a(features)));
        }
        return result;
    }

    public double FWt(Map<SystemFeature, Double> features) {
        return 1 - PNc(features) * Math.exp(-features.get(c) * features.get(Mu) * features.get(t) * (1 - a(features)));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return QAvg(features) + Ro(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        double tmp = 0;
        for (int i = 0; i <= features.get(c) - 1; i++) {
            tmp += Math.pow(Ro(features), i) / CalculatorHelper.factorial(i);
        }
        return Math.pow(tmp + (Math.pow(Ro(features), features.get(c)) / (CalculatorHelper.factorial(features.get(c)) * (1 - a(features)))), -1);
    }

    public double PNc(Map<SystemFeature, Double> features) {
        double recursive = ErlangBRecursive(features.get(c), Ro(features));
        return features.get(c) * recursive / (features.get(c) - Ro(features) + Ro(features) * recursive);
    }

    public double PNn(Map<SystemFeature, Double> features) {
        double tmp = 0;
        double result;
        for (double i = features.get(n); i <= features.get(c) - 1; i++) {
            tmp += Math.pow(Ro(features), i) / CalculatorHelper.factorial(i);
        }
        if (features.get(c) > features.get(n)) {
            result = P0(features) * (tmp + (Math.pow(Ro(features), features.get(c)) / (CalculatorHelper.factorial(features.get(c)) * (1 - features.get(c)))));
        } else {
            result = PNc(features) * Math.pow(a(features), features.get(n) - features.get(c));
        }
        return result;
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        return TAvg(features) + CalculatorHelper.VALUE_1_3 * Math.sqrt(D2T(features));
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        return TAvg(features) + 2 * Math.sqrt(D2T(features));
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        double tmp = (SAvg(features) / (features.get(c) * (1 - a(features)))) * Math.log10(CalculatorHelper.VALUE_10 * PNc(features));
        return Math.max(0, tmp);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        double num = CalculatorHelper.VALUE_20 * PNc(features);
        double tmp = (SAvg(features) / (features.get(c) * (1 - a(features)))) * Math.log10(num);
        return Math.max(0, tmp);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        double tmp = (SAvg(features) / (features.get(c) * (1 - a(features)))) * Math
                .log10((CalculatorHelper.VALUE_100 * PNc(features)) / (CalculatorHelper.VALUE_100 - features.get(r)));
        return Math.max(0, tmp);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        double result;
        if (features.get(c) > features.get(n)) {
            result = P0(features) * (Math.pow(Ro(features), features.get(n)) / CalculatorHelper.factorial(features.get(n)));
        } else {
            result = P0(features) * (Math.pow(Ro(features), features.get(n)) / (CalculatorHelper.factorial(features.get(c)) * Math
                    .pow(features.get(c), features.get(n) - features.get(c))));
        }
        return result;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return Ro(features) * PNc(features) / (features.get(c) * (1 - a(features)));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return features.get(c) - (features.get(Lambda) / features.get(Mu));
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return WAvg(features) + 1 / features.get(Mu);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return 1 / (features.get(Mu) * (features.get(c) - Ro(features))) * PNc(features);
    }

    public double WAvg0(Map<SystemFeature, Double> features) {
        return 1 - PNc(features);
    }

    public double WAvgW(Map<SystemFeature, Double> features) {
        double result = 0;
        if (features.get(t) > 0) {
            result = 1 - Math.exp((-features.get(c) * features.get(t) * (1 - a(features))) / SAvg(features));
        }
        return result;
    }

    public double a(Map<SystemFeature, Double> features) {
        return Ro(features) / features.get(c);
    }

    public double US(Map<SystemFeature, Double> features) {
        return a(features);
    }

    private double ErlangBRecursive(double cMmcc, double roMmcc) {
        double result;
        if (cMmcc == 1) {
            result = roMmcc / (1 + roMmcc);
        } else {
            double recursive = ErlangBRecursive(cMmcc - 1, roMmcc);
            result = roMmcc * recursive / (cMmcc + roMmcc * recursive);
        }
        return result;
    }
}
