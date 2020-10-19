package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates λ <= K - 1.
 */
@Component
public class SmallerEqualsLambdaFromKMinusOneValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.K);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double Lambda = features.get(SystemFeature.Lambda);
        double K = features.get(SystemFeature.K);
        if (Lambda > K - 1) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.bigger.parameter.lambdaFromKMinusOne")
                    .withInputIds(List.of(SystemFeature.Lambda.name(), SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
