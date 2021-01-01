package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.InputFeature;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Creates {@link InputFeature}.
 */
@Component
public class SystemInputAssembler {

    private static final String DEFAULT_EMPTY_VALUE = "";
    private static final String CONFIG_NAME = "NAME";
    private static final String CONFIG_DESCRIPTION = "DESCRIPTION";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Assembles a {@link InputFeature} based on its id.
     * @param inputId              feature id
     * @param inputRequiredIds     features are required or not
     * @param inputTypeFractionIds features type are fraction or not
     * @return Optional {@link InputFeature} if the feature exists, {@link Optional#empty()} otherwise
     */
    public Optional<InputFeature> assemble(String inputId, String[] inputRequiredIds, String[] inputTypeFractionIds) {
        return getSystemInputName(inputId)
                .map(name -> new InputFeature.Builder().withName(name))
                .map(builder -> builder.withId(inputId))
                .map(builder -> builder.withRequired(Arrays.asList(inputRequiredIds).contains(inputId)))
                .map(builder -> builder.withTypeFraction(Arrays.asList(inputTypeFractionIds).contains(inputId)))
                .map(builder -> builder.withDescription(getSystemInputDescription(inputId)))
                .map(InputFeature.Builder::build);
    }

    private String getSystemInputDescription(String inputId) {
        return configResolver.resolve(CONFIG_DESCRIPTION, qualifierAssembler.assemble(inputId)).orElse(DEFAULT_EMPTY_VALUE);
    }

    private Optional<String> getSystemInputName(final String inputId) {
        return configResolver.resolve(CONFIG_NAME, qualifierAssembler.assemble(inputId));
    }
}
