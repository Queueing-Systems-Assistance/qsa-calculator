package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates λ <= K - 1.
 */
@Component
public class SmallerEqualsLambdaFromKMinusOneValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.Lambda, SystemFeature.K);
        Optional<Map<String, List<String>>> result = Optional.empty();
        double Lambda = features.get(SystemFeature.Lambda);
        double K = features.get(SystemFeature.K);
        if (Lambda > K - 1) {
            result = Optional.of(Map.of(
                    SystemFeature.Lambda.name(), List.of("error.bigger.parameter.lambdaFromKMinusOne"),
                    SystemFeature.K.name(), List.of("error.bigger.parameter.lambdaFromKMinusOne")));
        }
        return result;
    }
}
