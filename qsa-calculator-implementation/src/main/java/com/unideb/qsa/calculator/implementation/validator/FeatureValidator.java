package com.unideb.qsa.calculator.implementation.validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.domain.exception.QSAMessageException;

/**
 * Validator for features.
 */
public abstract class FeatureValidator {

    private static final Logger LOG = LoggerFactory.getLogger(FeatureValidator.class);
    private static final String MISSING_FEATURE_ID = "Missing feature [id={}]";

    /**
     * Validate system features.
     * @param features  features and values from the request
     * @param featureId feature id
     * @return Validation error if the feature(s) are not compliant, otherwise {@link Optional#empty()}
     */
    public abstract Optional<ValidationErrorResponse> validate(Map<SystemFeature, Double> features, String featureId);

    /**
     * Validate system features that is present. If the system feature is not in the map, then an {@link ValidationErrorResponse} will create.
     * @param features       features and values from the request
     * @param systemFeatures feature id
     */
    protected void validatePresentFeatures(Map<SystemFeature, Double> features, SystemFeature... systemFeatures) {
        List.of(systemFeatures).stream()
            .filter(systemFeature -> !features.containsKey(systemFeature))
            .findAny()
            .ifPresent(systemFeature -> {
                LOG.warn(MISSING_FEATURE_ID, systemFeature);
                throw new QSAMessageException("error.global.missingFeatureId");
            });
    }


}
