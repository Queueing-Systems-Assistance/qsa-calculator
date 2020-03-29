package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Lambda;
import static com.unideb.qsa.calculator.domain.SystemFeature.eS;
import static com.unideb.qsa.calculator.domain.SystemFeature.eSPow2;
import static com.unideb.qsa.calculator.domain.SystemFeature.eSPow3;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | G | 1 Service.
 */
@Component
public class SystemMG1Calculator {

    public double SAvg(Map<SystemFeature, Double> features) {
        return features.get(eS);
    }

    public double C2S(Map<SystemFeature, Double> features) {
        return (features.get(eSPow2) - Math.pow(features.get(eS), CalculatorHelper.VALUE_2)) / Math.pow(features.get(eS), CalculatorHelper.VALUE_2);
    }

    public double D2N(Map<SystemFeature, Double> features) {
        return ((Math.pow(features.get(Lambda), CalculatorHelper.VALUE_3) * features.get(eSPow3)) / (CalculatorHelper.VALUE_3 * (1 - Ro(features))))
               + (Math.pow(Math.pow(features.get(Lambda), CalculatorHelper.VALUE_2) * features.get(eSPow2) / (CalculatorHelper.VALUE_2 * (1 - Ro(features))),
                CalculatorHelper.VALUE_2))
               + (Math.pow(features.get(Lambda), CalculatorHelper.VALUE_2) * (CalculatorHelper.VALUE_3 - CalculatorHelper.VALUE_2 * Ro(features)) * features
                .get(eSPow2)
                  / (CalculatorHelper.VALUE_2 * (1 - Ro(features))))
               + (Ro(features) * (1 - Ro(features)));
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        return ((Math.pow(features.get(Lambda), CalculatorHelper.VALUE_3) * features.get(eSPow3)) / (CalculatorHelper.VALUE_3 * (1 - Ro(features))))
               + (Math.pow(Math.pow(features.get(Lambda), CalculatorHelper.VALUE_2) * features.get(eSPow2) / (CalculatorHelper.VALUE_2 * (1 - Ro(features))),
                CalculatorHelper.VALUE_2))
               + (Math.pow(features.get(Lambda), CalculatorHelper.VALUE_2) * features.get(eSPow2) / (CalculatorHelper.VALUE_2 * (1 - Ro(features))));
    }

    public double D2T(Map<SystemFeature, Double> features) {
        return ET2(features) - Math.pow(TAvg(features), CalculatorHelper.VALUE_2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        return EW2(features) - Math.pow(WAvg(features), CalculatorHelper.VALUE_2);
    }

    public double ET2(Map<SystemFeature, Double> features) {
        return EW2(features) + features.get(eSPow2) / (1 - Ro(features));
    }

    public double EW2(Map<SystemFeature, Double> features) {
        return 2 * Math.pow(WAvg(features), CalculatorHelper.VALUE_2)
               + (features.get(Lambda) * features.get(eSPow3) / (CalculatorHelper.VALUE_3 * (1 - Ro(features))));
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        return features.get(eS) / (1 - Ro(features)) * ((1 + C2S(features)) / CalculatorHelper.VALUE_2);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return QAvg(features) + Ro(features);
    }

    public double PN1(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return features.get(Lambda) * WAvg(features);
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) * features.get(eS);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return WAvg(features) + features.get(eS);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return (features.get(Lambda) * features.get(eSPow2)) / (CalculatorHelper.VALUE_2 * (1 - Ro(features)));
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        return TAvg(features) + CalculatorHelper.VALUE_1_3 * Math.sqrt(D2T(features));
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        return TAvg(features) + CalculatorHelper.VALUE_2 * Math.sqrt(D2T(features));
    }

}
