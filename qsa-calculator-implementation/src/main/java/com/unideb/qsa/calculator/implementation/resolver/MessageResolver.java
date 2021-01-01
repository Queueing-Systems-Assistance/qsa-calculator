package com.unideb.qsa.calculator.implementation.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Localisation configuration.
 */
@Component
public class MessageResolver {

    @Autowired
    private MessageSource messageSource;

    /**
     * Get the i18n message.
     * @param key i18n key
     * @return localised message
     */
    public String getString(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * Get the i18n message.
     * @param key  i18n key
     * @param args i18n message arguments
     * @return localised message
     */
    public String getString(String key, String... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
