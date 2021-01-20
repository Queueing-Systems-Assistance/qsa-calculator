package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates t > Ds.
 */
@Component
public class BiggerEqualstFromDsValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.t, SystemFeature.Ds);
        Optional<ValidationErrorResponse> result = Optional.empty();
        final double t = features.get(SystemFeature.t);
        final double ds = features.get(SystemFeature.Ds);
        if (t < ds) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.biggerEquals.parameter.tFromDs")
                    .withInputIds(List.of(SystemFeature.t.name(), SystemFeature.Ds.name()))
                    .build());
        }
        return result;
    }
}
