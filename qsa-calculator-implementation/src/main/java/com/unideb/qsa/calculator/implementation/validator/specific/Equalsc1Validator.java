package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c = 1.
 */
@Component
public class Equalsc1Validator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.c);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        if (c != 1.0) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.equals.parameter.c.1")
                    .withInputIds(List.of(SystemFeature.c.name()))
                    .build());
        }
        return result;
    }
}
