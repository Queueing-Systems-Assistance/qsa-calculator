package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.exception.QSAInternalException;
import com.unideb.qsa.calculator.domain.system.SystemInput;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemInputAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Resolves a {@link SystemInput}.
 */
@Component
public class SystemInputResolver {

    private static final String DEFAULT_EMPTY_VALUE = "";
    private static final String CONFIG_INPUTS_DEFAULT = "INPUTS_DEFAULT";
    private static final String CONFIG_INPUTS_REQUIRED = "INPUTS_REQUIRED";
    private static final String CONFIG_INPUTS_TYPE_FRACTION = "INPUTS_TYPE_FRACTION";
    private static final String ERROR_NO_FEATURE_ID = "No system feature found with id [%s]";

    @Autowired
    private SystemInputAssembler systemInputAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves system inputs for a system.
     *
     * @param systemId system id
     * @return List system inputs for the given system
     */
    public List<SystemInput> resolve(String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] inputIds = configResolver.resolve(CONFIG_INPUTS_DEFAULT, qualifier).orElse(DEFAULT_EMPTY_VALUE).split(",");
        String[] inputRequiredIds = configResolver.resolve(CONFIG_INPUTS_REQUIRED, qualifier).orElse(DEFAULT_EMPTY_VALUE).split(",");
        String[] inputTypeFractionIds = configResolver.resolve(CONFIG_INPUTS_TYPE_FRACTION, qualifier).orElse(DEFAULT_EMPTY_VALUE).split(",");
        return Arrays.stream(inputIds)
                     .map(inputId -> systemInputAssembler.assemble(inputId, inputRequiredIds, inputTypeFractionIds).orElseThrow(getExceptionSupplier(inputId)))
                     .collect(Collectors.toList());
    }

    private Supplier<RuntimeException> getExceptionSupplier(String inputId) {
        return () -> new QSAInternalException(String.format(ERROR_NO_FEATURE_ID, inputId));
    }
}
