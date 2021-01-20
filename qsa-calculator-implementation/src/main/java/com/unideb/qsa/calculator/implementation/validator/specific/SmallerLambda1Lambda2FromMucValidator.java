package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 + Lambda2 > Mu * c.
 */
@Component
public class SmallerLambda1Lambda2FromMucValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.Mu, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double lambdaSum = features.get(SystemFeature.Lambda1) + features.get(SystemFeature.Lambda2);
        double muc = features.get(SystemFeature.Mu) * features.get(SystemFeature.c);
        if (lambdaSum >= muc) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda1.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu.c"),
                    SystemFeature.Lambda2.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu.c"),
                    SystemFeature.Mu.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu.c"),
                    SystemFeature.c.name(), List.of("error.validation.feature.Lambda1.Lambda2.should.be.smaller.from.Mu.c")));
        }
        return result;
    }
}
