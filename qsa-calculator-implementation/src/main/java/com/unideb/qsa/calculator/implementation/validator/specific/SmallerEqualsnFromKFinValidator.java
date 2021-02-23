package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n <= KFin.
 */
@Component
public class SmallerEqualsnFromKFinValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.KFin, SystemFeature.n);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double KFin = features.get(SystemFeature.KFin);
        double n = features.getOrDefault(SystemFeature.n, 0.0);
        if (n > KFin) {
            result = Optional.of(Map.of(
                    SystemFeature.n.name(), List.of("error.validation.feature.n.should.be.smaller.or.equal.to.K"),
                    SystemFeature.KFin.name(), List.of("error.validation.feature.n.should.be.smaller.or.equal.to.K")));
        }
        return result;
    }
}
