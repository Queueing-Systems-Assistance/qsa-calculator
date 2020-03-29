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
 * System M | M | 1 Service.
 */
@Component
public class SystemMM1Calculator {

    public double D2N(Map<SystemFeature, Double> features) {
        return Ro(features) / Math.pow(1 - Ro(features), 2);
    }

    public double US(Map<SystemFeature, Double> features) {
        return Ro(features);
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        return (Math.pow(Ro(features), 2) * (1 + Ro(features) - Math.pow(Ro(features), 2))) / Math.pow(1 - Ro(features), 2);
    }

    public double D2QQ0(Map<SystemFeature, Double> features) {
        return Ro(features) / Math.pow(1 - Ro(features), 2);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        return Math.pow(1 / (features.get(Mu) * (1 - Ro(features))), 2);
    }

    public double D2W(Map<SystemFeature, Double> features) {
        return ((2 - Ro(features)) * Ro(features) * Math.pow(SAvg(features), 2)) / (Math.pow(1 - Ro(features), 2));
    }

    public double EQQ0(Map<SystemFeature, Double> features) {
        return 1 / (1 - Ro(features));
    }

    public double FTt(Map<SystemFeature, Double> features) {
        return 1 - PTt(features);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        return 1 - PWt(features);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return Ro(features) / (1 - Ro(features));
    }

    public double PNn(Map<SystemFeature, Double> features) {
        return Math.pow(Ro(features), features.get(n));
    }

    public double PTt(Map<SystemFeature, Double> features) {
        return Math.exp(-features.get(t) / TAvg(features));
    }

    public double PWt(Map<SystemFeature, Double> features) {
        return Ro(features) * Math.exp(-features.get(t) / TAvg(features));
    }

    public double PiT90(Map<SystemFeature, Double> features) {
        return TAvg(features) * Math.log(CalculatorHelper.VALUE_10);
    }

    public double PiT95(Map<SystemFeature, Double> features) {
        return TAvg(features) * Math.log(CalculatorHelper.VALUE_20);
    }

    public double PiTr(Map<SystemFeature, Double> features) {
        return TAvg(features) * Math.log(CalculatorHelper.VALUE_100 / (CalculatorHelper.VALUE_100 - features.get(r)));
    }

    public double PiW90(Map<SystemFeature, Double> features) {
        double result = TAvg(features) * Math.log(CalculatorHelper.VALUE_10 * Ro(features));
        return result < 0 ? 0 : result;
    }

    public double PiW95(Map<SystemFeature, Double> features) {
        double result = TAvg(features) * Math.log(CalculatorHelper.VALUE_20 * Ro(features));
        return result < 0 ? 0 : result;
    }

    public double PiWr(Map<SystemFeature, Double> features) {
        double result = TAvg(features) * Math.log(CalculatorHelper.VALUE_100 * Ro(features) / (CalculatorHelper.VALUE_100 - features.get(r)));
        return result < 0 ? 0 : result;
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return (1 - Ro(features)) * Math.pow(Ro(features), features.get(n));
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return Math.pow(Ro(features), 2) / (1 - Ro(features));
    }

    public double Ro(Map<SystemFeature, Double> features) {
        return features.get(Lambda) / features.get(Mu);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return 1 / (features.get(Mu) * (1 - Ro(features)));
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return NAvg(features) * (1 / features.get(Mu));
    }

}
