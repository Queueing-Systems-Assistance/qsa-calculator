package com.unideb.qsa.calculator.implementation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.XAxis;
import com.unideb.qsa.calculator.domain.error.ValidationErrorResponse;
import com.unideb.qsa.calculator.domain.exception.QSAMessageException;
import com.unideb.qsa.calculator.domain.exception.QSAValidationException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Validates features if they are compliance before calculation.
 */
@Component
public class DefaultFeatureValidator {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultFeatureValidator.class);

    @Autowired
    private QualifierAssembler qualifierAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private XAxisValidator xAxisValidator;

    /**
     * Validates features based on system id.
     *
     * @param features features and values from the request
     * @param systemId system id
     */
    public void validate(Map<SystemFeature, Double> features, String systemId) {
        List<ValidationErrorResponse> errorResponses = defaultValidation(features, systemId);
        errorResponses.addAll(specificValidation(features, systemId));
        throwErrorIfResponsesNotEmpty(errorResponses);
    }

    /**
     * Validates input values for a specific calculation.
     *
     * @param features features and values from the request
     * @param systemId system id
     * @param outputId id of the output that we want to calculate
     * @return
     */
    public String validateCalculationInput(Map<SystemFeature, Double> features, String systemId, String outputId) {
        StringBuilder errorMessage = new StringBuilder();
        calculationInputValidation(features, systemId, outputId)
                .stream()
                .forEach(message -> errorMessage.append(message.getErrorMessage()+" "));
        return errorMessage.toString();
    }

    /**
     * Validates X axis.
     *
     * @param xAxis xAxis properties
     */
    public void validateXAxis(Map<XAxis, Double> xAxis) {
        List<ValidationErrorResponse> errorResponses = xAxisValidator.validate(xAxis);
        throwErrorIfResponsesNotEmpty(errorResponses);
    }

    private void throwErrorIfResponsesNotEmpty(final List<ValidationErrorResponse> errorResponses) {
        if (!errorResponses.isEmpty()) {
            throw new QSAValidationException(errorResponses);
        }
    }

    private List<ValidationErrorResponse> specificValidation(Map<SystemFeature, Double> features, String systemId) {
        List<ValidationErrorResponse> errorResponses = new ArrayList<>();
        configResolver.resolve("SPECIFIC_VALIDATOR_CONSTRAINTS", qualifierAssembler.assemble(systemId))
            .ifPresent(specificValidators -> List.of(specificValidators.split(","))
                                           .stream()
                                           .map(validator -> getValidateResponse(features, "", validator))
                                           .filter(Optional::isPresent)
                                           .map(Optional::get)
                                           .forEach(errorResponses::add));
        return errorResponses;
    }

    private List<ValidationErrorResponse> defaultValidation(Map<SystemFeature, Double> features, String systemId) {
        List<ValidationErrorResponse> errorResponses = new ArrayList<>();
        features.keySet().stream().map(Enum::toString).forEach(systemFeature ->
                configResolver.resolve("VALIDATOR_CONSTRAINTS", new Qualifier.Builder().put("name", systemId).put("feature", systemFeature).build())
                              .flatMap(validator -> getValidateResponse(features, systemFeature, validator))
                              .ifPresent(errorResponses::add));
        return errorResponses;
    }

    private List<ValidationErrorResponse> calculationInputValidation(Map<SystemFeature, Double> features, String systemId, String outputId) {
        List<ValidationErrorResponse> errorResponses = new ArrayList<>();
        features.keySet().stream().map(Enum::toString).forEach(systemFeature ->
                configResolver.resolve("CALCULATION_INPUT_VALIDATOR", new Qualifier.Builder().put("name", systemId).put("feature", systemFeature).put("output", outputId).build())
                        .flatMap(validator -> getValidateResponse(features, systemFeature, validator))
                        .ifPresent(errorResponses::add));

        return errorResponses;
    }

    private Optional<ValidationErrorResponse> getValidateResponse(Map<SystemFeature, Double> features, String systemFeature, String validator) {
        try {
            return applicationContext.getBean(validator, FeatureValidator.class).validate(features, systemFeature);
        } catch (NoSuchBeanDefinitionException e) {
            LOG.warn("Cannot validate system with feature [{}], called validator [{}]", systemFeature, validator);
            throw new QSAMessageException("error.global.validation");
        }
    }
}
