package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.domain.exception.QSAServerException;
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
    private static final String ERROR_SYSTEM_NAME_NOT_FOUND = "Missing system name, [config=%s, qualifier=%s]";

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private QualifierAssembler qualifierAssembler;
    @Autowired
    private SystemInputResolver systemInputResolver;

    /**
     * Assembles a {@link SystemElement} based on its id.
     * @param systemId feature id
     * @return assembled {@link SystemElement}
     */
    public SystemElement assemble(String systemId) {
        Qualifier qualifier = qualifierAssembler.assemble(systemId);
        String systemName = getSystemName(qualifier);
        return new SystemElement.Builder()
                .withName(systemName)
                .withId(systemId)
                .withDescription(getSystemDescription(qualifier))
                .withStatus(getSystemStatusCode(qualifier))
                .withInputs(systemInputResolver.resolve(systemId)).build();
    }

    private String getSystemStatusCode(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_STATUS_CODE, qualifier).orElse(NO_STATUS_AVAILABLE);
    }

    private String getSystemName(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_NAME, qualifier)
                             .orElseThrow(() -> new QSAServerException(String.format(ERROR_SYSTEM_NAME_NOT_FOUND, CONFIG_NAME, qualifier)));
    }

    private List<String> getSystemDescription(Qualifier qualifier) {
        return configResolver.resolve(CONFIG_ADDITIONAL_INFORMATION, qualifier, String[].class)
                             .map(Arrays::asList)
                             .orElse(List.of());
    }
}
