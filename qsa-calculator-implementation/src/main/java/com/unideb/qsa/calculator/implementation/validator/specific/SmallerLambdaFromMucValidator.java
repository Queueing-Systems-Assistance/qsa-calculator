package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;
/**
 * Validates Lambda > Mu * c.
 */
@Component
public class SmallerLambdaFromMucValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu, SystemFeature.c);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double muc = features.get(SystemFeature.Mu) * features.get(SystemFeature.c);
        if (features.get(SystemFeature.Lambda) >= muc) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.LambdaFromMuc")
                    .withInputIds(List.of(SystemFeature.Mu.name(), SystemFeature.c.name()))
                    .build());
        }
        return result;
    }
}
