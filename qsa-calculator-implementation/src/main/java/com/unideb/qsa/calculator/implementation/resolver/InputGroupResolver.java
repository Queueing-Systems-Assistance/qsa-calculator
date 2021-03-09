package com.unideb.qsa.calculator.implementation.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.InputGroup;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.implementation.assembler.QualifierAssembler;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Resolves an {@link InputGroup}.
 */
@Component
public class InputGroupResolver {

    private static final String CONFIG_INPUT_GROUPS = "INPUT_GROUPS";
    private static final String ERROR_INVALID_GROUP = "Config group is invalid!";

    @Autowired
    private ConfigResolver configResolver;

    /**
     * Resolves an {@link InputGroup} based on a system id and a system feature id.
     * @param systemId             system id
     * @param featureId            system feature id
     * @return Optional {@link InputGroup} if the input group is valid, {@link Optional#empty()} otherwise
     */
    public InputGroup resolve(String systemId, String featureId) {
        return configResolver.resolve(CONFIG_INPUT_GROUPS, new QualifierAssembler().assemble(systemId, featureId), InputGroup.class)
                .orElseThrow(() -> new QSAServerException(ERROR_INVALID_GROUP));
    }
}
