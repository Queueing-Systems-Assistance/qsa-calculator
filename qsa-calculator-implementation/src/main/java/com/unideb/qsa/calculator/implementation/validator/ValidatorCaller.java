package com.unideb.qsa.calculator.implementation.validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;

/**
 * A utility class to call a specific validator which should be a {@link Bean} in the application context.
 */
@Component
public class ValidatorCaller {

    private static final String ERROR_VALIDATION_FAILED = "Cannot validate system with feature [%s], called validator [%s]";

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Validates input features against validators.
     * @param features  input features from the request
     * @param inputId   the input id which should be validated (can be an empty string)
     * @param validator validator name
     * @return a map of errors, where the key is the feature id, the value is the error message
     */
    public Optional<Map<String, List<String>>> getValidateResponse(Map<SystemFeature, Double> features, String inputId, String validator) {
        try {
            return applicationContext.getBean(validator, FeatureValidator.class).validate(features, inputId);
        } catch (NoSuchBeanDefinitionException e) {
            throw new QSAServerException(String.format(ERROR_VALIDATION_FAILED, inputId, validator));
        }
    }
}
