package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n <= c.
 */
@Component
public class SmallerEqualsnFromcValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        double n = features.getOrDefault(SystemFeature.n, 0.0);
        if (n > c) {
            result = Optional.of(Map.of(
                    SystemFeature.n.name(), List.of("error.smallerEquals.parameter.nFromc"),
                    SystemFeature.c.name(), List.of("error.smallerEquals.parameter.nFromc")));
        }
        return result;
    }
}
