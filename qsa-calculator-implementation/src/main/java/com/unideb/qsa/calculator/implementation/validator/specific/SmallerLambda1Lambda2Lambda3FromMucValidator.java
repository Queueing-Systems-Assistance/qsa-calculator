package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 + Lambda2 + Lambda3 < Mu * c.
 */
@Component
public class SmallerLambda1Lambda2Lambda3FromMucValidator extends FeatureValidator {

    private static final String ERROR_MESSAGE = "error.validation.feature.Lambda1.Lambda2.Lambda3.should.be.smaller.from.Mu.c";

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.Lambda3, SystemFeature.Mu, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double lambdaSum = features.get(SystemFeature.Lambda1) + features.get(SystemFeature.Lambda2) + features.get(SystemFeature.Lambda3);
        double muc = features.get(SystemFeature.Mu) * features.get(SystemFeature.c);
        if (lambdaSum >= muc) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda1.name(), List.of(ERROR_MESSAGE),
                    SystemFeature.Lambda2.name(), List.of(ERROR_MESSAGE),
                    SystemFeature.Lambda3.name(), List.of(ERROR_MESSAGE),
                    SystemFeature.Mu.name(), List.of(ERROR_MESSAGE),
                    SystemFeature.c.name(), List.of(ERROR_MESSAGE)));
        }
        return result;
    }
}
