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
    private static final String QUALIFIER_FEATURE = "feature";
    private static final String QUALIFIER_OUTPUT = "output";

    /**
     * Assembles {@link Qualifier} with a 'name' configCondition.
     * @param name name of the configCondition
     * @return assembled qualifier
     */
    public Qualifier assemble(String name) {
        return new Qualifier.Builder()
                .put(QUALIFIER_NAME, name)
                .put(QUALIFIER_LOCALE, getLocale())
                .build();
    }

    /**
     * Assembles {@link Qualifier}.
     * @param systemId      system id
     * @param systemFeature system input feature id
     * @param outputId      system feature id
     * @return assembled qualifier
     */
    public Qualifier assemble(String systemId, String systemFeature, String outputId) {
        return new Qualifier.Builder()
                .put(QUALIFIER_NAME, systemId)
                .put(QUALIFIER_FEATURE, systemFeature)
                .put(QUALIFIER_OUTPUT, outputId)
                .build();
    }

    /**
     * Assembles {@link Qualifier}.
     * @param systemId      system id
     * @param systemFeature system input feature id
     * @return assembled qualifier
     */
    public Qualifier assemble(String systemId, String systemFeature) {
        return new Qualifier.Builder()
                .put(QUALIFIER_NAME, systemId)
                .put(QUALIFIER_FEATURE, systemFeature)
                .build();
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
