package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates r < 100.
 */
@Component
public class SmallerrFromHunderValidator extends FeatureValidator {

    private static final int HUNDREDTH_QUARTILE = 100;

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.r);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double r = features.get(SystemFeature.r);
        if (r >= HUNDREDTH_QUARTILE) {
            result = Optional.of(Map.of(
                    SystemFeature.r.name(), List.of("error.smaller.parameter.rFrom100")));
        }
        return result;
    }
}
