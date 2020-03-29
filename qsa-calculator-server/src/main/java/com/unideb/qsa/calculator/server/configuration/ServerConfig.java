package com.unideb.qsa.calculator.server.configuration;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;

/**
 * Configuration for the web interface.
 */
@Configuration
@ComponentScan("com.unideb.qsa")
public class ServerConfig implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfig.class);
    private static final String GEO_CONFIGURATION_LOG = "Geo configuration [enabled={}],[path={}]";

    @Value("${management.geo.enabled}")
    private Boolean metricsGeoEnabled;
    @Value("${management.geo.maxmind.path}")
    private String path;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Locale change interceptor.
     *
     * @return the interceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
    }

    /**
     * Creates a message source instance with UTF-8 encoding.
     *
     * @return the messages source
     */
    @Bean
    public MessageSource i18nKeySource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Locale resolver.
     *
     * @return the resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    /**
     * Maxmind client to resolve the country codes based on IP addresses.
     *
     * @return maxmind webservice client
     * @throws IOException if the database is not available/readable
     */
    @Bean
    @ConditionalOnProperty("management.geo.enabled")
    public DatabaseReader maxmindDatabaseReader() throws IOException {
        LOG.info(GEO_CONFIGURATION_LOG, metricsGeoEnabled, path);
        return new DatabaseReader.Builder(new File(path))
                .fileMode(Reader.FileMode.MEMORY)
                .build();
    }
}
