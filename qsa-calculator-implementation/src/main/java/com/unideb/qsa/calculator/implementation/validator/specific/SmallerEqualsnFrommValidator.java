package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n <= m.
 */
@Component
public class SmallerEqualsnFrommValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.n, SystemFeature.m);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double n = features.get(SystemFeature.n);
        double m = features.get(SystemFeature.m);
        if (n > m) {
            result = Optional.of(Map.of(
                    SystemFeature.n.name(), List.of("error.validation.feature.n.should.be.smaller.or.equal.to.m"),
                    SystemFeature.m.name(), List.of("error.validation.feature.n.should.be.smaller.or.equal.to.m")));
        }
        return result;
    }
}
