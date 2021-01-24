package com.unideb.qsa.calculator.implementation.resolver;

import java.util.List;

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
        return systemElementAssembler.assemble(List.of(resolveSystems()));
    }

    private String[] resolveSystems() {
        return configResolver.resolve(CONFIG_SYSTEMS_ORDER, qualifierAssembler.assemble(), String[].class)
                             .orElseThrow(() -> new QSAServerException(String.format(ERROR_NO_SYSTEMS, CONFIG_SYSTEMS_ORDER)));
    }

}
