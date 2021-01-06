package com.unideb.qsa.calculator.implementation.validator;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.domain.calculator.StreamOutput;
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
    private StreamOutputValidator streamOutputValidator;

    /**
     * Validates features based on system id.
     * @param features features and values from the request
     * @param systemId system id
     */
    public void validate(Map<SystemFeature, Double> features, String systemId) {
        Map<String, List<String>> defaultValidationErrors = defaultValidation(features, systemId);
        Map<String, List<String>> specificValidationErrors = specificValidation(features, systemId);
        Map<String, List<String>> validationErrors = Stream.of(defaultValidationErrors, specificValidationErrors)
                                                           .flatMap(map -> map.entrySet().stream())
                                                           .collect(mergeMaps());
        throwErrorIfResponsesNotEmpty(validationErrors);
    }

    /**
     * Validates input values for a specific calculation.
     * @param features features and values from the request
     * @param systemId system id
     * @param outputId id of the output that we want to calculate
     * @return list of input value errors.
     */
    public Map<String, List<String>> validateCalculationInput(Map<SystemFeature, Double> features, String systemId, String outputId) {
        return features.keySet().stream().map(Enum::toString).flatMap(systemFeature ->
                configResolver.resolve("CALCULATION_INPUT_VALIDATOR",
                        new Qualifier.Builder().put("name", systemId).put("feature", systemFeature).put("output", outputId).build())
                              .flatMap(validator -> getValidateResponse(features, systemFeature, validator)).stream())
                       .flatMap(map -> map.entrySet().stream())
                       .collect(mergeMaps());

    }

    /**
     * Validates stream output.
     * @param streamOutput stream input values (from, to, steps)
     */
    public void validateStreamOutput(Map<StreamOutput, String> streamOutput) {
        Map<String, List<String>> validationErrors = streamOutputValidator.validate(streamOutput);
        throwErrorIfResponsesNotEmpty(validationErrors);
    }

    private void throwErrorIfResponsesNotEmpty(Map<String, List<String>> validationErrors) {
        if (!validationErrors.isEmpty()) {
            throw new QSAValidationException(validationErrors);
        }
    }

    private Map<String, List<String>> specificValidation(Map<SystemFeature, Double> features, String systemId) {
        return List.of(configResolver.resolve("SPECIFIC_VALIDATOR_CONSTRAINTS", qualifierAssembler.assemble(systemId), String[].class).orElse(new String[]{}))
                   .stream()
                   .map(validator -> getValidateResponse(features, "", validator))
                   .filter(Optional::isPresent)
                   .map(Optional::get)
                   .flatMap(map -> map.entrySet().stream())
                   .collect(mergeMaps());
    }

    private Map<String, List<String>> defaultValidation(Map<SystemFeature, Double> features, String systemId) {
        return features.keySet().stream()
                       .map(Enum::toString)
                       .flatMap(systemFeature ->
                               configResolver
                                       .resolve("VALIDATOR_CONSTRAINTS", new Qualifier.Builder().put("name", systemId).put("feature", systemFeature).build())
                                       .flatMap(validator -> getValidateResponse(features, systemFeature, validator))
                                       .stream())
                       .flatMap(map -> map.entrySet().stream())
                       .collect(mergeMaps());
    }

    private Optional<Map<String, List<String>>> getValidateResponse(Map<SystemFeature, Double> features, String systemFeature, String validator) {
        try {
            return applicationContext.getBean(validator, FeatureValidator.class).validate(features, systemFeature);
        } catch (NoSuchBeanDefinitionException e) {
            LOG.warn("Cannot validate system with feature [{}], called validator [{}]", systemFeature, validator);
            throw new QSAMessageException("error.global.validation");
        }
    }
}
