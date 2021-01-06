package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemElementAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Resolves a {@link SystemElement}.
 */
@Component
public class SystemElementResolver {

    private static final String CONFIG_SYSTEMS_ORDER = "SYSTEMS_ORDER";
    private static final String ERROR_NO_SYSTEMS = "Config [%s] returned empty value!";

    @Autowired
    private SystemElementAssembler systemElementAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves all {@link SystemElement}s.
     * @return List with the available systems
     */
    public List<SystemElement> resolve() {
        return Arrays.stream(resolveSystems())
                     .map(systemId -> systemElementAssembler.assemble(systemId))
                     .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Resolves a {@link SystemElement} based on its id.
     * @param systemId system id
     * @return SystemElement resolved system element
     */
    public SystemElement resolve(String systemId) {
        return systemElementAssembler.assemble(systemId);
    }

    private String[] resolveSystems() {
        return configResolver.resolve(CONFIG_SYSTEMS_ORDER, qualifierAssembler.assemble(), String[].class)
                             .orElseThrow(() -> new QSAServerException(String.format(ERROR_NO_SYSTEMS, CONFIG_SYSTEMS_ORDER)));
    }

}
