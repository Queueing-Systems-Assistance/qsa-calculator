package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c < K.
 */
@Component
public class SmallercFromKValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        double K = features.get(SystemFeature.K);
        if (c >= K) {
            result = Optional.of(Map.of(
                    SystemFeature.c.name(), List.of("error.validation.feature.c.should.be.smaller.from.K"),
                    SystemFeature.K.name(), List.of("error.validation.feature.c.should.be.smaller.from.K")));
        }
        return result;
    }
}
