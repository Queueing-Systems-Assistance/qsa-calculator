package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * eS < 1.
 */
@Component
public class LambdaTimesEsSmallerFrom1 extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.eS, SystemFeature.Lambda);
        Optional<ValidationErrorResponse> result = Optional.empty();
        if (features.get(SystemFeature.eS) >= features.get(SystemFeature.Lambda)) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.eSFromLambda")
                    .withInputIds(List.of(SystemFeature.eS.name(), SystemFeature.Lambda.name()))
                    .build());
        }
        return result;
    }
}
