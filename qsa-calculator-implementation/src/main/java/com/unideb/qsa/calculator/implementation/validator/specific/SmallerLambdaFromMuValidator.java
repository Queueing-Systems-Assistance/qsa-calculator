package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda > Mu.
 */
@Component
public class SmallerLambdaFromMuValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu);
        Optional<Map<String, List<String>>> result = Optional.empty();
        if (features.get(SystemFeature.Lambda) >= features.get(SystemFeature.Mu)) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of("error.validation.feature.Lambda.should.be.smaller.from.Mu"),
                    SystemFeature.Mu.name(), List.of("error.validation.feature.Lambda.should.be.smaller.from.Mu")));
        }
        return result;
    }
}
