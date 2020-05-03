package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates K - 1 > 1.
 */
@Component
public class BiggerKMin1From1 extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.K);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double K = features.get(SystemFeature.K);
        if (K - 1 <= 1.0) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.bigger.parameter.KMin1")
                    .withInputIds(List.of(SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
