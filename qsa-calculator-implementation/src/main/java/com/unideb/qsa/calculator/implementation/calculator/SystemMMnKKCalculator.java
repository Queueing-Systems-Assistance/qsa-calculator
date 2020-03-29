package com.unideb.qsa.calculator.implementation.calculator;

import static com.unideb.qsa.calculator.domain.SystemFeature.Alpha;
import static com.unideb.qsa.calculator.domain.SystemFeature.K;
import static com.unideb.qsa.calculator.domain.SystemFeature.Mu;
import static com.unideb.qsa.calculator.domain.SystemFeature.c;
import static com.unideb.qsa.calculator.domain.SystemFeature.n;
import static com.unideb.qsa.calculator.domain.SystemFeature.t;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper;

/**
 * System M | M | n | K | K Service.
 */
@Component
public class SystemMMnKKCalculator {

    public double C1(Map<SystemFeature, Double> features) {
        return C2(features) + 1;
    }

    public double C2(Map<SystemFeature, Double> features) {
        return Math.pow(features.get(c), features.get(c))
               * QkAlpha(features.get(K) - features.get(c) - 1, features.get(c) * z(features))
               / (CalculatorHelper.factorial(features.get(c))
                  * (features.get(c) - 1)
                  * (features.get(K) - features.get(c) - 1)
                  * pkAlpha(features.get(K) - 1, features.get(c) * z(features))) * P0Process(features.get(c), features.get(K) - 1,
                z(features));
    }

    public double D(Map<SystemFeature, Double> features) {
        double sum = 0;
        for (double i = 0; i < features.get(c); i++) {
            sum += (features.get(K) - i) * PnProcess(features, i, features.get(K))
                   / (features.get(K) - NAvg(features));
        }
        return 1 - sum;
    }

    public double E0(Map<SystemFeature, Double> features) {
        return 1 / features.get(Alpha);
    }

    public double EWW0(Map<SystemFeature, Double> features) {
        return WAvg(features) / D(features);
    }

    public double FTt(Map<SystemFeature, Double> features) {
        return 1 - C1(features) * Math.exp(-features.get(t) / SAvg(features))
               + C2(features) * (QkAlpha(features.get(K) - features.get(c)
                                         - 1, features.get(c) * (z(features) + features.get(t) * features.get(Mu)))
                                 / QkAlpha(features.get(K) - features.get(c) - 1, features.get(c) * z(features)));
    }

    public double FWt(Map<SystemFeature, Double> features) {
        return 1 - (Math.pow(features.get(c), features.get(c))
                    * QkAlpha(features.get(K) - features.get(c) - 1, features.get(c) * z(features))
                    * P0Process(features.get(c), features.get(K) - 1, z(features)))
                   / (CalculatorHelper.factorial(features.get(c))
                      * pkAlpha(features.get(K) - 1, features.get(c) * z(features)));
    }

    public double LambdaAvg(Map<SystemFeature, Double> features) {
        return features.get(K) / (E0(features) + WAvg(features) + (1 / features.get(Mu)));
    }

    public double NAvg(Map<SystemFeature, Double> features) {
        return LambdaAvg(features) * TAvg(features);
    }

    public double P0(Map<SystemFeature, Double> features) {
        return P0Process(features.get(c), features.get(K), z(features));
    }

    public double Pin(Map<SystemFeature, Double> features) {
        return (Math.pow(features.get(c), features.get(c)) / CalculatorHelper.factorial(features.get(c)))
               * (pkAlpha(features.get(K) - features.get(n) - 1, features.get(c) * z(features))
                  / (pkAlpha(features.get(K) - 1, features.get(c) * z(features))))
               * P0Process(features.get(c), features.get(K) - 1, z(features));
    }

    public double PinK(Map<SystemFeature, Double> features) {
        return PnProcess(features, features.get(n), features.get(K) - 1);
    }

    public double PnKMin1(Map<SystemFeature, Double> features) {
        return Math.pow(features.get(c), features.get(c)) / CalculatorHelper.factorial(features.get(c))
               * (pkAlpha(features.get(K) - features.get(n) - 1, features.get(c) * z(features)))
               / (pkAlpha(features.get(K) - 1, features.get(c) * z(features)))
               * P0Process(features.get(c), features.get(K) - 1, z(features));
    }

    public double Pn(Map<SystemFeature, Double> features) {
        return PnProcess(features, features.get(n), features.get(K));
    }

    public double QAvg(Map<SystemFeature, Double> features) {
        double result = 0;
        for (double i = features.get(c) + 1; i <= features.get(K); i++) {
            result += (i - features.get(c))
                      * PiBiggerc(features, i);
        }
        return result;
    }

    public double SAvg(Map<SystemFeature, Double> features) {
        return 1 / features.get(Mu);
    }

    public double TAvg(Map<SystemFeature, Double> features) {
        return features.get(K) / LambdaAvg(features) - E0(features);
    }

    public double WAvg(Map<SystemFeature, Double> features) {
        return (QAvg(features) * (E0(features) + (1 / features.get(Mu))))
               / (features.get(K) - QAvg(features));
    }

    public double z(Map<SystemFeature, Double> features) {
        return E0(features) / (1 / features.get(Mu));
    }

    private double P0Process(double numOfServer, double maxNumofCustomers, double zValue) {
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i <= numOfServer; i++) {
            sum1 += functionNCK(maxNumofCustomers, i)
                    * Math.pow(zValue, -i);
        }
        for (double j = numOfServer + 1; j <= maxNumofCustomers; j++) {
            sum2 += (CalculatorHelper.factorial(j))
                    / (CalculatorHelper.factorial(numOfServer) * Math.pow(numOfServer, j - numOfServer))
                    * functionNCK(maxNumofCustomers, j)
                    * Math.pow(zValue, -j);
        }
        return Math.pow(sum1 + sum2, -1);
    }

    private double PiBiggerc(Map<SystemFeature, Double> features, double index) {
        return (CalculatorHelper.factorial(index)
                / (CalculatorHelper.factorial(features.get(c))
                   * Math.pow(features.get(c), index - features.get(c))))
               * functionNCK(features.get(K), index)
               * Math.pow(z(features), -index) * P0(features);
    }


    private double PnProcess(Map<SystemFeature, Double> features, double index, double Kvalue) {
        double result;
        if (index <= features.get(c)) {
            result = functionNCK(Kvalue, index)
                     * Math.pow(z(features), -index)
                     * P0Process(features.get(c), Kvalue, z(features));
        } else {
            result = (CalculatorHelper.factorial(index)
                      / (CalculatorHelper.factorial(features.get(c))
                         * Math.pow(features.get(c), index - features.get(c))))
                     * functionNCK(Kvalue, index)
                     * Math.pow(z(features), -index) * P0Process(features.get(c), Kvalue, z(features));
        }
        return result;
    }


    private double QkAlpha(double kValue, double alpha) {
        double sum = 0;
        for (double i = 0; i <= kValue; i++) {
            sum += Math.pow(alpha, i) / CalculatorHelper.factorial(i);
        }
        return Math.pow(Math.E, -alpha) * sum;
    }

    private double pkAlpha(double kValue, double alpha) {
        return (Math.pow(alpha, kValue) / CalculatorHelper.factorial(kValue)) * Math.pow(Math.E, -alpha);
    }

    private double functionNCK(double n, double k) {
        return CalculatorHelper.factorial(n) / (CalculatorHelper.factorial(k) * CalculatorHelper.factorial(n - k));
    }
}
