package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Alpha;
import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.c;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System D | D | n | K | K Service.
 */
@Component
public class SystemDDnKKCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        return 1 / features.get(Alpha);
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return features.get(c) * a(features) * features.get(Mu);
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * TAvg(features);
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * WAvg(features);
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return features.get(K) / LambdaAvg(features) - E0(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return TAvg(features) - SAvg(features);
    }

    public double a(Map<SystemFeature, Double> features) {
        return Math.min(1, features.get(K) / (features.get(c) * (1 + z(features))));
    }

    public double z(Map<SystemFeature, Double> features) {
        return E0(features) / SAvg(features);
    }
}
