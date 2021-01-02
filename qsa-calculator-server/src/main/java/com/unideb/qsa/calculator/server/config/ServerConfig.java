package com.unideb.qsa.calculator.server.config;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;

/**
 * Configuration for the web interface.
 */
@Configuration
public class ServerConfig implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfig.class);

    @Value("${management.metrics.enabled}")
    private String[] enabledMetrics;
    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public MessageSource i18nKeySource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @PostConstruct
    public void setup() {
        String applicationVersion = buildProperties.getVersion();
        LOG.info("Setting [{}] application version to logback", applicationVersion);
        MDC.put("version", applicationVersion);
    }

    @Bean
    public MeterFilter meterFilter() {
        return new MeterFilter() {
            @Override
            @NonNull
            public MeterFilterReply accept(@NonNull Meter.Id id) {
                return Optional.of(List.of(enabledMetrics).contains(id.getName()))
                               .filter(isEnabled -> isEnabled)
                               .map(isEnabled -> MeterFilterReply.ACCEPT)
                               .orElse(MeterFilterReply.DENY);
            }
        };
    }
}
