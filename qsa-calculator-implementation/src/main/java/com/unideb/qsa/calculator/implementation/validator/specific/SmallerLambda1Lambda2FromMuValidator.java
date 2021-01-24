package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 + Lambda2 > Mu.
 */
@Component
public class SmallerLambda1Lambda2FromMuValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.Mu);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double lambdaSum = features.get(SystemFeature.Lambda1) + features.get(SystemFeature.Lambda2);
        if (lambdaSum >= features.get(SystemFeature.Mu)) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda1.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu"),
                    SystemFeature.Lambda2.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu"),
                    SystemFeature.Mu.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu")));
        }
        return result;
    }
}
