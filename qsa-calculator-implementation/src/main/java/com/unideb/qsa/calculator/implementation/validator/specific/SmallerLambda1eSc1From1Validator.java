package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 * eSc1 < 1.
 */
@Component
public class SmallerLambda1eSc1From1Validator extends FeatureValidator {
    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.eSc1, SystemFeature.Lambda1);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda1 = features.get(SystemFeature.Lambda1);
        final double eSc1 = features.get(SystemFeature.eSc1);
        if (lambda1 * eSc1 >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.eSc1.name(), List.of("error.validation.feature.Lambda1.eSc1.should.be.smaller.from.1"),
                    SystemFeature.Lambda1.name(), List.of("error.validation.feature.Lambda1.eSc1.should.be.smaller.from.1")));
        }
        return result;
    }
}
