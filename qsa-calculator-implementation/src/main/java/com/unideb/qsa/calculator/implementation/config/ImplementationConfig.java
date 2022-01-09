package com.unideb.qsa.calculator.implementation.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import com.unideb.qsa.calculator.domain.localisation.I18nElement;
import com.unideb.qsa.calculator.implementation.resolver.i18n.I18nCacheLoader;
import com.unideb.qsa.config.resolver.config.ConfigPackResolverConfiguration;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Configuration for the implementation module.
 */
@Configuration
public class ImplementationConfig {

    private static final int EXPIRE_DURATION_TWO_DAYS_IN_HOURS = 48;

    @Value("${qsa.config.uris}")
    private String[] configPackUris;
    @Value("${qsa.config.refresh-rate-in-minutes}")
    private int refreshRate;

    @Autowired
    private I18nCacheLoader i18nCacheLoader;

    @Bean
    public ConfigResolver configResolver() {
        return new ConfigPackResolverConfiguration(Arrays.asList(configPackUris), refreshRate).createConfigResolver();
    }

    @Bean
    public LoadingCache<String, I18nElement> i18nCache() {
        return CacheBuilder.newBuilder()
                           .expireAfterWrite(EXPIRE_DURATION_TWO_DAYS_IN_HOURS, TimeUnit.HOURS)
                           .build(i18nCacheLoader);
    }

}
