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
 * Validator for all common validators (see {@link com.unideb.qsa.calculator.implementation.validator.common} classes.
 */
@Component
public class CommonFeaturesValidator {

    private static final String CONFIG_VALIDATOR_CONSTRAINTS = "VALIDATOR_CONSTRAINTS";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private ValidatorCaller validatorCaller;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Validates input features against common validators.
     * @param features input features from the request
     * @param systemId system id
     * @return a map of errors, where the key is the feature id, the value is the error message
     */
    public Map<String, List<String>> validate(Map<SystemFeature, Double> features, String systemId) {
        return features.keySet().stream()
                       .map(Enum::toString)
                       .flatMap(featureId -> callValidator(features, systemId, featureId))
                       .flatMap(map -> map.entrySet().stream())
                       .collect(mergeMaps());
    }

    private Stream<Map<String, List<String>>> callValidator(Map<SystemFeature, Double> features, String systemId, String featureId) {
        return configResolver
                .resolve(CONFIG_VALIDATOR_CONSTRAINTS, qualifierAssembler.assemble(systemId, featureId))
                .flatMap(validator -> validatorCaller.getValidateResponse(features, featureId, validator))
                .stream();
    }
}
