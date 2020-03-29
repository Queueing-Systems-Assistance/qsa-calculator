package com.unideb.qsa.calculator.implementation.validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;

/**
 * Validates the given featureId is greater then to 0.
 */
@Component
public class PositiveValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.valueOf(featureId));
        Optional<ValidationErrorResponse> result = Optional.empty();
        if (features.get(SystemFeature.valueOf(featureId)) <= 0.0) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage(String.format("error.positive.parameter.%s", featureId))
                    .withInputIds(List.of(featureId))
                    .build());
        }
        return result;
    }
}
