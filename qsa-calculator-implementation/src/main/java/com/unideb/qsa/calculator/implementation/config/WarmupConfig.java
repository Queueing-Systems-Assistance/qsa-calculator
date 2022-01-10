package com.unideb.qsa.calculator.implementation.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.unideb.qsa.calculator.implementation.service.SystemElementService;
import com.unideb.qsa.config.resolver.resolver.ConfigResolver;
import com.unideb.qsa.domain.context.Qualifier;

/**
 * Warmup config, so that the first couple of requests will be processed faster.
 */
@Configuration
public class WarmupConfig {

    private static final Logger LOG = LoggerFactory.getLogger(WarmupConfig.class);
    private static final String CONFIG_SYSTEM_ORDER = "SYSTEMS_ORDER";
    private static final float DIVIDED_BY_SECONDS = 1000f;

    @Autowired
    private ConfigResolver configResolver;
    @Autowired
    private SystemElementService systemElementService;

    @PostConstruct
    public void initConfigPacs() {
        long startTime = System.currentTimeMillis();
        String[] systems = configResolver.resolve(CONFIG_SYSTEM_ORDER, new Qualifier.Builder().build(), String[].class).orElse(new String[]{});
        long endTimeConfigPacks = System.currentTimeMillis();
        LOG.info("Successfully initialized config packs, took {} seconds", (endTimeConfigPacks - startTime) / DIVIDED_BY_SECONDS);
        systemElementService.getSystemElements(List.of(systems));
        long endTimeSystemElements = System.currentTimeMillis();
        LOG.info("Successfully initialized the basic system information, took {} seconds", (endTimeSystemElements - endTimeConfigPacks) / DIVIDED_BY_SECONDS);
    }
}
