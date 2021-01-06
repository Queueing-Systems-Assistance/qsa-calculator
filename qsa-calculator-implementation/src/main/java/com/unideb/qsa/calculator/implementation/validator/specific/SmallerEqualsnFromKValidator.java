package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n <= K.
 */
@Component
public class SmallerEqualsnFromKValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double K = features.get(SystemFeature.K);
        double n = features.getOrDefault(SystemFeature.n, 0.0);
        if (n > K) {
            result = Optional.of(Map.of(
                    SystemFeature.n.name(), List.of("error.smallerEquals.parameter.nFromK"),
                    SystemFeature.K.name(), List.of("error.smallerEquals.parameter.nFromK")));
        }
        return result;
    }
}
