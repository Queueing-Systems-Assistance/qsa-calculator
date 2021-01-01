package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.implementation.resolver.SystemInputResolver;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Assembles a {@link SystemElement}.
 */
@Component
public class SystemElementAssembler {

    private static final String NO_STATUS_AVAILABLE = "";
    private static final String CONFIG_NAME = "NAME";
    private static final String CONFIG_STATUS_CODE = "STATUS_CODE";
    private static final String CONFIG_ADDITIONAL_INFORMATION = "ADDITIONAL_INFORMATION";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;
    @Autowired
    private SystemInputResolver systemInputResolver;

    /**
     * Assembles a {@link SystemElement} based on its id.
     * @param systemId feature id
     * @return Optional {@link SystemElement} if the system exists, {@link Optional#empty()} otherwise
     */
    public Optional<SystemElement> assemble(String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        return getSystemName(qualifier)
                .map(name -> new SystemElement.Builder().withName(name))
                .map(builder -> builder.withId(systemId))
                .map(builder -> builder.withDescription(getSystemDescription(qualifier)))
                .map(builder -> builder.withStatus(getSystemStatusCode(qualifier)))
                .map(builder -> builder.withInputs(systemInputResolver.resolve(systemId)))
                .map(SystemElement.Builder::build);
    }

    private String getSystemStatusCode(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_STATUS_CODE, qualifier).orElse(NO_STATUS_AVAILABLE);
    }

    private Optional<String> getSystemName(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_NAME, qualifier);
    }

    private List<String> getSystemDescription(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_ADDITIONAL_INFORMATION, qualifier, String[].class)
                             .map(Arrays::asList)
                             .orElse(List.of());
    }
}
