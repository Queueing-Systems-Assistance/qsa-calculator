package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * Alpha / Mu < 1.
 */
@Component
public class SmallerLambdaAlphaMuFrom1Validator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Alpha, SystemFeature.Mu);
        Optional<ValidationErrorResponse> result = Optional.empty();
        final double lambda = features.get(SystemFeature.Lambda);
        final double alpha = features.get(SystemFeature.Alpha);
        final double mu = features.get(SystemFeature.Mu);
        if (lambda * alpha / mu >= 1) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.LambdaAlphaMuFrom1")
                    .withInputIds(List.of(SystemFeature.Lambda.name(), SystemFeature.Alpha.name(), SystemFeature.Mu.name()))
                    .build());
        }
        return result;
    }
}
