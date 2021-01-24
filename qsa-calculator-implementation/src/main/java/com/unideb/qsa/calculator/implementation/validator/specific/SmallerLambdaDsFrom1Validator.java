package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * Ds < 1.
 */
@Component
public class SmallerLambdaDsFrom1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Ds, SystemFeature.Lambda);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda = features.get(SystemFeature.Lambda);
        final double ds = features.get(SystemFeature.Ds);
        if (lambda * ds >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of("error.validation.feature.Lambda.Ds.should.be.smaller.from.1"),
                    SystemFeature.Ds.name(), List.of("error.validation.feature.Lambda.Ds.should.be.smaller.from.1")
            ));
        }
        return result;
    }
}
