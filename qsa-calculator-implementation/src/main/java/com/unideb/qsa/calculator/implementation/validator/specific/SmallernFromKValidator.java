package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n < K.
 */
@Component
public class SmallernFromKValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.n, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double n = features.get(SystemFeature.n);
        double K = features.get(SystemFeature.K);
        if (n >= K) {
            result = Optional.of(Map.of(
                    SystemFeature.n.name(), List.of("error.validation.feature.n.should.be.smaller.from.K"),
                    SystemFeature.K.name(), List.of("error.validation.feature.n.should.be.smaller.from.K")));
        }
        return result;
    }
}
