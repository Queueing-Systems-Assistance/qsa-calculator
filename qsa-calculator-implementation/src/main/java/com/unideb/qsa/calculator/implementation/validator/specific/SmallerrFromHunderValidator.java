package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates r < 100.
 */
@Component
public class SmallerrFromHunderValidator extends FeatureValidator {

    private static final int HUNDREDTH_QUARTILE = 100;

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.r);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double r = features.get(SystemFeature.r);
        if (r >= HUNDREDTH_QUARTILE) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.rFrom100")
                    .withInputIds(List.of(SystemFeature.r.name()))
                    .build());
        }
        return result;
    }
}
