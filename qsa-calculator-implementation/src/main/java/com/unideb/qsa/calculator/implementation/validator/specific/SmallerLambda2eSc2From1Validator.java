package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda2 * eSc2 < 1.
 */
@Component
public class SmallerLambda2eSc2From1Validator extends FeatureValidator {
    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.eSc2, SystemFeature.Lambda2);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda2 = features.get(SystemFeature.Lambda2);
        final double eSc2 = features.get(SystemFeature.eSc2);
        if (lambda2 * eSc2 >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.eSc2.name(), List.of("error.validation.feature.Lambda2.eSc2.should.be.smaller.from.1"),
                    SystemFeature.Lambda2.name(), List.of("error.validation.feature.Lambda2.eSc2.should.be.smaller.from.1")));
        }
        return result;
    }
}
