package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c < KFin.
 */
@Component
public class SmallercFromKFinValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.KFin, SystemFeature.c);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        double KFin = features.get(SystemFeature.KFin);
        if (c >= KFin) {
            result = Optional.of(Map.of(
                    SystemFeature.c.name(), List.of("error.validation.feature.c.should.be.smaller.from.K"),
                    SystemFeature.KFin.name(), List.of("error.validation.feature.c.should.be.smaller.from.K")));
        }
        return result;
    }
}
