package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda > Mu * 2.
 */
@Component
public class SmallerLambdaFromMu2Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu);
        Optional<Map<String, List<String>>> result = Optional.empty();
        if (features.get(SystemFeature.Lambda) >= (features.get(SystemFeature.Mu) * 2)) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of("error.smaller.parameter.LambdaFromMu2"),
                    SystemFeature.Mu.name(), List.of("error.smaller.parameter.LambdaFromMu2")));
        }
        return result;
    }
}
