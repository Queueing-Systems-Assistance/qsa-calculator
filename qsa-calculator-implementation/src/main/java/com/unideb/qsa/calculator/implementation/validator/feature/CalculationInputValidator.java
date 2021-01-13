package com.unideb.qsa.calculator.implementation.validator.feature;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.validator.ValidatorCaller;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Validator before calculating a system output feature.
 */
@Component
public class CalculationInputValidator {

    private static final String CONFIG_CALCULATION_INPUT_VALIDATOR = "CALCULATION_INPUT_VALIDATOR";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private ValidatorCaller validatorCaller;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Validates input values for a specific calculation.
     * @param features features and values from the request
     * @param systemId system id
     * @param outputId id of the output that we want to calculate
     * @return a map of errors, where the key is the feature id, the value is the error message
     */
    public Map<String, List<String>> validate(Map<SystemFeature, Double> features, String systemId, String outputId) {
        return features.keySet().stream().map(Enum::toString).flatMap(inputId -> callValidator(features, systemId, outputId, inputId))
                       .flatMap(map -> map.entrySet().stream())
                       .collect(mergeMaps());

    }

    private Stream<Map<String, List<String>>> callValidator(Map<SystemFeature, Double> features, String systemId, String outputId, String inputId) {
        return configResolver.resolve(CONFIG_CALCULATION_INPUT_VALIDATOR, qualifierAssembler.assemble(systemId, inputId, outputId))
                             .flatMap(validator -> validatorCaller.getValidateResponse(features, inputId, validator))
                             .stream();
    }
}
