package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates c <= m <= K
 */
@Component
public class MBetweencAndKValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(final Map<SystemFeature, Double> features, final String featureId) {
        validatePresentFeatures(features, SystemFeature.c, SystemFeature.m, SystemFeature.K);
        Optional<ValidationErrorResponse> result = Optional.empty();
        final double c = features.get(SystemFeature.c);
        final double m = features.get(SystemFeature.m);
        final double K = features.get(SystemFeature.K);
        if (m < c || m > K) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.parameter.m.not.between.cAndK")
                    .withInputIds(List.of(SystemFeature.c.name(), SystemFeature.m.name(), SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
