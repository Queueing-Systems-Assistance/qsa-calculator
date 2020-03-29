package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 + Lambda2 > Mu.
 */
@Component
public class SmallerLambda1Lambda2FromMuValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.Mu);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double lambdaSum = features.get(SystemFeature.Lambda1) + features.get(SystemFeature.Lambda2);
        if (lambdaSum >= features.get(SystemFeature.Mu)) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.SumOfLambda1Lambda2FromMu")
                    .withInputIds(List.of(SystemFeature.Lambda1.name(), SystemFeature.Lambda2.name(), SystemFeature.Mu.name()))
                    .build());
        }
        return result;
    }
}
