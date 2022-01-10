package com.unideb.qsa.calculator.implementation.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.lambda.AWSLambda;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import com.unideb.qsa.calculator.domain.localisation.I18nElement;
import com.unideb.qsa.calculator.implementation.resolver.i18n.I18nCacheLoader;
import com.unideb.qsa.config.resolver.ConfigPackResolverBuilder;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Configuration for the implementation module.
 */
@Configuration
public class ImplementationConfig {

    private static final int EXPIRE_DURATION_TWO_DAYS_IN_HOURS = 48;

    @Value("${qsa.config.refresh-rate-in-minutes}")
    private int refreshRate;
    @Value("${qsa.aws.config-pack-lambda-name}")
    private String configPackLambdaName;

    @Autowired
    private I18nCacheLoader i18nCacheLoader;
    @Autowired
    private AWSLambda awsLambda;

    @Bean
    public ConfigResolver configResolver() {
        return new ConfigPackResolverBuilder()
                .withRefreshInMinutes(refreshRate)
                .withAwsLambda(awsLambda, configPackLambdaName)
                .build();
    }

    @Bean
    public LoadingCache<String, I18nElement> i18nCache() {
        return CacheBuilder.newBuilder()
                           .expireAfterWrite(EXPIRE_DURATION_TWO_DAYS_IN_HOURS, TimeUnit.HOURS)
                           .build(i18nCacheLoader);
    }
}
