package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates n <= K.
 */
@Component
public class SmallerEqualsnFromKValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double n = features.getOrDefault(SystemFeature.n, 0.0);
        double K = features.get(SystemFeature.K);
        if (n > K) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.nFromK")
                    .withInputIds(List.of(SystemFeature.n.name(), SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
