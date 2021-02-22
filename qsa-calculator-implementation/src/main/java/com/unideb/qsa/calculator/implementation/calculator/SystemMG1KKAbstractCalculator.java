package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static java.lang.Math.pow;
import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble;

import java.util.Map;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | G | 1 | K | K Service.
 * Abstract class providing calculations for M | G | 1 | K | K systems.
 */
public abstract class SystemMG1KKAbstractCalculator {

    public double E0(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        return 1 / LambdaFin;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double SAvg = SAvg(features);
        final double E0 = E0(features);
        double sum = 0.0;
        for (double i = 0.0; i <= KFin - 1; i++) {
            final double binomialCoefficient = binomialCoefficientDouble((int) KFin - 1, (int) i);
            final double Bi = functionBn(features, i);
            sum += binomialCoefficient * Bi;
        }
        return pow(1 + KFin * SAvg / E0 * sum, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double k = KFin - n;
        double sum = 0.0;
        for (double i = k; i <= KFin; i++) {
            sum += pow(-1, i - k) * binomialCoefficientDouble((int)i, (int)k) * binomialMomentPi(features, i);
        }
        return sum;
    }

    public double PinFin(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double n = features.get(SystemFeature.n);
        final double k = KFin - n;
        double sum = 0.0;
        for (double i = k; i <= KFin - 1; i++) {
            sum += pow(-1, i - k) * binomialCoefficientDouble((int)i, (int)k) * binomialMomentPii(features, i);
        }
        return sum;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double mAvg = mAvg(features);
        return mAvg / KFin;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double NAvg = NAvg(features);
        return KFin - NAvg;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double SAvg = SAvg(features);
        final double a = a(features);
        return a / SAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final double LambdaAvg = LambdaAvg(features);
        final double E0 = E0(features);
        return KFin / LambdaAvg - E0;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double TAvg = TAvg(features);
        return LambdaAvg * TAvg;
    }

    public double EN2(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= KFin; i++) {
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

    public double WAvg(Map<SystemFeature, Double> features) {
        final double TAvg = TAvg(features);
        final double SAvg = SAvg(features);
        return TAvg - SAvg;
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        final double LambdaAvg = LambdaAvg(features);
        final double WAvg = WAvg(features);
        return LambdaAvg * WAvg;
    }

    public double EQ2(Map<SystemFeature, Double> features) {
        final double KFin = features.get(SystemFeature.KFin);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 2.0; i <= KFin; i++) {
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

    public double EDelta1(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double P0 = P0(features);
        final double dividend = 1 - P0;
        final double divisor = LambdaFin * P0;
        return dividend / divisor;
    }

    public double KStar(Map<SystemFeature, Double> features) {
        final double LambdaFin = features.get(SystemFeature.LambdaFin);
        final double SAvg = SAvg(features);
        return 1 + 1 / (SAvg * LambdaFin);
    }

    public double functionBn(Map<SystemFeature, Double> features, double index) {
        double result = 1.0;
        if (index != 0) {
            for (double i = 1.0; i <= index; i++) {
                final double laplace = laplaceTransform(features, i);
                result *= (1 - laplace) / laplace;
            }
        }
        return result;
    }

    public double binomialMomentPii(Map<SystemFeature, Double> features, double index) {
        final double KFin = features.get(SystemFeature.KFin);
        final double Cindex = pow(functionBn(features, index), -1);
        double dividend = 0.0;
        for (double i = index; i <= KFin - 1; i++) {
            dividend += binomialCoefficientDouble((int)KFin - 1, (int)i) * functionBn(features, i);
        }
        double divisor = 0.0;
        for (double i = 0.0; i <= KFin - 1; i++) {
            divisor += binomialCoefficientDouble((int)KFin - 1, (int)i) * functionBn(features, i);
        }
        return Cindex * dividend / divisor;
    }

    public double binomialMomentPi(Map<SystemFeature, Double> features, double index) {
        double result;
        if (index == 0.0) {
            result = 1.0;
        } else {
            final double LambdaFin = features.get(SystemFeature.LambdaFin);
            final double KFin = features.get(SystemFeature.KFin);
            final double SAvg = SAvg(features);
            final double part1 = KFin * pow(functionBn(features, index - 1), -1) / index;
            double dividend = 0.0;
            for (double i = index - 1; i <= KFin - 1; i++) {
                dividend += binomialCoefficientDouble((int)KFin - 1, (int)i) * functionBn(features, i);
            }
            double divisorSum = 0.0;
            for (double i = 0.0; i <= KFin - 1; i++) {
                divisorSum += binomialCoefficientDouble((int)KFin - 1, (int)i) * functionBn(features, i);
            }
            final double divisor = 1 + KFin * LambdaFin * SAvg * divisorSum;
            result = part1 * dividend / divisor;
        }
        return result;
    }

    public abstract double SAvg(Map<SystemFeature, Double> features);

    public abstract double laplaceTransform(Map<SystemFeature, Double> features, double index);
}
