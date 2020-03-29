package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda1 + Lambda2 > Mu * c.
 */
@Component
public class SmallerLambda1Lambda2FromMucValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda1, SystemFeature.Lambda2, SystemFeature.Mu, SystemFeature.c);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double lambdaSum = features.get(SystemFeature.Lambda1) + features.get(SystemFeature.Lambda2);
        double muc = features.get(SystemFeature.Mu) * features.get(SystemFeature.c);
        if (lambdaSum >= muc) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.SumOfLambda1Lambda2FromMuc")
                    .withInputIds(List.of(SystemFeature.Lambda1.name(), SystemFeature.Lambda2.name(), SystemFeature.Mu.name(), SystemFeature.c.name()))
                    .build());
        }
        return result;
    }
}
