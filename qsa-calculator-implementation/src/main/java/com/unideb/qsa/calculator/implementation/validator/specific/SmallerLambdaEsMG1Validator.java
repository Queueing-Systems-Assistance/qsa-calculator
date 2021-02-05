package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * eS < 1 in M | G | 1 priority systems.
 */
@Component
public class SmallerLambdaEsMG1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.eSc1, SystemFeature.eSc2, SystemFeature.eSPow2c1, SystemFeature.eSPow2c2, SystemFeature.eSPow3c1, SystemFeature.eSPow3c2);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda = lambda(features);
        final double eS = eS(features);
        if (lambda * eS >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.eS.name(), List.of("error.validation.feature.Lambda.Es.should.be.smaller.from.1"),
                    SystemFeature.Lambda.name(), List.of("error.validation.feature.Lambda.Es.should.be.smaller.from.1")));
        }
        return result;
    }

    private double lambda(Map<SystemFeature, Double> features) {
        final double lambda1 = features.get(SystemFeature.Lambda1);
        final double lambda2 = features.get(SystemFeature.Lambda2);
        return lambda1 + lambda2;
    }

    private double eS(Map<SystemFeature, Double> features) {
        final double lambda1 = features.get(SystemFeature.Lambda1);
        final double lambda2 = features.get(SystemFeature.Lambda2);
        final double eSc1 = features.get(SystemFeature.eSc1);
        final double eSc2 = features.get(SystemFeature.eSc2);
        final double lambda = lambda(features);
        final double part1 = lambda1 * eSc1 / lambda;
        final double part2 = lambda2 * eSc2 / lambda;
        return part1 + part2;
    }
}
