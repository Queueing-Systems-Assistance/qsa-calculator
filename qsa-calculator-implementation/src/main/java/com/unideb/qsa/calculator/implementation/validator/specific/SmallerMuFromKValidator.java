package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates µ < K.
 */
@Component
public class SmallerMuFromKValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Mu, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double Mu = features.get(SystemFeature.Mu);
        double K = features.get(SystemFeature.K);
        if (Mu > K) {
            result = Optional.of(Map.of(
                    SystemFeature.Mu.name(), List.of("error.bigger.parameter.muFromK"),
                    SystemFeature.K.name(), List.of("error.bigger.parameter.muFromK")));
        }
        return result;
    }
}
