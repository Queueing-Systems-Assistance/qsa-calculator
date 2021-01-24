package com.unideb.qsa.calculator.implementation.validator.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates the given featureId >= 1.
 */
@Component
public class PositiveOrEqualsFrom1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.valueOf(featureId));
        Optional<Map<String, List<String>>> result = Optional.empty();
        if (features.get(SystemFeature.valueOf(featureId)) < 1.0) {
            result = Optional.of(Map.of(featureId, List.of(String.format("error.validation.feature.%s.should.be.positive.or.equal.to.1", featureId))));
        }
        return result;
    }
}
