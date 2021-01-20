package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates K - 1 > 1.
 */
@Component
public class BiggerKMin1From1 extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double K = features.get(SystemFeature.K);
        if (K - 1 <= 1.0) {
            result = Optional.of(Map.of(SystemFeature.K.name(), List.of("error.validation.feature.K.1.should.be.bigger.from.1")));
        }
        return result;
    }
}
