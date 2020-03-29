package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.r;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | 2 Service.
 */
@Component
public class SystemMM2Calculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return D2Q(features) + ((CalculatorHelper.VALUE_2 * a(features)
                                 * (1 + a(features) + CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_2))) / (1 + a(features)));
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_3)
                * (Math.pow(1 + a(features), CalculatorHelper.VALUE_2) - CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_3)))
               / Math.pow(1 - Math.pow(a(features), CalculatorHelper.VALUE_2), CalculatorHelper.VALUE_2);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        return ET2(features) - Math.pow(TAvg(features), CalculatorHelper.VALUE_2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        return (Math.pow(a(features), CalculatorHelper.VALUE_2) * (1 + a(features) - Math.pow(a(features), CalculatorHelper.VALUE_2))
                * Math.pow(SAvg(features), CalculatorHelper.VALUE_2))
               / (Math.pow(1 - Math.pow(a(features), CalculatorHelper.VALUE_2), CalculatorHelper.VALUE_2));
    }

    public double D2WW0(Map<SystemFeature, Double> features) {
        return Math.pow(SAvg(features) / (CalculatorHelper.VALUE_2 * (1 - a(features))), CalculatorHelper.VALUE_2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        double result;
        if (Ro(features) == 1) {
            result = (CalculatorHelper.VALUE_10 / CalculatorHelper.VALUE_3) * Math.pow(SAvg(features), CalculatorHelper.VALUE_2);
        } else {
            result = ((Math.pow(a(features), CalculatorHelper.VALUE_2) * (1 - CalculatorHelper.VALUE_4 * (Math.pow(1 - a(features), CalculatorHelper.VALUE_2))))
                      * Math.pow(SAvg(features), CalculatorHelper.VALUE_2))
                     / ((CalculatorHelper.VALUE_2 * a(features) - 1) * (1 - a(features)) * (1 - Math.pow(a(features), CalculatorHelper.VALUE_2)))
                     + CalculatorHelper.VALUE_2 * Math.pow(SAvg(features), CalculatorHelper.VALUE_2);
        }
        return result;
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        return SAvg(features) / (CalculatorHelper.VALUE_2 * (1 - a(features)));
    }


    public double FTt(Map<SystemFeature, Double> features) {
        return Ro(features) == 1 ? 1 - ((1 + (features.get(Mu) * features.get(t) / CalculatorHelper.VALUE_3))
                                        * Math.pow(Math.E, -features.get(Mu) * features.get(t)))
                : 1 + (((1 - a(features)) / (1 - Math.pow(a(features), 2) - Math.pow(a(features), 2) * 2))
                       * Math.pow(Math.E, -features.get(Mu) * features.get(t)))
                  + (((Math.pow(a(features), 2) * 2) / (1 - a(features) - Math.pow(a(features), 2) * 2))
                     * Math.pow(Math.E, (1 - a(features)) * -features.get(Mu) * features.get(t) * 2));
    }


    public double FWt(Map<SystemFeature, Double> features) {
        return 1 - ((CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_2)) / (1 + a(features)))
                   * (Math.exp(-CalculatorHelper.VALUE_2 * features.get(Mu) * features.get(t) * (1 - a(features))));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.VALUE_2 * a(features))
               / (1 - Math.pow(a(features), CalculatorHelper.VALUE_2));
    }

    public double P0(Map<SystemFeature, Double> features) {
        return (1 - a(features)) / (1 + a(features));
    }

    public double PN2(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_2)) / (1 + a(features));
    }

    public double PNn(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.VALUE_2 * Math.pow(a(features), features.get(n))) / (1 + a(features));
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        return Math.sqrt(D2T(features)) * CalculatorHelper.VALUE_1_3 + TAvg(features);
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        return Math.sqrt(D2T(features)) * CalculatorHelper.VALUE_2 + TAvg(features);
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        double tmp = ((SAvg(features)) / (CalculatorHelper.VALUE_2 * (1 - a(features))))
                     * Math.log10((CalculatorHelper.VALUE_20 * Math.pow(a(features), CalculatorHelper.VALUE_2)) / (1 + a(features)));
        return Math.max(0, tmp);
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        double tmp = ((SAvg(features)) / (CalculatorHelper.VALUE_2 * (1 - a(features))))
                     * Math.log10((CalculatorHelper.VALUE_40 * Math.pow(a(features), CalculatorHelper.VALUE_2)) / (1 + a(features)));
        return Math.max(0, tmp);
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        double tmp = ((SAvg(features)) / (CalculatorHelper.VALUE_2 * (1 - a(features))))
                     * Math.log10((CalculatorHelper.VALUE_200 * Math.pow(a(features), CalculatorHelper.VALUE_2))
                                  / ((CalculatorHelper.VALUE_100 - features.get(r)) * (1 + a(features))));
        return Math.max(0, tmp);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return CalculatorHelper.VALUE_2 * P0(features) * Math.pow(a(features), features.get(n));
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return (CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_3)) / (1 - Math.pow(a(features), CalculatorHelper.VALUE_2));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 2 - (features.get(Lambda) / features.get(Mu));
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu) + WAvg(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return (Math.pow(a(features), CalculatorHelper.VALUE_2) * SAvg(features)) / (1 - Math.pow(a(features), CalculatorHelper.VALUE_2));
    }

    public double WAvg0(Map<SystemFeature, Double> features) {
        return (1 + a(features) - CalculatorHelper.VALUE_2 * Math.pow(a(features), CalculatorHelper.VALUE_2)) / (1 + a(features));
    }

    public double WAvgW(Map<SystemFeature, Double> features) {
        double result = 0;
        if (features.get(t) > 0) {
            result = 1 - (Math.exp((-CalculatorHelper.VALUE_2 * features.get(t) * (1 - a(features))) / SAvg(features)));
        }
        return result;
    }

    public double a(Map<SystemFeature, Double> features) {
        return Ro(features) / CalculatorHelper.VALUE_2;
    }

    public double US(Map<SystemFeature, Double> features) {
        return a(features);
    }

}
