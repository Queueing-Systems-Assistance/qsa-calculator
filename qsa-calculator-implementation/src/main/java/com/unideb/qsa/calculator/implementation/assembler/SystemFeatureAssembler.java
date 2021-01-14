package com.unideb.qsa.calculator.implementation.assembler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.implementation.resolver.MessageResolver;

/**
 * System feature resolver which resolves 18n feature keys.
 */
@Component
public class SystemFeatureAssembler {

    public static final String FEATURE_DESCRIPTION_KEY = "feature.element.%s.description";
    public static final String FEATURE_NAME_KEY = "feature.element.%s.name";

    @Autowired
    private MessageResolver messageResolver;

    /**
     * Resolve feature i18n keys based on featueId. This will resolve {@link SystemFeatureAssembler#FEATURE_DESCRIPTION_KEY} and {@link
     * SystemFeatureAssembler#FEATURE_NAME_KEY}.
     * @param featureIds feature ids
     * @return a map, where the key is the i18n key and the value is the corresponding value
     */
    public Map<String, String> resolveI18nKeys(String[] featureIds) {
        List<String> nameI18nKeys = getI18nKeys(featureIds, FEATURE_NAME_KEY);
        List<String> descriptionI18nKeys = getI18nKeys(featureIds, FEATURE_DESCRIPTION_KEY);
        List<String> i18nKeys = Stream.of(nameI18nKeys, descriptionI18nKeys).flatMap(Collection::stream).collect(Collectors.toList());
        return messageResolver.resolveKeyValuePairs(i18nKeys);
    }

    /**
     * Resolves feature i18n key value based on the resolved keys. See {@link SystemFeatureAssembler#resolveI18nKeys(String[])}.
     * @param featureId  feature id
     * @param i18nKeys   resolved i18n keys
     * @param rawI18nKey unformatted i18n key (eg. {@link SystemFeatureAssembler#FEATURE_DESCRIPTION_KEY})
     */
    public String findI18nKey(String featureId, Map<String, String> i18nKeys, String rawI18nKey) {
        return i18nKeys.get(String.format(rawI18nKey, featureId));
    }

    private List<String> getI18nKeys(String[] systemIds, String i18nKey) {
        return Arrays.stream(systemIds)
                     .map(systemId -> String.format(i18nKey, systemId))
                     .collect(Collectors.toList());
    }
}
