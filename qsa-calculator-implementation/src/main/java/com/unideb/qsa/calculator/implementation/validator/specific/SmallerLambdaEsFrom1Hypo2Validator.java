package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * (1 / Mu1 + 1 / Mu2) < 1.
 */
@Component
public class SmallerLambdaEsFrom1Hypo2Validator extends FeatureValidator {

    private static final String VALIDATION_ERROR_MESSAGE = "error.validation.feature.Lambda.Es.should.be.smaller.from.1";

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu1, SystemFeature.Mu2);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double lambda = features.get(SystemFeature.Lambda);
        final double mu1 = features.get(SystemFeature.Mu1);
        final double mu2 = features.get(SystemFeature.Mu2);
        final double eS = 1 / mu1 + 1 / mu2;
        if (lambda * eS >= 1) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of(VALIDATION_ERROR_MESSAGE),
                    SystemFeature.Mu1.name(), List.of(VALIDATION_ERROR_MESSAGE),
                    SystemFeature.Mu2.name(), List.of(VALIDATION_ERROR_MESSAGE)
            ));
        }
        return result;
    }
}
