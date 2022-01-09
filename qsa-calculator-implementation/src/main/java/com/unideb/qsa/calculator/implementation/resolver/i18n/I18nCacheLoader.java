package com.unideb.qsa.calculator.implementation.resolver.i18n;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;

import com.unideb.qsa.calculator.domain.localisation.I18nElement;

/**
 * Loader for the i18n keys. If the cache evicts the i18n keys, this class will "reload" them.
 */
@Component
public class I18nCacheLoader extends CacheLoader<String, I18nElement> {

    @Autowired
    private I18nResolver i18nAWSLambdaResolver;

    @NonNull
    @Override
    public I18nElement load(@NonNull String key) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public Map<String, I18nElement> loadAll(@NonNull Iterable<? extends String> keys) {
        List<String> keysToLoad = StreamSupport.stream(keys.spliterator(), false).collect(Collectors.toList());
        return i18nAWSLambdaResolver.resolve(keysToLoad).stream()
                           .collect(HashMap::new, (map, key) -> map.put(key.getKey(), key), HashMap::putAll);
    }
}
