package com.unideb.qsa.calculator.domain.localisation;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        I18nElement that = (I18nElement) o;
        return Objects.equals(key, that.key)
               && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
