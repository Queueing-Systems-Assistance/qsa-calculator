package com.unideb.qsa.calculator.implementation.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.InputFeature;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemInputAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Resolves a {@link InputFeature}.
 */
@Component
public class SystemInputResolver {

    private static final String[] DEFAULT_EMPTY_VALUE = new String[]{};
    private static final String CONFIG_INPUTS_DEFAULT = "INPUTS_DEFAULT";
    private static final String CONFIG_INPUTS_TYPE_FRACTION = "INPUTS_TYPE_FRACTION";
    private static final String ERROR_NO_FEATURE_ID = "No system input found for [%s]";

    @Autowired
    private SystemInputAssembler systemInputAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves system inputs for a system.
     * @param systemId system id
     * @return List system inputs for the given system
     */
    public List<InputFeature> resolve(String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String[] inputIds = configResolver.resolve(CONFIG_INPUTS_DEFAULT, qualifier, String[].class)
                                          .orElseThrow(() -> new QSAServerException(String.format(ERROR_NO_FEATURE_ID, systemId)));
        String[] inputTypeFractionIds = configResolver.resolve(CONFIG_INPUTS_TYPE_FRACTION, qualifier, String[].class).orElse(DEFAULT_EMPTY_VALUE);
        return systemInputAssembler.assemble(inputIds, inputTypeFractionIds, systemId);
    }
}
