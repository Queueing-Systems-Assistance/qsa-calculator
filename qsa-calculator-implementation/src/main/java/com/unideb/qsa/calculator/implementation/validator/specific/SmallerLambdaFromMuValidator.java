package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda > Mu.
 */
@Component
public class SmallerLambdaFromMuValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu);
        Optional<ValidationErrorResponse> result = Optional.empty();
        if (features.get(SystemFeature.Lambda) >= features.get(SystemFeature.Mu)) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.LambdaFromMu")
                    .withInputIds(List.of(SystemFeature.Lambda.name(), SystemFeature.Mu.name()))
                    .build());
        }
        return result;
    }
}
