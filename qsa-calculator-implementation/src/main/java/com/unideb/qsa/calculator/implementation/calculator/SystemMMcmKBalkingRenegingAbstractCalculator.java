package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.copyOf;
import static java.lang.Math.pow;

import java.util.Map;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * System M | M | c | m | K Balking/Reneging Service.
 * Abstract class providing calculations for M | M | c | m | K Balking/Reneging systems.
 */
public abstract class SystemMMcmKBalkingRenegingAbstractCalculator {

    public double LambdaN(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double K = features.get(SystemFeature.K);
        final double n = features.get(SystemFeature.n);
        final double bn = bn(features);
        return (K - n) * Lambda * bn;
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> iFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 0.0; i <= m - 1.0; i++) {
            iFeatures.put(SystemFeature.n, i);
            final double Pi = Pn(iFeatures);
            final double Lambdai = LambdaN(iFeatures);
            sum += Lambdai * Pi;
        }
        return sum;
    }

    public double Mun(Map<SystemFeature, Double> features) {
        final double Mu = features.get(SystemFeature.Mu);
        final double c = features.get(SystemFeature.c);
        final double n = features.get(SystemFeature.n);
        double result;
        if (n < c) {
            result = n * Mu;
        } else {
            final double rn = rn(features);
            result = c * Mu + rn;
        }
        return result;
    }

    public double MuAvg(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> iFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= m; i++) {
            iFeatures.put(SystemFeature.n, i);
            final double Pi = Pn(iFeatures);
            final double Mui = Mun(iFeatures);
            sum += Mui * Pi;
        }
        return sum;
    }

    public double P0(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        double sum = 0.0;
        for (double j = 1.0; j <= m; j++) {
            final Map<SystemFeature, Double> LambdaFeatures = copyOf(features);
            final Map<SystemFeature, Double> MuFeatures = copyOf(features);
            double LambdaProduct = 1.0;
            double MuProduct = 1.0;
            for (double i = 1.0; i <= j; i++) {
                LambdaFeatures.put(SystemFeature.n, i - 1.0);
                MuFeatures.put(SystemFeature.n, i);
                LambdaProduct *= LambdaN(LambdaFeatures);
                MuProduct *= Mun(MuFeatures);
            }
            sum += LambdaProduct / MuProduct;
        }
        return pow(1 + sum, -1);
    }

    public double Pn(Map<SystemFeature, Double> features) {
        final double n = features.get(SystemFeature.n);
        final double P0 = P0(features);
        final Map<SystemFeature, Double> LambdaFeatures = copyOf(features);
        final Map<SystemFeature, Double> MuFeatures = copyOf(features);
        double LambdaProduct = 1.0;
        double MuProduct = 1.0;
        for (double i = 1.0; i <= n; i++) {
            LambdaFeatures.put(SystemFeature.n, i - 1.0);
            MuFeatures.put(SystemFeature.n, i);
            LambdaProduct *= LambdaN(LambdaFeatures);
            MuProduct *= Mun(MuFeatures);
        }
        return LambdaProduct * P0 / MuProduct;
    }

    public double Pin(Map<SystemFeature, Double> features) {
        final double LambdaN = LambdaN(features);
        final double Pn = Pn(features);
        final double LambdaAvg = LambdaAvg(features);
        return LambdaN * Pn / LambdaAvg;
    }

    public double PW(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = c; i <= m - 1.0; i++) {
            PiiFeatures.put(SystemFeature.n, i);
            sum += Pin(PiiFeatures);
        }
        return sum;
    }

    public double PB(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> mFeatures = copyOf(features);
        mFeatures.put(SystemFeature.n, m);
        final double Lambdam = LambdaN(mFeatures);
        final double Pm = Pn(mFeatures);
        double sum = 0.0;
        for (double i = 0.0; i <= m; i++) {
            mFeatures.put(SystemFeature.n, i);
            final double Lambdai = LambdaN(mFeatures);
            final double Pi = Pn(mFeatures);
            sum += Lambdai * Pi;
        }
        return Lambdam * Pm / sum;
    }

    public double PJ(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        final double LambdaAvg = LambdaAvg(features);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 0.0; i <= m - 1.0; i++) {
            PiFeatures.put(SystemFeature.n, i);
            final double Pi = Pn(PiFeatures);
            sum += (K - i) * Lambda * Pi;
        }
        return LambdaAvg / sum;
    }

    public double PR(Map<SystemFeature, Double> features) {
        final double MuAvg = MuAvg(features);
        final double rAvg = rAvg(features);
        return rAvg / MuAvg;
    }

    public double US(Map<SystemFeature, Double> features) {
        final double P0 = P0(features);
        return 1 - P0;
    }

    public double a(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double cAvg = cAvg(features);
        return cAvg / c;
    }

    public double Ut(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double mAvg = mAvg(features);
        return mAvg / K;
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= m; i++) {
            PiFeatures.put(SystemFeature.n, i);
            sum += i * Pn(PiFeatures);
        }
        return sum;
    }

    public double EN2(Map<SystemFeature, Double> features) {
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= m; i++) {
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

    public double QAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = c; i <= m; i++) {
            PiFeatures.put(SystemFeature.n, i);
            sum += (i - c) * Pn(PiFeatures);
        }
        return sum;
    }

    public double EQ2(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = c; i <= m; i++) {
            PiFeatures.put(SystemFeature.n, i);
            sum += pow(i - c, 2) * Pn(PiFeatures);
        }
        return sum;
    }

    public double D2Q(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double EQ2 = EQ2(features);
        return EQ2 - pow(QAvg, 2);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        final double QAvg = QAvg(features);
        final double LambdaAvg = LambdaAvg(features);
        return QAvg / LambdaAvg;
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        final double NAvg = NAvg(features);
        final double LambdaAvg = LambdaAvg(features);
        return NAvg / LambdaAvg;
    }

    public double cAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> PiFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = 1.0; i <= m; i++) {
            PiFeatures.put(SystemFeature.n, i);
            final double Pi = Pn(PiFeatures);
            if (i < c) {
                sum += i * Pi;
            } else {
                sum += c * Pi;
            }
        }
        return sum;
    }

    public double mAvg(Map<SystemFeature, Double> features) {
        final double K = features.get(SystemFeature.K);
        final double NAvg = NAvg(features);
        return K - NAvg;
    }

    public double rAvg(Map<SystemFeature, Double> features) {
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final Map<SystemFeature, Double> iFeatures = copyOf(features);
        double sum = 0.0;
        for (double i = c; i <= m; i++) {
            iFeatures.put(SystemFeature.n, i);
            sum += rn(iFeatures) * Pn(iFeatures);
        }
        return sum;
    }

    public double EDeltar(Map<SystemFeature, Double> features) {
        final double Lambda = features.get(SystemFeature.Lambda);
        final double US = US(features);
        return US / (Lambda * (1 - US));
    }

    public abstract double bn(Map<SystemFeature, Double> features);

    public abstract double rn(Map<SystemFeature, Double> features);
}
