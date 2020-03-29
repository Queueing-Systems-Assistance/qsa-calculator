package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Creates {@link SystemInput}.
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
     * Assembles a {@link SystemInput} based on its id.
     *
     * @param inputId              feature id
     * @param inputRequiredIds     features are required or not
     * @param inputTypeFractionIds features type are fraction or not
     * @return Optional {@link SystemInput} if the feature exists, {@link Optional#empty()} otherwise
     */
    public Optional<SystemInput> assemble(String inputId, String[] inputRequiredIds, String[] inputTypeFractionIds) {
        Optional<String> optionalName = configResolver.resolve(CONFIG_NAME, qualifierAssembler.assemble(inputId));
        Optional<SystemInput> result = Optional.empty();
        if (optionalName.isPresent()) {
            result = Optional.of(new SystemInput.Builder()
                    .withId(inputId)
                    .withRequired(Arrays.asList(inputRequiredIds).contains(inputId))
                    .withTypeFraction(Arrays.asList(inputTypeFractionIds).contains(inputId))
                    .withName(optionalName.get())
                    .withDescription(configResolver.resolve(CONFIG_DESCRIPTION, qualifierAssembler.assemble(inputId)).orElse(DEFAULT_EMPTY_VALUE))
                    .build());
        }
        return result;
    }
}
