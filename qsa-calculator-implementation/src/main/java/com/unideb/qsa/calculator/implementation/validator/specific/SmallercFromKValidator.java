package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c < K.
 */
@Component
public class SmallercFromKValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K, SystemFeature.c);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double c = features.get(SystemFeature.c);
        double K = features.get(SystemFeature.K);
        if (c >= K) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.cFromK")
                    .withInputIds(List.of(SystemFeature.c.name(), SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
