package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.calculator.SystemElement;
import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;
import com.unideb.qsa.calculator.implementation.resolver.SystemInputResolver;

/**
 * Assembles a {@link SystemElement}.
 */
@Component
public class SystemElementAssembler {

    private static final String SYSTEM_DESCRIPTION_KEY = "system.element.%s.description";
    private static final String SYSTEM_NAME_KEY = "system.element.%s.name";
    private static final String SYSTEM_STATUS_KEY = "system.element.%s.status";

    @Autowired
    private MessageResolver messageResolver;
    @Autowired
    private SystemInputResolver systemInputResolver;

    /**
     * Assembles a {@link SystemElement} based on its id.
     * @param systemIds feature id
     * @return assembled {@link SystemElement}
     */
    public List<SystemElement> assemble(List<String> systemIds) {
        List<String> descriptionI18nKeys = getI18nKeys(systemIds, SYSTEM_DESCRIPTION_KEY);
        List<String> nameI18nKeys = getI18nKeys(systemIds, SYSTEM_NAME_KEY);
        List<String> statusI18nKeys = getI18nKeys(systemIds, SYSTEM_STATUS_KEY);
        List<String> i18nKeys = Stream.of(descriptionI18nKeys, nameI18nKeys, statusI18nKeys).flatMap(Collection::stream).collect(Collectors.toList());
        Map<String, String> resolvedI18nKeys = messageResolver.resolveKeyValuePairs(i18nKeys);
        return systemIds.stream()
                        .map(systemId -> assembleSystemElement(resolvedI18nKeys, systemId))
                        .collect(Collectors.toList());
    }

    private SystemElement assembleSystemElement(Map<String, String> resolvedI18nKeys, String systemId) {
        return new SystemElement.Builder()
                .withName(findI18nKey(systemId, resolvedI18nKeys, String.format(SYSTEM_NAME_KEY, systemId)))
                .withId(systemId)
                .withStatus(findI18nKey(systemId, resolvedI18nKeys, String.format(SYSTEM_STATUS_KEY, systemId)))
                .withDescription(findI18nKey(systemId, resolvedI18nKeys, String.format(SYSTEM_DESCRIPTION_KEY, systemId)))
                .withInputs(systemInputResolver.resolve(systemId))
                .build();
    }

    private List<String> getI18nKeys(List<String> systemIds, String i18nKey) {
        return systemIds.stream()
                        .map(systemId -> String.format(i18nKey, systemId))
                        .collect(Collectors.toList());
    }

    private String findI18nKey(String systemId, Map<String, String> i18nKeys, String rawI18nKey) {
        return i18nKeys.get(String.format(rawI18nKey, systemId));
    }
}
