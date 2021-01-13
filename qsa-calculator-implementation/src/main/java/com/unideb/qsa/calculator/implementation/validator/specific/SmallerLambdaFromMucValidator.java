package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda > Mu * c.
 */
@Component
public class SmallerLambdaFromMucValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double muc = features.get(SystemFeature.Mu) * features.get(SystemFeature.c);
        if (features.get(SystemFeature.Lambda) >= muc) {
            result = Optional.of(Map.of(
                    SystemFeature.Mu.name(), List.of("error.validation.feature.Lambda.should.be.smaller.from.Mu.c"),
                    SystemFeature.c.name(), List.of("error.validation.feature.Lambda.should.be.smaller.from.Mu.c")));
        }
        return result;
    }
}
