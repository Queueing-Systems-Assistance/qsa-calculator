package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c = 1.
 */
@Component
public class Equalsc1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        if (c != 1.0) {
            result = Optional.of(Map.of(SystemFeature.c.name(), List.of("error.equals.parameter.c.1")));
        }
        return result;
    }
}
