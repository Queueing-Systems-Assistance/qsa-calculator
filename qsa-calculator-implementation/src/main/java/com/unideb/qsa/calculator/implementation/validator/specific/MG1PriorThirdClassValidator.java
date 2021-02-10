package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates that in M/G/1 priority systems the inputs for the 3rd class are either all defined or all left 0.
 */
@Component
public class MG1PriorThirdClassValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda3, SystemFeature.eSc3, SystemFeature.eSPow2c3, SystemFeature.eSPow3c3);
        Optional<Map<String, List<String>>> result = Optional.empty();
        if (!areAllThirdClassInputsDefined(features) && !areAllThirdClassInputsZero(features)) {
            result = Optional.of(Map.of(
                    SystemFeature.c.name(), List.of("error.validation.feature.Lambda3.eSc3.eSPow2c3.eSPow3c3.should.be.all.zero.or.positive"),
                    SystemFeature.K.name(), List.of("error.validation.feature.Lambda3.eSc3.eSPow2c3.eSPow3c3.should.be.all.zero.or.positive")));
        }
        return result;
    }

    private boolean areAllThirdClassInputsDefined(Map<SystemFeature, Double> features) {
        final double lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        final double eSPow3c3 = features.get(SystemFeature.eSPow3c3);
        return lambda3 > 0 && eSc3 > 0 && eSPow2c3 > 0 && eSPow3c3 > 0;
    }

    private boolean areAllThirdClassInputsZero(Map<SystemFeature, Double> features) {
        final double lambda3 = features.get(SystemFeature.Lambda3);
        final double eSc3 = features.get(SystemFeature.eSc3);
        final double eSPow2c3 = features.get(SystemFeature.eSPow2c3);
        final double eSPow3c3 = features.get(SystemFeature.eSPow3c3);
        return lambda3 == 0 && eSc3 == 0 && eSPow2c3 == 0 && eSPow3c3 == 0;
    }
}
