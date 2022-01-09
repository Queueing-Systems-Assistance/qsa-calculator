package com.unideb.qsa.calculator.implementation.resolver.i18n;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.LoadingCache;

import com.unideb.qsa.calculator.domain.exception.QSAServerException;
import com.unideb.qsa.calculator.domain.localisation.I18nElement;

/**
 * I18n cache pool. Primary bean when resolving i18n keys, it's just a proxy to the AWS Lambda resolver.
 */
@Component
public class I18nCacheResolver implements I18nResolver {

    @Autowired
    private LoadingCache<String, I18nElement> i18nCache;

    @Override
    public Collection<I18nElement> resolve(Collection<String> keys) {
        try {
            return i18nCache.getAll(keys).values();
        } catch (ExecutionException e) {
            throw new QSAServerException("Cannot retrieve i18n keys: %s".formatted(keys));
        }
    }

}

