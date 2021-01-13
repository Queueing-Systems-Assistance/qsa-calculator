package com.unideb.qsa.calculator.domain.localisation;

import java.util.Map;

/**
 * I18n key with locales. The {@link I18nElement#value} key is the locale (en_US, hu_HU, etc.) and the value is the i18n key value.
 */
public class I18nElement {

    private String key;
    private Map<String, String> value;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(final Map<String, String> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "I18nElement{"
               + "key='" + key + "'"
               + ", value=" + value
               + "}";
    }
}
