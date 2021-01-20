package com.unideb.qsa.calculator.implementation.validator.specific;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.validator.FeatureValidator;

/**
 * Validates t > Ds.
 */
@Component
public class BiggerEqualstFromDsValidator extends FeatureValidator {

    @Override
    public Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId) {
        validatePresentFeatures(features, SystemFeature.t, SystemFeature.Ds);
        Optional<Map<String, List<String>>> result = Optional.empty();
        final double t = features.get(SystemFeature.t);
        final double ds = features.get(SystemFeature.Ds);
        if (t < ds) {
            result = Optional.of(Map.of(
                    SystemFeature.t.name(), List.of("error.validation.feature.t.should.be.bigger.or.equal.to.Ds"),
                    SystemFeature.Ds.name(), List.of("error.validation.feature.t.should.be.bigger.or.equal.to.Ds")
            ));
        }
        return result;
    }
}
