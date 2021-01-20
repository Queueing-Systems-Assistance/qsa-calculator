package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates p1 + p2 + p3 == 1.
 */
@Component
public class Equalsp1p2p3To1Validator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.p1, SystemFeature.p2, SystemFeature.p3);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double pSum = features.get(SystemFeature.p1) + features.get(SystemFeature.p2) + features.get(SystemFeature.p3);
        if (pSum != 1.0) {
            result = Optional.of(Map.of(
                    SystemFeature.p1.name(), List.of("error.validation.feature.p1.p2.p3.should.be.equal.to.1"),
                    SystemFeature.p2.name(), List.of("error.validation.feature.p1.p2.p3.should.be.equal.to.1"),
                    SystemFeature.p3.name(), List.of("error.validation.feature.p1.p2.p3.should.be.equal.to.1"))
            );
        }
        return result;
    }
}
