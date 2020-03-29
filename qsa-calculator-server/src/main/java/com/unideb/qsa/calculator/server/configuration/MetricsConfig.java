package com.unideb.qsa.calculator.server.configuration;

import static java.lang.management.ManagementFactory.getPlatformMBeanServer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.BufferPoolMetricSet;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import com.unideb.qsa.calculator.server.monitoring.CpuUsageGaugeSet;
import com.unideb.qsa.calculator.server.monitoring.JvmUptimeGaugeSet;

/**
 * Metrics for Graphite reporting.
 */
@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MetricsConfig.class);
    private static final String METRICS_CONFIGURATION_LOG = "Metrics configuration [enabled={}],[prefix={}],[host={}],[port={}],[rate={}]";
    private static final String JVM = "jvm";
    private static final String JVM_GC = "jvm.gc";
    private static final String JVM_MEMORY = "jvm.memory";
    private static final String JVM_THREAD_STATES = "jvm.thread-states";
    private static final String JVM_BUFFERPOOLS = "jvm.bufferpools";
    private static final String JVM_CLASSLOADING = "jvm.classloading";
    private static final String CPU = "cpu";

    @Value("${management.metrics.export.graphite.host}")
    private String graphiteHost;
    @Value("${management.metrics.export.graphite.tags-as-prefix}")
    private String tagsAsPrefix;
    @Value("${management.metrics.export.graphite.port}")
    private Integer graphitePort;
    @Value("${management.metrics.export.graphite.step}")
    private Integer refreshRate;
    @Value("${management.metrics.export.graphite.enabled}")
    private boolean serviceMetricsEnabled;

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        LOG.info(METRICS_CONFIGURATION_LOG, serviceMetricsEnabled, tagsAsPrefix, graphiteHost, graphitePort, refreshRate);
        metricRegistry.registerAll(createMetricSet());
        GraphiteReporter reporter = createReporter(metricRegistry);
        registerReporter(reporter);
        if (serviceMetricsEnabled) {
            reporter.start(refreshRate, TimeUnit.SECONDS);
        }
    }

    private GraphiteReporter createReporter(MetricRegistry metricRegistry) {
        return GraphiteReporter
                .forRegistry(metricRegistry)
                .prefixedWith(tagsAsPrefix)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(new Graphite(new InetSocketAddress(graphiteHost, graphitePort)));
    }

    private MetricSet createMetricSet() {
        return () -> {
            Map<String, Metric> metrics = new HashMap<>();
            metrics.put(JVM, new JvmUptimeGaugeSet());
            metrics.put(JVM_GC, new GarbageCollectorMetricSet());
            metrics.put(JVM_MEMORY, new MemoryUsageGaugeSet());
            metrics.put(JVM_THREAD_STATES, new ThreadStatesGaugeSet());
            metrics.put(JVM_BUFFERPOOLS, new BufferPoolMetricSet(getPlatformMBeanServer()));
            metrics.put(JVM_CLASSLOADING, new ClassLoadingGaugeSet());
            metrics.put(CPU, new CpuUsageGaugeSet());
            return metrics;
        };
    }
}
