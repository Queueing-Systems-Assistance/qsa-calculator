package com.unideb.qsa.calculator.implementation.assembler;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.unideb.qsa.domain.context.Qualifier;

/**
 * Assembler a {@link Qualifier}.
 */
@Component
public class QualifierAssembler {

    private static final String QUALIFIER_NAME = "name";
    private static final String QUALIFIER_LOCALE = "locale";

    /**
     * Assembles {@link Qualifier} with a 'name' configCondition.
     * @param name name of the configCondition
     * @return assembled qualifier
     */
    public Qualifier assemble(String name) {
        return new Qualifier.Builder().put(QUALIFIER_NAME, name).put(QUALIFIER_LOCALE, getLocale()).build();
    }


    /**
     * Assembles a {@link Qualifier}. It's useful for resolving default config values.
     * @return assembled qualifier
     */
    public Qualifier assemble() {
        return new Qualifier.Builder().put(QUALIFIER_LOCALE, getLocale()).build();
    }

    private String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }
}
