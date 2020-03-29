package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.exception.QSAMessageException;
import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.calculator.implementation.assembler.SystemElementAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Resolves a {@link SystemElement}.
 */
@Component
public class SystemElementResolver {

    private static final String CONFIG_SYSTEMS_ORDER = "SYSTEMS_ORDER";
    private static final String ERROR_NO_SYSTEM_ID = "error.global.noSystemId";
    private static final String ERROR_NO_SYSTEMS = "error.global.noSystemsAvailable";

    @Autowired
    private SystemElementAssembler systemElementAssembler;
    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Resolves all {@link SystemElement}s.
     *
     * @return List with the available systems
     */
    public List<SystemElement> resolve() {
        return Arrays.stream(resolveSystems())
                     .map(systemId -> systemElementAssembler.assemble(systemId).orElseThrow(getExceptionSupplier(ERROR_NO_SYSTEMS)))
                     .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Resolves a {@link SystemElement} based on its id.
     *
     * @param systemId system id
     * @return SystemElement resolved system element
     */
    public SystemElement resolve(String systemId) {
        return systemElementAssembler.assemble(systemId).orElseThrow(getExceptionSupplier(ERROR_NO_SYSTEM_ID));
    }

    private String[] resolveSystems() {
        return configResolver.resolve(CONFIG_SYSTEMS_ORDER, qualifierAssembler.assemble()).orElseThrow(getExceptionSupplier(ERROR_NO_SYSTEMS)).split(",");
    }

    private Supplier<RuntimeException> getExceptionSupplier(String errorMessage) {
        return () -> new QSAMessageException(errorMessage);
    }
}
