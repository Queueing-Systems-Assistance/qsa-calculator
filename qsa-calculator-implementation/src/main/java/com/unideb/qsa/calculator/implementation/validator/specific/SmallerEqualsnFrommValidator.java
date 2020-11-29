package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

@Component
public class SmallerEqualsnFrommValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.n, SystemFeature.m);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double n = features.get(SystemFeature.n);
        double m = features.get(SystemFeature.m);
        if (n > m) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smallerEquals.parameter.nFromm")
                    .withInputIds(List.of(SystemFeature.n.name(), SystemFeature.m.name()))
                    .build());
        }
        return result;
    }
}
