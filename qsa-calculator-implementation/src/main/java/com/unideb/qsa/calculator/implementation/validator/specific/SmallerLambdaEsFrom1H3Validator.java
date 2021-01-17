package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates Lambda * (p1 / Mu1 + p2 / Mu2 + p3 / Mu3) < 1.
 */
@Component
public class SmallerLambdaEsFrom1H3Validator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.Mu1, SystemFeature.Mu2, SystemFeature.Mu3, SystemFeature.p1, SystemFeature.p2, SystemFeature.p3);
        Optional<ValidationErrorResponse> result = Optional.empty();
        final double lambda = features.get(SystemFeature.Lambda);
        final double mu1 = features.get(SystemFeature.Mu1);
        final double mu2 = features.get(SystemFeature.Mu2);
        final double mu3 = features.get(SystemFeature.Mu3);
        final double p1 = features.get(SystemFeature.p1);
        final double p2 = features.get(SystemFeature.p2);
        final double p3 = features.get(SystemFeature.p3);
        final double eS = p1 / mu1 + p2 / mu2 + p3 / mu3;
        if (lambda * eS >= 1) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.smaller.parameter.LambdaEsFrom1.H3")
                    .withInputIds(List.of(SystemFeature.Lambda.name(), SystemFeature.Mu1.name(), SystemFeature.Mu2.name(), SystemFeature.Mu3.name(), SystemFeature.p1.name(), SystemFeature.p2.name(), SystemFeature.p3.name()))
                    .build());
        }
        return result;
    }
}