package com.unideb.qsa.calculator.implementation.validator.feature;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;

/**
 * Facade to validate input features against required and output validators.
 */
@Component
public class OutputFeatureValidatorFacade {

    @Autowired
    private CalculationInputValidator calculationInputValidator;
    @Autowired
    private RequiredFeaturesValidator requiredFeaturesValidator;

    /**
     * Validates input features against required (common&specific) and output validators.
     * @param features input features from the request
     * @param systemId system id
     * @return a map of errors, where the key is the feature id, the value is the error message
     */
    public Map<String, List<String>> validate(Map<SystemFeature, Double> features, String systemId, String outputId) {
        requiredFeaturesValidator.validate(features, systemId);
        return calculationInputValidator.validate(features, systemId, outputId);
    }
}
