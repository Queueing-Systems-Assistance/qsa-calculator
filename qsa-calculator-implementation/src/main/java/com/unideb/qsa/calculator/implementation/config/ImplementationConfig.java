package com.unideb.qsa.calculator.implementation.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.config.resolver.config.ConfigPackResolverConfiguration;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;

/**
 * Configuration for the implementation module.
 */
@Configuration
public class ImplementationConfig {

    @Value("${qsa.config.uris}")
    private String[] configPackUris;
    @Value("${qsa.config.refresh-rate-in-minutes}")
    private int refreshRate;

    @Bean
    public ConfigResolver configResolver() {
        return new ConfigPackResolverConfiguration(Arrays.asList(configPackUris), refreshRate).createConfigResolver();
    }
}
