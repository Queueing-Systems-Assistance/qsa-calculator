package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates µ < K.
 */
@Component
public class SmallerMuFromKValidator extends FeatureValidator {

    @Override
    public Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Mu, SystemFeature.K);
        Optional<ValidationErrorResponse> result = Optional.empty();
        double Mu = features.get(SystemFeature.Mu);
        double K = features.get(SystemFeature.K);
        if (Mu > K) {
            result = Optional.of(new ValidationErrorResponse.Builder()
                    .withErrorMessage("error.bigger.parameter.muFromK")
                    .withInputIds(List.of(SystemFeature.Mu.name(), SystemFeature.K.name()))
                    .build());
        }
        return result;
    }
}
