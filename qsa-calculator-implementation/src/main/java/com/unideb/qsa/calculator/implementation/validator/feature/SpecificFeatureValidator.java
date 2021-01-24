package com.unideb.qsa.calculator.implementation.validator.feature;

import static com.unideb.qsa.calculator.implementation.calculator.helper.CalculatorHelper.mergeMaps;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.SystemFeature;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.validator.ValidatorCaller;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Validator for specific validators. (see {@link com.unideb.qsa.calculator.implementation.validator.specific} classes.
 */
@Component
public class SpecificFeatureValidator {

    private static final String[] NO_VALIDATOR = {};
    private static final String CONFIG_SPECIFIC_VALIDATOR_CONSTRAINTS = "SPECIFIC_VALIDATOR_CONSTRAINTS";

    @Autowired
    private QualifierAssembler qualifierAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private ValidatorCaller validatorCaller;

    /**
     * Validates input features against specific validators.
     * @param features input features from the request
     * @param systemId system id
     * @return a map of errors, where the key is the feature id, the value is the error message
     */
    public Map<String, List<String>> validate(Map<SystemFeature, Double> features, String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        return List.of(configResolver.resolve(CONFIG_SPECIFIC_VALIDATOR_CONSTRAINTS, qualifier, String[].class).orElse(NO_VALIDATOR))
                   .stream()
                   .map(resolvedValidator -> validatorCaller.getValidateResponse(features, "", resolvedValidator))
                   .filter(Optional::isPresent)
                   .map(Optional::get)
                   .flatMap(map -> map.entrySet().stream())
                   .collect(mergeMaps());
    }
}
