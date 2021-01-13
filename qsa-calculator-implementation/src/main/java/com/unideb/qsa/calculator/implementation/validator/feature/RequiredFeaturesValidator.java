package com.unideb.qsa.calculator.implementation.validator.feature;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.exception.QSAValidationException;
import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;

/**
 * Validates input features against common and specific constraints (validators).
 */
@Component
public class RequiredFeaturesValidator {

    private static final String ERROR_MESSAGE_VALIDATION = "error.validation";

    @Autowired
    private SpecificFeatureValidator specificFeatureValidator;
    @Autowired
    private CommonFeaturesValidator commonFeaturesValidator;
    @Autowired
    private MessageResolver messageResolver;

    /**
     * Validates input features against common and specific validators.
     * @param features input features from the request
     * @param systemId system id
     */
    public void validate(Map<SystemFeature, Double> features, String systemId) {
        Map<String, List<String>> defaultValidationErrors = commonFeaturesValidator.validate(features, systemId);
        Map<String, List<String>> specificValidationErrors = specificFeatureValidator.validate(features, systemId);
        Map<String, List<String>> validationErrors = mergeValidationErrors(defaultValidationErrors, specificValidationErrors);
        if (!validationErrors.isEmpty()) {
            validationErrors.keySet().forEach(key -> validationErrors.put(key, messageResolver.resolve(validationErrors.get(key))));
            throw new QSAValidationException(messageResolver.resolve(ERROR_MESSAGE_VALIDATION), validationErrors);
        }
    }

    private Map<String, List<String>> mergeValidationErrors(Map<String, List<String>> defaultErrors, Map<String, List<String>> specificErrors) {
        return Stream.of(defaultErrors, specificErrors)
                     .flatMap(map -> map.entrySet().stream())
                     .collect(mergeMaps());
    }
}
