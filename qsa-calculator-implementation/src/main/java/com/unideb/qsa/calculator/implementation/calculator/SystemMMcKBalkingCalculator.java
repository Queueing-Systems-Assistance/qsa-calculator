package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.factorial;
import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | c | K Balking Service.
 */
@Component
public class SystemMMcKBalkingCalculator extends SystemMMcKBalkingRenegingAbstractCalculator {

    @Override
    public double bn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        return 1.0 / (n + 1.0);
    }

    @Override
    public double rn(Map<SystemFeature, Double> features) {
        return 0;
    }

    public double EW2(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        double sum = 0.0;
        final Map<SystemFeature, Double> PiiFeatures = copyOf(features);
        for (double i = c; i <= K - 1.0; i++) {
            PiiFeatures.put(SystemFeature.n, i);
            final double dividend = (i - c + 1.0) + pow(i - c + 1.0, 2);
            final double divisor = pow(c * Mu, 2);
            sum += dividend * Pin(PiiFeatures) / divisor;
        }
        return sum;
    }

    public double D2W(Map<SystemFeature, Double> features) {
        final double WAvg = WAvg(features);
        final double EW2 = EW2(features);
        return EW2 - pow(WAvg, 2);
    }

    public double D2T(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double D2W = D2W(features);
        return D2W + 1 / pow(Mu, 2);
    }

    public double FWt(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double K = features.get(SystemFeature.K);
        final double t = features.get(SystemFeature.t);
        double sum = 0.0;
        final Map<SystemFeature, Double> PiiFeatures = copyOf(features);
        for (double i = c; i <= K - 1.0; i++) {
            double innerSum = 0.0;
            for (int j = 0; j <= i - c; j++) {
                final double dividend = pow(c * Mu * t, j) * pow(E, -1 * c * Mu * t);
                innerSum += dividend / factorial(j);
            }
            PiiFeatures.put(SystemFeature.n, i);
            final double Pii = Pin(PiiFeatures);
            sum += Pii * innerSum;
        }
        return 1 - sum;
    }
}
