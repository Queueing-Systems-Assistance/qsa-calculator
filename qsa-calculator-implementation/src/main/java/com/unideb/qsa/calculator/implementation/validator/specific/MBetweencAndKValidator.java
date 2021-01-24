package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c <= m <= K.
 */
@Component
public class MBetweencAndKValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.c, SystemFeature.m, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double k = features.get(SystemFeature.K);
        if (m < c || m > k) {
            result = Optional.of(Map.of(
                    SystemFeature.c.name(), List.of("error.validation.feature.c.K.should.be.positive"),
                    SystemFeature.m.name(), List.of("error.validation.feature.c.K.should.be.positive"),
                    SystemFeature.K.name(), List.of("error.validation.feature.c.K.should.be.positive")));
        }
        return result;
    }
}
