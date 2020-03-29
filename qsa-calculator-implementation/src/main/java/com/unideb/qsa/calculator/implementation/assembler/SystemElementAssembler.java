package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.system.SystemElement;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Assembles a {@link SystemElement}.
 */
@Component
public class SystemElementAssembler {

    private static final String CONFIG_NAME = "NAME";
    private static final String CONFIG_STATUS_CODE = "STATUS_CODE";
    private static final String CONFIG_ADDITIONAL_INFORMATION = "ADDITIONAL_INFORMATION";
    private static final String DEFAULT_EMPTY_VALUE = "";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;

    /**
     * Assembles a {@link SystemElement} based on its id.
     *
     * @param systemId feature id
     * @return Optional {@link SystemElement} if the system exists, {@link Optional#empty()} otherwise
     */
    public Optional<SystemElement> assemble(String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        Optional<String> optionalName = configResolver.resolve(CONFIG_NAME, qualifier);
        Optional<String> optionalStatusCode = configResolver.resolve(CONFIG_STATUS_CODE, qualifier);
        Optional<SystemElement> result = Optional.empty();
        if (optionalName.isPresent() && optionalStatusCode.isPresent()) {
            result = Optional.of(new SystemElement.Builder()
                    .withId(systemId)
                    .withName(optionalName.get())
                    .withDescription(configResolver.resolve(CONFIG_ADDITIONAL_INFORMATION, qualifier).orElse(DEFAULT_EMPTY_VALUE))
                    .withStatus(optionalStatusCode.get())
                    .build());
        }
        return result;
    }
}
