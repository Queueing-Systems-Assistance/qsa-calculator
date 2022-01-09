package com.unideb.qsa.calculator.implementation.resolver.i18n;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.unideb.qsa.calculator.domain.localisation.I18nElement;

/**
 * Localisation configuration.
 */
@Component
public class MessageResolver {

    private static final String DEFAULT_EN_US_LOCALE = "en_US";
    private static final int FIRST_ELEMENT = 0;

    @Autowired
    private I18nResolver i18nCacheResolver;

    /**
     * Resolve i18n keys with its values based on the current locale (eg. en_US).
     * @param keys i18n keys
     * @return a map, where the key is the i18n key, value is the corresponding value
     */
    public Map<String, String> resolveKeyValuePairs(Collection<String> keys) {
        Collection<I18nElement> resolvedI18nKeys = i18nCacheResolver.resolve(keys);
        return resolvedI18nKeys.stream().collect(Collectors.toMap(
                I18nElement::getKey,
                i18nElement -> i18nElement.getValue().get(getCurrentLocale(i18nElement.getValue())))
        );
    }

    /**
     * Resolve an i18n key value based on the current locale (eg. en_US).
     * @param key i18n key
     * @return localised message
     */
    public String resolve(String key) {
        return resolve(List.of(key)).get(FIRST_ELEMENT);
    }

    /**
     * Resolve i18n keys based on the current locale (eg. en_US).
     * @param keys i18n keys
     * @return localised messages
     */
    public List<String> resolve(Collection<String> keys) {
        Collection<I18nElement> resolvedI18nKeys = i18nCacheResolver.resolve(keys);
        return resolvedI18nKeys.stream()
                               .map(I18nElement::getValue)
                               .map(values -> values.get(getCurrentLocale(values)))
                               .collect(Collectors.toList());
    }

    private String getCurrentLocale(Map<String, String> values) {
        return values.keySet().stream()
                     .filter(LocaleContextHolder.getLocale().toString()::equalsIgnoreCase)
                     .findFirst()
                     .orElse(DEFAULT_EN_US_LOCALE);
    }
}
