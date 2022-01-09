package com.unideb.qsa.calculator.implementation.validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.error.ErrorResponse;
import com.unideb.qsa.calculator.domain.exception.QSAInvalidOutputException;
import com.unideb.qsa.calculator.implementation.resolver.i18n.MessageResolver;

/**
 * Validator for features.
 */
public abstract class FeatureValidator {

    private static final Logger LOG = LoggerFactory.getLogger(FeatureValidator.class);
    private static final String VALIDATION_ERROR_INVALID_FEATURE_ID = "error.bad.request.no.feature.available.with.id";
    private static final String ERROR_MISSING_FEATURE_ID = "Missing feature from input [id={}] [input={}]";

    @Autowired
    private MessageResolver messageResolver;

    /**
     * Validate system features.
     * @param features  features and values from the request
     * @param featureId feature id
     * @return Validation error if the feature(s) are not compliant, otherwise {@link Optional#empty()}
     */
    public abstract Optional<Map<String, List<String>>> validate(Map<SystemFeature, Double> features, String featureId);

    /**
     * Validate system features that is present. If the system feature is not in the map, then an {@link ErrorResponse} will create.
     * @param features       features and values from the request
     * @param systemFeatures feature id
     */
    protected void validatePresentFeatures(Map<SystemFeature, Double> features, SystemFeature... systemFeatures) {
        Stream.of(systemFeatures)
              .filter(systemFeature -> !features.containsKey(systemFeature))
              .findAny()
              .ifPresent(systemFeature -> {
                  LOG.warn(ERROR_MISSING_FEATURE_ID, systemFeature, features);
                  throw new QSAInvalidOutputException(messageResolver.resolve(VALIDATION_ERROR_INVALID_FEATURE_ID));
              });
    }


}
