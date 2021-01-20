package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * Alpha / Mu < 1.
 */
@Component
public class SmallerLambdaAlphaMuFrom1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Alpha, SystemFeature.Mu);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda = features.get(SystemFeature.Lambda);
        final double alpha = features.get(SystemFeature.Alpha);
        final double mu = features.get(SystemFeature.Mu);
        if (lambda * alpha / mu >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of("error.validation.feature.Lambda.Alpha.Mu.should.be.smaller.from.1"),
                    SystemFeature.Alpha.name(), List.of("error.validation.feature.Lambda.Alpha.Mu.should.be.smaller.from.1"),
                    SystemFeature.Mu.name(), List.of("error.validation.feature.Lambda.Alpha.Mu.should.be.smaller.from.1")
                    ));
        }
        return result;
    }
}
